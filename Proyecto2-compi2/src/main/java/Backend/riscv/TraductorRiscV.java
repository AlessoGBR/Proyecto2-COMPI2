package Backend.riscv;

import Backend.c3d.ControladorC3D;
import Backend.c3d.Cuarteta;
import Backend.c3d.OperadorC3D;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

public class TraductorRiscV {

    public static final int CELDAS_STACK = 100000;
    public static final int CELDAS_HEAP = 100000;
    public static final int BYTES_CELDA = 8;

    private static final Pattern TEMPORAL = Pattern.compile("T\\d+");
    private static final Pattern ENTERO = Pattern.compile("-?\\d+");
    private static final Pattern DECIMAL = Pattern.compile("-?\\d+(\\.\\d+)?([eE][-+]?\\d+)?");

    private final StringBuilder codigo = new StringBuilder();
    private final Map<String, String> constantes = new LinkedHashMap<>();

    // MARCO DE LA FUNCION
    private final Map<String, Integer> desplazamientoTemporal = new LinkedHashMap<>();
    private int tamanioMarco;
    private String etiquetaFin;
    private int contadorEtiquetas;
    // LECTURA SI SE REQUIERE
    private boolean usaLectura;

    public static String traducir(ControladorC3D controlador) {
        if (controlador == null) {
            return "";
        }
        return new TraductorRiscV().generar(controlador.getCuartetas(), controlador.getTamanioAreaGlobal());
    }

    private String generar(List<Cuarteta> cuartetas, int areaGlobal) {
        escribirArranque();
        escribirFunciones(cuartetas);
        if (usaLectura) {
            codigo.append(RUTINA_LECTURA);
        }

        StringBuilder programa = new StringBuilder();
        escribirDatos(programa, areaGlobal);
        programa.append(codigo);
        return programa.toString();
    }

    // SEGMENTO DE DATOS

    private void escribirDatos(StringBuilder sb, int areaGlobal) {
        sb.append(".data\n");
        sb.append(".align 3\n");
        sb.append("rt_P:        .double ").append(areaGlobal).append("\n");
        sb.append("rt_H:        .double 1\n");
        sb.append("rt_THIS:     .double 0\n");
        for (Map.Entry<String, String> constante : constantes.entrySet()) {
            sb.append(constante.getValue()).append(": .double ").append(constante.getKey()).append("\n");
        }
        if (usaLectura) {
            sb.append("rt_lectura_pos: .word 0\n");
            sb.append("rt_lectura:     .space 1024\n");
        }
        sb.append('\n');
    }

    // ARRANQUE

    private void escribirArranque() {
        codigo.append(".text\n");
        codigo.append(".globl main\n");
        codigo.append("main:\n");
        instruccion("andi sp, sp, -16");
        comentario("stack[] del C3D: " + CELDAS_STACK + " celdas reservadas en la pila");
        instruccion("li t0, " + (CELDAS_STACK * BYTES_CELDA));
        instruccion("sub sp, sp, t0");
        instruccion("mv s1, sp");
        comentario("heap[] del C3D: " + CELDAS_HEAP + " celdas de memoria dinamica (sbrk)");
        instruccion("li a0, " + (CELDAS_HEAP * BYTES_CELDA));
        instruccion("li a7, 9");
        instruccion("ecall");
        instruccion("mv s2, a0");
        instruccion("jal ra, main_programa");
        instruccion("li a7, 10");
        instruccion("ecall");
        codigo.append("\n");
    }

    // FUNCIONES

    private void escribirFunciones(List<Cuarteta> cuartetas) {
        int i = 0;
        while (i < cuartetas.size()) {
            Cuarteta actual = cuartetas.get(i);
            if (actual.getOperador() != OperadorC3D.INICIO_FUNCION) {
                comentario("(fuera de funcion) " + actual.aFormatoC3D().trim());
                i++;
                continue;
            }
            int fin = i + 1;
            while (fin < cuartetas.size() && cuartetas.get(fin).getOperador() != OperadorC3D.FIN_FUNCION) {
                fin++;
            }
            escribirFuncion(actual.getResultado(), cuartetas.subList(i + 1, Math.min(fin, cuartetas.size())));
            i = fin + 1;
        }
    }

    private void escribirFuncion(String nombre, List<Cuarteta> cuerpo) {
        desplazamientoTemporal.clear();
        Set<String> temporales = new LinkedHashSet<>();
        for (Cuarteta c : cuerpo) {
            agregarSiTemporal(c.getArg1(), temporales);
            agregarSiTemporal(c.getArg2(), temporales);
            agregarSiTemporal(c.getResultado(), temporales);
        }
        int desplazamiento = BYTES_CELDA;
        for (String temporal : temporales) {
            desplazamientoTemporal.put(temporal, desplazamiento);
            desplazamiento += BYTES_CELDA;
        }
        tamanioMarco = (desplazamiento + 15) / 16 * 16;
        etiquetaFin = "rt_fin_" + nombre;

        codigo.append("\n# ---------------- ").append(nombre).append(" (marco: ")
                .append(tamanioMarco).append(" bytes, ").append(temporales.size()).append(" temporales)\n");
        codigo.append(nombre).append(":\n");
        moverSp(-tamanioMarco);
        instruccion("sw ra, 0(sp)");

        for (Cuarteta c : cuerpo) {
            traducirCuarteta(c);
        }

        codigo.append(etiquetaFin).append(":\n");
        instruccion("lw ra, 0(sp)");
        moverSp(tamanioMarco);
        instruccion("ret");
    }

    private void agregarSiTemporal(String operando, Set<String> destino) {
        if (operando != null && TEMPORAL.matcher(operando).matches()) {
            destino.add(operando);
        }
    }

    private void moverSp(int bytes) {
        if (bytes >= -2048 && bytes <= 2047) {
            instruccion("addi sp, sp, " + bytes);
        } else {
            instruccion("li t0, " + bytes);
            instruccion("add sp, sp, t0");
        }
    }

    // CUARTETAS

    private void traducirCuarteta(Cuarteta c) {
        String a = c.getArg1();
        String b = c.getArg2();
        String r = c.getResultado();
        OperadorC3D op = c.getOperador();

        if (op != OperadorC3D.ETIQUETA && op != OperadorC3D.COMENTARIO) {
            comentario(c.aFormatoC3D().trim());
        }

        switch (op) {
            case SUMA -> operacionDecimal("fadd.d", a, b, r);
            case RESTA -> operacionDecimal("fsub.d", a, b, r);
            case MULT -> operacionDecimal("fmul.d", a, b, r);
            case DIV -> operacionDecimal("fdiv.d", a, b, r);
            case DIV_ENT, MOD -> {
                cargar(a, "ft0");
                cargar(b, "ft1");
                instruccion("fcvt.w.d t0, ft0, rtz");
                instruccion("fcvt.w.d t1, ft1, rtz");
                instruccion((op == OperadorC3D.MOD ? "rem" : "div") + " t2, t0, t1");
                instruccion("fcvt.d.w ft2, t2");
                guardar(r, "ft2");
            }
            case A_ENTERO -> {
                cargar(a, "ft0");
                instruccion("fcvt.w.d t0, ft0, rtz");
                instruccion("fcvt.d.w ft1, t0");
                guardar(r, "ft1");
            }
            case ASIG -> {
                cargar(a, "ft0");
                guardar(r, "ft0");
            }
            case GOTO -> instruccion("j " + r);
            case IF_EQ -> saltoComparacion("feq.d t0, ft0, ft1", false, a, b, r);
            case IF_NEQ -> saltoComparacion("feq.d t0, ft0, ft1", true, a, b, r);
            case IF_LT -> saltoComparacion("flt.d t0, ft0, ft1", false, a, b, r);
            case IF_LE -> saltoComparacion("fle.d t0, ft0, ft1", false, a, b, r);
            case IF_GT -> saltoComparacion("flt.d t0, ft1, ft0", false, a, b, r);
            case IF_GE -> saltoComparacion("fle.d t0, ft1, ft0", false, a, b, r);
            case IF_FALSE -> {
                cargar(a, "ft0");
                instruccion("fcvt.d.w ft1, zero");
                instruccion("feq.d t0, ft0, ft1");
                saltarSiT0(r, true);
            }
            case ACCESO_STACK -> leerMemoria("s1", a, r);
            case ASIG_STACK -> escribirMemoria("s1", r, a);
            case ACCESO_HEAP -> leerMemoria("s2", a, r);
            case ASIG_HEAP -> escribirMemoria("s2", r, a);
            case ETIQUETA -> codigo.append(r).append(":\n");
            case COMENTARIO -> comentario(a);
            case CALL -> instruccion("jal ra, " + r);
            case PARAM -> {
            }
            case RETURN -> instruccion("j " + etiquetaFin);
            case PRINT_INT -> {
                cargar(a, "ft0");
                instruccion("fcvt.w.d a0, ft0");
                instruccion("li a7, 1");
                instruccion("ecall");
            }
            case PRINT_CHAR -> {
                cargar(a, "ft0");
                instruccion("fcvt.w.d a0, ft0");
                instruccion("li a7, 11");
                instruccion("ecall");
            }
            case LEER_CARACTER -> {
                usaLectura = true;
                instruccion("jal ra, rt_leer_caracter");
                instruccion("fcvt.d.w ft0, a0");
                guardar(r, "ft0");
            }
            case SALIR -> {
                cargar(a, "ft0");
                instruccion("fcvt.w.d a0, ft0");
                instruccion("li a7, 93");
                instruccion("ecall");
            }
            case INICIO_FUNCION, FIN_FUNCION -> comentario("(funcion anidada no esperada) " + r);
        }
    }

    private void operacionDecimal(String instruccion, String a, String b, String r) {
        cargar(a, "ft0");
        cargar(b, "ft1");
        instruccion(instruccion + " ft2, ft0, ft1");
        guardar(r, "ft2");
    }

    private void saltoComparacion(String comparacion, boolean negada, String a, String b, String destino) {
        cargar(a, "ft0");
        cargar(b, "ft1");
        instruccion(comparacion);
        saltarSiT0(destino, !negada);
    }

    private void saltarSiT0(String destino, boolean cuandoVerdadero) {
        String continuar = "rt_s" + (contadorEtiquetas++);
        instruccion((cuandoVerdadero ? "beqz" : "bnez") + " t0, " + continuar);
        instruccion("j " + destino);
        codigo.append(continuar).append(":\n");
    }

    private void leerMemoria(String base, String indice, String destino) {
        direccionCelda(base, indice);
        instruccion("fld ft1, 0(t0)");
        guardar(destino, "ft1");
    }

    private void escribirMemoria(String base, String indice, String valor) {
        direccionCelda(base, indice);
        cargar(valor, "ft1");
        instruccion("fsd ft1, 0(t0)");
    }

    private void direccionCelda(String base, String indice) {
        cargar(indice, "ft0");
        instruccion("fcvt.w.d t0, ft0");
        instruccion("slli t0, t0, 3");
        instruccion("add t0, " + base + ", t0");
    }

    // OPERANDOS
    // t6 COMO AUXILIAR
    private void cargar(String operando, String registro) {
        if (operando == null || operando.isEmpty()) {
            instruccion("fcvt.d.w " + registro + ", zero");
            return;
        }
        Integer desplazamiento = desplazamientoTemporal.get(operando);
        if (desplazamiento != null) {
            accesoMarco("fld", registro, desplazamiento);
            return;
        }
        String global = etiquetaGlobal(operando);
        if (global != null) {
            instruccion("la t6, " + global);
            instruccion("fld " + registro + ", 0(t6)");
            return;
        }
        if (ENTERO.matcher(operando).matches() && cabeEnEntero(operando)) {
            if (operando.equals("0")) {
                instruccion("fcvt.d.w " + registro + ", zero");
            } else {
                instruccion("li t6, " + operando);
                instruccion("fcvt.d.w " + registro + ", t6");
            }
            return;
        }
        if (DECIMAL.matcher(operando).matches()) {
            String etiqueta = constantes.computeIfAbsent(operando, k -> "rt_k" + constantes.size());
            instruccion("la t6, " + etiqueta);
            instruccion("fld " + registro + ", 0(t6)");
            return;
        }
        comentario("OPERANDO NO RECONOCIDO: " + operando + " (se usa 0)");
        instruccion("fcvt.d.w " + registro + ", zero");
    }

    private void guardar(String destino, String registro) {
        Integer desplazamiento = desplazamientoTemporal.get(destino);
        if (desplazamiento != null) {
            accesoMarco("fsd", registro, desplazamiento);
            return;
        }
        String global = etiquetaGlobal(destino);
        if (global != null) {
            instruccion("la t6, " + global);
            instruccion("fsd " + registro + ", 0(t6)");
            return;
        }
        comentario("DESTINO NO RECONOCIDO: " + destino);
    }

    private void accesoMarco(String instruccion, String registro, int desplazamiento) {
        if (desplazamiento <= 2047) {
            instruccion(instruccion + " " + registro + ", " + desplazamiento + "(sp)");
        } else {
            instruccion("li t6, " + desplazamiento);
            instruccion("add t6, sp, t6");
            instruccion(instruccion + " " + registro + ", 0(t6)");
        }
    }

    private String etiquetaGlobal(String operando) {
        return switch (operando) {
            case "P" -> "rt_P";
            case "H" -> "rt_H";
            case "THIS" -> "rt_THIS";
            default -> null;
        };
    }

    private boolean cabeEnEntero(String texto) {
        try {
            Integer.parseInt(texto);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void instruccion(String texto) {
        codigo.append("    ").append(texto).append('\n');
    }

    private void comentario(String texto) {
        codigo.append("    # ").append(texto.replace('\n', ' ')).append('\n');
    }

    //ECTURA DEL TECLADO
    private static final String RUTINA_LECTURA = """
            # LECTURA DEL TECLADO:
            rt_leer_caracter:
                la t0, rt_lectura_pos
                lw t1, 0(t0)
                bnez t1, rt_lc_tomar
            rt_lc_nueva_linea:
                la a0, rt_lectura
                li a1, 1024
                li a7, 8
                ecall
                la t1, rt_lectura
                lbu t2, 0(t1)
                bnez t2, rt_lc_tomar
                sw zero, 0(t0)
                li a0, -1
                ret
            rt_lc_tomar:
                lbu a0, 0(t1)
                beqz a0, rt_lc_nueva_linea
                addi t1, t1, 1
                sw t1, 0(t0)
                ret
            """;
}
