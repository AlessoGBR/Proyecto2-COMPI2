package Frontend;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.function.Consumer;

public class ExploradorArchivos extends JPanel {

    private final JTree arbol;
    private File directorioRaiz;
    private final Consumer<Path> accionAbrirArchivo;

    public ExploradorArchivos(File carpetaInicial, Consumer<Path> accionAbrirArchivo) {
        super(new BorderLayout());
        this.directorioRaiz = carpetaInicial != null ? carpetaInicial : new File(System.getProperty("user.dir"));
        this.accionAbrirArchivo = accionAbrirArchivo;

        JPanel panelCabecera = new JPanel(new BorderLayout(5, 5));
        panelCabecera.setBorder(new EmptyBorder(6, 8, 6, 8));
        panelCabecera.setBackground(new Color(240, 240, 240));

        JLabel titulo = new JLabel("PROYECTO");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 12));

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 2, 0));
        panelBotones.setOpaque(false);

        JButton btnAbrirCarpeta = new JButton("ABRIR");
        btnAbrirCarpeta.setToolTipText("ABRIR CARPETA");
        btnAbrirCarpeta.setFont(new Font("SansSerif", Font.PLAIN, 11));
        btnAbrirCarpeta.setMargin(new Insets(2, 6, 2, 6));
        btnAbrirCarpeta.addActionListener(e -> seleccionarCarpeta());

        JButton btnRefrescar = new JButton("REFRESCAR");
        btnRefrescar.setToolTipText("VOLVER A LEER LA CARPETA");
        btnRefrescar.setFont(new Font("SansSerif", Font.PLAIN, 11));
        btnRefrescar.setMargin(new Insets(2, 6, 2, 6));
        btnRefrescar.addActionListener(e -> refrescar());

        panelBotones.add(btnAbrirCarpeta);
        panelBotones.add(btnRefrescar);

        panelCabecera.add(titulo, BorderLayout.WEST);
        panelCabecera.add(panelBotones, BorderLayout.EAST);

        this.arbol = new JTree();
        this.arbol.setFont(new Font("SansSerif", Font.PLAIN, 12));
        this.arbol.setCellRenderer(new RenderizadorNodoArchivo());
        this.arbol.setRootVisible(true);

        this.arbol.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    abrirElementoSeleccionado();
                }
            }
        });

        JScrollPane scrollArbol = new JScrollPane(this.arbol);
        scrollArbol.setBorder(BorderFactory.createEmptyBorder());

        add(panelCabecera, BorderLayout.NORTH);
        add(scrollArbol, BorderLayout.CENTER);

        refrescar();
    }

    public void cargarDirectorio(File directorio) {
        if (directorio != null && directorio.isDirectory()) {
            this.directorioRaiz = directorio;
            refrescar();
        }
    }

    public void refrescar() {
        if (directorioRaiz == null || !directorioRaiz.exists()) {
            return;
        }

        DefaultMutableTreeNode raizNodo = new DefaultMutableTreeNode(new ElementoArchivo(directorioRaiz));
        construirNodos(directorioRaiz, raizNodo);

        DefaultTreeModel modelo = new DefaultTreeModel(raizNodo);
        arbol.setModel(modelo);

        arbol.expandRow(0);
    }

    private void construirNodos(File dir, DefaultMutableTreeNode padre) {
        File[] hijos = dir.listFiles();
        if (hijos == null) return;

        Arrays.sort(hijos, (f1, f2) -> {
            if (f1.isDirectory() && !f2.isDirectory()) return -1;
            if (!f1.isDirectory() && f2.isDirectory()) return 1;
            return f1.getName().compareToIgnoreCase(f2.getName());
        });

        for (File hijo : hijos) {
            if (hijo.getName().startsWith(".") || hijo.getName().equals("target")) {
                continue;
            }

            DefaultMutableTreeNode nodoHijo = new DefaultMutableTreeNode(new ElementoArchivo(hijo));
            padre.add(nodoHijo);

            if (hijo.isDirectory()) {
                construirNodos(hijo, nodoHijo);
            }
        }
    }

    private void seleccionarCarpeta() {
        JFileChooser chooser = new JFileChooser(directorioRaiz);
        chooser.setDialogTitle("SELECCIONAR CARPETA PROYECTO");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int resultado = chooser.showOpenDialog(this);
        if (resultado == JFileChooser.APPROVE_OPTION) {
            cargarDirectorio(chooser.getSelectedFile());
        }
    }

    private void abrirElementoSeleccionado() {
        TreePath ruta = arbol.getSelectionPath();
        if (ruta == null) return;

        DefaultMutableTreeNode nodo = (DefaultMutableTreeNode) ruta.getLastPathComponent();
        if (nodo.getUserObject() instanceof ElementoArchivo elemento) {
            File f = elemento.getFile();
            if (f.isFile() && accionAbrirArchivo != null) {
                accionAbrirArchivo.accept(f.toPath());
            }
        }
    }

    public File getDirectorioRaiz() {
        return directorioRaiz;
    }

    public static class ElementoArchivo {
        private final File file;

        public ElementoArchivo(File file) {
            this.file = file;
        }

        public File getFile() {
            return file;
        }

        @Override
        public String toString() {
            return file.getName().isEmpty() ? file.getPath() : file.getName();
        }
    }

    private static class RenderizadorNodoArchivo extends DefaultTreeCellRenderer {
        @Override
        public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel,
                                                      boolean expanded, boolean leaf, int row, boolean hasFocus) {
            super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);

            if (value instanceof DefaultMutableTreeNode nodo) {
                if (nodo.getUserObject() instanceof ElementoArchivo elem) {
                    File f = elem.getFile();
                    if (f.isDirectory()) {
                        setText(f.getName().isEmpty() ? f.getPath() : f.getName());
                    } else {
                        String nombre = f.getName().toLowerCase();
                        if (nombre.endsWith(".pig")) {
                            setText(f.getName());
                        } else if (nombre.endsWith(".z")) {
                            setText(f.getName());
                        } else if (nombre.endsWith(".y")) {
                            setText(f.getName());
                        } else if (nombre.endsWith(".c") || nombre.endsWith(".h")) {
                            setText(f.getName());
                        } else {
                            setText(f.getName());
                        }
                    }
                }
            }
            return this;
        }
    }
}

