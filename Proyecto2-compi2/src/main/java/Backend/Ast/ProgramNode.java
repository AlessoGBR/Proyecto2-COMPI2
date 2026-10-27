package Backend.Ast;

import Backend.Ast.declaration.*;
import Backend.Ast.statement.BlockStmt;
import Backend.Ast.statement.VarDeclStmt;
import java.util.ArrayList;
import java.util.List;

public class ProgramNode extends Node {

    public enum SourceLanguage {
        PIG_LATIN,
        ZETARIANO,
        Y_LANG
    }

    private final SourceLanguage language;
    private final List<ImportDecl> imports;
    private final List<VarDeclStmt> globalVariables;
    private final List<StructDecl> structs;
    private final List<ClassDecl> classes;
    private final List<FunctionDecl> functions;
    private BlockStmt mainBlock; 

    public ProgramNode(SourceLanguage language, int line, int column) {
        super(line, column);
        this.language = language;
        this.imports = new ArrayList<>();
        this.globalVariables = new ArrayList<>();
        this.structs = new ArrayList<>();
        this.classes = new ArrayList<>();
        this.functions = new ArrayList<>();
        this.mainBlock = null;
    }

    public SourceLanguage getLanguage() {
        return language;
    }

    public List<ImportDecl> getImports() {
        return imports;
    }

    public List<VarDeclStmt> getGlobalVariables() {
        return globalVariables;
    }

    public List<StructDecl> getStructs() {
        return structs;
    }

    public List<ClassDecl> getClasses() {
        return classes;
    }

    public List<FunctionDecl> getFunctions() {
        return functions;
    }

    public BlockStmt getMainBlock() {
        return mainBlock;
    }

    public void setMainBlock(BlockStmt mainBlock) {
        this.mainBlock = mainBlock;
    }

    public void addImport(ImportDecl importDecl) {
        this.imports.add(importDecl);
    }

    public void addGlobalVariable(VarDeclStmt varDecl) {
        this.globalVariables.add(varDecl);
    }

    public void addStruct(StructDecl structDecl) {
        this.structs.add(structDecl);
    }

    public void addClass(ClassDecl classDecl) {
        this.classes.add(classDecl);
    }

    public void addFunction(FunctionDecl functionDecl) {
        this.functions.add(functionDecl);
    }

    // Clases que un archivo .z menciona (extends, tipos, new). Zetariano no tiene import,
    // asi que el compilador del proyecto busca esos archivos .z por su cuenta
    private final java.util.Set<String> referenciasClase = new java.util.LinkedHashSet<>();

    public java.util.Set<String> getReferenciasClase() {
        return referenciasClase;
    }

    public void addReferenciaClase(String nombre) {
        if (nombre != null && !nombre.isBlank()) {
            referenciasClase.add(nombre);
        }
    }

    private int tamanioAreaGlobal = 0;

    public int getTamanioAreaGlobal() {
        return tamanioAreaGlobal;
    }

    public void setTamanioAreaGlobal(int tamanioAreaGlobal) {
        this.tamanioAreaGlobal = tamanioAreaGlobal;
    }

    private int tamanioMarcoPrincipal = 1;

    public int getTamanioMarcoPrincipal() {
        return tamanioMarcoPrincipal;
    }

    public void setTamanioMarcoPrincipal(int tamanioMarcoPrincipal) {
        this.tamanioMarcoPrincipal = tamanioMarcoPrincipal;
    }

    @Override
    public <R, C> R accept(AstVisitor<R, C> visitor, C context) {
        return visitor.visitProgramNode(this, context);
    }
}

