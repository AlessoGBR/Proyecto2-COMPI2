package Frontend;

import Backend.c3d.Cuarteta;
import Backend.errores.ErrorCompilacion;
import Backend.simbolos.Ambito;
import Backend.simbolos.RolSimbolo;
import Backend.simbolos.Simbolo;
import Backend.simbolos.TablaSimbolos;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class ModelosTablas {

    public static class ModeloTablaErrores extends AbstractTableModel {

        private static final String[] COLUMNAS = {"#", "TIPO", "DESCRIPCION", "LINEA", "COLUMNA", "ARCHIVO"};
        private final List<ErrorCompilacion> errores = new ArrayList<>();

        public void setErrores(List<ErrorCompilacion> nuevosErrores) {
            errores.clear();
            if (nuevosErrores != null) {
                errores.addAll(nuevosErrores);
            }
            fireTableDataChanged();
        }

        public void limpiar() {
            errores.clear();
            fireTableDataChanged();
        }

        public ErrorCompilacion obtenerError(int fila) {
            if (fila >= 0 && fila < errores.size()) {
                return errores.get(fila);
            }
            return null;
        }

        @Override
        public int getRowCount() {
            return errores.size();
        }

        @Override
        public int getColumnCount() {
            return COLUMNAS.length;
        }

        @Override
        public String getColumnName(int column) {
            return COLUMNAS[column];
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            ErrorCompilacion err = errores.get(rowIndex);
            return switch (columnIndex) {
                case 0 -> rowIndex + 1;
                case 1 -> err.getTipo() != null ? err.getTipo().getEtiqueta() : "DESCONOCIDO";
                case 2 -> err.getMensaje();
                case 3 -> err.getLinea();
                case 4 -> err.getColumna();
                case 5 -> err.getArchivo().isEmpty() ? "-" : err.getArchivo();
                default -> "";
            };
        }
    }

    public static class ModeloTablaCuartetas extends AbstractTableModel {

        private static final String[] COLUMNAS = {"#", "OPERADOR", "ARGUMENTO1 ", "ARGUMENTO 2", "RESULTADO"};
        private final List<Cuarteta> cuartetas = new ArrayList<>();

        public void setCuartetas(List<Cuarteta> nuevasCuartetas) {
            cuartetas.clear();
            if (nuevasCuartetas != null) {
                cuartetas.addAll(nuevasCuartetas);
            }
            fireTableDataChanged();
        }

        public void limpiar() {
            cuartetas.clear();
            fireTableDataChanged();
        }

        @Override
        public int getRowCount() {
            return cuartetas.size();
        }

        @Override
        public int getColumnCount() {
            return COLUMNAS.length;
        }

        @Override
        public String getColumnName(int column) {
            return COLUMNAS[column];
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Cuarteta c = cuartetas.get(rowIndex);
            return switch (columnIndex) {
                case 0 -> rowIndex + 1;
                case 1 -> c.getOperador() != null ? c.getOperador().name() : "";
                case 2 -> c.getArg1().isEmpty() ? "-" : c.getArg1();
                case 3 -> c.getArg2().isEmpty() ? "-" : c.getArg2();
                case 4 -> c.getResultado().isEmpty() ? "-" : c.getResultado();
                default -> "";
            };
        }
    }

    public static class ModeloTablaSimbolos extends AbstractTableModel {

        private static final String[] COLUMNAS = {"#", "NOMBRE", "TIPO", "ROL", "ACCESO", "AMBITO", "OFFSET", "GLOBAL"};

        public static class FilaSimbolo {
            public final String nombre;
            public final String tipo;
            public final String rol;
            public final String acceso;
            public final String ambito;
            public final String offset;
            public final String esGlobal;

            public FilaSimbolo(String nombre, String tipo, String rol, String acceso, String ambito, String offset, String esGlobal) {
                this.nombre = nombre;
                this.tipo = tipo;
                this.rol = rol;
                this.acceso = acceso;
                this.ambito = ambito;
                this.offset = offset;
                this.esGlobal = esGlobal;
            }
        }

        private final List<FilaSimbolo> filas = new ArrayList<>();

        public void cargarDesdeTabla(TablaSimbolos tabla) {
            filas.clear();
            if (tabla != null && tabla.getAmbitoGlobal() != null) {
                recolectarSimbolosDeAmbito(tabla.getAmbitoGlobal());
            }
            fireTableDataChanged();
        }

        private void recolectarSimbolosDeAmbito(Ambito ambito) {
            if (ambito == null) return;
            String nombreAmbito = ambito.getNombre();

            List<Simbolo> simbolos = new ArrayList<>(ambito.getTabla().values());
            simbolos.addAll(ambito.getFunciones().values());
            for (Simbolo s : simbolos) {
                String tipoStr = s.getTipo() != null ? s.getTipo().getTypeName() : "-";
                String rolStr = s.getRol() != null ? s.getRol().name() : "-";
                String offsetStr = String.valueOf(s.getDireccionRelativa());
                String globalStr = s.esGlobal() ? "SÍ" : "NO";

                boolean esClase = s.getRol() == RolSimbolo.CLASE;
                if (esClase && s.getSuperClase() != null) {
                    tipoStr = tipoStr + " extends " + s.getSuperClase().getIdentificador();
                }
                String accesoStr = esClase || s.esMiembroDeObjeto() ? s.getModificador().getPalabra() : "-";
                // un atributo copiado del padre dentro del ambito de la clase hija
                if (s.esMiembroDeObjeto() && s.getClaseDuenia() != null
                        && !nombreAmbito.equals("Clase_" + s.getClaseDuenia())) {
                    rolStr = "ATRIBUTO HEREDADO DE " + s.getClaseDuenia();
                }

                filas.add(new FilaSimbolo(s.getIdentificador(), tipoStr, rolStr, accesoStr, nombreAmbito, offsetStr, globalStr));

                // Si es estructura o clase, listar sus miembros
                if (!s.getMiembros().isEmpty()) {
                    for (Simbolo m : s.getMiembros().values()) {
                        String tipoM = m.getTipo() != null ? m.getTipo().getTypeName() : "-";
                        boolean heredado = esClase && m.getClaseDuenia() != null
                                && !m.getClaseDuenia().equals(s.getIdentificador());
                        filas.add(new FilaSimbolo(s.getIdentificador() + "." + m.getIdentificador(),
                                tipoM, heredado ? "CAMPO (DE " + m.getClaseDuenia() + ")" : "CAMPO",
                                esClase ? m.getModificador().getPalabra() : "-",
                                nombreAmbito + "::" + s.getIdentificador(),
                                String.valueOf(m.getDireccionRelativa()), "NO"));
                    }
                }
                if (esClase) {
                    for (Simbolo constructor : s.getConstructores()) {
                        filas.add(new FilaSimbolo(s.getIdentificador() + "." + constructor.getFirma(), "-", "CONSTRUCTOR",
                                constructor.getModificador().getPalabra(), nombreAmbito + "::" + s.getIdentificador(), "-", "NO"));
                    }
                    for (Simbolo metodo : s.getMetodos()) {
                        filas.add(new FilaSimbolo(s.getIdentificador() + "." + metodo.getFirma(),
                                metodo.getTipo() != null ? metodo.getTipo().getTypeName() : "void",
                                metodo.esOverride() ? "METODO @Override" : "METODO",
                                metodo.getModificador().getPalabra(), nombreAmbito + "::" + s.getIdentificador(), "-", "NO"));
                    }
                }
            }

            for (Ambito hijo : ambito.getHijos()) {
                recolectarSimbolosDeAmbito(hijo);
            }
        }

        public void limpiar() {
            filas.clear();
            fireTableDataChanged();
        }

        @Override
        public int getRowCount() {
            return filas.size();
        }

        @Override
        public int getColumnCount() {
            return COLUMNAS.length;
        }

        @Override
        public String getColumnName(int column) {
            return COLUMNAS[column];
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            FilaSimbolo f = filas.get(rowIndex);
            return switch (columnIndex) {
                case 0 -> rowIndex + 1;
                case 1 -> f.nombre;
                case 2 -> f.tipo;
                case 3 -> f.rol;
                case 4 -> f.acceso;
                case 5 -> f.ambito;
                case 6 -> f.offset;
                case 7 -> f.esGlobal;
                default -> "";
            };
        }
    }
}
