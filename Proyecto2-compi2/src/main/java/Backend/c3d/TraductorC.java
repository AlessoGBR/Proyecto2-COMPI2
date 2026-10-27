package Backend.c3d;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TraductorC {

    private static final int TAMANIO_MEMORIA = 100000;

    private static final Pattern TEMPORAL = Pattern.compile("\\bT\\d+\\b");

    public static String traducir(ControladorC3D controlador) {
        if (controlador == null) {
            return "";
        }

        List<Cuarteta> cuartetas = controlador.getCuartetas();
        StringBuilder sb = new StringBuilder();

        escribirCabecera(sb, controlador.getTamanioAreaGlobal());
        escribirPrototipos(sb, cuartetas);
        escribirFunciones(sb, cuartetas);
        escribirMain(sb, controlador.getTamanioAreaGlobal());

        return sb.toString();
    }


    private static void escribirCabecera(StringBuilder sb, int areaGlobal) {
        sb.append("/* CODIGO DE TRES DIRECCIONES EN C: una instruccion por cuarteta */\n");
        sb.append("#include <stdio.h>   /* printf, getchar */\n");
        sb.append("#include <stdlib.h>  /* exit */\n\n");

        sb.append("/* SEGMENTOS DE MEMORIA */\n");
        sb.append("double stack[").append(TAMANIO_MEMORIA).append("];\n");
        sb.append("double heap[").append(TAMANIO_MEMORIA).append("];\n");
        sb.append("double P = ").append(areaGlobal).append(";\n");
        sb.append("double H = 1;\n");
        sb.append("double THIS = 0;\n\n");
    }

    private static void escribirPrototipos(StringBuilder sb, List<Cuarteta> cuartetas) {
        for (Cuarteta c : cuartetas) {
            if (c.getOperador() == OperadorC3D.INICIO_FUNCION) {
                sb.append("void ").append(c.getResultado()).append("();\n");
            }
        }
        sb.append('\n');
    }

    private static void escribirFunciones(StringBuilder sb, List<Cuarteta> cuartetas) {

        int i = 0;
        while (i < cuartetas.size()) {
            Cuarteta actual = cuartetas.get(i);

            if (actual.getOperador() != OperadorC3D.INICIO_FUNCION) {
                sb.append("/* ").append(actual.aFormatoC3D()).append(" */\n");
                i++;
                continue;
            }

            int fin = buscarFinDeFuncion(cuartetas, i);
            List<Cuarteta> cuerpo = cuartetas.subList(i + 1, fin);

            sb.append('\n').append(actual.aCodigoC()).append('\n');
            escribirDeclaracionDeTemporales(sb, cuerpo);
            for (Cuarteta c : cuerpo) {
                sb.append(c.aCodigoC()).append('\n');
            }
            sb.append("}\n");

            i = fin + 1;
        }
    }

    private static int buscarFinDeFuncion(List<Cuarteta> cuartetas, int inicio) {
        for (int j = inicio + 1; j < cuartetas.size(); j++) {
            if (cuartetas.get(j).getOperador() == OperadorC3D.FIN_FUNCION) {
                return j;
            }
        }
        return cuartetas.size();
    }

    private static void escribirDeclaracionDeTemporales(StringBuilder sb, List<Cuarteta> cuerpo) {
        Set<String> temporales = new LinkedHashSet<>();
        for (Cuarteta c : cuerpo) {
            recolectarTemporales(c.getArg1(), temporales);
            recolectarTemporales(c.getArg2(), temporales);
            recolectarTemporales(c.getResultado(), temporales);
        }
        if (temporales.isEmpty()) {
            return;
        }

        List<String> ordenados = new ArrayList<>(temporales);
        sb.append("    double ");
        for (int i = 0; i < ordenados.size(); i++) {
            if (i > 0) {
                sb.append(", ");
            }
            if (i > 0 && i % 12 == 0) {
                sb.append("\n            ");
            }
            sb.append(ordenados.get(i)).append(" = 0");
        }
        sb.append(";\n");
    }

    private static void recolectarTemporales(String texto, Set<String> destino) {
        if (texto == null || texto.isEmpty()) {
            return;
        }
        Matcher m = TEMPORAL.matcher(texto);
        while (m.find()) {
            destino.add(m.group());
        }
    }

    private static void escribirMain(StringBuilder sb, int areaGlobal) {
        sb.append("\nint main() {\n");
        sb.append("    P = ").append(areaGlobal).append(";\n");
        sb.append("    H = 1;\n");
        sb.append("    main_programa();\n");
        sb.append("    return 0;\n");
        sb.append("}\n");
    }
}
