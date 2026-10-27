package Backend.riscv;

import Backend.riscv.InstruccionRiscV.Operacion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EnsambladorRiscV {

    private static final Pattern ETIQUETA = Pattern.compile("^([A-Za-z_.$][A-Za-z0-9_.$]*)\\s*:(.*)$");
    private static final Pattern MEMORIA = Pattern.compile("^(.*)\\(\\s*([A-Za-z0-9]+)\\s*\\)$");
    private static final Map<String, Integer> REGISTROS = new HashMap<>();
    private static final Map<String, Integer> REGISTROS_F = new HashMap<>();
    private static final Map<String, Integer> REDONDEOS = Map.of(
            "rne", InstruccionRiscV.RNE, "rtz", InstruccionRiscV.RTZ, "rdn", InstruccionRiscV.RDN,
            "rup", InstruccionRiscV.RUP, "rmm", InstruccionRiscV.RMM, "dyn", InstruccionRiscV.DYN);

    static {
        String[] abi = {"zero", "ra", "sp", "gp", "tp", "t0", "t1", "t2", "s0", "s1",
                "a0", "a1", "a2", "a3", "a4", "a5", "a6", "a7",
                "s2", "s3", "s4", "s5", "s6", "s7", "s8", "s9", "s10", "s11", "t3", "t4", "t5", "t6"};
        for (int i = 0; i < 32; i++) {
            REGISTROS.put("x" + i, i);
            REGISTROS.put(abi[i], i);
            REGISTROS_F.put("f" + i, i);
        }
        REGISTROS.put("fp", 8);
        String[] abiF = {"ft0", "ft1", "ft2", "ft3", "ft4", "ft5", "ft6", "ft7", "fs0", "fs1",
                "fa0", "fa1", "fa2", "fa3", "fa4", "fa5", "fa6", "fa7",
                "fs2", "fs3", "fs4", "fs5", "fs6", "fs7", "fs8", "fs9", "fs10", "fs11",
                "ft8", "ft9", "ft10", "ft11"};
        for (int i = 0; i < 32; i++) {
            REGISTROS_F.put(abiF[i], i);
        }
    }

    // INSTRUCCION LEIDA
    private record Pendiente(int indice, int linea, String mnemonico, List<String> operandos, String fuente) {
    }

    private record PalabraPendiente(int posicion, String etiqueta, int linea) {
    }

    private final List<ErrorRiscV> errores = new ArrayList<>();
    private final Map<String, Integer> etiquetas = new LinkedHashMap<>();
    private final List<Pendiente> pendientes = new ArrayList<>();
    private final List<PalabraPendiente> palabrasPendientes = new ArrayList<>();
    private byte[] datos = new byte[1024];
    private int tamanioDatos = 0;

    public List<ErrorRiscV> getErrores() {
        return errores;
    }

    // NULL SI HAY ERRORES
    public ProgramaRiscV ensamblar(String fuente) {
        errores.clear();
        etiquetas.clear();
        pendientes.clear();
        palabrasPendientes.clear();
        tamanioDatos = 0;

        primeraPasada(fuente);

        List<InstruccionRiscV> instrucciones = new ArrayList<>(pendientes.size());
        for (Pendiente p : pendientes) {
            try {
                instrucciones.add(traducir(p));
            } catch (ErrorRiscV e) {
                errores.add(e);
                instrucciones.add(new InstruccionRiscV(Operacion.ADDI, 0, 0, 0, 0, 0, p.linea(), p.fuente()));
            }
        }
        for (PalabraPendiente palabra : palabrasPendientes) {
            Integer direccion = etiquetas.get(palabra.etiqueta());
            if (direccion == null) {
                errores.add(new ErrorRiscV("ETIQUETA NO DEFINIDA: " + palabra.etiqueta(), palabra.linea()));
            } else {
                escribirEntero(palabra.posicion(), direccion, 4);
            }
        }
        if (!errores.isEmpty()) {
            return null;
        }

        Integer main = etiquetas.get("main");
        int entrada = main != null ? (main - ProgramaRiscV.BASE_TEXTO) / 4 : 0;
        byte[] imagen = new byte[tamanioDatos];
        System.arraycopy(datos, 0, imagen, 0, tamanioDatos);
        return new ProgramaRiscV(instrucciones, imagen, etiquetas, entrada);
    }

    // PRIMERA PASADA 

    private void primeraPasada(String fuente) {
        boolean enTexto = true;
        String[] lineas = fuente.split("\r?\n", -1);
        for (int n = 0; n < lineas.length; n++) {
            int numero = n + 1;
            String linea = quitarComentario(lineas[n]).trim();
            try {
                Matcher m;
                while ((m = ETIQUETA.matcher(linea)).matches()) {
                    String nombre = m.group(1);
                    int direccion = enTexto
                            ? ProgramaRiscV.BASE_TEXTO + 4 * pendientes.size()
                            : ProgramaRiscV.BASE_DATOS + tamanioDatos;
                    if (etiquetas.putIfAbsent(nombre, direccion) != null) {
                        throw new ErrorRiscV("ETIQUETA DUPLICADA: " + nombre, numero);
                    }
                    linea = m.group(2).trim();
                }
                if (linea.isEmpty()) {
                    continue;
                }

                String[] partes = linea.split("\\s+", 2);
                String palabra = partes[0].toLowerCase(Locale.ROOT);
                String resto = partes.length > 1 ? partes[1].trim() : "";

                if (palabra.startsWith(".")) {
                    switch (palabra) {
                        case ".text" -> enTexto = true;
                        case ".data" -> enTexto = false;
                        case ".globl", ".global", ".extern", ".section" -> {
                        }
                        default -> {
                            if (enTexto && !palabra.equals(".align")) {
                                throw new ErrorRiscV("LA DIRECTIVA " + palabra + " SOLO VA EN .data", numero);
                            }
                            if (!enTexto) {
                                directivaDeDatos(palabra, resto, numero);
                            }
                        }
                    }
                    continue;
                }

                if (!enTexto) {
                    throw new ErrorRiscV("INSTRUCCION FUERA DEL SEGMENTO .text: " + linea, numero);
                }
                pendientes.add(new Pendiente(pendientes.size(), numero, palabra, separarOperandos(resto), linea));
            } catch (ErrorRiscV e) {
                errores.add(e);
            }
        }
    }

    private void directivaDeDatos(String directiva, String argumentos, int linea) throws ErrorRiscV {
        switch (directiva) {
            case ".align" -> {
                int potencia = (int) numero(argumentos, linea);
                alinear(1 << Math.max(0, Math.min(potencia, 12)));
            }
            case ".space" -> reservar((int) numero(argumentos, linea), linea);
            case ".byte" -> {
                for (String valor : separarOperandos(argumentos)) {
                    agregarEntero((int) numero(valor, linea), 1);
                }
            }
            case ".half" -> {
                alinear(2);
                for (String valor : separarOperandos(argumentos)) {
                    agregarEntero((int) numero(valor, linea), 2);
                }
            }
            case ".word" -> {
                alinear(4);
                for (String valor : separarOperandos(argumentos)) {
                    if (esEtiqueta(valor)) {
                        palabrasPendientes.add(new PalabraPendiente(tamanioDatos, valor, linea));
                        agregarEntero(0, 4);
                    } else {
                        agregarEntero((int) numero(valor, linea), 4);
                    }
                }
            }
            case ".float" -> {
                alinear(4);
                for (String valor : separarOperandos(argumentos)) {
                    agregarEntero(Float.floatToRawIntBits((float) decimal(valor, linea)), 4);
                }
            }
            case ".double" -> {
                alinear(8);
                for (String valor : separarOperandos(argumentos)) {
                    long bits = Double.doubleToRawLongBits(decimal(valor, linea));
                    agregarEntero((int) bits, 4);
                    agregarEntero((int) (bits >>> 32), 4);
                }
            }
            case ".ascii", ".asciz", ".string" -> {
                String texto = cadena(argumentos, linea);
                for (int i = 0; i < texto.length(); i++) {
                    agregarEntero(texto.charAt(i) & 0xFF, 1);
                }
                if (!directiva.equals(".ascii")) {
                    agregarEntero(0, 1);
                }
            }
            default -> throw new ErrorRiscV("DIRECTIVA NO SOPORTADA: " + directiva, linea);
        }
    }

    // SEGUNDA PASADA

    private InstruccionRiscV traducir(Pendiente p) throws ErrorRiscV {
        List<String> o = p.operandos();
        int linea = p.linea();
        String m = p.mnemonico();
        int direccionActual = ProgramaRiscV.BASE_TEXTO + 4 * p.indice();

        return switch (m) {
            // RV32I
            case "lui" -> {
                cantidad(o, 2, p);
                int valor = (int) numero(o.get(1), linea);
                if (valor < 0 || valor > 0xFFFFF) {
                    throw new ErrorRiscV("EL INMEDIATO DE lui DEBE ESTAR ENTRE 0 Y 0xFFFFF", linea);
                }
                yield nueva(Operacion.LUI, reg(o.get(0), linea), 0, 0, valor << 12, p);
            }
            case "auipc" -> {
                cantidad(o, 2, p);
                int valor = (int) numero(o.get(1), linea);
                yield nueva(Operacion.AUIPC, reg(o.get(0), linea), 0, 0, direccionActual + (valor << 12), p);
            }
            case "jal" -> {
                if (o.size() == 1) {
                    yield nueva(Operacion.JAL, 1, 0, 0, destino(o.get(0), linea), p);
                }
                cantidad(o, 2, p);
                yield nueva(Operacion.JAL, reg(o.get(0), linea), 0, 0, destino(o.get(1), linea), p);
            }
            case "jalr" -> {
                if (o.size() == 1) {
                    yield nueva(Operacion.JALR, 1, reg(o.get(0), linea), 0, 0, p);
                }
                if (o.size() == 2) {
                    int[] mem = memoria(o.get(1), linea);
                    yield nueva(Operacion.JALR, reg(o.get(0), linea), mem[1], 0, mem[0], p);
                }
                cantidad(o, 3, p);
                yield nueva(Operacion.JALR, reg(o.get(0), linea), reg(o.get(1), linea), 0, inmediato12(o.get(2), linea), p);
            }
            case "beq", "bne", "blt", "bge", "bltu", "bgeu" -> {
                cantidad(o, 3, p);
                yield nueva(Operacion.valueOf(m.toUpperCase(Locale.ROOT)), 0, reg(o.get(0), linea), reg(o.get(1), linea),
                        destino(o.get(2), linea), p);
            }
            case "lb", "lh", "lw", "lbu", "lhu" -> {
                cantidad(o, 2, p);
                int[] mem = memoria(o.get(1), linea);
                yield nueva(Operacion.valueOf(m.toUpperCase(Locale.ROOT)), reg(o.get(0), linea), mem[1], 0, mem[0], p);
            }
            case "sb", "sh", "sw" -> {
                cantidad(o, 2, p);
                int[] mem = memoria(o.get(1), linea);
                yield nueva(Operacion.valueOf(m.toUpperCase(Locale.ROOT)), 0, mem[1], reg(o.get(0), linea), mem[0], p);
            }
            case "addi", "slti", "sltiu", "xori", "ori", "andi" -> {
                cantidad(o, 3, p);
                yield nueva(Operacion.valueOf(m.toUpperCase(Locale.ROOT)), reg(o.get(0), linea), reg(o.get(1), linea), 0,
                        inmediato12(o.get(2), linea), p);
            }
            case "slli", "srli", "srai" -> {
                cantidad(o, 3, p);
                int desplazamiento = (int) numero(o.get(2), linea);
                if (desplazamiento < 0 || desplazamiento > 31) {
                    throw new ErrorRiscV("EL DESPLAZAMIENTO DEBE ESTAR ENTRE 0 Y 31", linea);
                }
                yield nueva(Operacion.valueOf(m.toUpperCase(Locale.ROOT)), reg(o.get(0), linea), reg(o.get(1), linea), 0,
                        desplazamiento, p);
            }
            case "add", "sub", "sll", "slt", "sltu", "xor", "srl", "sra", "or", "and",
                 "mul", "mulh", "mulhsu", "mulhu", "div", "divu", "rem", "remu" -> {
                cantidad(o, 3, p);
                yield nueva(Operacion.valueOf(m.toUpperCase(Locale.ROOT)), reg(o.get(0), linea), reg(o.get(1), linea),
                        reg(o.get(2), linea), 0, p);
            }
            case "ecall" -> nueva(Operacion.ECALL, 0, 0, 0, 0, p);
            case "ebreak" -> nueva(Operacion.EBREAK, 0, 0, 0, 0, p);

            // RV32D
            case "fld" -> {
                cantidad(o, 2, p);
                int[] mem = memoria(o.get(1), linea);
                yield nueva(Operacion.FLD, freg(o.get(0), linea), mem[1], 0, mem[0], p);
            }
            case "fsd" -> {
                cantidad(o, 2, p);
                int[] mem = memoria(o.get(1), linea);
                yield nueva(Operacion.FSD, 0, mem[1], freg(o.get(0), linea), mem[0], p);
            }
            case "fadd.d", "fsub.d", "fmul.d", "fdiv.d" -> {
                cantidadEntre(o, 3, 4, p);
                yield nuevaConRedondeo(operacionD(m), freg(o.get(0), linea), freg(o.get(1), linea),
                        freg(o.get(2), linea), redondeo(o, 3, linea), p);
            }
            case "fsqrt.d" -> {
                cantidadEntre(o, 2, 3, p);
                yield nuevaConRedondeo(Operacion.FSQRT_D, freg(o.get(0), linea), freg(o.get(1), linea), 0,
                        redondeo(o, 2, linea), p);
            }
            case "fmin.d", "fmax.d", "fsgnj.d", "fsgnjn.d", "fsgnjx.d" -> {
                cantidad(o, 3, p);
                yield nueva(operacionD(m), freg(o.get(0), linea), freg(o.get(1), linea), freg(o.get(2), linea), 0, p);
            }
            case "feq.d", "flt.d", "fle.d" -> {
                cantidad(o, 3, p);
                yield nueva(operacionD(m), reg(o.get(0), linea), freg(o.get(1), linea), freg(o.get(2), linea), 0, p);
            }
            case "fcvt.w.d", "fcvt.wu.d" -> {
                cantidadEntre(o, 2, 3, p);
                yield nuevaConRedondeo(m.equals("fcvt.w.d") ? Operacion.FCVT_W_D : Operacion.FCVT_WU_D,
                        reg(o.get(0), linea), freg(o.get(1), linea), 0, redondeo(o, 2, linea), p);
            }
            case "fcvt.d.w", "fcvt.d.wu" -> {
                cantidadEntre(o, 2, 3, p);
                yield nuevaConRedondeo(m.equals("fcvt.d.w") ? Operacion.FCVT_D_W : Operacion.FCVT_D_WU,
                        freg(o.get(0), linea), reg(o.get(1), linea), 0, redondeo(o, 2, linea), p);
            }

            case "li" -> {
                cantidad(o, 2, p);
                yield nueva(Operacion.LI, reg(o.get(0), linea), 0, 0, (int) numero(o.get(1), linea), p);
            }
            case "la" -> {
                cantidad(o, 2, p);
                yield nueva(Operacion.LI, reg(o.get(0), linea), 0, 0, destino(o.get(1), linea), p);
            }
            case "mv" -> {
                cantidad(o, 2, p);
                yield nueva(Operacion.ADDI, reg(o.get(0), linea), reg(o.get(1), linea), 0, 0, p);
            }
            case "not" -> {
                cantidad(o, 2, p);
                yield nueva(Operacion.XORI, reg(o.get(0), linea), reg(o.get(1), linea), 0, -1, p);
            }
            case "neg" -> {
                cantidad(o, 2, p);
                yield nueva(Operacion.SUB, reg(o.get(0), linea), 0, reg(o.get(1), linea), 0, p);
            }
            case "seqz" -> {
                cantidad(o, 2, p);
                yield nueva(Operacion.SLTIU, reg(o.get(0), linea), reg(o.get(1), linea), 0, 1, p);
            }
            case "snez" -> {
                cantidad(o, 2, p);
                yield nueva(Operacion.SLTU, reg(o.get(0), linea), 0, reg(o.get(1), linea), 0, p);
            }
            case "sltz" -> {
                cantidad(o, 2, p);
                yield nueva(Operacion.SLT, reg(o.get(0), linea), reg(o.get(1), linea), 0, 0, p);
            }
            case "sgtz" -> {
                cantidad(o, 2, p);
                yield nueva(Operacion.SLT, reg(o.get(0), linea), 0, reg(o.get(1), linea), 0, p);
            }
            case "nop" -> nueva(Operacion.ADDI, 0, 0, 0, 0, p);
            case "j", "tail" -> {
                cantidad(o, 1, p);
                yield nueva(Operacion.JAL, 0, 0, 0, destino(o.get(0), linea), p);
            }
            case "call" -> {
                cantidad(o, 1, p);
                yield nueva(Operacion.JAL, 1, 0, 0, destino(o.get(0), linea), p);
            }
            case "jr" -> {
                cantidad(o, 1, p);
                yield nueva(Operacion.JALR, 0, reg(o.get(0), linea), 0, 0, p);
            }
            case "ret" -> nueva(Operacion.JALR, 0, 1, 0, 0, p);
            case "beqz", "bnez", "blez", "bgez", "bltz", "bgtz" -> {
                cantidad(o, 2, p);
                int r = reg(o.get(0), linea);
                int destino = destino(o.get(1), linea);
                yield switch (m) {
                    case "beqz" -> nueva(Operacion.BEQ, 0, r, 0, destino, p);
                    case "bnez" -> nueva(Operacion.BNE, 0, r, 0, destino, p);
                    case "blez" -> nueva(Operacion.BGE, 0, 0, r, destino, p);
                    case "bgez" -> nueva(Operacion.BGE, 0, r, 0, destino, p);
                    case "bltz" -> nueva(Operacion.BLT, 0, r, 0, destino, p);
                    default -> nueva(Operacion.BLT, 0, 0, r, destino, p);
                };
            }
            case "bgt", "ble", "bgtu", "bleu" -> {
                cantidad(o, 3, p);
                Operacion base = switch (m) {
                    case "bgt" -> Operacion.BLT;
                    case "ble" -> Operacion.BGE;
                    case "bgtu" -> Operacion.BLTU;
                    default -> Operacion.BGEU;
                };
                yield nueva(base, 0, reg(o.get(1), linea), reg(o.get(0), linea), destino(o.get(2), linea), p);
            }
            case "fmv.d", "fneg.d", "fabs.d" -> {
                cantidad(o, 2, p);
                Operacion base = switch (m) {
                    case "fmv.d" -> Operacion.FSGNJ_D;
                    case "fneg.d" -> Operacion.FSGNJN_D;
                    default -> Operacion.FSGNJX_D;
                };
                int fuente = freg(o.get(1), linea);
                yield nueva(base, freg(o.get(0), linea), fuente, fuente, 0, p);
            }
            default -> throw new ErrorRiscV("INSTRUCCION NO SOPORTADA: " + m, linea);
        };
    }

    private Operacion operacionD(String mnemonico) {
        return Operacion.valueOf(mnemonico.toUpperCase(Locale.ROOT).replace('.', '_'));
    }

    private InstruccionRiscV nueva(Operacion op, int rd, int rs1, int rs2, int inmediato, Pendiente p) {
        return new InstruccionRiscV(op, rd, rs1, rs2, inmediato, InstruccionRiscV.DYN, p.linea(), p.fuente());
    }

    private InstruccionRiscV nuevaConRedondeo(Operacion op, int rd, int rs1, int rs2, int redondeo, Pendiente p) {
        return new InstruccionRiscV(op, rd, rs1, rs2, 0, redondeo, p.linea(), p.fuente());
    }

    // OPERANDOS 

    private void cantidad(List<String> operandos, int esperada, Pendiente p) throws ErrorRiscV {
        if (operandos.size() != esperada) {
            throw new ErrorRiscV(p.mnemonico() + " ESPERA " + esperada + " OPERANDO(S) Y RECIBIO " + operandos.size(), p.linea());
        }
    }

    private void cantidadEntre(List<String> operandos, int minimo, int maximo, Pendiente p) throws ErrorRiscV {
        if (operandos.size() < minimo || operandos.size() > maximo) {
            throw new ErrorRiscV(p.mnemonico() + " ESPERA ENTRE " + minimo + " Y " + maximo + " OPERANDOS", p.linea());
        }
    }

    private int reg(String texto, int linea) throws ErrorRiscV {
        Integer numero = REGISTROS.get(texto.trim().toLowerCase(Locale.ROOT));
        if (numero == null) {
            throw new ErrorRiscV("REGISTRO ENTERO NO VALIDO: " + texto, linea);
        }
        return numero;
    }

    private int freg(String texto, int linea) throws ErrorRiscV {
        Integer numero = REGISTROS_F.get(texto.trim().toLowerCase(Locale.ROOT));
        if (numero == null) {
            throw new ErrorRiscV("REGISTRO DE PUNTO FLOTANTE NO VALIDO: " + texto, linea);
        }
        return numero;
    }

    private int redondeo(List<String> operandos, int posicion, int linea) throws ErrorRiscV {
        if (operandos.size() <= posicion) {
            return InstruccionRiscV.DYN;
        }
        Integer modo = REDONDEOS.get(operandos.get(posicion).trim().toLowerCase(Locale.ROOT));
        if (modo == null) {
            throw new ErrorRiscV("MODO DE REDONDEO NO VALIDO: " + operandos.get(posicion), linea);
        }
        return modo;
    }

    private int[] memoria(String texto, int linea) throws ErrorRiscV {
        Matcher m = MEMORIA.matcher(texto.trim());
        if (!m.matches()) {
            throw new ErrorRiscV("SE ESPERABA UN OPERANDO DE MEMORIA desplazamiento(registro): " + texto, linea);
        }
        String desplazamiento = m.group(1).trim();
        int valor = desplazamiento.isEmpty() ? 0 : inmediato12(desplazamiento, linea);
        return new int[]{valor, reg(m.group(2), linea)};
    }

    private int inmediato12(String texto, int linea) throws ErrorRiscV {
        long valor = numero(texto, linea);
        if (valor < -2048 || valor > 2047) {
            throw new ErrorRiscV("EL INMEDIATO " + texto + " NO CABE EN 12 BITS (-2048 A 2047)", linea);
        }
        return (int) valor;
    }

    private int destino(String texto, int linea) throws ErrorRiscV {
        String etiqueta = texto.trim();
        Integer direccion = etiquetas.get(etiqueta);
        if (direccion == null) {
            throw new ErrorRiscV("ETIQUETA NO DEFINIDA: " + etiqueta, linea);
        }
        return direccion;
    }

    private boolean esEtiqueta(String texto) {
        String t = texto.trim();
        return !t.isEmpty() && (Character.isLetter(t.charAt(0)) || t.charAt(0) == '_');
    }

    private long numero(String texto, int linea) throws ErrorRiscV {
        String t = texto.trim();
        try {
            if (t.length() >= 3 && t.startsWith("'") && t.endsWith("'")) {
                String contenido = escapes(t.substring(1, t.length() - 1), linea);
                if (contenido.length() == 1) {
                    return contenido.charAt(0);
                }
            }
            boolean negativo = t.startsWith("-");
            String sinSigno = negativo || t.startsWith("+") ? t.substring(1) : t;
            long valor;
            if (sinSigno.toLowerCase(Locale.ROOT).startsWith("0x")) {
                valor = Long.parseLong(sinSigno.substring(2), 16);
            } else if (sinSigno.toLowerCase(Locale.ROOT).startsWith("0b")) {
                valor = Long.parseLong(sinSigno.substring(2), 2);
            } else {
                valor = Long.parseLong(sinSigno);
            }
            valor = negativo ? -valor : valor;
            if (valor < Integer.MIN_VALUE || valor > 0xFFFFFFFFL) {
                throw new ErrorRiscV("EL NUMERO " + texto + " NO CABE EN 32 BITS", linea);
            }
            return valor > Integer.MAX_VALUE ? (int) valor : valor;
        } catch (NumberFormatException e) {
            throw new ErrorRiscV("NUMERO NO VALIDO: " + texto, linea);
        }
    }

    private double decimal(String texto, int linea) throws ErrorRiscV {
        try {
            return Double.parseDouble(texto.trim());
        } catch (NumberFormatException e) {
            throw new ErrorRiscV("NUMERO DECIMAL NO VALIDO: " + texto, linea);
        }
    }

    private String cadena(String texto, int linea) throws ErrorRiscV {
        String t = texto.trim();
        if (t.length() < 2 || !t.startsWith("\"") || !t.endsWith("\"")) {
            throw new ErrorRiscV("SE ESPERABA UNA CADENA ENTRE COMILLAS: " + texto, linea);
        }
        return escapes(t.substring(1, t.length() - 1), linea);
    }

    private String escapes(String texto, int linea) throws ErrorRiscV {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < texto.length(); i++) {
            char c = texto.charAt(i);
            if (c != '\\') {
                sb.append(c);
                continue;
            }
            if (++i >= texto.length()) {
                throw new ErrorRiscV("SECUENCIA DE ESCAPE INCOMPLETA", linea);
            }
            char e = texto.charAt(i);
            sb.append(switch (e) {
                case 'n' -> '\n';
                case 't' -> '\t';
                case 'r' -> '\r';
                case '0' -> '\0';
                case 'b' -> '\b';
                case 'f' -> '\f';
                default -> e;
            });
        }
        return sb.toString();
    }

    private List<String> separarOperandos(String texto) {
        List<String> resultado = new ArrayList<>();
        if (texto == null || texto.isBlank()) {
            return resultado;
        }
        StringBuilder actual = new StringBuilder();
        boolean enComillas = false;
        char comilla = 0;
        for (int i = 0; i < texto.length(); i++) {
            char c = texto.charAt(i);
            if (enComillas) {
                actual.append(c);
                if (c == '\\' && i + 1 < texto.length()) {
                    actual.append(texto.charAt(++i));
                } else if (c == comilla) {
                    enComillas = false;
                }
            } else if (c == '"' || c == '\'') {
                enComillas = true;
                comilla = c;
                actual.append(c);
            } else if (c == ',') {
                resultado.add(actual.toString().trim());
                actual.setLength(0);
            } else {
                actual.append(c);
            }
        }
        resultado.add(actual.toString().trim());
        return resultado;
    }

    private String quitarComentario(String linea) {
        boolean enComillas = false;
        char comilla = 0;
        for (int i = 0; i < linea.length(); i++) {
            char c = linea.charAt(i);
            if (enComillas) {
                if (c == '\\') {
                    i++;
                } else if (c == comilla) {
                    enComillas = false;
                }
            } else if (c == '"' || c == '\'') {
                enComillas = true;
                comilla = c;
            } else if (c == '#') {
                return linea.substring(0, i);
            }
        }
        return linea;
    }

    // SEGMENTO DE DATOS 

    private void alinear(int bytes) {
        while (tamanioDatos % bytes != 0) {
            agregarEntero(0, 1);
        }
    }

    private void reservar(int bytes, int linea) throws ErrorRiscV {
        if (bytes < 0) {
            throw new ErrorRiscV(".space NO ACEPTA TAMANIOS NEGATIVOS", linea);
        }
        asegurarCapacidad(tamanioDatos + bytes);
        tamanioDatos += bytes;
    }

    private void agregarEntero(int valor, int bytes) {
        asegurarCapacidad(tamanioDatos + bytes);
        escribirEntero(tamanioDatos, valor, bytes);
        tamanioDatos += bytes;
    }

    private void escribirEntero(int posicion, int valor, int bytes) {
        for (int i = 0; i < bytes; i++) {
            datos[posicion + i] = (byte) (valor >>> (8 * i));
        }
    }

    private void asegurarCapacidad(int requerida) {
        if (requerida > datos.length) {
            byte[] nuevo = new byte[Math.max(requerida, datos.length * 2)];
            System.arraycopy(datos, 0, nuevo, 0, tamanioDatos);
            datos = nuevo;
        }
    }
}
