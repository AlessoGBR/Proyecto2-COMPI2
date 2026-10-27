package Frontend;

import javax.swing.JTextPane;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.TabSet;
import javax.swing.text.TabStop;
import java.awt.Component;
import java.awt.Font;

public class EditorCodigo extends JTextPane {

    private static final int ESPACIOS_POR_TABULACION = 4;

    public EditorCodigo(Font fuente) {
        setFont(fuente);

        Style base = getStyle(StyleContext.DEFAULT_STYLE);
        StyleConstants.setFontFamily(base, fuente.getFamily());
        StyleConstants.setFontSize(base, fuente.getSize());

        int anchoTabulacion = getFontMetrics(fuente).charWidth(' ') * ESPACIOS_POR_TABULACION;
        TabStop[] paradas = new TabStop[100];
        for (int i = 0; i < paradas.length; i++) {
            paradas[i] = new TabStop((i + 1) * anchoTabulacion);
        }
        StyleConstants.setTabSet(base, new TabSet(paradas));
    }

    @Override
    public boolean getScrollableTracksViewportWidth() {
        Component padre = getParent();
        return padre == null || getUI().getPreferredSize(this).width <= padre.getSize().width;
    }
}
