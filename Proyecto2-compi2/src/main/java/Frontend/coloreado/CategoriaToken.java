package Frontend.coloreado;

import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.Color;

public enum CategoriaToken {

    PALABRA_RESERVADA(new Color(0, 51, 179), true, false),
    TIPO_DATO(new Color(0, 128, 128), true, false),
    SECCION(new Color(135, 16, 148), true, false),         
    FUNCION_SISTEMA(new Color(196, 90, 0), true, false),     
    FUNCION(new Color(0, 115, 180), false, false),          
    IDENTIFICADOR(new Color(30, 30, 30), false, false),
    CADENA(new Color(6, 125, 23), false, false),
    NUMERO(new Color(170, 0, 110), false, false),
    CONSTANTE(new Color(128, 0, 0), true, false),            
    OPERADOR(new Color(90, 90, 90), false, false),
    DELIMITADOR(new Color(110, 110, 110), false, false),
    COMENTARIO(new Color(140, 140, 140), false, true);

    private final SimpleAttributeSet estilo = new SimpleAttributeSet();

    CategoriaToken(Color color, boolean negrita, boolean cursiva) {
        StyleConstants.setForeground(estilo, color);
        StyleConstants.setBold(estilo, negrita);
        StyleConstants.setItalic(estilo, cursiva);
    }

    public AttributeSet getEstilo() {
        return estilo;
    }
}
