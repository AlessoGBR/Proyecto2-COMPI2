package Backend.visitor;

import Backend.Ast.ModificadorAcceso;
import Backend.Ast.Node;
import Backend.Ast.ProgramNode;
import Backend.Ast.Type;
import Backend.Ast.declaration.*;
import Backend.Ast.expression.*;
import Backend.Ast.statement.*;
import Backend.antlr.ZetarianoBaseVisitor;
import Backend.antlr.ZetarianoParser;
import org.antlr.v4.runtime.ParserRuleContext;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class ZetarianoAstVisitor extends ZetarianoBaseVisitor<Node> {

    private final Set<String> referenciasClase = new LinkedHashSet<>();

    private int getLine(ParserRuleContext ctx) {
        return ctx.getStart() != null ? ctx.getStart().getLine() : 0;
    }

    private int getCol(ParserRuleContext ctx) {
        return ctx.getStart() != null ? ctx.getStart().getCharPositionInLine() : 0;
    }

    @Override
    public Node visitPrograma(ZetarianoParser.ProgramaContext ctx) {
        ProgramNode program = new ProgramNode(ProgramNode.SourceLanguage.ZETARIANO, getLine(ctx), getCol(ctx));
        if (ctx.definicionClase() != null) {
            ClassDecl cls = (ClassDecl) visit(ctx.definicionClase());
            if (cls != null) {
                program.addClass(cls);
                referenciasClase.remove(cls.getName());
            }
        }
        for (String referencia : referenciasClase) {
            program.addReferenciaClase(referencia);
        }
        return program;
    }

    @Override
    public Node visitDefinicionClase(ZetarianoParser.DefinicionClaseContext ctx) {
        String className = ctx.ID(0).getText();
        boolean isPublic = ctx.KW_PUBLIC() != null;
        List<String> superClases = new ArrayList<>();
        for (int i = 1; i < ctx.ID().size(); i++) {
            superClases.add(ctx.ID(i).getText());
            referenciasClase.add(ctx.ID(i).getText());
        }

        List<VarDeclStmt> attributes = new ArrayList<>();
        List<ConstructorDecl> constructors = new ArrayList<>();
        List<FunctionDecl> methods = new ArrayList<>();

        for (ZetarianoParser.MiembroClaseContext mCtx : ctx.miembroClase()) {
            if (mCtx.declaracionCampo() != null) {
                Node n = visit(mCtx.declaracionCampo());
                if (n instanceof VarDeclStmt vds) attributes.add(vds);
            } else if (mCtx.definicionConstructor() != null) {
                Node n = visit(mCtx.definicionConstructor());
                if (n instanceof ConstructorDecl cd) constructors.add(cd);
            } else if (mCtx.definicionMetodo() != null) {
                Node n = visit(mCtx.definicionMetodo());
                if (n instanceof FunctionDecl fd) methods.add(fd);
            }
        }

        return new ClassDecl(className, attributes, constructors, methods, isPublic, superClases, getLine(ctx), getCol(ctx));
    }

    @Override
    public Node visitDeclaracionCampo(ZetarianoParser.DeclaracionCampoContext ctx) {
        Type type = parseType(ctx.tipo());
        String name = ctx.ID().getText();
        Expression init = null;
        if (ctx.expresion() != null) {
            init = (Expression) visit(ctx.expresion());
        } else if (ctx.literalArreglo() != null) {
            init = (Expression) visit(ctx.literalArreglo());
        }
        VarDeclStmt atributo = new VarDeclStmt(type, name, init, getLine(ctx), getCol(ctx));
        atributo.setModificador(modificador(ctx.modificadorAcceso()));
        return atributo;
    }

    @Override
    public Node visitDefinicionConstructor(ZetarianoParser.DefinicionConstructorContext ctx) {
        String name = ctx.ID().getText();
        ModificadorAcceso acceso = modificador(ctx.modificadorAcceso());
        List<Parameter> params = parseParameters(ctx.listaParametros());
        BlockStmt body = (BlockStmt) visit(ctx.bloque());
        ConstructorDecl constructor = new ConstructorDecl(name, params, body,
                acceso == ModificadorAcceso.PUBLICO, getLine(ctx), getCol(ctx));
        constructor.setModificador(acceso);
        return constructor;
    }

    @Override
    public Node visitDefinicionMetodo(ZetarianoParser.DefinicionMetodoContext ctx) {
        String name = ctx.ID().getText();
        ModificadorAcceso acceso = modificador(ctx.modificadorAcceso());
        Type retType =ctx.tipoRetorno().KW_VOID() != null ? Type.VOID : parseType(ctx.tipoRetorno().tipo());
        List<Parameter> params = parseParameters(ctx.listaParametros());
        BlockStmt body = (BlockStmt) visit(ctx.bloque());
        FunctionDecl metodo = new FunctionDecl(name, retType, params, body, true,
                acceso == ModificadorAcceso.PUBLICO, ctx.ID().getSymbol().getLine(), ctx.ID().getSymbol().getCharPositionInLine());
        metodo.setModificador(acceso);
        if (ctx.ANOTACION() != null) {
            metodo.setAnotacion(ctx.ANOTACION().getText());
        }
        return metodo;
    }

    // SIN MODIFICADOR = DEFAULT
    private ModificadorAcceso modificador(ZetarianoParser.ModificadorAccesoContext ctx) {
        if (ctx == null) return ModificadorAcceso.DEFECTO;
        if (ctx.KW_PUBLIC() != null) return ModificadorAcceso.PUBLICO;
        if (ctx.KW_PRIVATE() != null) return ModificadorAcceso.PRIVADO;
        return ModificadorAcceso.PROTEGIDO;
    }

    private List<Parameter> parseParameters(ZetarianoParser.ListaParametrosContext ctx) {
        List<Parameter> params = new ArrayList<>();
        if (ctx != null) {
            for (ZetarianoParser.ParametroContext pCtx : ctx.parametro()) {
                params.add(new Parameter(pCtx.ID().getText(), parseType(pCtx.tipo()), getLine(pCtx), getCol(pCtx)));
            }
        }
        return params;
    }

    @Override
    public Node visitBloque(ZetarianoParser.BloqueContext ctx) {
        BlockStmt block = new BlockStmt(getLine(ctx), getCol(ctx));
        for (ZetarianoParser.InstruccionContext instCtx : ctx.instruccion()) {
            Node n = visit(instCtx);
            if (n instanceof Statement s) block.addStatement(s);
        }
        return block;
    }

    @Override
    public Node visitInstruccion(ZetarianoParser.InstruccionContext ctx) {
        if (ctx.bloque() != null) return visit(ctx.bloque());
        if (ctx.declaracionVariableLocal() != null) return visit(ctx.declaracionVariableLocal());
        if (ctx.instruccionIf() != null) return visit(ctx.instruccionIf());
        if (ctx.instruccionSwitch() != null) return visit(ctx.instruccionSwitch());
        if (ctx.instruccionFor() != null) return visit(ctx.instruccionFor());
        if (ctx.instruccionWhile() != null) return visit(ctx.instruccionWhile());
        if (ctx.instruccionDoWhile() != null) return visit(ctx.instruccionDoWhile());
        if (ctx.instruccionBreak() != null) return new BreakStmt(getLine(ctx), getCol(ctx));
        if (ctx.instruccionContinue() != null) return new ContinueStmt(getLine(ctx), getCol(ctx));
        if (ctx.instruccionReturn() != null) {
            Expression val = ctx.instruccionReturn().expresion() != null ? (Expression) visit(ctx.instruccionReturn().expresion()) : null;
            return new ReturnStmt(val, getLine(ctx), getCol(ctx));
        }
        if (ctx.instruccionExpresion() != null) return visit(ctx.instruccionExpresion());
        return null;
    }

    @Override
    public Node visitDeclaracionVariableLocal(ZetarianoParser.DeclaracionVariableLocalContext ctx) {
        Type type = parseType(ctx.tipo());
        List<ZetarianoParser.DeclaradorVariableContext> decls = ctx.listaDeclaradoresVariable().declaradorVariable();
        if (decls.size() == 1) {
            ZetarianoParser.DeclaradorVariableContext d = decls.get(0);
            Expression init = d.inicializador() != null ? parseInicializador(d.inicializador()) : null;
            return new VarDeclStmt(type, d.ID().getText(), init, getLine(ctx), getCol(ctx));
        } else {
            BlockStmt block = new BlockStmt(getLine(ctx), getCol(ctx));
            for (ZetarianoParser.DeclaradorVariableContext d : decls) {
                Expression init = d.inicializador() != null ? parseInicializador(d.inicializador()) : null;
                block.addStatement(new VarDeclStmt(type, d.ID().getText(), init, getLine(d), getCol(d)));
            }
            return block;
        }
    }

    private Expression parseInicializador(ZetarianoParser.InicializadorContext ctx) {
        if (ctx.expresion() != null) return (Expression) visit(ctx.expresion());
        if (ctx.literalArreglo() != null) return (Expression) visit(ctx.literalArreglo());
        return null;
    }

    @Override
    public Node visitLiteralArreglo(ZetarianoParser.LiteralArregloContext ctx) {
        List<Expression> elements = new ArrayList<>();
        for (ZetarianoParser.InicializadorContext initCtx : ctx.inicializador()) {
            elements.add(parseInicializador(initCtx));
        }
        return new NewArrayExpr(null, List.of(), elements, getLine(ctx), getCol(ctx));
    }

    @Override
    public Node visitInstruccionIf(ZetarianoParser.InstruccionIfContext ctx) {
        Expression cond = (Expression) visit(ctx.expresion());
        Statement thenBranch = (Statement) visit(ctx.instruccion(0));
        Statement elseBranch = ctx.KW_ELSE() != null ? (Statement) visit(ctx.instruccion(1)) : null;

        List<ElseIfBranch> elseIfs = new ArrayList<>();
        while (elseBranch instanceof IfStmt nestedIf) {
            elseIfs.add(new ElseIfBranch(nestedIf.getCondition(), nestedIf.getThenBranch(), nestedIf.getLine(), nestedIf.getColumn()));
            elseIfs.addAll(nestedIf.getElseIfBranches());
            elseBranch = nestedIf.getElseBranch();
        }

        return new IfStmt(cond, thenBranch, elseIfs, elseBranch, getLine(ctx), getCol(ctx));
    }

    @Override
    public Node visitInstruccionSwitch(ZetarianoParser.InstruccionSwitchContext ctx) {
        Expression expr = (Expression) visit(ctx.expresion());
        List<CaseBranch> cases = new ArrayList<>();

        for (ZetarianoParser.BloqueSwitchContext bCtx : ctx.bloqueSwitch()) {
            List<Statement> stmts = new ArrayList<>();
            for (ZetarianoParser.InstruccionContext iCtx : bCtx.instruccion()) {
                Node n = visit(iCtx);
                if (n instanceof Statement s) stmts.add(s);
            }

            for (ZetarianoParser.EtiquetaSwitchContext lCtx : bCtx.etiquetaSwitch()) {
                if (lCtx.KW_DEFAULT() != null) {
                    cases.add(new CaseBranch(null, stmts, true, getLine(lCtx), getCol(lCtx)));
                } else {
                    Expression cExpr = (Expression) visit(lCtx.expresion());
                    cases.add(new CaseBranch(cExpr, stmts, false, getLine(lCtx), getCol(lCtx)));
                }
            }
        }

        return new SwitchStmt(expr, cases, getLine(ctx), getCol(ctx));
    }

    @Override
    public Node visitInstruccionFor(ZetarianoParser.InstruccionForContext ctx) {
        Node init = null;
        if (ctx.forInit() != null) {
            if (ctx.forInit().tipo() != null) {
                Type type = parseType(ctx.forInit().tipo());
                List<ZetarianoParser.DeclaradorVariableContext> decls = ctx.forInit().listaDeclaradoresVariable().declaradorVariable();
                ZetarianoParser.DeclaradorVariableContext d = decls.get(0);
                Expression initVal = d.inicializador() != null ? parseInicializador(d.inicializador()) : null;
                init = new VarDeclStmt(type, d.ID().getText(), initVal, getLine(d), getCol(d));
            } else if (ctx.forInit().listaExpresiones() != null) {
                init = visit(ctx.forInit().listaExpresiones().expresion(0));
            }
        }

        Expression cond = ctx.expresion() != null ? (Expression) visit(ctx.expresion()) : null;

        Node update = null;
        if (ctx.forUpdate() != null && !ctx.forUpdate().listaExpresiones().expresion().isEmpty()) {
            update = visit(ctx.forUpdate().listaExpresiones().expresion(0));
        }

        Statement body = (Statement) visit(ctx.instruccion());
        return new ForStmt(init, cond, update, body, getLine(ctx), getCol(ctx));
    }

    @Override
    public Node visitInstruccionWhile(ZetarianoParser.InstruccionWhileContext ctx) {
        Expression cond = (Expression) visit(ctx.expresion());
        Statement body = (Statement) visit(ctx.instruccion());
        return new WhileStmt(cond, body, getLine(ctx), getCol(ctx));
    }

    @Override
    public Node visitInstruccionDoWhile(ZetarianoParser.InstruccionDoWhileContext ctx) {
        Statement body = (Statement) visit(ctx.instruccion());
        Expression cond = (Expression) visit(ctx.expresion());
        return new DoWhileStmt(body, cond, getLine(ctx), getCol(ctx));
    }

    @Override
    public Node visitInstruccionExpresion(ZetarianoParser.InstruccionExpresionContext ctx) {
        Node expr = visit(ctx.expresion());
        if (expr instanceof Statement s) return s;
        if (expr instanceof Expression e) return new ExprStmt(e, getLine(ctx), getCol(ctx));
        return null;
    }

    // EXPRESIONES

    @Override
    public Node visitExprPostfijoIncDec(ZetarianoParser.ExprPostfijoIncDecContext ctx) {
        Expression operand = (Expression) visit(ctx.expresion());
        UnaryOp op = ctx.op.getType() == ZetarianoParser.INC ? UnaryOp.INCREMENT : UnaryOp.DECREMENT;
        return new UnaryExpr(op, operand, true, getLine(ctx), getCol(ctx));
    }

    @Override
    public Node visitExprPrefijoIncDec(ZetarianoParser.ExprPrefijoIncDecContext ctx) {
        Expression operand = (Expression) visit(ctx.expresion());
        UnaryOp op = ctx.op.getType() == ZetarianoParser.INC ? UnaryOp.INCREMENT : UnaryOp.DECREMENT;
        return new UnaryExpr(op, operand, false, getLine(ctx), getCol(ctx));
    }

    @Override
    public Node visitExprAccesoMiembro(ZetarianoParser.ExprAccesoMiembroContext ctx) {
        Expression target = (Expression) visit(ctx.expresion());
        String member = ctx.ID().getText();
        return new MemberAccessExpr(target, member, ctx.ID().getSymbol().getLine(), ctx.ID().getSymbol().getCharPositionInLine());
    }

    @Override
    public Node visitExprIndexacion(ZetarianoParser.ExprIndexacionContext ctx) {
        Expression target = (Expression) visit(ctx.expresion(0));
        Expression index = (Expression) visit(ctx.expresion(1));
        return new ArrayAccessExpr(target, index, getLine(ctx), getCol(ctx));
    }

    @Override
    public Node visitExprLlamadaMetodo(ZetarianoParser.ExprLlamadaMetodoContext ctx) {
        Expression targetExpr = (Expression) visit(ctx.expresion());
        List<Expression> args = parseArguments(ctx.listaArgumentos());
        if (targetExpr instanceof SuperExpr llamadaPadre) {
            return new CallExpr(llamadaPadre, CallExpr.NOMBRE_CONSTRUCTOR_PADRE, args, llamadaPadre.getLine(), llamadaPadre.getColumn());
        } else if (targetExpr instanceof MemberAccessExpr mae) {
            return new CallExpr(mae.getTarget(), mae.getMemberName(), args, mae.getLine(), mae.getColumn());
        } else if (targetExpr instanceof IdentifierExpr ide) {
            return new CallExpr(null, ide.getName(), args, ide.getLine(), ide.getColumn());
        }
        return new CallExpr(targetExpr, "", args, getLine(ctx), getCol(ctx));
    }

    private List<Expression> parseArguments(ZetarianoParser.ListaArgumentosContext ctx) {
        List<Expression> args = new ArrayList<>();
        if (ctx != null) {
            for (ZetarianoParser.ExpresionContext e : ctx.expresion()) {
                args.add((Expression) visit(e));
            }
        }
        return args;
    }

    @Override
    public Node visitExprUnariaAritmetica(ZetarianoParser.ExprUnariaAritmeticaContext ctx) {
        Expression operand = (Expression) visit(ctx.expresion());
        UnaryOp op = ctx.op.getType() == ZetarianoParser.MINUS ? UnaryOp.NEGATION : UnaryOp.POSITIVE;
        return new UnaryExpr(op, operand, false, getLine(ctx), getCol(ctx));
    }

    @Override
    public Node visitExprNotLogico(ZetarianoParser.ExprNotLogicoContext ctx) {
        Expression operand = (Expression) visit(ctx.expresion());
        return new UnaryExpr(UnaryOp.NOT, operand, false, getLine(ctx), getCol(ctx));
    }

    @Override
    public Node visitExprCreacionObjeto(ZetarianoParser.ExprCreacionObjetoContext ctx) {
        String className = ctx.ID().getText();
        referenciasClase.add(className);
        List<Expression> args = parseArguments(ctx.listaArgumentos());
        return new NewObjectExpr(className, args, getLine(ctx), getCol(ctx));
    }

    @Override
    public Node visitExprCreacionArreglo(ZetarianoParser.ExprCreacionArregloContext ctx) {
        Type baseType = parseTipoBasico(ctx.tipoBasico());
        List<Expression> dimensions = new ArrayList<>();
        for (ZetarianoParser.DimensionArregloNuevaContext dCtx : ctx.dimensionArregloNueva()) {
            dimensions.add((Expression) visit(dCtx.expresion()));
        }
        return new NewArrayExpr(baseType, dimensions, List.of(), getLine(ctx), getCol(ctx));
    }

    @Override
    public Node visitExprMultiplicativa(ZetarianoParser.ExprMultiplicativaContext ctx) {
        Expression left = (Expression) visit(ctx.expresion(0));
        Expression right = (Expression) visit(ctx.expresion(1));
        BinaryOp op = switch (ctx.op.getType()) {
            case ZetarianoParser.STAR -> BinaryOp.MUL;
            case ZetarianoParser.SLASH -> BinaryOp.DIV;
            default -> BinaryOp.MOD;
        };
        return new BinaryExpr(left, op, right, getLine(ctx), getCol(ctx));
    }

    @Override
    public Node visitExprAditiva(ZetarianoParser.ExprAditivaContext ctx) {
        Expression left = (Expression) visit(ctx.expresion(0));
        Expression right = (Expression) visit(ctx.expresion(1));
        BinaryOp op = ctx.op.getType() == ZetarianoParser.PLUS ? BinaryOp.ADD : BinaryOp.SUB;
        return new BinaryExpr(left, op, right, getLine(ctx), getCol(ctx));
    }

    @Override
    public Node visitExprRelacional(ZetarianoParser.ExprRelacionalContext ctx) {
        Expression left = (Expression) visit(ctx.expresion(0));
        Expression right = (Expression) visit(ctx.expresion(1));
        BinaryOp op = switch (ctx.op.getType()) {
            case ZetarianoParser.LT -> BinaryOp.LESS_THAN;
            case ZetarianoParser.LE -> BinaryOp.LESS_EQUAL;
            case ZetarianoParser.GT -> BinaryOp.GREATER_THAN;
            default -> BinaryOp.GREATER_EQUAL;
        };
        return new BinaryExpr(left, op, right, getLine(ctx), getCol(ctx));
    }

    @Override
    public Node visitExprIgualdad(ZetarianoParser.ExprIgualdadContext ctx) {
        Expression left = (Expression) visit(ctx.expresion(0));
        Expression right = (Expression) visit(ctx.expresion(1));
        BinaryOp op = ctx.op.getType() == ZetarianoParser.EQ ? BinaryOp.EQUALS : BinaryOp.NOT_EQUALS;
        return new BinaryExpr(left, op, right, getLine(ctx), getCol(ctx));
    }

    @Override
    public Node visitExprAndLogico(ZetarianoParser.ExprAndLogicoContext ctx) {
        Expression left = (Expression) visit(ctx.expresion(0));
        Expression right = (Expression) visit(ctx.expresion(1));
        return new BinaryExpr(left, BinaryOp.AND, right, getLine(ctx), getCol(ctx));
    }

    @Override
    public Node visitExprOrLogico(ZetarianoParser.ExprOrLogicoContext ctx) {
        Expression left = (Expression) visit(ctx.expresion(0));
        Expression right = (Expression) visit(ctx.expresion(1));
        return new BinaryExpr(left, BinaryOp.OR, right, getLine(ctx), getCol(ctx));
    }

    @Override
    public Node visitExprTernaria(ZetarianoParser.ExprTernariaContext ctx) {
        Expression cond = (Expression) visit(ctx.expresion(0));
        Expression thenExpr = (Expression) visit(ctx.expresion(1));
        Expression elseExpr = (Expression) visit(ctx.expresion(2));
        return new TernaryExpr(cond, thenExpr, elseExpr, getLine(ctx), getCol(ctx));
    }

    @Override
    public Node visitExprAsignacion(ZetarianoParser.ExprAsignacionContext ctx) {
        Expression target = (Expression) visit(ctx.expresion(0));
        Expression value = (Expression) visit(ctx.expresion(1));
        AssignOp op = switch (ctx.op.getType()) {
            case ZetarianoParser.ADD_ASSIGN -> AssignOp.ADD_ASSIGN;
            case ZetarianoParser.SUB_ASSIGN -> AssignOp.SUB_ASSIGN;
            case ZetarianoParser.MUL_ASSIGN -> AssignOp.MUL_ASSIGN;
            case ZetarianoParser.DIV_ASSIGN -> AssignOp.DIV_ASSIGN;
            case ZetarianoParser.MOD_ASSIGN -> AssignOp.MOD_ASSIGN;
            default -> AssignOp.ASSIGN;
        };
        return new AssignStmt(target, op, value, getLine(ctx), getCol(ctx));
    }

    @Override
    public Node visitExprLlamadaSistema(ZetarianoParser.ExprLlamadaSistemaContext ctx) {
        ZetarianoParser.LlamadaSistemaContext sc = ctx.llamadaSistema();
        int line = getLine(sc);
        int col = getCol(sc);
        if (sc.KW_PRINTLN() != null) {
            List<Expression> args = sc.expresion() != null ? List.of((Expression) visit(sc.expresion())) : List.of();
            return new PrintStmt(args, true, line, col);
        } else if (sc.KW_PRINT() != null) {
            List<Expression> args = sc.expresion() != null ? List.of((Expression) visit(sc.expresion())) : List.of();
            return new PrintStmt(args, false, line, col);
        } else if (sc.KW_READLN() != null) {
            return new ReadExpr(line, col);
        }
        return null;
    }

    @Override
    public Node visitExprParentesis(ZetarianoParser.ExprParentesisContext ctx) {
        return visit(ctx.expresion());
    }

    @Override
    public Node visitExprLiteralArreglo(ZetarianoParser.ExprLiteralArregloContext ctx) {
        return visit(ctx.literalArreglo());
    }

    @Override
    public Node visitExprThis(ZetarianoParser.ExprThisContext ctx) {
        return new ThisExpr(getLine(ctx), getCol(ctx));
    }

    @Override
    public Node visitExprSuper(ZetarianoParser.ExprSuperContext ctx) {
        return new SuperExpr(getLine(ctx), getCol(ctx));
    }

    @Override
    public Node visitExprIdentificador(ZetarianoParser.ExprIdentificadorContext ctx) {
        return new IdentifierExpr(ctx.ID().getText(), getLine(ctx), getCol(ctx));
    }

    @Override
    public Node visitExprLiteral(ZetarianoParser.ExprLiteralContext ctx) {
        ZetarianoParser.LiteralContext litCtx = ctx.literal();
        int line = getLine(ctx);
        int col = getCol(ctx);

        if (litCtx.ENTERO_LIT() != null) {
            int val = Integer.parseInt(litCtx.ENTERO_LIT().getText());
            return new LiteralExpr(val, Type.INT, line, col);
        } else if (litCtx.DECIMAL_LIT() != null) {
            double val = Double.parseDouble(litCtx.DECIMAL_LIT().getText());
            return new LiteralExpr(val, Type.FLOAT, line, col);
        } else if (litCtx.STRING_LIT() != null) {
            return new LiteralExpr(Literales.cadena(litCtx.STRING_LIT().getText()), Type.STRING, line, col);
        } else if (litCtx.CHAR_LIT() != null) {
            return new LiteralExpr(Literales.caracter(litCtx.CHAR_LIT().getText()), Type.CHAR, line, col);
        } else if (litCtx.KW_TRUE() != null) {
            return new LiteralExpr(true, Type.BOOLEAN, line, col);
        } else if (litCtx.KW_FALSE() != null) {
            return new LiteralExpr(false, Type.BOOLEAN, line, col);
        } else if (litCtx.KW_NULL() != null) {
            return new LiteralExpr(null, Type.NULL, line, col);
        }
        return null;
    }

    private Type parseTipoBasico(ZetarianoParser.TipoBasicoContext ctx) {
        if (ctx.KW_INT() != null) return Type.INT;
        if (ctx.KW_DOUBLE() != null) return Type.FLOAT;
        if (ctx.KW_CHAR() != null) return Type.CHAR;
        if (ctx.KW_BOOLEAN() != null) return Type.BOOLEAN;
        if (ctx.KW_STRING() != null) return Type.STRING;
        if (ctx.ID() != null) {
            referenciasClase.add(ctx.ID().getText());
            return Type.createCustomType(ctx.ID().getText(), true);
        }
        return Type.ERROR;
    }

    private Type parseType(ZetarianoParser.TipoContext ctx) {
        Type base = parseTipoBasico(ctx.tipoBasico());
        int dims = ctx.dimensionesTipo().size();
        if (dims > 0) {
            return Type.createArrayType(base, dims);
        }
        return base;
    }
}

