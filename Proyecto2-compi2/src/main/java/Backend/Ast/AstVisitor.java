package Backend.Ast;

import Backend.Ast.declaration.*;
import Backend.Ast.expression.*;
import Backend.Ast.statement.*;

public interface AstVisitor<R, C> {

    // PROGRAMA RAIZ
    R visitProgramNode(ProgramNode node, C context);

    // EXPRESIOENS
    R visitBinaryExpr(BinaryExpr node, C context);
    R visitUnaryExpr(UnaryExpr node, C context);
    R visitTernaryExpr(TernaryExpr node, C context);
    R visitLiteralExpr(LiteralExpr node, C context);
    R visitIdentifierExpr(IdentifierExpr node, C context);
    R visitMemberAccessExpr(MemberAccessExpr node, C context);
    R visitArrayAccessExpr(ArrayAccessExpr node, C context);
    R visitCallExpr(CallExpr node, C context);
    R visitNewObjectExpr(NewObjectExpr node, C context);
    R visitNewArrayExpr(NewArrayExpr node, C context);
    R visitNewStructExpr(NewStructExpr node, C context);
    R visitReadExpr(ReadExpr node, C context);
    R visitThisExpr(ThisExpr node, C context);
    R visitSuperExpr(SuperExpr node, C context);

    // SENTENCIAS E INSTRUCCIONES
    R visitBlockStmt(BlockStmt node, C context);
    R visitVarDeclStmt(VarDeclStmt node, C context);
    R visitAssignStmt(AssignStmt node, C context);
    R visitIfStmt(IfStmt node, C context);
    R visitElseIfBranch(ElseIfBranch node, C context);
    R visitSwitchStmt(SwitchStmt node, C context);
    R visitCaseBranch(CaseBranch node, C context);
    R visitWhileStmt(WhileStmt node, C context);
    R visitDoWhileStmt(DoWhileStmt node, C context);
    R visitForStmt(ForStmt node, C context);
    R visitBreakStmt(BreakStmt node, C context);
    R visitContinueStmt(ContinueStmt node, C context);
    R visitReturnStmt(ReturnStmt node, C context);
    R visitPrintStmt(PrintStmt node, C context);
    R visitReadStmt(ReadStmt node, C context);
    R visitExprStmt(ExprStmt node, C context);

    // DECLARACIONES
    R visitImportDecl(ImportDecl node, C context);
    R visitParameter(Parameter node, C context);
    R visitFunctionDecl(FunctionDecl node, C context);
    R visitConstructorDecl(ConstructorDecl node, C context);
    R visitStructField(StructField node, C context);
    R visitStructDecl(StructDecl node, C context);
    R visitClassDecl(ClassDecl node, C context);
}

