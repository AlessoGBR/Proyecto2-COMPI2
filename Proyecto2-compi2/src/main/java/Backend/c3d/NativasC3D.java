package Backend.c3d;

import java.util.EnumSet;
import java.util.Set;
import java.util.function.Consumer;

/**
 * Procedimientos nativos escritos en codigo de tres direcciones (imprimir cadenas y
 * decimales, concatenar, convertir a cadena, leer del teclado). Se generan como
 * cuartetas, igual que las funciones del usuario, para que el C y el RISC-V sean una
 * traduccion directa del C3D sin funciones de apoyo escritas a mano.
 *
 * Convencion de llamada (la misma de las funciones del usuario): el que llama hace
 * P = P + marco, deja los parametros en stack[P + 1], stack[P + 2], ... y el valor de
 * retorno queda en stack[P]. Solo se generan las nativas que el programa usa.
 */
public class NativasC3D {

    public enum Nativa {
        IMPRIMIR_CADENA("nativa_imprimir_cadena"),
        IMPRIMIR_DECIMAL("nativa_imprimir_decimal"),
        IMPRIMIR_BOOLEANO("nativa_imprimir_booleano"),
        CONCATENAR("nativa_concatenar"),
        ENTERO_A_CADENA("nativa_entero_a_cadena"),
        DECIMAL_A_CADENA("nativa_decimal_a_cadena"),
        BOOLEANO_A_CADENA("nativa_booleano_a_cadena"),
        LEER_CADENA("nativa_leer_cadena"),
        LEER_NUMERO("nativa_leer_numero"),
        ERROR_DIVISION("nativa_error_division"),
        ERROR_MODULO("nativa_error_modulo");

        private final String etiqueta;

        Nativa(String etiqueta) {
            this.etiqueta = etiqueta;
        }

        public String getEtiqueta() {
            return etiqueta;
        }
    }

    private static final String FIN_CADENA = "-1";
    private static final int SALTO_LINEA = 10;

    private final ControladorC3D c;
    private final Set<Nativa> usadas = EnumSet.noneOf(Nativa.class);

    public NativasC3D(ControladorC3D controlador) {
        this.c = controlador;
    }

    // marca la nativa para generarla al final y devuelve su etiqueta
    public String usar(Nativa nativa) {
        usadas.add(nativa);
        return nativa.getEtiqueta();
    }

    public void generarUsadas() {
        for (Nativa nativa : usadas) {
            c.agregar(OperadorC3D.INICIO_FUNCION, "", "", nativa.getEtiqueta());
            switch (nativa) {
                case IMPRIMIR_CADENA -> imprimirCadena();
                case IMPRIMIR_DECIMAL -> imprimirDecimal();
                case IMPRIMIR_BOOLEANO -> imprimirBooleano();
                case CONCATENAR -> concatenar();
                case ENTERO_A_CADENA -> enteroACadena();
                case DECIMAL_A_CADENA -> decimalACadena();
                case BOOLEANO_A_CADENA -> booleanoACadena();
                case LEER_CADENA -> leerCadena();
                case LEER_NUMERO -> leerNumero();
                case ERROR_DIVISION -> error("ERROR: DIVISION ENTRE CERO");
                case ERROR_MODULO -> error("ERROR: MODULO ENTRE CERO");
            }
            c.agregar(OperadorC3D.RETURN, "", "", "");
            c.agregar(OperadorC3D.FIN_FUNCION, "", "", nativa.getEtiqueta());
        }
    }

    // ===================== NATIVAS =====================

    // imprime la cadena del heap que empieza en el parametro 1 (0 = null)
    private void imprimirCadena() {
        String dir = parametro(1);
        String lBucle = c.nuevaEtiqueta();
        String lFin = c.nuevaEtiqueta();
        c.agregar(OperadorC3D.IF_NEQ, dir, "0", lBucle);
        imprimirTexto("null");
        c.agregar(OperadorC3D.RETURN, "", "", "");
        c.agregarEtiqueta(lBucle);
        String caracter = c.nuevoTemporal();
        c.agregar(OperadorC3D.ACCESO_HEAP, dir, "", caracter);
        c.agregar(OperadorC3D.IF_EQ, caracter, FIN_CADENA, lFin);
        c.agregar(OperadorC3D.PRINT_CHAR, caracter, "", "");
        c.agregar(OperadorC3D.SUMA, dir, "1", dir);
        c.agregarGoto(lBucle);
        c.agregarEtiqueta(lFin);
    }

    // imprime el decimal del parametro 1 con hasta 6 decimales, sin ceros sobrantes (2.5, 5.0)
    private void imprimirDecimal() {
        String valor = parametro(1);
        String[] partes = separarDecimal(valor, codigo -> c.agregar(OperadorC3D.PRINT_CHAR, codigo, "", ""));
        c.agregar(OperadorC3D.PRINT_INT, partes[0], "", "");
        c.agregar(OperadorC3D.PRINT_CHAR, String.valueOf((int) '.'), "", "");
        emitirFraccion(partes[1], codigo -> c.agregar(OperadorC3D.PRINT_CHAR, codigo, "", ""));
    }

    private void imprimirBooleano() {
        String valor = parametro(1);
        String lFalso = c.nuevaEtiqueta();
        c.agregar(OperadorC3D.IF_EQ, valor, "0", lFalso);
        imprimirTexto("true");
        c.agregar(OperadorC3D.RETURN, "", "", "");
        c.agregarEtiqueta(lFalso);
        imprimirTexto("false");
    }

    // devuelve una cadena nueva con el parametro 1 seguido del parametro 2
    private void concatenar() {
        String primera = parametro(1);
        String segunda = parametro(2);
        String inicio = c.nuevoTemporal();
        c.agregarAsignacion(inicio, "H");
        anexarCadena(primera);
        anexarCadena(segunda);
        cerrarCadenaYRetornar(inicio);
    }

    private void enteroACadena() {
        String valor = parametro(1);
        String inicio = c.nuevoTemporal();
        c.agregarAsignacion(inicio, "H");
        String lPositivo = c.nuevaEtiqueta();
        c.agregar(OperadorC3D.IF_GE, valor, "0", lPositivo);
        escribirEnHeap(String.valueOf((int) '-'));
        c.agregar(OperadorC3D.RESTA, "0", valor, valor);
        c.agregarEtiqueta(lPositivo);
        emitirEntero(valor, this::escribirEnHeap);
        cerrarCadenaYRetornar(inicio);
    }

    private void decimalACadena() {
        String valor = parametro(1);
        String inicio = c.nuevoTemporal();
        c.agregarAsignacion(inicio, "H");
        String[] partes = separarDecimal(valor, this::escribirEnHeap);
        emitirEntero(partes[0], this::escribirEnHeap);
        escribirEnHeap(String.valueOf((int) '.'));
        emitirFraccion(partes[1], this::escribirEnHeap);
        cerrarCadenaYRetornar(inicio);
    }

    private void booleanoACadena() {
        String valor = parametro(1);
        String lFalso = c.nuevaEtiqueta();
        c.agregar(OperadorC3D.IF_EQ, valor, "0", lFalso);
        retornar(c.guardarCadenaEnHeap("true"));
        c.agregarEtiqueta(lFalso);
        retornar(c.guardarCadenaEnHeap("false"));
    }

    // lee caracteres hasta el salto de linea (o fin de la entrada) y devuelve la cadena
    private void leerCadena() {
        String inicio = c.nuevoTemporal();
        c.agregarAsignacion(inicio, "H");
        String lBucle = c.nuevaEtiqueta();
        String lFin = c.nuevaEtiqueta();
        String caracter = c.nuevoTemporal();
        c.agregarEtiqueta(lBucle);
        c.agregar(OperadorC3D.LEER_CARACTER, "", "", caracter);
        c.agregar(OperadorC3D.IF_EQ, caracter, String.valueOf(SALTO_LINEA), lFin);
        c.agregar(OperadorC3D.IF_EQ, caracter, "-1", lFin);
        c.agregar(OperadorC3D.IF_EQ, caracter, "13", lBucle);
        escribirEnHeap(caracter);
        c.agregarGoto(lBucle);
        c.agregarEtiqueta(lFin);
        cerrarCadenaYRetornar(inicio);
    }

    // lee una linea y la convierte en numero: [espacios][signo]digitos[.digitos]; si no hay
    // numero devuelve 0. El resto de la linea se descarta
    private void leerNumero() {
        String resultado = c.nuevoTemporal();
        String negativo = c.nuevoTemporal();
        String caracter = c.nuevoTemporal();
        String digito = c.nuevoTemporal();
        String escala = c.nuevoTemporal();
        String lEspacios = c.nuevaEtiqueta();
        String lSigno = c.nuevaEtiqueta();
        String lMas = c.nuevaEtiqueta();
        String lEntera = c.nuevaEtiqueta();
        String lPunto = c.nuevaEtiqueta();
        String lFraccion = c.nuevaEtiqueta();
        String lResto = c.nuevaEtiqueta();
        String lSignoFinal = c.nuevaEtiqueta();
        String lRetorno = c.nuevaEtiqueta();

        c.agregarAsignacion(resultado, "0");
        c.agregarAsignacion(negativo, "0");
        c.agregar(OperadorC3D.LEER_CARACTER, "", "", caracter);
        c.agregarEtiqueta(lEspacios);
        c.agregar(OperadorC3D.IF_NEQ, caracter, String.valueOf((int) ' '), lSigno);
        c.agregar(OperadorC3D.LEER_CARACTER, "", "", caracter);
        c.agregarGoto(lEspacios);

        c.agregarEtiqueta(lSigno);
        c.agregar(OperadorC3D.IF_NEQ, caracter, String.valueOf((int) '-'), lMas);
        c.agregarAsignacion(negativo, "1");
        c.agregar(OperadorC3D.LEER_CARACTER, "", "", caracter);
        c.agregarGoto(lEntera);
        c.agregarEtiqueta(lMas);
        c.agregar(OperadorC3D.IF_NEQ, caracter, String.valueOf((int) '+'), lEntera);
        c.agregar(OperadorC3D.LEER_CARACTER, "", "", caracter);

        c.agregarEtiqueta(lEntera);
        c.agregar(OperadorC3D.IF_LT, caracter, String.valueOf((int) '0'), lPunto);
        c.agregar(OperadorC3D.IF_GT, caracter, String.valueOf((int) '9'), lPunto);
        c.agregar(OperadorC3D.MULT, resultado, "10", resultado);
        c.agregar(OperadorC3D.RESTA, caracter, String.valueOf((int) '0'), digito);
        c.agregar(OperadorC3D.SUMA, resultado, digito, resultado);
        c.agregar(OperadorC3D.LEER_CARACTER, "", "", caracter);
        c.agregarGoto(lEntera);

        c.agregarEtiqueta(lPunto);
        c.agregar(OperadorC3D.IF_NEQ, caracter, String.valueOf((int) '.'), lResto);
        c.agregarAsignacion(escala, "10");
        c.agregar(OperadorC3D.LEER_CARACTER, "", "", caracter);
        c.agregarEtiqueta(lFraccion);
        c.agregar(OperadorC3D.IF_LT, caracter, String.valueOf((int) '0'), lResto);
        c.agregar(OperadorC3D.IF_GT, caracter, String.valueOf((int) '9'), lResto);
        c.agregar(OperadorC3D.RESTA, caracter, String.valueOf((int) '0'), digito);
        c.agregar(OperadorC3D.DIV, digito, escala, digito);
        c.agregar(OperadorC3D.SUMA, resultado, digito, resultado);
        c.agregar(OperadorC3D.MULT, escala, "10", escala);
        c.agregar(OperadorC3D.LEER_CARACTER, "", "", caracter);
        c.agregarGoto(lFraccion);

        // descartar lo que quede de la linea para que la siguiente lectura empiece limpia
        c.agregarEtiqueta(lResto);
        c.agregar(OperadorC3D.IF_EQ, caracter, String.valueOf(SALTO_LINEA), lSignoFinal);
        c.agregar(OperadorC3D.IF_EQ, caracter, "-1", lSignoFinal);
        c.agregar(OperadorC3D.LEER_CARACTER, "", "", caracter);
        c.agregarGoto(lResto);

        c.agregarEtiqueta(lSignoFinal);
        c.agregar(OperadorC3D.IF_EQ, negativo, "0", lRetorno);
        c.agregar(OperadorC3D.RESTA, "0", resultado, resultado);
        c.agregarEtiqueta(lRetorno);
        retornar(resultado);
    }

    // division o modulo entre cero: mensaje y fin del programa
    private void error(String mensaje) {
        imprimirTexto("\n" + mensaje + "\n");
        c.agregar(OperadorC3D.SALIR, "1", "", "");
    }

    // ===================== PIEZAS COMUNES =====================

    // valor = stack[P + numero]
    private String parametro(int numero) {
        String direccion = c.nuevoTemporal();
        String valor = c.nuevoTemporal();
        c.agregar(OperadorC3D.SUMA, "P", String.valueOf(numero), direccion);
        c.agregar(OperadorC3D.ACCESO_STACK, direccion, "", valor);
        return valor;
    }

    private void retornar(String valor) {
        c.agregar(OperadorC3D.ASIG_STACK, valor, "", "P");
        c.agregar(OperadorC3D.RETURN, "", "", "");
    }

    private void imprimirTexto(String texto) {
        for (int i = 0; i < texto.length(); i++) {
            c.agregar(OperadorC3D.PRINT_CHAR, String.valueOf((int) texto.charAt(i)), "", "");
        }
    }

    // heap[H] = codigo; H = H + 1
    private void escribirEnHeap(String codigo) {
        c.agregar(OperadorC3D.ASIG_HEAP, codigo, "", "H");
        c.agregar(OperadorC3D.SUMA, "H", "1", "H");
    }

    // copia al final del heap los caracteres de la cadena que empieza en 'direccion'
    private void anexarCadena(String direccion) {
        String lCopiar = c.nuevaEtiqueta();
        String lFin = c.nuevaEtiqueta();
        c.agregar(OperadorC3D.IF_NEQ, direccion, "0", lCopiar);
        for (char letra : "null".toCharArray()) {
            escribirEnHeap(String.valueOf((int) letra));
        }
        c.agregarGoto(lFin);
        c.agregarEtiqueta(lCopiar);
        String caracter = c.nuevoTemporal();
        c.agregar(OperadorC3D.ACCESO_HEAP, direccion, "", caracter);
        c.agregar(OperadorC3D.IF_EQ, caracter, FIN_CADENA, lFin);
        escribirEnHeap(caracter);
        c.agregar(OperadorC3D.SUMA, direccion, "1", direccion);
        c.agregarGoto(lCopiar);
        c.agregarEtiqueta(lFin);
    }

    private void cerrarCadenaYRetornar(String inicio) {
        escribirEnHeap(FIN_CADENA);
        retornar(inicio);
    }

    // Separa un decimal en parte entera y 6 decimales redondeados. Si es negativo emite el
    // signo y trabaja con su valor absoluto. Devuelve {parteEntera, fraccion (0..999999)}
    private String[] separarDecimal(String valor, Consumer<String> emitir) {
        String lPositivo = c.nuevaEtiqueta();
        String lFraccionLista = c.nuevaEtiqueta();
        c.agregar(OperadorC3D.IF_GE, valor, "0", lPositivo);
        emitir.accept(String.valueOf((int) '-'));
        c.agregar(OperadorC3D.RESTA, "0", valor, valor);
        c.agregarEtiqueta(lPositivo);

        String entera = c.nuevoTemporal();
        String fraccion = c.nuevoTemporal();
        String escalada = c.nuevoTemporal();
        c.agregar(OperadorC3D.A_ENTERO, valor, "", entera);
        c.agregar(OperadorC3D.RESTA, valor, entera, escalada);
        c.agregar(OperadorC3D.MULT, escalada, "1000000", escalada);
        c.agregar(OperadorC3D.SUMA, escalada, "0.5", escalada);
        c.agregar(OperadorC3D.A_ENTERO, escalada, "", fraccion);
        // 0.9999996 redondea a 1000000: se pasa la unidad a la parte entera
        c.agregar(OperadorC3D.IF_LT, fraccion, "1000000", lFraccionLista);
        c.agregar(OperadorC3D.RESTA, fraccion, "1000000", fraccion);
        c.agregar(OperadorC3D.SUMA, entera, "1", entera);
        c.agregarEtiqueta(lFraccionLista);
        return new String[]{entera, fraccion};
    }

    // emite los digitos de un entero no negativo, del mas significativo al menos
    private void emitirEntero(String numero, Consumer<String> emitir) {
        String divisor = c.nuevoTemporal();
        String cociente = c.nuevoTemporal();
        String codigo = c.nuevoTemporal();
        String lMagnitud = c.nuevaEtiqueta();
        String lDigitos = c.nuevaEtiqueta();

        c.agregarAsignacion(divisor, "1");
        c.agregarEtiqueta(lMagnitud);
        c.agregar(OperadorC3D.DIV_ENT, numero, divisor, cociente);
        c.agregar(OperadorC3D.IF_LT, cociente, "10", lDigitos);
        c.agregar(OperadorC3D.MULT, divisor, "10", divisor);
        c.agregarGoto(lMagnitud);

        c.agregarEtiqueta(lDigitos);
        c.agregar(OperadorC3D.DIV_ENT, numero, divisor, cociente);
        c.agregar(OperadorC3D.SUMA, cociente, String.valueOf((int) '0'), codigo);
        emitir.accept(codigo);
        c.agregar(OperadorC3D.MOD, numero, divisor, numero);
        c.agregar(OperadorC3D.DIV_ENT, divisor, "10", divisor);
        c.agregar(OperadorC3D.IF_GE, divisor, "1", lDigitos);
    }

    // emite los 6 decimales (fraccion = 0..999999) sin ceros a la derecha, minimo uno
    private void emitirFraccion(String fraccion, Consumer<String> emitir) {
        String digitos = c.nuevoTemporal();
        String divisor = c.nuevoTemporal();
        String resto = c.nuevoTemporal();
        String digito = c.nuevoTemporal();
        String lQuitarCeros = c.nuevaEtiqueta();
        String lEmitir = c.nuevaEtiqueta();

        c.agregarAsignacion(digitos, "6");
        c.agregarAsignacion(divisor, "100000");
        c.agregarEtiqueta(lQuitarCeros);
        c.agregar(OperadorC3D.IF_LE, digitos, "1", lEmitir);
        c.agregar(OperadorC3D.MOD, fraccion, "10", resto);
        c.agregar(OperadorC3D.IF_NEQ, resto, "0", lEmitir);
        c.agregar(OperadorC3D.DIV_ENT, fraccion, "10", fraccion);
        c.agregar(OperadorC3D.DIV_ENT, divisor, "10", divisor);
        c.agregar(OperadorC3D.RESTA, digitos, "1", digitos);
        c.agregarGoto(lQuitarCeros);

        c.agregarEtiqueta(lEmitir);
        c.agregar(OperadorC3D.DIV_ENT, fraccion, divisor, digito);
        c.agregar(OperadorC3D.SUMA, digito, String.valueOf((int) '0'), digito);
        emitir.accept(digito);
        c.agregar(OperadorC3D.MOD, fraccion, divisor, fraccion);
        c.agregar(OperadorC3D.DIV_ENT, divisor, "10", divisor);
        c.agregar(OperadorC3D.IF_GE, divisor, "1", lEmitir);
    }
}
