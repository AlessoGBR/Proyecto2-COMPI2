package Backend.visitor;

public final class Literales {

    private Literales() {
    }

    public static String cadena(String lexema) {
        return resolverEscapes(lexema.substring(1, lexema.length() - 1));
    }

    public static char caracter(String lexema) {
        String valor = resolverEscapes(lexema.substring(1, lexema.length() - 1));
        return valor.isEmpty() ? ' ' : valor.charAt(0);
    }

    private static String resolverEscapes(String texto) {
        StringBuilder sb = new StringBuilder(texto.length());
        for (int i = 0; i < texto.length(); i++) {
            char c = texto.charAt(i);
            if (c != '\\' || i + 1 >= texto.length()) {
                sb.append(c);
                continue;
            }
            char siguiente = texto.charAt(++i);
            switch (siguiente) {
                case 'n' -> sb.append('\n');
                case 't' -> sb.append('\t');
                case 'r' -> sb.append('\r');
                case 'b' -> sb.append('\b');
                case 'f' -> sb.append('\f');
                default -> sb.append(siguiente);
            }
        }
        return sb.toString();
    }
}
