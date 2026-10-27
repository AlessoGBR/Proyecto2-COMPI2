package Backend.simbolos;

import Backend.Ast.Type;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Simbolo {

    private final String identificador;
    private Type tipo;
    private final RolSimbolo rol;
    private int direccionRelativa;
    private boolean esGlobal;
    private boolean esPorReferencia;
    private final List<Simbolo> parametros;
    private final Map<String, Simbolo> miembros;
    private final List<Simbolo> metodos;
    private final List<Simbolo> constructores;
    private String nombreAmbito;
    private boolean esMiembroDeObjeto;
    private String etiquetaC3D;
    private final int linea;
    private final int columna;

    public Simbolo(String identificador, Type tipo, RolSimbolo rol, int direccionRelativa,
                   boolean esGlobal, int linea, int columna) {
        this.identificador = identificador;
        this.tipo = tipo;
        this.rol = rol;
        this.direccionRelativa = direccionRelativa;
        this.esGlobal = esGlobal;
        this.esPorReferencia = false;
        this.parametros = new ArrayList<>();
        this.miembros = new LinkedHashMap<>();
        this.metodos = new ArrayList<>();
        this.constructores = new ArrayList<>();
        this.nombreAmbito = "";
        this.esMiembroDeObjeto = false;
        this.etiquetaC3D = null;
        this.linea = linea;
        this.columna = columna;
    }

    public Simbolo(String identificador, Type tipo, RolSimbolo rol, int linea, int columna) {
        this(identificador, tipo, rol, 0, false, linea, columna);
    }

    public String getIdentificador() {
        return identificador;
    }

    public Type getTipo() {
        return tipo;
    }

    public void setTipo(Type tipo) {
        this.tipo = tipo;
    }

    public RolSimbolo getRol() {
        return rol;
    }

    public int getDireccionRelativa() {
        return direccionRelativa;
    }

    public void setDireccionRelativa(int direccionRelativa) {
        this.direccionRelativa = direccionRelativa;
    }

    public boolean esGlobal() {
        return esGlobal;
    }

    public void setEsGlobal(boolean esGlobal) {
        this.esGlobal = esGlobal;
    }

    public boolean esPorReferencia() {
        return esPorReferencia;
    }

    public void setEsPorReferencia(boolean esPorReferencia) {
        this.esPorReferencia = esPorReferencia;
    }

    public List<Simbolo> getParametros() {
        return parametros;
    }

    public void agregarParametro(Simbolo param) {
        this.parametros.add(param);
    }

    public Map<String, Simbolo> getMiembros() {
        return miembros;
    }

    public void agregarMiembro(Simbolo miembro) {
        this.miembros.put(miembro.getIdentificador(), miembro);
    }

    public Simbolo buscarMiembro(String nombre) {
        return this.miembros.get(nombre);
    }

    public int obtenerIndiceMiembro(String nombre) {
        int i = 0;
        for (String clave : miembros.keySet()) {
            if (clave.equals(nombre)) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public List<Simbolo> getMetodos() {
        return metodos;
    }

    public void agregarMetodo(Simbolo metodo) {
        this.metodos.add(metodo);
    }

    public List<Simbolo> buscarMetodos(String nombre) {
        List<Simbolo> encontrados = new ArrayList<>();
        for (Simbolo m : metodos) {
            if (m.getIdentificador().equals(nombre)) {
                encontrados.add(m);
            }
        }
        return encontrados;
    }

    public List<Simbolo> getConstructores() {
        return constructores;
    }

    public void agregarConstructor(Simbolo constructor) {
        this.constructores.add(constructor);
    }

    public String getFirma() {
        StringBuilder sb = new StringBuilder(identificador).append('(');
        for (int i = 0; i < parametros.size(); i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(parametros.get(i).getTipo());
        }
        return sb.append(')').toString();
    }

    public boolean esMiembroDeObjeto() {
        return esMiembroDeObjeto;
    }

    public void setEsMiembroDeObjeto(boolean esMiembroDeObjeto) {
        this.esMiembroDeObjeto = esMiembroDeObjeto;
    }

    public String getEtiquetaC3D() {
        return etiquetaC3D;
    }

    public void setEtiquetaC3D(String etiquetaC3D) {
        this.etiquetaC3D = etiquetaC3D;
    }

    // PROGRAMACION ORIENTADA A OBJETOS
    // ATRIBUTOS, METODOS Y CONSTRUCTORES
    private Backend.Ast.ModificadorAcceso modificador = Backend.Ast.ModificadorAcceso.PUBLICO;
    private String claseDuenia;
    private boolean esOverride;
    private Simbolo superClase;
    private int idClase;

    public Backend.Ast.ModificadorAcceso getModificador() {
        return modificador;
    }

    public void setModificador(Backend.Ast.ModificadorAcceso modificador) {
        this.modificador = modificador != null ? modificador : Backend.Ast.ModificadorAcceso.PUBLICO;
    }

    public String getClaseDuenia() {
        return claseDuenia;
    }

    public void setClaseDuenia(String claseDuenia) {
        this.claseDuenia = claseDuenia;
    }

    public boolean esOverride() {
        return esOverride;
    }

    public void setEsOverride(boolean esOverride) {
        this.esOverride = esOverride;
    }

    public Simbolo getSuperClase() {
        return superClase;
    }

    public void setSuperClase(Simbolo superClase) {
        this.superClase = superClase;
    }

    public int getIdClase() {
        return idClase;
    }

    public void setIdClase(int idClase) {
        this.idClase = idClase;
    }

    // VERDADERO SI ESTA CLASE ES ANCESTO O DESCENDIENTE DEE ELLA
    public boolean esSubclaseDe(String ancestro) {
        Simbolo actual = this;
        int pasos = 0;
        while (actual != null && pasos++ < 1000) {
            if (actual.getIdentificador().equals(ancestro)) {
                return true;
            }
            actual = actual.getSuperClase();
        }
        return false;
    }

    public Simbolo buscarImplementacion(String firma) {
        Simbolo actual = this;
        int pasos = 0;
        while (actual != null && pasos++ < 1000) {
            for (Simbolo m : actual.getMetodos()) {
                if (m.getFirma().equals(firma)) {
                    return m;
                }
            }
            actual = actual.getSuperClase();
        }
        return null;
    }

    public String getNombreAmbito() {
        return nombreAmbito;
    }

    public void setNombreAmbito(String nombreAmbito) {
        this.nombreAmbito = nombreAmbito;
    }

    public int getLinea() {
        return linea;
    }

    public int getColumna() {
        return columna;
    }

    @Override
    public String toString() {
        return "SIMBOLO{" +
                "ID='" + identificador + '\'' +
                ", TIPO=" + tipo +
                ", ROL=" + rol +
                ", OFFSET=" + direccionRelativa +
                ", GLOBAL=" + esGlobal +
                ", AMBITO='" + nombreAmbito + '\'' +
                '}';
    }
}
