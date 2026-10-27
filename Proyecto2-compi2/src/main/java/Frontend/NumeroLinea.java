package Frontend;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Element;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.Map;

public class NumeroLinea extends JComponent implements DocumentListener, CaretListener, PropertyChangeListener {

    private static final int MARGEN = 8;

    private final JTextComponent componenteTexto;
    private final Map<String, FontMetrics> fuentes;
    private int ultimoDigitos;
    private int ultimaAltura;
    private int ultimaLineaActual;

    public NumeroLinea(JTextComponent componenteTexto) {
        this.componenteTexto = componenteTexto;
        this.fuentes = new HashMap<>();

        setFont(componenteTexto.getFont());
        setBorderGap(MARGEN);
        setForeground(new Color(130, 130, 130));
        setBackground(new Color(245, 245, 245));

        componenteTexto.getDocument().addDocumentListener(this);
        componenteTexto.addCaretListener(this);
        componenteTexto.addPropertyChangeListener("font", this);
    }

    public void setBorderGap(int margen) {
        Border bordeExterior = new MatteBorder(0, 0, 0, 1, new Color(210, 210, 210));
        Border bordeInterior = new EmptyBorder(0, margen, 0, margen);
        setBorder(new CompoundBorder(bordeExterior, bordeInterior));
        ultimoDigitos = 0;
        setPreferredWidth();
    }

    private void setPreferredWidth() {
        Element raiz = componenteTexto.getDocument().getDefaultRootElement();
        int lineas = raiz.getElementCount();
        int digitos = Math.max(String.valueOf(lineas).length(), 2);

        if (ultimoDigitos != digitos) {
            ultimoDigitos = digitos;
            FontMetrics fm = getFontMetrics(getFont());
            int ancho = fm.charWidth('0') * digitos;
            Insets insets = getInsets();
            int anchoPreferido = insets.left + insets.right + ancho;

            Dimension d = getPreferredSize();
            d.setSize(anchoPreferido, componenteTexto.getHeight());
            setPreferredSize(d);
            setSize(d);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        g2d.setColor(getBackground());
        g2d.fillRect(0, 0, getWidth(), getHeight());

        FontMetrics fm = componenteTexto.getFontMetrics(componenteTexto.getFont());
        Insets insets = getInsets();
        int anchoDisponible = getWidth() - insets.right;

        Rectangle clip = g.getClipBounds();
        int puntoInicioY = clip.y;
        int puntoFinY = clip.y + clip.height;

        int inicioOffset = componenteTexto.viewToModel2D(new Point(0, puntoInicioY));
        int finOffset = componenteTexto.viewToModel2D(new Point(0, puntoFinY));

        Element raiz = componenteTexto.getDocument().getDefaultRootElement();
        int lineaInicio = raiz.getElementIndex(inicioOffset);
        int lineaFin = raiz.getElementIndex(finOffset);

        for (int i = lineaInicio; i <= lineaFin; i++) {
            Element lineaElem = raiz.getElement(i);
            try {
                Rectangle rect = componenteTexto.modelToView2D(lineaElem.getStartOffset()).getBounds();
                int y = rect.y + fm.getAscent();

                String textoNumero = String.valueOf(i + 1);
                int anchoTexto = fm.stringWidth(textoNumero);
                int x = anchoDisponible - anchoTexto;

                if (i == ultimaLineaActual) {
                    g2d.setColor(new Color(40, 40, 40));
                    g2d.setFont(getFont().deriveFont(Font.BOLD));
                } else {
                    g2d.setColor(getForeground());
                    g2d.setFont(getFont());
                }

                g2d.drawString(textoNumero, x, y);
            } catch (BadLocationException ignored) {
            }
        }
    }

    private void actualizarLineaActual() {
        int caretPos = componenteTexto.getCaretPosition();
        Element raiz = componenteTexto.getDocument().getDefaultRootElement();
        int lineaActual = raiz.getElementIndex(caretPos);
        if (lineaActual != ultimaLineaActual) {
            ultimaLineaActual = lineaActual;
            repaint();
        }
    }

    @Override
    public void caretUpdate(CaretEvent e) {
        actualizarLineaActual();
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        documentoModificado();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        documentoModificado();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        documentoModificado();
    }

    private void documentoModificado() {
        SwingUtilities.invokeLater(() -> {
            try {
                int finPos = componenteTexto.getDocument().getLength();
                Rectangle rect = componenteTexto.modelToView2D(finPos).getBounds();
                if (rect != null && rect.y != ultimaAltura) {
                    setPreferredWidth();
                    repaint();
                    ultimaAltura = rect.y;
                }
            } catch (Exception ignored) {
                setPreferredWidth();
                repaint();
            }
        });
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("font".equals(evt.getPropertyName())) {
            setFont(componenteTexto.getFont());
            ultimoDigitos = 0;
            setPreferredWidth();
            repaint();
        }
    }
}

