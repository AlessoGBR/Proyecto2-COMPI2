package Backend.visitor;

import Backend.Ast.Node;
import Backend.Ast.ProgramNode;
import Backend.Ast.Type;
import Backend.Ast.declaration.*;
import Backend.Ast.expression.*;
import Backend.Ast.statement.*;
import Backend.antlr.pigLatinBaseVisitor;
import Backend.antlr.pigLatinParser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;
import java.util.List;

public class PigLatinAstVisitor extends pigLatinBaseVisitor<Node> {

    private int getLine(ParserRuleContext ctx) {
        return ctx.getStart() != null ? ctx.getStart().getLine() : 0;
    }

    private int getCol(ParserRuleContext ctx) {
        return ctx.getStart() != null ? ctx.getStart().getCharPositionInLine() : 0;
    }

    @Override
    public Node visitPrograma(pigLatinParser.ProgramaContext ctx) {
        ProgramNode program = new ProgramNode(ProgramNode.SourceLanguage.PIG_LATIN, getLine(ctx), getCol(ctx));

        if (ctx.seccionImportaciones() != null) {
            for (pigLatinParser.ImportacionContext impCtx : ctx.seccionImportaciones().importacion()) {
                ImportDecl importDecl = (ImportDecl) visit(impCtx);
                if (importDecl != null) program.addImport(importDecl);
            }
        }

        if (ctx.seccionVariablesGlobales() != null) {
            for (pigLatinParser.DeclaracionGlobalContext declCtx : ctx.seccionVariablesGlobales().declaracionGlobal()) {
                Node node = visit(declCtx);
                if (node instanceof VarDeclStmt varDecl) {
                    program.addGlobalVariable(varDecl);
                } else if (node instanceof StructDecl structDecl) {
                    program.addStruct(structDecl);
                }
            }
        }

        if (ctx.seccionFunciones() != null) {
            for (pigLatinParser.DefinicionFuncionContext funcCtx : ctx.seccionFunciones().definicionFuncion()) {
                FunctionDecl func = (FunctionDecl) visit(funcCtx);
                if (func != null) program.addFunction(func);
            }
        }

        if (ctx.seccionPrincipal() != null) {
            BlockStmt mainBlock = new BlockStmt(getLine(ctx.seccionPrincipal()), getCol(ctx.seccionPrincipal()));
            for (pigLatinParser.InstruccionContext instCtx : ctx.seccionPrincipal().instruccion()) {
                Node inst = visit(instCtx);
                if (inst instanceof Statement stmt) {
                    mainBlock.addStatement(stmt);
                }
            }
            program.setMainBlock(mainBlock);
        }

        return program;
    }

    @Override
    public Node visitImportacion(pigLatinParser.ImportacionContext ctx) {
        String path = ctx.rutaImport().getText();
        return new ImportDecl(path, getLine(ctx), getCol(ctx));
    }

    @Override
    public Node visitDeclaracionGlobal(pigLatinParser.DeclaracionGlobalContext ctx) {
        if (ctx.declaracionVariable() != null) return visit(ctx.declaracionVariable());
        if (ctx.declaracionArreglo() != null) return visit(ctx.declaracionArreglo());
        return null;
    }

    @Override
    public Node visitDeclaracionVariable(pigLatinParser.DeclaracionVariableContext ctx) {
        String name = ctx.ID().getText();
        int line = getLine(ctx);
        int col = getCol(ctx);

        pigLatinParser.InicializadorVariableContext initCtx = ctx.inicializadorVariable();
        Type varType = Type.ERROR;
        Expression initExpr = null;

        if (initCtx.tipoPrimitivo() != null) {
            varType = parsePrimitiveType(initCtx.tipoPrimitivo().getText());
            if (initCtx.expresion() != null) {
                initExpr = (Expression) visit(initCtx.expresion());
            }
        } else if (initCtx.KW_BOOL() != null) {
            varType = Type.BOOLEAN;
            if (initCtx.expresion() != null) {
                initExpr = (Expression) visit(initCtx.expresion());
            }
        } else if (initCtx.valorBooleano() != null) {
            varType = Type.BOOLEAN;
            boolean val = initCtx.valorBooleano().KW_VERUM() != null;
            initExpr = new LiteralExpr(val, Type.BOOLEAN, line, col);
        } else if (initCtx.COLON() != null) {
            // esto x : <valor> : Tipo  -> el tipo declarado manda (tipo estatico),
            // el valor puede ser un objeto de una clase hija o null
            initExpr = (Expression) visit(initCtx.expresion());
            pigLatinParser.TipoContext tipoCtx = initCtx.tipo();
            varType = tipoCtx.ID() != null
                    ? Type.createCustomType(tipoCtx.ID().getText(), true)
                    : parseType(tipoCtx);
        } else if (initCtx.instanciacionObjeto() != null) {
            initExpr = (Expression) visit(initCtx.instanciacionObjeto());
            String cls = initCtx.instanciacionObjeto().ID().getText();
            varType = Type.createCustomType(cls, true);
        } else if (initCtx.ID() != null && initCtx.literalStruct() != null) {
            String structName = initCtx.ID().getText();
            varType = Type.createCustomType(structName, false);
            initExpr = parseLiteralStruct(initCtx.literalStruct(), structName, line, col);
        } else if (initCtx.tipo() != null) {
            varType = parseType(initCtx.tipo());
            if (initCtx.expresion() != null) {
                initExpr = (Expression) visit(initCtx.expresion());
            } else if (initCtx.literalStruct() != null) {
                initExpr = parseLiteralStruct(initCtx.literalStruct(), varType.getTypeName(), line, col);
            }
        } else if (initCtx.expresion() != null) {
            initExpr = (Expression) visit(initCtx.expresion());
        }

        return new VarDeclStmt(varType, name, initExpr, line, col);
    }

    @Override
    public Node visitDeclaracionArreglo(pigLatinParser.DeclaracionArregloContext ctx) {
        String name = ctx.ID().getText();
        int line = getLine(ctx);
        int col = getCol(ctx);

        Expression sizeExpr = (Expression) visit(ctx.expresion());
        Type elemType = parseType(ctx.tipo());
        Type arrayType = Type.createArrayType(elemType, 1);

        List<Expression> initValues = new ArrayList<>();
        if (ctx.listaValoresArreglo() != null) {
            for (pigLatinParser.ElementoArregloContext elCtx : ctx.listaValoresArreglo().elementoArreglo()) {
                if (elCtx.expresion() != null) {
                    initValues.add((Expression) visit(elCtx.expresion()));
                } else if (elCtx.literalStruct() != null) {
                    initValues.add(parseLiteralStruct(elCtx.literalStruct(), elemType.getTypeName(), line, col));
                }
            }
        }

        NewArrayExpr arrayAlloc = new NewArrayExpr(elemType, List.of(sizeExpr), initValues, line, col);
        return new VarDeclStmt(arrayType, name, arrayAlloc, List.of(sizeExpr), true, line, col);
    }

    private NewStructExpr parseLiteralStruct(pigLatinParser.LiteralStructContext ctx, String structName, int line, int col) {
        List<Expression> values = new ArrayList<>();
        for (pigLatinParser.ElementoStructContext el : ctx.elementoStruct()) {
            if (el.expresion() != null) {
                values.add((Expression) visit(el.expresion()));
            } else if (el.literalStruct() != null) {
                values.add(parseLiteralStruct(el.literalStruct(), null, line, col));
            }
        }
        return new NewStructExpr(structName, values, line, col);
    }

    @Override
    public Node visitFuncionSinRetorno(pigLatinParser.FuncionSinRetornoContext ctx) {
        String name = ctx.ID().getText();
        List<Parameter> params = parseParams(ctx.listaParametros());
        BlockStmt body = new BlockStmt(getLine(ctx), getCol(ctx));
        for (pigLatinParser.InstruccionContext instCtx : ctx.instruccion()) {
            Node n = visit(instCtx);
            if (n instanceof Statement s) body.addStatement(s);
        }
        return new FunctionDecl(name, Type.VOID, params, body, false, true, getLine(ctx), getCol(ctx));
    }

    @Override
    public Node visitFuncionConRetorno(pigLatinParser.FuncionConRetornoContext ctx) {
        String name = ctx.ID().getText();
        Type retType = parseType(ctx.tipo());
        List<Parameter> params = parseParams(ctx.listaParametros());
        BlockStmt body = new BlockStmt(getLine(ctx), getCol(ctx));
        for (pigLatinParser.InstruccionContext instCtx : ctx.instruccion()) {
            Node n = visit(instCtx);
            if (n instanceof Statement s) body.addStatement(s);
        }
        return new FunctionDecl(name, retType, params, body, false, true, getLine(ctx), getCol(ctx));
    }

    private List<Parameter> parseParams(pigLatinParser.ListaParametrosContext ctx) {
        List<Parameter> params = new ArrayList<>();
        if (ctx != null) {
            for (pigLatinParser.ParametroContext p : ctx.parametro()) {
                params.add(new Parameter(p.ID().getText(), parseType(p.tipo()), getLine(p), getCol(p)));
            }
        }
        return params;
    }

    @Override
    public Node visitInstruccion(pigLatinParser.InstruccionContext ctx) {
        if (ctx.declaracionVariable() != null) return visit(ctx.declaracionVariable());
        if (ctx.declaracionArreglo() != null) return visit(ctx.declaracionArreglo());
        if (ctx.asignacion() != null) return visit(ctx.asignacion());
        if (ctx.instruccionLlamadaMetodo() != null) return visit(ctx.instruccionLlamadaMetodo());
        if (ctx.instruccionSi() != null) return visit(ctx.instruccionSi());
        if (ctx.instruccionDum() != null) return visit(ctx.instruccionDum());
        if (ctx.instruccionFacere() != null) return visit(ctx.instruccionFacere());
        if (ctx.instruccionPer() != null) return visit(ctx.instruccionPer());
        if (ctx.instruccionInterrumpe() != null) return new BreakStmt(getLine(ctx), getCol(ctx));
        if (ctx.instruccionPerge() != null) return new ContinueStmt(getLine(ctx), getCol(ctx));
        if (ctx.instruccionReddere() != null) {
            Expression val = ctx.instruccionReddere().expresion() != null ? (Expression) visit(ctx.instruccionReddere().expresion()) : null;
            return new ReturnStmt(val, getLine(ctx), getCol(ctx));
        }
        if (ctx.instruccionImprimir() != null) return visit(ctx.instruccionImprimir());
        if (ctx.instruccionLeer() != null) return visit(ctx.instruccionLeer());
        if (ctx.instruccionIncrementoDecremento() != null) {
            Expression target = parseDestino(ctx.instruccionIncrementoDecremento().destino());
            UnaryOp op = ctx.instruccionIncrementoDecremento().INC() != null ? UnaryOp.INCREMENT : UnaryOp.DECREMENT;
            return new ExprStmt(new UnaryExpr(op, target, true, getLine(ctx), getCol(ctx)), getLine(ctx), getCol(ctx));
        }
        return null;
    }

    @Override
    public Node visitAsignacion(pigLatinParser.AsignacionContext ctx) {
        Expression target = parseDestino(ctx.destino());
        Expression value;
        if (ctx.expresion() != null) {
            value = (Expression) visit(ctx.expresion());
        } else {
            value = parseLiteralStruct(ctx.literalStruct(), null, getLine(ctx), getCol(ctx));
        }
        return new AssignStmt(target, AssignOp.ASSIGN, value, getLine(ctx), getCol(ctx));
    }

    private Expression parseDestino(pigLatinParser.DestinoContext ctx) {
        Expression expr = new IdentifierExpr(ctx.ID().getText(), getLine(ctx), getCol(ctx));
        for (pigLatinParser.AccesoMiembroContext acc : ctx.accesoMiembro()) {
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
    public Node visitInstruccionLlamadaMetodo(pigLatinParser.InstruccionLlamadaMetodoContext ctx) {
        pigLatinParser.LlamadaMetodoContext callCtx = ctx.llamadaMetodo();
        Expression expr = new IdentifierExpr(callCtx.ID().getText(), getLine(callCtx), getCol(callCtx));
        for (pigLatinParser.AccesoPostfijoContext acc : callCtx.accesoPostfijo()) {
            if (acc.DOT() != null) {
                expr = new MemberAccessExpr(expr, acc.ID().getText(), acc.ID().getSymbol().getLine(), acc.ID().getSymbol().getCharPositionInLine());
            } else if (acc.LBRACKET() != null) {
                Expression idx = (Expression) visit(acc.expresion());
                expr = new ArrayAccessExpr(expr, idx, getLine(acc), getCol(acc));
            } else if (acc.LPAREN() != null) {
                List<Expression> args = parseArgs(acc.listaArgumentos());
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

    private List<Expression> parseArgs(pigLatinParser.ListaArgumentosContext ctx) {
        List<Expression> args = new ArrayList<>();
        if (ctx != null) {
            for (pigLatinParser.ExpresionContext e : ctx.expresion()) {
                args.add((Expression) visit(e));
            }
        }
        return args;
    }

    @Override
    public Node visitInstruccionSi(pigLatinParser.InstruccionSiContext ctx) {
        Expression cond = (Expression) visit(ctx.expresion());
        Statement thenBranch = parseBloque(ctx.bloqueInstrucciones());

        List<ElseIfBranch> elseIfs = new ArrayList<>();
        for (pigLatinParser.RamaAliterCondicionalContext rCtx : ctx.ramaAliterCondicional()) {
            Expression rCond = (Expression) visit(rCtx.expresion());
            Statement rBody = parseBloque(rCtx.bloqueInstrucciones());
            elseIfs.add(new ElseIfBranch(rCond, rBody, getLine(rCtx), getCol(rCtx)));
        }

        Statement elseBranch = null;
        if (ctx.ramaAliterFinal() != null) {
            elseBranch = parseBloque(ctx.ramaAliterFinal().bloqueInstrucciones());
        }

        return new IfStmt(cond, thenBranch, elseIfs, elseBranch, getLine(ctx), getCol(ctx));
    }

    @Override
    public Node visitInstruccionDum(pigLatinParser.InstruccionDumContext ctx) {
        Expression cond = (Expression) visit(ctx.expresion());
        Statement body = parseBloque(ctx.bloqueInstrucciones());
        return new WhileStmt(cond, body, getLine(ctx), getCol(ctx));
    }

    @Override
    public Node visitInstruccionFacere(pigLatinParser.InstruccionFacereContext ctx) {
        Statement body = parseBloque(ctx.bloqueInstrucciones());
        Expression cond = (Expression) visit(ctx.expresion());
        return new DoWhileStmt(body, cond, getLine(ctx), getCol(ctx));
    }

    @Override
    public Node visitInstruccionPer(pigLatinParser.InstruccionPerContext ctx) {
        pigLatinParser.DeclaracionCicloForContext forDecl = ctx.declaracionCicloFor();
        Type type = parseType(forDecl.tipo());
        Expression initVal = forDecl.expresion() != null ? (Expression) visit(forDecl.expresion()) : null;
        VarDeclStmt init = new VarDeclStmt(type, forDecl.ID().getText(), initVal, getLine(forDecl), getCol(forDecl));

        Expression cond = (Expression) visit(ctx.expresion());

        Node update = null;
        pigLatinParser.ActualizacionCicloContext actCtx = ctx.actualizacionCiclo();
        if (actCtx.INC() != null || actCtx.DEC() != null) {
            UnaryOp op = actCtx.INC() != null ? UnaryOp.INCREMENT : UnaryOp.DECREMENT;
            Expression dest = parseDestino(actCtx.destino());
            update = new UnaryExpr(op, dest, true, getLine(actCtx), getCol(actCtx));
        } else if (actCtx.ASSIGN() != null) {
            Expression dest = parseDestino(actCtx.destino());
            Expression val = (Expression) visit(actCtx.expresion());
            update = new AssignStmt(dest, AssignOp.ASSIGN, val, getLine(actCtx), getCol(actCtx));
        }

        Statement body = parseBloque(ctx.bloqueInstrucciones());
        return new ForStmt(init, cond, update, body, getLine(ctx), getCol(ctx));
    }

    private BlockStmt parseBloque(pigLatinParser.BloqueInstruccionesContext ctx) {
        BlockStmt block = new BlockStmt(getLine(ctx), getCol(ctx));
        if (ctx != null && ctx.instruccion() != null) {
            for (pigLatinParser.InstruccionContext inst : ctx.instruccion()) {
                Node n = visit(inst);
                if (n instanceof Statement s) block.addStatement(s);
            }
        }
        return block;
    }

    // Como el ';' es opcional, ANTLR junta en una sola regla dos impresiones escritas
    // en lineas distintas; un '>>' que abre una linea nueva inicia otra impresion.
    @Override
    public Node visitInstruccionImprimir(pigLatinParser.InstruccionImprimirContext ctx) {
        List<TerminalNode> flechas = ctx.SHIFT_OUT();
        List<pigLatinParser.ExpresionContext> expresiones = ctx.expresion();

        List<PrintStmt> impresiones = new ArrayList<>();
        List<Expression> actual = new ArrayList<>();
        Token inicio = null;
        int lineaAnterior = -1;

        for (int i = 0; i < expresiones.size(); i++) {
            Token flecha = flechas.get(i).getSymbol();
            if (!actual.isEmpty() && flecha.getLine() > lineaAnterior) {
                impresiones.add(new PrintStmt(actual, true, inicio.getLine(), inicio.getCharPositionInLine()));
                actual = new ArrayList<>();
            }
            if (actual.isEmpty()) {
                inicio = flecha;
            }
            actual.add((Expression) visit(expresiones.get(i)));
            lineaAnterior = expresiones.get(i).getStop().getLine();
        }
        impresiones.add(new PrintStmt(actual, true, inicio.getLine(), inicio.getCharPositionInLine()));

        if (impresiones.size() == 1) {
            return impresiones.get(0);
        }
        BlockStmt bloque = new BlockStmt(getLine(ctx), getCol(ctx));
        for (PrintStmt impresion : impresiones) {
            bloque.addStatement(impresion);
        }
        return bloque;
    }

    @Override
    public Node visitInstruccionLeer(pigLatinParser.InstruccionLeerContext ctx) {
        Expression target = ctx.destino() != null ? parseDestino(ctx.destino()) : null;
        return new ReadStmt(target, getLine(ctx), getCol(ctx));
    }

    // EXPRESIONES

    @Override
    public Node visitExprPostfija(pigLatinParser.ExprPostfijaContext ctx) {
        Expression operand = (Expression) visit(ctx.expresion());
        UnaryOp op = ctx.INC() != null ? UnaryOp.INCREMENT : UnaryOp.DECREMENT;
        return new UnaryExpr(op, operand, true, getLine(ctx), getCol(ctx));
    }

    @Override
    public Node visitExprAccesoMiembro(pigLatinParser.ExprAccesoMiembroContext ctx) {
        Expression target = (Expression) visit(ctx.expresion());
        String member = ctx.ID().getText();
        return new MemberAccessExpr(target, member, ctx.ID().getSymbol().getLine(), ctx.ID().getSymbol().getCharPositionInLine());
    }

    @Override
    public Node visitExprIndexacion(pigLatinParser.ExprIndexacionContext ctx) {
        Expression target = (Expression) visit(ctx.expresion(0));
        Expression index = (Expression) visit(ctx.expresion(1));
        return new ArrayAccessExpr(target, index, getLine(ctx), getCol(ctx));
    }

    @Override
    public Node visitExprLlamadaMetodo(pigLatinParser.ExprLlamadaMetodoContext ctx) {
        Expression targetExpr = (Expression) visit(ctx.expresion());
        List<Expression> args = parseArgs(ctx.listaArgumentos());
        if (targetExpr instanceof MemberAccessExpr mae) {
            return new CallExpr(mae.getTarget(), mae.getMemberName(), args, mae.getLine(), mae.getColumn());
        } else if (targetExpr instanceof IdentifierExpr ide) {
            return new CallExpr(null, ide.getName(), args, ide.getLine(), ide.getColumn());
        }
        return new CallExpr(targetExpr, "", args, getLine(ctx), getCol(ctx));
    }

    @Override
    public Node visitExprInstanciacionNovus(pigLatinParser.ExprInstanciacionNovusContext ctx) {
        return visit(ctx.instanciacionObjeto());
    }

    @Override
    public Node visitInstanciacionObjeto(pigLatinParser.InstanciacionObjetoContext ctx) {
        String className = ctx.ID().getText();
        List<Expression> args = parseArgs(ctx.listaArgumentos());
        return new NewObjectExpr(className, args, getLine(ctx), getCol(ctx));
    }

    @Override
    public Node visitExprNegacionLogica(pigLatinParser.ExprNegacionLogicaContext ctx) {
        Expression expr = (Expression) visit(ctx.expresion());
        return new UnaryExpr(UnaryOp.NOT, expr, false, getLine(ctx), getCol(ctx));
    }

    @Override
    public Node visitExprNotSimbolo(pigLatinParser.ExprNotSimboloContext ctx) {
        Expression expr = (Expression) visit(ctx.expresion());
        return new UnaryExpr(UnaryOp.NOT, expr, false, getLine(ctx), getCol(ctx));
    }

    @Override
    public Node visitExprMenosUnario(pigLatinParser.ExprMenosUnarioContext ctx) {
        Expression expr = (Expression) visit(ctx.expresion());
        return new UnaryExpr(UnaryOp.NEGATION, expr, false, getLine(ctx), getCol(ctx));
    }

    @Override
    public Node visitExprMultiplicativa(pigLatinParser.ExprMultiplicativaContext ctx) {
        Expression left = (Expression) visit(ctx.expresion(0));
        Expression right = (Expression) visit(ctx.expresion(1));
        BinaryOp op = ctx.STAR() != null ? BinaryOp.MUL : (ctx.SLASH() != null ? BinaryOp.DIV : BinaryOp.MOD);
        return new BinaryExpr(left, op, right, getLine(ctx), getCol(ctx));
    }

    @Override
    public Node visitExprAditiva(pigLatinParser.ExprAditivaContext ctx) {
        Expression left = (Expression) visit(ctx.expresion(0));
        Expression right = (Expression) visit(ctx.expresion(1));
        BinaryOp op = ctx.PLUS() != null ? BinaryOp.ADD : BinaryOp.SUB;
        return new BinaryExpr(left, op, right, getLine(ctx), getCol(ctx));
    }

    @Override
    public Node visitExprRelacional(pigLatinParser.ExprRelacionalContext ctx) {
        Expression left = (Expression) visit(ctx.expresion(0));
        Expression right = (Expression) visit(ctx.expresion(1));
        BinaryOp op;
        if (ctx.LT() != null) op = BinaryOp.LESS_THAN;
        else if (ctx.LE() != null) op = BinaryOp.LESS_EQUAL;
        else if (ctx.GT() != null) op = BinaryOp.GREATER_THAN;
        else op = BinaryOp.GREATER_EQUAL;
        return new BinaryExpr(left, op, right, getLine(ctx), getCol(ctx));
    }

    @Override
    public Node visitExprIgualdad(pigLatinParser.ExprIgualdadContext ctx) {
        Expression left = (Expression) visit(ctx.expresion(0));
        Expression right = (Expression) visit(ctx.expresion(1));
        BinaryOp op = (ctx.NEQ() != null) ? BinaryOp.NOT_EQUALS : BinaryOp.EQUALS;
        return new BinaryExpr(left, op, right, getLine(ctx), getCol(ctx));
    }

    @Override
    public Node visitExprAndLogico(pigLatinParser.ExprAndLogicoContext ctx) {
        Expression left = (Expression) visit(ctx.expresion(0));
        Expression right = (Expression) visit(ctx.expresion(1));
        return new BinaryExpr(left, BinaryOp.AND, right, getLine(ctx), getCol(ctx));
    }

    @Override
    public Node visitExprOrLogico(pigLatinParser.ExprOrLogicoContext ctx) {
        Expression left = (Expression) visit(ctx.expresion(0));
        Expression right = (Expression) visit(ctx.expresion(1));
        return new BinaryExpr(left, BinaryOp.OR, right, getLine(ctx), getCol(ctx));
    }

    @Override
    public Node visitExprParentesis(pigLatinParser.ExprParentesisContext ctx) {
        return visit(ctx.expresion());
    }

    @Override
    public Node visitExprIdentificador(pigLatinParser.ExprIdentificadorContext ctx) {
        return new IdentifierExpr(ctx.ID().getText(), getLine(ctx), getCol(ctx));
    }

    @Override
    public Node visitExprLiteral(pigLatinParser.ExprLiteralContext ctx) {
        pigLatinParser.LiteralContext litCtx = ctx.literal();
        int line = getLine(ctx);
        int col = getCol(ctx);

        if (litCtx.NUMERUS_LIT() != null) {
            int val = Integer.parseInt(litCtx.NUMERUS_LIT().getText());
            return new LiteralExpr(val, Type.INT, line, col);
        } else if (litCtx.DECIMALIS_LIT() != null) {
            double val = Double.parseDouble(litCtx.DECIMALIS_LIT().getText());
            return new LiteralExpr(val, Type.FLOAT, line, col);
        } else if (litCtx.TEXTUM_LIT() != null) {
            return new LiteralExpr(Literales.cadena(litCtx.TEXTUM_LIT().getText()), Type.STRING, line, col);
        } else if (litCtx.LITTERA_LIT() != null) {
            return new LiteralExpr(Literales.caracter(litCtx.LITTERA_LIT().getText()), Type.CHAR, line, col);
        } else if (litCtx.KW_VERUM() != null) {
            return new LiteralExpr(true, Type.BOOLEAN, line, col);
        } else if (litCtx.KW_FALSUS() != null) {
            return new LiteralExpr(false, Type.BOOLEAN, line, col);
        } else if (litCtx.KW_NULL() != null) {
            return new LiteralExpr(null, Type.NULL, line, col);
        }
        return null;
    }

    private Type parsePrimitiveType(String text) {
        return switch (text) {
            case "numerus" -> Type.INT;
            case "decimalis" -> Type.FLOAT;
            case "littera" -> Type.CHAR;
            case "textum" -> Type.STRING;
            case "bool" -> Type.BOOLEAN;
            default -> Type.ERROR;
        };
    }

    private Type parseType(pigLatinParser.TipoContext ctx) {
        if (ctx.tipoPrimitivo() != null) {
            return parsePrimitiveType(ctx.tipoPrimitivo().getText());
        } else if (ctx.KW_BOOL() != null) {
            return Type.BOOLEAN;
        } else if (ctx.ID() != null) {
            return Type.createCustomType(ctx.ID().getText(), false);
        }
        return Type.ERROR;
    }
}
