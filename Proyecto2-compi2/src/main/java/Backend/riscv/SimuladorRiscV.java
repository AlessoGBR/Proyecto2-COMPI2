package Backend.riscv;

import java.util.HashMap;
import java.util.Map;

public class SimuladorRiscV {

    public static final long LIMITE_INSTRUCCIONES = 1_000_000_000L;

    private final InstruccionRiscV[] codigo;
    private final int indiceEntrada;
    private final ConsolaRiscV consola;
    private final Memoria memoria = new Memoria();

    private final int[] x = new int[32];
    private final long[] f = new long[32];
    private int finHeap;

    private final StringBuilder salidaPendiente = new StringBuilder();
    private volatile boolean detenerSolicitado;
    private long limite = LIMITE_INSTRUCCIONES;
    private long instruccionesEjecutadas;

    public SimuladorRiscV(ProgramaRiscV programa, ConsolaRiscV consola) {
        this.codigo = programa.getInstrucciones().toArray(new InstruccionRiscV[0]);
        this.indiceEntrada = programa.getIndiceEntrada();
        this.consola = consola;

        byte[] datos = programa.getDatos();
        for (int i = 0; i < datos.length; i++) {
            memoria.escribirByte(ProgramaRiscV.BASE_DATOS + i, datos[i]);
        }
        int finDatos = ProgramaRiscV.BASE_DATOS + datos.length;
        finHeap = Math.max(ProgramaRiscV.BASE_HEAP, (finDatos + 7) & ~7);
        x[2] = ProgramaRiscV.PILA_INICIAL;
        x[3] = ProgramaRiscV.GP_INICIAL;
    }

    public void setLimite(long limite) {
        this.limite = limite > 0 ? limite : Long.MAX_VALUE;
    }

    // SE PUEDE LLAMAR EN OTRO HILO, BOTON DETENER
    public void detener() {
        detenerSolicitado = true;
    }

    public long getInstruccionesEjecutadas() {
        return instruccionesEjecutadas;
    }

    public ResultadoEjecucion ejecutar() {
        try {
            int codigoSalida = ciclo();
            vaciarSalida();
            return new ResultadoEjecucion(codigoSalida, instruccionesEjecutadas, null, false);
        } catch (Detencion d) {
            vaciarSalida();
            return new ResultadoEjecucion(-1, instruccionesEjecutadas, d.getMessage(), true);
        } catch (ErrorRiscV e) {
            vaciarSalida();
            return new ResultadoEjecucion(-1, instruccionesEjecutadas, e.toString(), false);
        }
    }

    private static final class Detencion extends Exception {
        Detencion(String mensaje) {
            super(mensaje);
        }
    }

    private static final class Salida extends Exception {
        final int codigo;

        Salida(int codigo) {
            super(null, null, false, false);
            this.codigo = codigo;
        }
    }

    // CICLO DE EJECUCION

    private int ciclo() throws ErrorRiscV, Detencion {
        InstruccionRiscV[] cod = codigo;
        int indice = indiceEntrada;
        try {
            while (true) {
                if (indice == cod.length) {
                    return 0;
                }
                if (indice < 0 || indice > cod.length) {
                    throw new ErrorRiscV("SALTO A UNA DIRECCION FUERA DEL CODIGO: 0x"
                            + Integer.toHexString(direccionDe(indice)), 0);
                }
                if (++instruccionesEjecutadas > limite) {
                    throw new Detencion("SE ALCANZO EL LIMITE DE " + limite + " INSTRUCCIONES (POSIBLE CICLO INFINITO)");
                }
                if ((instruccionesEjecutadas & 0xFFFF) == 0 && detenerSolicitado) {
                    throw new Detencion("EJECUCION DETENIDA POR EL USUARIO");
                }

                InstruccionRiscV in = cod[indice];
                int siguiente = indice + 1;
                try {
                    siguiente = paso(in, indice, siguiente);
                } catch (ErrorRiscV e) {
                    throw new ErrorRiscV(e.getMessage() + " EN: " + in.fuente, in.linea);
                }
                x[0] = 0;
                indice = siguiente;
            }
        } catch (Salida s) {
            return s.codigo;
        }
    }

    private int paso(InstruccionRiscV in, int indice, int siguiente) throws ErrorRiscV, Salida, Detencion {
        int rs1 = x[in.rs1];
        int rs2 = x[in.rs2];
        switch (in.operacion) {
            case LUI, LI -> x[in.rd] = in.inmediato;
            case AUIPC -> x[in.rd] = in.inmediato;
            case JAL -> {
                x[in.rd] = direccionDe(indice + 1);
                return indiceDe(in.inmediato);
            }
            case JALR -> {
                int destino = (rs1 + in.inmediato) & ~1;
                x[in.rd] = direccionDe(indice + 1);
                return indiceDe(destino);
            }
            case BEQ -> { if (rs1 == rs2) return indiceDe(in.inmediato); }
            case BNE -> { if (rs1 != rs2) return indiceDe(in.inmediato); }
            case BLT -> { if (rs1 < rs2) return indiceDe(in.inmediato); }
            case BGE -> { if (rs1 >= rs2) return indiceDe(in.inmediato); }
            case BLTU -> { if (Integer.compareUnsigned(rs1, rs2) < 0) return indiceDe(in.inmediato); }
            case BGEU -> { if (Integer.compareUnsigned(rs1, rs2) >= 0) return indiceDe(in.inmediato); }

            case LB -> x[in.rd] = (byte) memoria.leerByte(direccion(rs1 + in.inmediato, 1));
            case LBU -> x[in.rd] = memoria.leerByte(direccion(rs1 + in.inmediato, 1)) & 0xFF;
            case LH -> x[in.rd] = (short) memoria.leer(direccion(rs1 + in.inmediato, 2), 2);
            case LHU -> x[in.rd] = (int) memoria.leer(direccion(rs1 + in.inmediato, 2), 2) & 0xFFFF;
            case LW -> x[in.rd] = (int) memoria.leer(direccion(rs1 + in.inmediato, 4), 4);
            case SB -> memoria.escribirByte(direccion(rs1 + in.inmediato, 1), (byte) rs2);
            case SH -> memoria.escribir(direccion(rs1 + in.inmediato, 2), rs2, 2);
            case SW -> memoria.escribir(direccion(rs1 + in.inmediato, 4), rs2, 4);

            case ADDI -> x[in.rd] = rs1 + in.inmediato;
            case SLTI -> x[in.rd] = rs1 < in.inmediato ? 1 : 0;
            case SLTIU -> x[in.rd] = Integer.compareUnsigned(rs1, in.inmediato) < 0 ? 1 : 0;
            case XORI -> x[in.rd] = rs1 ^ in.inmediato;
            case ORI -> x[in.rd] = rs1 | in.inmediato;
            case ANDI -> x[in.rd] = rs1 & in.inmediato;
            case SLLI -> x[in.rd] = rs1 << in.inmediato;
            case SRLI -> x[in.rd] = rs1 >>> in.inmediato;
            case SRAI -> x[in.rd] = rs1 >> in.inmediato;

            case ADD -> x[in.rd] = rs1 + rs2;
            case SUB -> x[in.rd] = rs1 - rs2;
            case SLL -> x[in.rd] = rs1 << (rs2 & 31);
            case SLT -> x[in.rd] = rs1 < rs2 ? 1 : 0;
            case SLTU -> x[in.rd] = Integer.compareUnsigned(rs1, rs2) < 0 ? 1 : 0;
            case XOR -> x[in.rd] = rs1 ^ rs2;
            case SRL -> x[in.rd] = rs1 >>> (rs2 & 31);
            case SRA -> x[in.rd] = rs1 >> (rs2 & 31);
            case OR -> x[in.rd] = rs1 | rs2;
            case AND -> x[in.rd] = rs1 & rs2;

            case MUL -> x[in.rd] = rs1 * rs2;
            case MULH -> x[in.rd] = (int) (((long) rs1 * (long) rs2) >> 32);
            case MULHSU -> x[in.rd] = (int) (((long) rs1 * Integer.toUnsignedLong(rs2)) >> 32);
            case MULHU -> x[in.rd] = (int) ((Integer.toUnsignedLong(rs1) * Integer.toUnsignedLong(rs2)) >>> 32);
            case DIV -> x[in.rd] = rs2 == 0 ? -1 : (rs1 == Integer.MIN_VALUE && rs2 == -1 ? rs1 : rs1 / rs2);
            case DIVU -> x[in.rd] = rs2 == 0 ? -1 : Integer.divideUnsigned(rs1, rs2);
            case REM -> x[in.rd] = rs2 == 0 ? rs1 : (rs1 == Integer.MIN_VALUE && rs2 == -1 ? 0 : rs1 % rs2);
            case REMU -> x[in.rd] = rs2 == 0 ? rs1 : Integer.remainderUnsigned(rs1, rs2);

            case ECALL -> llamadaAlSistema();
            case EBREAK -> throw new Detencion("EJECUCION DETENIDA EN ebreak");

            case FLD -> f[in.rd] = memoria.leer(direccion(rs1 + in.inmediato, 4), 8);
            case FSD -> memoria.escribir(direccion(rs1 + in.inmediato, 4), f[in.rs2], 8);
            case FADD_D -> d(in.rd, d(in.rs1) + d(in.rs2));
            case FSUB_D -> d(in.rd, d(in.rs1) - d(in.rs2));
            case FMUL_D -> d(in.rd, d(in.rs1) * d(in.rs2));
            case FDIV_D -> d(in.rd, d(in.rs1) / d(in.rs2));
            case FSQRT_D -> d(in.rd, Math.sqrt(d(in.rs1)));
            case FMIN_D -> d(in.rd, minimo(d(in.rs1), d(in.rs2)));
            case FMAX_D -> d(in.rd, maximo(d(in.rs1), d(in.rs2)));
            case FSGNJ_D -> f[in.rd] = (f[in.rs1] & Long.MAX_VALUE) | (f[in.rs2] & Long.MIN_VALUE);
            case FSGNJN_D -> f[in.rd] = (f[in.rs1] & Long.MAX_VALUE) | (~f[in.rs2] & Long.MIN_VALUE);
            case FSGNJX_D -> f[in.rd] = f[in.rs1] ^ (f[in.rs2] & Long.MIN_VALUE);
            case FEQ_D -> x[in.rd] = d(in.rs1) == d(in.rs2) ? 1 : 0;
            case FLT_D -> x[in.rd] = d(in.rs1) < d(in.rs2) ? 1 : 0;
            case FLE_D -> x[in.rd] = d(in.rs1) <= d(in.rs2) ? 1 : 0;
            case FCVT_W_D -> x[in.rd] = aEntero(d(in.rs1), in.redondeo);
            case FCVT_WU_D -> x[in.rd] = aEnteroSinSigno(d(in.rs1), in.redondeo);
            case FCVT_D_W -> d(in.rd, rs1);
            case FCVT_D_WU -> d(in.rd, (double) Integer.toUnsignedLong(rs1));
        }
        return siguiente;
    }

    // LLAMADAS AL SISTEMA

    private void llamadaAlSistema() throws ErrorRiscV, Salida, Detencion {
        int servicio = x[17];
        switch (servicio) {
            case 1 -> imprimir(Integer.toString(x[10]));
            case 2 -> imprimir(Float.toString(Float.intBitsToFloat((int) f[10])));
            case 3 -> imprimir(Double.toString(d(10)));
            case 4 -> imprimir(leerTexto(x[10]));
            case 5 -> {
                String linea = leerLineaCompleta().trim();
                try {
                    x[10] = Integer.parseInt(linea);
                } catch (NumberFormatException e) {
                    throw new ErrorRiscV("ENTRADA NO ES UN ENTERO: " + linea, 0);
                }
            }
            case 6, 7 -> {
                String linea = leerLineaCompleta().trim();
                try {
                    double valor = Double.parseDouble(linea);
                    if (servicio == 6) {
                        f[10] = 0xFFFFFFFF00000000L | Float.floatToRawIntBits((float) valor);
                    } else {
                        d(10, valor);
                    }
                } catch (NumberFormatException e) {
                    throw new ErrorRiscV("ENTRADA NO ES UN NUMERO: " + linea, 0);
                }
            }
            case 8 -> leerCadena(x[10], x[11]);
            case 9 -> {
                int bytes = x[10];
                if (bytes < 0) {
                    throw new ErrorRiscV("sbrk CON TAMANIO NEGATIVO", 0);
                }
                long nuevoFin = (long) finHeap + ((bytes + 7L) & ~7L);
                if (nuevoFin >= ProgramaRiscV.PILA_INICIAL - 0x01000000L) {
                    throw new ErrorRiscV("SIN MEMORIA DINAMICA DISPONIBLE (sbrk)", 0);
                }
                x[10] = finHeap;
                finHeap = (int) nuevoFin;
            }
            case 10 -> throw new Salida(0);
            case 11 -> imprimir(String.valueOf((char) (x[10] & 0xFF)));
            case 12 -> {
                String linea = leerLineaCompleta();
                x[10] = linea.isEmpty() ? '\n' : linea.charAt(0) & 0xFF;
            }
            case 17, 93 -> throw new Salida(x[10]);
            case 34 -> imprimir(String.format("0x%08x", x[10]));
            case 35 -> imprimir(String.format("%32s", Integer.toBinaryString(x[10])).replace(' ', '0'));
            case 36 -> imprimir(Integer.toUnsignedString(x[10]));
            default -> throw new ErrorRiscV("LLAMADA AL SISTEMA NO SOPORTADA: a7 = " + servicio, 0);
        }
    }

    private void leerCadena(int destino, int maximo) throws ErrorRiscV, Detencion {
        String leida = leerLineaONulo();
        String linea = leida != null ? leida + "\n" : "";
        int cantidad = Math.max(0, Math.min(linea.length(), maximo - 1));
        for (int i = 0; i < cantidad; i++) {
            memoria.escribirByte(direccion(destino + i, 1), (byte) linea.charAt(i));
        }
        if (maximo > 0) {
            memoria.escribirByte(direccion(destino + cantidad, 1), (byte) 0);
        }
    }

    private String leerLineaCompleta() throws Detencion {
        String linea = leerLineaONulo();
        return linea != null ? linea : "";
    }

    private String leerLineaONulo() throws Detencion {
        vaciarSalida();
        try {
            String linea = consola.leerLinea();
            if (detenerSolicitado) {
                throw new Detencion("EJECUCION DETENIDA POR EL USUARIO");
            }
            return linea;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new Detencion("EJECUCION DETENIDA POR EL USUARIO");
        }
    }

    private String leerTexto(int inicio) throws ErrorRiscV {
        StringBuilder sb = new StringBuilder();
        int dir = inicio;
        while (true) {
            int b = memoria.leerByte(direccion(dir, 1)) & 0xFF;
            if (b == 0) {
                return sb.toString();
            }
            sb.append((char) b);
            dir++;
            if (sb.length() > 1_000_000) {
                throw new ErrorRiscV("CADENA SIN TERMINADOR EN 0x" + Integer.toHexString(inicio), 0);
            }
        }
    }

    private void imprimir(String texto) {
        salidaPendiente.append(texto);
        if (salidaPendiente.length() >= 512 || texto.indexOf('\n') >= 0) {
            vaciarSalida();
        }
    }

    private void vaciarSalida() {
        if (salidaPendiente.length() > 0) {
            consola.escribir(salidaPendiente.toString());
            salidaPendiente.setLength(0);
        }
    }

    // APOYO

    private double d(int registro) {
        return Double.longBitsToDouble(f[registro]);
    }

    private void d(int registro, double valor) {
        f[registro] = Double.doubleToRawLongBits(valor);
    }

    private static double minimo(double a, double b) {
        if (Double.isNaN(a)) return b;
        if (Double.isNaN(b)) return a;
        return Math.min(a, b);
    }

    private static double maximo(double a, double b) {
        if (Double.isNaN(a)) return b;
        if (Double.isNaN(b)) return a;
        return Math.max(a, b);
    }

    private static double redondear(double valor, int modo) {
        return switch (modo == InstruccionRiscV.DYN ? InstruccionRiscV.RNE : modo) {
            case InstruccionRiscV.RTZ -> valor < 0 ? Math.ceil(valor) : Math.floor(valor);
            case InstruccionRiscV.RDN -> Math.floor(valor);
            case InstruccionRiscV.RUP -> Math.ceil(valor);
            case InstruccionRiscV.RMM -> Math.signum(valor) * Math.floor(Math.abs(valor) + 0.5);
            default -> Math.rint(valor);
        };
    }

    private static int aEntero(double valor, int modo) {
        if (Double.isNaN(valor)) {
            return Integer.MAX_VALUE;
        }
        double r = redondear(valor, modo);
        if (r >= Integer.MAX_VALUE) return Integer.MAX_VALUE;
        if (r <= Integer.MIN_VALUE) return Integer.MIN_VALUE;
        return (int) r;
    }

    private static int aEnteroSinSigno(double valor, int modo) {
        if (Double.isNaN(valor)) {
            return -1;
        }
        double r = redondear(valor, modo);
        if (r <= 0) return 0;
        if (r >= 4294967295.0) return -1;
        return (int) (long) r;
    }

    private static int direccionDe(int indice) {
        return ProgramaRiscV.BASE_TEXTO + 4 * indice;
    }

    private static int indiceDe(int direccion) throws ErrorRiscV {
        if ((direccion & 3) != 0 || Integer.compareUnsigned(direccion, ProgramaRiscV.BASE_TEXTO) < 0) {
            throw new ErrorRiscV("SALTO A UNA DIRECCION NO VALIDA: 0x" + Integer.toHexString(direccion), 0);
        }
        return (direccion - ProgramaRiscV.BASE_TEXTO) >>> 2;
    }

    private static int direccion(int dir, int alineacion) throws ErrorRiscV {
        if (Integer.compareUnsigned(dir, 0x10000000) < 0 || dir < 0) {
            throw new ErrorRiscV("ACCESO A MEMORIA FUERA DE RANGO: 0x" + Integer.toHexString(dir), 0);
        }
        if ((dir & (alineacion - 1)) != 0) {
            throw new ErrorRiscV("DIRECCION 0x" + Integer.toHexString(dir) + " NO ALINEADA A " + alineacion + " BYTES", 0);
        }
        return dir;
    }

    private static final class Memoria {
        private static final int BITS_PAGINA = 12;
        private static final int TAMANIO_PAGINA = 1 << BITS_PAGINA;
        private static final int CACHE = 64;

        private final Map<Integer, byte[]> paginas = new HashMap<>();
        private final int[] clavesCache = new int[CACHE];
        private final byte[][] paginasCache = new byte[CACHE][];

        Memoria() {
            java.util.Arrays.fill(clavesCache, -1);
        }

        private byte[] pagina(int direccion) {
            int clave = direccion >>> BITS_PAGINA;
            int ranura = clave & (CACHE - 1);
            if (clavesCache[ranura] == clave) {
                return paginasCache[ranura];
            }
            byte[] pagina = paginas.computeIfAbsent(clave, k -> new byte[TAMANIO_PAGINA]);
            clavesCache[ranura] = clave;
            paginasCache[ranura] = pagina;
            return pagina;
        }

        byte leerByte(int direccion) {
            return pagina(direccion)[direccion & (TAMANIO_PAGINA - 1)];
        }

        void escribirByte(int direccion, byte valor) {
            pagina(direccion)[direccion & (TAMANIO_PAGINA - 1)] = valor;
        }

        long leer(int direccion, int bytes) {
            byte[] p = pagina(direccion);
            int base = direccion & (TAMANIO_PAGINA - 1);
            if (base + bytes > TAMANIO_PAGINA) {
                long valor = 0;
                for (int i = 0; i < bytes; i++) {
                    valor |= (leerByte(direccion + i) & 0xFFL) << (8 * i);
                }
                return valor;
            }
            long valor = 0;
            for (int i = 0; i < bytes; i++) {
                valor |= (p[base + i] & 0xFFL) << (8 * i);
            }
            return valor;
        }

        void escribir(int direccion, long valor, int bytes) {
            byte[] p = pagina(direccion);
            int base = direccion & (TAMANIO_PAGINA - 1);
            if (base + bytes > TAMANIO_PAGINA) {
                for (int i = 0; i < bytes; i++) {
                    escribirByte(direccion + i, (byte) (valor >>> (8 * i)));
                }
                return;
            }
            for (int i = 0; i < bytes; i++) {
                p[base + i] = (byte) (valor >>> (8 * i));
            }
        }
    }

    // RESULTADO

    public record ResultadoEjecucion(int codigoSalida, long instrucciones, String error, boolean detenido) {
        public boolean exitoso() {
            return error == null;
        }
    }
}
