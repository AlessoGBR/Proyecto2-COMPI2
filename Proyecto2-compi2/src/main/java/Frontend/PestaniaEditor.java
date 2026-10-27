package Frontend;

import Frontend.coloreado.ColoreadorSintaxis;
import Frontend.coloreado.LenguajeColoreado;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Set;

public class PestaniaEditor extends JPanel {

    private static final int RETARDO_COLOREADO_MS = 200;

    private Path archivoAsociado;
    private final EditorCodigo areaTexto;
    private final JScrollPane scrollPane;
    private final NumeroLinea numeroLinea;
    private final Timer temporizadorColoreado;
    private EncabezadoPestania encabezado;
    private boolean modificado;

    private boolean usaSaltoWindows;

    private Set<String> tiposDelProyecto = Set.of();

    public PestaniaEditor(Path archivoAsociado, String contenidoInicial) {
        super(new BorderLayout());

        this.archivoAsociado = archivoAsociado;
        this.modificado = false;

        this.areaTexto = new EditorCodigo(new Font("Monospaced", Font.PLAIN, 14));
        this.areaTexto.setMargin(new Insets(4, 8, 4, 8));
        if (contenidoInicial != null) {
            this.usaSaltoWindows = contenidoInicial.contains("\r\n");
            this.areaTexto.setText(contenidoInicial.replace("\r\n", "\n"));
            this.areaTexto.setCaretPosition(0);
        }

        this.numeroLinea = new NumeroLinea(this.areaTexto);
        this.scrollPane = new JScrollPane(this.areaTexto);
        this.scrollPane.setRowHeaderView(this.numeroLinea);
        this.scrollPane.setBorder(BorderFactory.createEmptyBorder());

        add(this.scrollPane, BorderLayout.CENTER);

        this.temporizadorColoreado = new Timer(RETARDO_COLOREADO_MS, e -> colorear());
        this.temporizadorColoreado.setRepeats(false);

        configurarListeners();
        colorear();
    }

    public PestaniaEditor() {
        this(null, "");
    }

    private void configurarListeners() {

        this.areaTexto.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                textoEditado();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                textoEditado();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });
    }

    private void textoEditado() {
        marcarModificado(true);
        temporizadorColoreado.restart();
    }

    public LenguajeColoreado getLenguaje() {
        if (archivoAsociado == null) {
            return LenguajeColoreado.PIG_LATIN;
        }
        return LenguajeColoreado.desdeNombreArchivo(archivoAsociado.getFileName().toString());
    }

    public void colorear() {
        temporizadorColoreado.stop();
        LenguajeColoreado lenguaje = getLenguaje();
        List<ColoreadorSintaxis.Segmento> segmentos = lenguaje != null
                ? ColoreadorSintaxis.analizar(areaTexto.getText(), lenguaje, tiposDelProyecto)
                : List.of();
        ColoreadorSintaxis.aplicar(areaTexto.getStyledDocument(), segmentos);
    }

    public void aplicarTiposDelProyecto(Set<String> tipos) {
        tiposDelProyecto = Set.copyOf(tipos);
        colorear();
    }

    public String detectarLenguaje() {
        if (archivoAsociado == null) {
            return "SIN GUARDAR";
        }
        String nombre = archivoAsociado.getFileName().toString().toLowerCase();
        if (nombre.endsWith(".pig")) {
            return "Pig Latin (.pig)";
        } else if (nombre.endsWith(".z")) {
            return "Zetariano (.z)";
        } else if (nombre.endsWith(".y")) {
            return "Y? (.y)";
        }
        return "Texto Plano";
    }

    public void marcarModificado(boolean mod) {
        this.modificado = mod;
        if (encabezado != null) {
            encabezado.setModificado(mod);
        }
    }

    private String textoParaGuardar() {
        String texto = areaTexto.getText();
        return usaSaltoWindows ? texto.replace("\n", "\r\n") : texto;
    }

    public boolean guardar() throws IOException {
        if (archivoAsociado == null) {
            return false;
        }
        Files.writeString(archivoAsociado, textoParaGuardar());
        marcarModificado(false);
        return true;
    }

    public void guardarComo(Path nuevoArchivo) throws IOException {
        this.archivoAsociado = nuevoArchivo;
        Files.writeString(nuevoArchivo, textoParaGuardar());
        marcarModificado(false);
        if (encabezado != null) {
            encabezado.setTitulo(nuevoArchivo.getFileName().toString());
        }
        colorear(); // la extension nueva puede ser de otro lenguaje
    }

    public String getNombreArchivo() {
        if (archivoAsociado != null) {
            return archivoAsociado.getFileName().toString();
        }
        return "NUEVO ARCHIVO";
    }

    public Path getArchivoAsociado() {
        return archivoAsociado;
    }

    public void setArchivoAsociado(Path archivoAsociado) {
        this.archivoAsociado = archivoAsociado;
        if (encabezado != null && archivoAsociado != null) {
            encabezado.setTitulo(archivoAsociado.getFileName().toString());
        }
        colorear();
    }

    public String getTexto() {
        return areaTexto.getText();
    }

    public void setTexto(String texto) {
        this.usaSaltoWindows = texto.contains("\r\n");
        this.areaTexto.setText(texto.replace("\r\n", "\n"));
        this.areaTexto.setCaretPosition(0);
        marcarModificado(false);
        colorear();
    }

    public EditorCodigo getAreaTexto() {
        return areaTexto;
    }

    public boolean isModificado() {
        return modificado;
    }

    public EncabezadoPestania getEncabezado() {
        return encabezado;
    }

    public void setEncabezado(EncabezadoPestania encabezado) {
        this.encabezado = encabezado;
    }
}
