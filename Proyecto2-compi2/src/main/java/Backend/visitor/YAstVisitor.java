package Backend.visitor;

import Backend.Ast.Node;
import Backend.Ast.ProgramNode;
import Backend.Ast.Type;
import Backend.Ast.declaration.*;
import Backend.Ast.expression.*;
import Backend.Ast.statement.*;
import Backend.antlr.YParser;
import Backend.antlr.YBaseVisitor;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.ArrayList;
import java.util.List;

public class YAstVisitor extends YBaseVisitor<Node> {

    private int getLine(ParserRuleContext ctx) {
        return ctx.getStart() != null ? ctx.getStart().getLine() : 0;
    }

    private int getCol(ParserRuleContext ctx) {
        return ctx.getStart() != null ? ctx.getStart().getCharPositionInLine() : 0;
    }

    @Override
    public Node visitPrograma(YParser.ProgramaContext ctx) {
        ProgramNode program = new ProgramNode(ProgramNode.SourceLanguage.Y_LANG, getLine(ctx), getCol(ctx));

        if (ctx.seccionEstructuras() != null) {
            for (YParser.DeclaracionEstructuraContext sCtx : ctx.seccionEstructuras().declaracionEstructura()) {
                StructDecl structDecl = (StructDecl) visit(sCtx);
                if (structDecl != null) program.addStruct(structDecl);
            }
        }

        if (ctx.seccionFunciones() != null) {
            for (YParser.DefinicionFuncionContext fCtx : ctx.seccionFunciones().definicionFuncion()) {
                FunctionDecl func = (FunctionDecl) visit(fCtx);
                if (func != null) program.addFunction(func);
            }
        }

        return program;
    }

    @Override
    public Node visitDeclaracionEstructura(YParser.DeclaracionEstructuraContext ctx) {
        String name = ctx.ID().getText();
        List<StructField> fields = new ArrayList<>();

        List<YParser.CampoEstructuraContext> campos = ctx.bloqueEstructura().campoEstructura();
        for (YParser.CampoEstructuraContext c : campos) {
            Type type = parseType(c.tipoY());
            String fieldName = c.ID().getText();
            List<Expression> tamanios = new ArrayList<>();
            for (YParser.ExpresionContext e : c.expresion()) {
                tamanios.add((Expression) visit(e));
            }
            Type fieldType = tamanios.isEmpty() ? type : Type.createArrayType(type, tamanios.size());
            fields.add(new StructField(fieldName, fieldType, tamanios, getLine(c), getCol(c)));
        }

        return new StructDecl(name, fields, getLine(ctx), getCol(ctx));
    }

    @Override
    public Node visitDefinicionFuncion(YParser.DefinicionFuncionContext ctx) {
        String name = ctx.ID().getText();
        Type retType = ctx.tipoY() != null ? parseType(ctx.tipoY()) : Type.VOID;
        List<Parameter> params = new ArrayList<>();

        if (ctx.listaParametrosY() != null) {
            for (YParser.ParametroYContext pCtx : ctx.listaParametrosY().parametroY()) {
                Type pType = parseType(pCtx.tipoY());
                String pName = pCtx.ID().getText();
                boolean isByRef = false;
                if (pCtx.LBRACKET() != null) {
                    pType = Type.crearArregloSinDimensiones(pType);
                    isByRef = true;
                } else if (pCtx.LBRACE() != null) {
                    isByRef = true;
                }
                params.add(new Parameter(pName, pType, isByRef, getLine(pCtx), getCol(pCtx)));
            }
        }

        BlockStmt body = (BlockStmt) visit(ctx.bloque());
        return new FunctionDecl(name, retType, params, body, false, true, getLine(ctx), getCol(ctx));
    }

    @Override
    public Node visitBloque(YParser.BloqueContext ctx) {
        BlockStmt block = new BlockStmt(getLine(ctx), getCol(ctx));
        if (ctx.instruccion() != null) {
            for (YParser.InstruccionContext iCtx : ctx.instruccion()) {
                Node n = visit(iCtx);
                if (n instanceof Statement s) block.addStatement(s);
            }
        }
        return block;
    }

    @Override
    public Node visitInstruccion(YParser.InstruccionContext ctx) {
        if (ctx.declaracionEstructuraLocal() != null) return visit(ctx.declaracionEstructuraLocal());
        if (ctx.declaracionVariableY() != null) return visit(ctx.declaracionVariableY());
        if (ctx.asignacionY() != null) return visit(ctx.asignacionY());
        if (ctx.instruccionIncrementoDecrementoY() != null) return visit(ctx.instruccionIncrementoDecrementoY());
        if (ctx.instruccionSiY() != null) return visit(ctx.instruccionSiY());
        if (ctx.instruccionElegirY() != null) return visit(ctx.instruccionElegirY());
        if (ctx.instruccionParaY() != null) return visit(ctx.instruccionParaY());
        if (ctx.instruccionMientrasY() != null) return visit(ctx.instruccionMientrasY());
        if (ctx.instruccionHacerMientrasY() != null) return visit(ctx.instruccionHacerMientrasY());
        if (ctx.instruccionRomper() != null) return new BreakStmt(getLine(ctx), getCol(ctx));
        if (ctx.instruccionContinuar() != null) return new ContinueStmt(getLine(ctx), getCol(ctx));
        if (ctx.instruccionRetornar() != null) {
            Expression val = ctx.instruccionRetornar().expresion() != null ? (Expression) visit(ctx.instruccionRetornar().expresion()) : null;
            return new ReturnStmt(val, getLine(ctx), getCol(ctx));
        }
        if (ctx.instruccionImprimirY() != null) return visit(ctx.instruccionImprimirY());
        if (ctx.instruccionLeerY() != null) return visit(ctx.instruccionLeerY());
        if (ctx.instruccionLlamadaMetodoY() != null) return visit(ctx.instruccionLlamadaMetodoY());
        return null;
    }

    @Override
    public Node visitDeclaracionEstructuraLocal(YParser.DeclaracionEstructuraLocalContext ctx) {
        return visit(ctx.declaracionEstructura());
    }

    @Override
    public Node visitDeclaracionVariableY(YParser.DeclaracionVariableYContext ctx) {
        Type baseType = parseType(ctx.tipoY());
        String name = ctx.ID().getText();
        int line = getLine(ctx);
        int col = getCol(ctx);

        int dims = ctx.expresion().size();
        Type varType = dims > 0 ? Type.createArrayType(baseType, dims) : baseType;

        List<Expression> dimSizes = new ArrayList<>();
        if (dims > 0) {
            for (YParser.ExpresionContext e : ctx.expresion()) {
                dimSizes.add((Expression) visit(e));
            }
        }

        Expression initVal = null;
        if (ctx.inicializadorY() != null) {
            if (ctx.inicializadorY().literalArregloOEstructura() != null) {
                initVal = parseLiteralArregloOEstructura(ctx.inicializadorY().literalArregloOEstructura(), varType);
            } else if (ctx.inicializadorY().expresion() != null) {
                initVal = ajustarLiteralAlTipo((Expression) visit(ctx.inicializadorY().expresion()), varType);
            }
        }

        return new VarDeclStmt(varType, name, initVal, dimSizes, dims > 0, line, col);
    }

    private Expression ajustarLiteralAlTipo(Expression valor, Type tipoDeclarado) {
        if (valor instanceof NewArrayExpr arreglo
                && tipoDeclarado != null
                && tipoDeclarado.getCategory() == Type.TypeCategory.STRUCT) {
            return new NewStructExpr(tipoDeclarado.getTypeName(), arreglo.getInitialValues(),
                    valor.getLine(), valor.getColumn());
        }
        return valor;
    }

    private Expression parseLiteralArregloOEstructura(YParser.LiteralArregloOEstructuraContext ctx, Type expectedType) {
        List<Expression> values = new ArrayList<>();
        for (YParser.ExpresionContext e : ctx.expresion()) {
            values.add((Expression) visit(e));
        }
        int line = getLine(ctx);
        int col = getCol(ctx);
        if (expectedType != null && expectedType.getCategory() == Type.TypeCategory.STRUCT) {
            return new NewStructExpr(expectedType.getTypeName(), values, line, col);
        }
        if (expectedType != null && expectedType.getCategory() == Type.TypeCategory.ARRAY
                && expectedType.getDimensions() == 1 && expectedType.getBaseType() != null
                && expectedType.getBaseType().getCategory() == Type.TypeCategory.STRUCT) {
            values.replaceAll(v -> ajustarLiteralAlTipo(v, expectedType.getBaseType()));
        }
        return new NewArrayExpr(expectedType != null ? expectedType.getBaseType() : null, List.of(), values, line, col);
    }

    @Override
    public Node visitAsignacionY(YParser.AsignacionYContext ctx) {
        Expression target = parseDestino(ctx.destinoY());
        Expression value;
        if (ctx.expresion() != null) {
            value = (Expression) visit(ctx.expresion());
        } else {
            value = parseLiteralArregloOEstructura(ctx.literalArregloOEstructura(), null);
        }
        return new AssignStmt(target, AssignOp.ASSIGN, value, getLine(ctx), getCol(ctx));
    }

    private Expression parseDestino(YParser.DestinoYContext ctx) {
        Expression expr = new IdentifierExpr(ctx.ID().getText(), getLine(ctx), getCol(ctx));
        for (YParser.AccesoPostfijoYContext acc : ctx.accesoPostfijoY()) {
            if (acc.DOT() != null) {
                expr = new MemberAccessExpr(expr, acc.ID().getText(), acc.ID().getSymbol().getLine(), acc.ID().getSymbol().getCharPositionInLine());
            } else if (acc.LBRACKET() != null) {
                Expression idx = (Expression) visit(acc.expresion());
                expr = new ArrayAccessExpr(expr, idx, getLine(acc), getCol(acc));
            }
        }
        return expr;
    }

    @Override
    public Node visitInstruccionIncrementoDecrementoY(YParser.InstruccionIncrementoDecrementoYContext ctx) {
        Expression target = parseDestino(ctx.destinoY());
        UnaryOp op = ctx.op.getType() == YParser.INC ? UnaryOp.INCREMENT : UnaryOp.DECREMENT;
        return new ExprStmt(new UnaryExpr(op, target, true, getLine(ctx), getCol(ctx)), getLine(ctx), getCol(ctx));
    }

    @Override
    public Node visitInstruccionSiY(YParser.InstruccionSiYContext ctx) {
        Expression cond = (Expression) visit(ctx.condicionY().expresion());
        Statement thenBranch = (Statement) visit(ctx.bloque());

        List<ElseIfBranch> elseIfs = new ArrayList<>();
        for (YParser.RamaSinoYContext sCtx : ctx.ramaSinoY()) {
            Expression sCond = (Expression) visit(sCtx.condicionY().expresion());
            Statement sBody = (Statement) visit(sCtx.bloque());
            elseIfs.add(new ElseIfBranch(sCond, sBody, getLine(sCtx), getCol(sCtx)));
        }

        Statement elseBranch = null;
        if (ctx.ramaContrarioY() != null) {
            elseBranch = (Statement) visit(ctx.ramaContrarioY().bloque());
        }

        return new IfStmt(cond, thenBranch, elseIfs, elseBranch, getLine(ctx), getCol(ctx));
    }

    @Override
    public Node visitInstruccionElegirY(YParser.InstruccionElegirYContext ctx) {
        Expression expr = (Expression) visit(ctx.expresion());
        List<CaseBranch> cases = new ArrayList<>();

        YParser.BloqueElegirContext bCtx = ctx.bloqueElegir();
        for (YParser.CasoElegirContext cCtx : bCtx.casoElegir()) {
            Expression cVal = (Expression) visit(cCtx.expresion());
            Statement body = (Statement) visit(cCtx.bloque());
            List<Statement> stmts = body instanceof BlockStmt bs ? bs.getStatements() : List.of(body);
            cases.add(new CaseBranch(cVal, stmts, false, getLine(cCtx), getCol(cCtx)));
        }

        if (bCtx.siempreElegir() != null) {
            Statement body = (Statement) visit(bCtx.siempreElegir().bloque());
            List<Statement> stmts = body instanceof BlockStmt bs ? bs.getStatements() : List.of(body);
            cases.add(new CaseBranch(null, stmts, true, getLine(bCtx.siempreElegir()), getCol(bCtx.siempreElegir())));
        }

        return new SwitchStmt(expr, cases, getLine(ctx), getCol(ctx));
    }

    @Override
    public Node visitInstruccionParaY(YParser.InstruccionParaYContext ctx) {
        YParser.DeclaracionParaYContext dCtx = ctx.declaracionParaY();
        Type type = parseType(dCtx.tipoY());
        Expression initVal = (Expression) visit(dCtx.expresion());
        VarDeclStmt init = new VarDeclStmt(type, dCtx.ID().getText(), initVal, getLine(dCtx), getCol(dCtx));

        Expression cond = (Expression) visit(ctx.expresion());
        Node update = visit(ctx.actualizacionParaY().expresion());
        Statement body = (Statement) visit(ctx.bloque());

        return new ForStmt(init, cond, update, body, getLine(ctx), getCol(ctx));
    }

    @Override
    public Node visitInstruccionMientrasY(YParser.InstruccionMientrasYContext ctx) {
        Expression cond = (Expression) visit(ctx.expresion());
        Statement body = (Statement) visit(ctx.bloque());
        return new WhileStmt(cond, body, getLine(ctx), getCol(ctx));
    }

    @Override
    public Node visitInstruccionHacerMientrasY(YParser.InstruccionHacerMientrasYContext ctx) {
        Statement body = (Statement) visit(ctx.bloque());
        Expression cond = (Expression) visit(ctx.expresion());
        return new DoWhileStmt(body, cond, getLine(ctx), getCol(ctx));
    }

    @Override
    public Node visitInstruccionImprimirY(YParser.InstruccionImprimirYContext ctx) {
        Expression expr = (Expression) visit(ctx.expresion());
        return new PrintStmt(List.of(expr), true, getLine(ctx), getCol(ctx));
    }

    @Override
    public Node visitInstruccionLeerY(YParser.InstruccionLeerYContext ctx) {
        return new ReadStmt(null, getLine(ctx), getCol(ctx));
    }

    @Override
    public Node visitInstruccionLlamadaMetodoY(YParser.InstruccionLlamadaMetodoYContext ctx) {
        YParser.LlamadaMetodoYContext callCtx = ctx.llamadaMetodoY();
        Expression expr = new IdentifierExpr(callCtx.ID().getText(), getLine(callCtx), getCol(callCtx));
        for (YParser.AccesoPostfijoYContext acc : callCtx.accesoPostfijoY()) {
            if (acc.DOT() != null) {
                expr = new MemberAccessExpr(expr, acc.ID().getText(), acc.ID().getSymbol().getLine(), acc.ID().getSymbol().getCharPositionInLine());
            } else if (acc.LBRACKET() != null) {
                Expression idx = (Expression) visit(acc.expresion());
                expr = new ArrayAccessExpr(expr, idx, getLine(acc), getCol(acc));
            } else if (acc.LPAREN() != null) {
                List<Expression> args = parseArgs(acc.listaArgumentosY());
                if (expr instanceof MemberAccessExpr mae) {
                    expr = new CallExpr(mae.getTarget(), mae.getMemberName(), args, mae.getLine(), mae.getColumn());
                } else if (expr instanceof IdentifierExpr ide) {
                    expr = new CallExpr(null, ide.getName(), args, ide.getLine(), ide.getColumn());
                } else {
                    expr = new CallExpr(expr, "", args, getLine(acc), getCol(acc));
                }
            }
        }
        return new ExprStmt(expr, getLine(ctx), getCol(ctx));
    }

    private List<Expression> parseArgs(YParser.ListaArgumentosYContext ctx) {
        List<Expression> args = new ArrayList<>();
        if (ctx != null) {
            for (YParser.ExpresionContext e : ctx.expresion()) {
                args.add((Expression) visit(e));
            }
        }
        return args;
    }

    // EXPRESIONES

    @Override
    public Node visitExprPostfijaY(YParser.ExprPostfijaYContext ctx) {
        Expression operand = (Expression) visit(ctx.expresion());
        UnaryOp op = ctx.op.getType() == YParser.INC ? UnaryOp.INCREMENT : UnaryOp.DECREMENT;
        return new UnaryExpr(op, operand, true, getLine(ctx), getCol(ctx));
    }

    @Override
    public Node visitExprPrefijaIncDecY(YParser.ExprPrefijaIncDecYContext ctx) {
        Expression operand = (Expression) visit(ctx.expresion());
        UnaryOp op = ctx.op.getType() == YParser.INC ? UnaryOp.INCREMENT : UnaryOp.DECREMENT;
        return new UnaryExpr(op, operand, false, getLine(ctx), getCol(ctx));
    }

    @Override
    public Node visitExprAccesoMiembroY(YParser.ExprAccesoMiembroYContext ctx) {
        Expression target = (Expression) visit(ctx.expresion());
        String member = ctx.ID().getText();
        return new MemberAccessExpr(target, member, ctx.ID().getSymbol().getLine(), ctx.ID().getSymbol().getCharPositionInLine());
    }

    @Override
    public Node visitExprIndexacionY(YParser.ExprIndexacionYContext ctx) {
        Expression target = (Expression) visit(ctx.expresion(0));
        Expression index = (Expression) visit(ctx.expresion(1));
        return new ArrayAccessExpr(target, index, getLine(ctx), getCol(ctx));
    }

    @Override
    public Node visitExprLlamadaMetodoY(YParser.ExprLlamadaMetodoYContext ctx) {
        Expression targetExpr = (Expression) visit(ctx.expresion());
        List<Expression> args = parseArgs(ctx.listaArgumentosY());
        if (targetExpr instanceof MemberAccessExpr mae) {
            return new CallExpr(mae.getTarget(), mae.getMemberName(), args, mae.getLine(), mae.getColumn());
        } else if (targetExpr instanceof IdentifierExpr ide) {
            return new CallExpr(null, ide.getName(), args, ide.getLine(), ide.getColumn());
        }
        return new CallExpr(targetExpr, "", args, getLine(ctx), getCol(ctx));
    }

    @Override
    public Node visitExprNotLogicoY(YParser.ExprNotLogicoYContext ctx) {
        Expression operand = (Expression) visit(ctx.expresion());
        return new UnaryExpr(UnaryOp.NOT, operand, false, getLine(ctx), getCol(ctx));
    }

    @Override
    public Node visitExprMenosUnarioY(YParser.ExprMenosUnarioYContext ctx) {
        Expression operand = (Expression) visit(ctx.expresion());
        return new UnaryExpr(UnaryOp.NEGATION, operand, false, getLine(ctx), getCol(ctx));
    }

    @Override
    public Node visitExprMultiplicativaY(YParser.ExprMultiplicativaYContext ctx) {
        Expression left = (Expression) visit(ctx.expresion(0));
        Expression right = (Expression) visit(ctx.expresion(1));
        BinaryOp op = ctx.op.getType() == YParser.STAR ? BinaryOp.MUL : BinaryOp.DIV;
        return new BinaryExpr(left, op, right, getLine(ctx), getCol(ctx));
    }

    @Override
    public Node visitExprAditivaY(YParser.ExprAditivaYContext ctx) {
        Expression left = (Expression) visit(ctx.expresion(0));
        Expression right = (Expression) visit(ctx.expresion(1));
        BinaryOp op = ctx.op.getType() == YParser.PLUS ? BinaryOp.ADD : BinaryOp.SUB;
        return new BinaryExpr(left, op, right, getLine(ctx), getCol(ctx));
    }

    @Override
    public Node visitExprRelacionalY(YParser.ExprRelacionalYContext ctx) {
        Expression left = (Expression) visit(ctx.expresion(0));
        Expression right = (Expression) visit(ctx.expresion(1));
        BinaryOp op = switch (ctx.op.getType()) {
            case YParser.LT -> BinaryOp.LESS_THAN;
            case YParser.LE -> BinaryOp.LESS_EQUAL;
            case YParser.GT -> BinaryOp.GREATER_THAN;
            default -> BinaryOp.GREATER_EQUAL;
        };
        return new BinaryExpr(left, op, right, getLine(ctx), getCol(ctx));
    }

    @Override
    public Node visitExprIgualdadY(YParser.ExprIgualdadYContext ctx) {
        Expression left = (Expression) visit(ctx.expresion(0));
        Expression right = (Expression) visit(ctx.expresion(1));
        BinaryOp op = ctx.op.getType() == YParser.EQ ? BinaryOp.EQUALS : BinaryOp.NOT_EQUALS;
        return new BinaryExpr(left, op, right, getLine(ctx), getCol(ctx));
    }

    @Override
    public Node visitExprAndLogicoY(YParser.ExprAndLogicoYContext ctx) {
        Expression left = (Expression) visit(ctx.expresion(0));
        Expression right = (Expression) visit(ctx.expresion(1));
        return new BinaryExpr(left, BinaryOp.AND, right, getLine(ctx), getCol(ctx));
    }

    @Override
    public Node visitExprOrLogicoY(YParser.ExprOrLogicoYContext ctx) {
        Expression left = (Expression) visit(ctx.expresion(0));
        Expression right = (Expression) visit(ctx.expresion(1));
        return new BinaryExpr(left, BinaryOp.OR, right, getLine(ctx), getCol(ctx));
    }

    @Override
    public Node visitExprLeerY(YParser.ExprLeerYContext ctx) {
        return new ReadExpr(getLine(ctx), getCol(ctx));
    }

    @Override
    public Node visitExprParentesisY(YParser.ExprParentesisYContext ctx) {
        return visit(ctx.expresion());
    }

    @Override
    public Node visitExprLiteralEstructuraY(YParser.ExprLiteralEstructuraYContext ctx) {
        return parseLiteralArregloOEstructura(ctx.literalArregloOEstructura(), null);
    }

    @Override
    public Node visitExprIdentificadorY(YParser.ExprIdentificadorYContext ctx) {
        return new IdentifierExpr(ctx.ID().getText(), getLine(ctx), getCol(ctx));
    }

    @Override
    public Node visitExprLiteralY(YParser.ExprLiteralYContext ctx) {
        YParser.LiteralYContext litCtx = ctx.literalY();
        int line = getLine(ctx);
        int col = getCol(ctx);

        if (litCtx.ENTERO_LIT() != null) {
            int val = Integer.parseInt(litCtx.ENTERO_LIT().getText());
            return new LiteralExpr(val, Type.INT, line, col);
        } else if (litCtx.DECIMAL_LIT() != null) {
            double val = Double.parseDouble(litCtx.DECIMAL_LIT().getText());
            return new LiteralExpr(val, Type.FLOAT, line, col);
        } else if (litCtx.CADENA_LIT() != null) {
            return new LiteralExpr(Literales.cadena(litCtx.CADENA_LIT().getText()), Type.STRING, line, col);
        } else if (litCtx.CARACTER_LIT() != null) {
            return new LiteralExpr(Literales.caracter(litCtx.CARACTER_LIT().getText()), Type.CHAR, line, col);
        } else if (litCtx.KW_VERDADERO() != null) {
            return new LiteralExpr(true, Type.BOOLEAN, line, col);
        } else if (litCtx.KW_FALSO() != null) {
            return new LiteralExpr(false, Type.BOOLEAN, line, col);
        }
        return null;
    }

    private Type parseType(YParser.TipoYContext ctx) {
        if (ctx.tipoBasico() != null) {
            YParser.TipoBasicoContext tb = ctx.tipoBasico();
            if (tb.KW_ENTERO() != null) return Type.INT;
            if (tb.KW_FLOTANTE() != null) return Type.FLOAT;
            if (tb.KW_CADENA() != null) return Type.STRING;
            if (tb.KW_CARACTER() != null) return Type.CHAR;
            if (tb.KW_BOOL() != null) return Type.BOOLEAN;
        } else if (ctx.ID() != null) {
            return Type.createCustomType(ctx.ID().getText(), false);
        }
        return Type.ERROR;
    }
}

