package Frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EncabezadoPestania extends JPanel {

    public interface AccionCerrarPestania {
        void cerrar();
    }

    private final JLabel etiquetaTitulo;
    private final JButton botonCerrar;
    private boolean modificado;
    private String tituloBase;

    public EncabezadoPestania(String titulo, AccionCerrarPestania accionCerrar) {
        super(new FlowLayout(FlowLayout.LEFT, 0, 0));
        setOpaque(false);

        this.tituloBase = titulo;
        this.modificado = false;

        this.etiquetaTitulo = new JLabel(titulo);
        this.etiquetaTitulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 8));

        this.botonCerrar = new JButton("X");
        this.botonCerrar.setFont(new Font("SansSerif", Font.BOLD, 14));
        this.botonCerrar.setPreferredSize(new Dimension(18, 18));
        this.botonCerrar.setFocusPainted(false);
        this.botonCerrar.setBorder(BorderFactory.createEmptyBorder());
        this.botonCerrar.setContentAreaFilled(false);
        this.botonCerrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        this.botonCerrar.setToolTipText("CERRAR PESTANIA");

        this.botonCerrar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                botonCerrar.setForeground(Color.RED);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                botonCerrar.setForeground(Color.GRAY);
            }
        });
        this.botonCerrar.setForeground(Color.GRAY);

        this.botonCerrar.addActionListener(e -> {
            if (accionCerrar != null) {
                accionCerrar.cerrar();
            }
        });

        add(this.etiquetaTitulo);
        add(this.botonCerrar);
    }

    public void setTitulo(String titulo) {
        this.tituloBase = titulo;
        actualizarTexto();
    }

    public void setModificado(boolean modificado) {
        this.modificado = modificado;
        actualizarTexto();
    }

    public boolean isModificado() {
        return modificado;
    }

    private void actualizarTexto() {
        etiquetaTitulo.setText(tituloBase + (modificado ? " *" : ""));
    }
}

