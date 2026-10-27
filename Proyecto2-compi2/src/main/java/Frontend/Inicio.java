/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Frontend;

import Backend.c3d.ControladorC3D;
import Backend.c3d.GeneradorC3D;
import Backend.c3d.TraductorC;
import Backend.Ast.ProgramNode;
import Backend.errores.ErrorCompilacion;
import Backend.proyecto.CompiladorProyecto;
import Backend.proyecto.ResultadoProyecto;
import Backend.riscv.ConsolaRiscV;
import Backend.riscv.EnsambladorRiscV;
import Backend.riscv.ErrorRiscV;
import Backend.riscv.ProgramaRiscV;
import Backend.riscv.SimuladorRiscV;
import Backend.riscv.TraductorRiscV;
import Backend.semantica.AnalizadorSemantico;
import Backend.visitor.ResultadoAnalisis;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author alesso
 */
public class Inicio extends javax.swing.JFrame {

    private static final Logger logger = Logger.getLogger(Inicio.class.getName());

    private static final int ANCHO_EXPLORADOR = 340;
    private JTabbedPane pestaniasEditor;
    private JTabbedPane pestaniasSalida;
    private JTextArea areaConsola;
    private JTextArea areaC3DLineal;
    private JTextArea areaCodigoC;
    private JTable tablaCuartetas;
    private JTable tablaErrores;
    private JTable tablaSimbolos;
    private ModelosTablas.ModeloTablaErrores modeloTablaErrores;
    private ModelosTablas.ModeloTablaCuartetas modeloTablaCuartetas;
    private ModelosTablas.ModeloTablaSimbolos modeloTablaSimbolos;
    private ExploradorArchivos exploradorArchivos;

    private Path ultimoArchivoCompilado;

    private JTextField campoEntrada;

    // RISC-V: codigo generado y su ejecucion en el simulador interno (hilo aparte)
    private JTextArea areaRiscV;
    private String codigoRiscV = "";
    private JButton btnEjecutar;
    private JButton btnDetener;
    private volatile SimuladorRiscV simuladorEnEjecucion;
    private Thread hiloEjecucion;
    private final LinkedBlockingQueue<String> entradasPrograma = new LinkedBlockingQueue<>();

    /**
     * Creates new form Inicio
     */
    public Inicio() {
        initComponents();
        inicializarComponentesPersonalizados();
    }

    private void inicializarComponentesPersonalizados() {
        setTitle("PROYECTO 2");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(1100, 750));
        setLocationRelativeTo(null);

        this.modeloTablaErrores = new ModelosTablas.ModeloTablaErrores();
        this.modeloTablaCuartetas = new ModelosTablas.ModeloTablaCuartetas();
        this.modeloTablaSimbolos = new ModelosTablas.ModeloTablaSimbolos();

        configurarPanelSuperior();
        configurarPanelIzquierdo();
        configurarPanelCentral();
        crearNuevaPestania();
    }

    private void configurarPanelSuperior() {
        jPanel1.setLayout(new BorderLayout());
        jPanel1.removeAll();

        JPanel toolbar = new JPanel(new FlowLayout(FlowLayout.LEFT, 6, 6));

        JButton btnNuevo = crearBotonHerramienta("NUEVO", "CREAR NUEVO ARCHIVO", e -> crearNuevaPestania());
        JButton btnAbrir = crearBotonHerramienta("ABRIR", "ABRIR ARCHIVO", e -> accionAbrirArchivo());
        JButton btnCarpeta = crearBotonHerramienta("CARPETA", "ABRIR CARPETA", e -> accionAbrirCarpeta());
        JButton btnGuardar = crearBotonHerramienta("GUARDAR", "GUARDAR ACTUAL", e -> accionGuardar());
        JButton btnCompilar = new JButton("COMPILAR");
        btnCompilar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnCompilar.addActionListener(e -> accionCompilarC3D());

        this.btnEjecutar = crearBotonHerramienta("EJECUTAR", "VOLVER A EJECUTAR EL RISC-V GENERADO", e -> ejecutarRiscV());
        this.btnEjecutar.setEnabled(false);
        this.btnDetener = crearBotonHerramienta("DETENER", "DETENER EL PROGRAMA EN EJECUCION", e -> detenerProgramaEnEjecucion());
        this.btnDetener.setEnabled(false);

        JButton btnLimpiar = crearBotonHerramienta("LIMPIAR", "LIMPIAR CONSOLA Y TABLAS", e -> limpiarResultados());

        toolbar.add(btnNuevo);
        toolbar.add(btnAbrir);
        toolbar.add(btnCarpeta);
        toolbar.add(btnGuardar);
        toolbar.add(btnCompilar);
        toolbar.add(btnEjecutar);
        toolbar.add(btnDetener);
        toolbar.add(btnLimpiar);
        JPanel panelEstado = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 8));
        panelEstado.setOpaque(false);
        jPanel1.add(toolbar, BorderLayout.WEST);
        jPanel1.add(panelEstado, BorderLayout.EAST);
        jPanel1.revalidate();
        jPanel1.repaint();
    }

    private JButton crearBotonHerramienta(String texto, String tooltip, java.awt.event.ActionListener accion) {
        JButton btn = new JButton(texto);
        btn.setFocusPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setToolTipText(tooltip);
        btn.addActionListener(accion);
        return btn;
    }

    private void configurarPanelIzquierdo() {
        jPanel2.setLayout(new BorderLayout());
        jPanel2.removeAll();

        File dirInicial = new File(System.getProperty("user.dir"));
        this.exploradorArchivos = new ExploradorArchivos(dirInicial, this::abrirArchivoEnPestania);
        jPanel2.add(this.exploradorArchivos, BorderLayout.CENTER);
        jPanel2.setPreferredSize(new Dimension(ANCHO_EXPLORADOR, jPanel2.getPreferredSize().height));
        jPanel2.revalidate();
        jPanel2.repaint();
    }

    private void configurarPanelCentral() {
        jPanel3.setLayout(new BorderLayout());
        jPanel3.removeAll();
        this.pestaniasEditor = new JTabbedPane();
        this.pestaniasSalida = new JTabbedPane();
        construirPestaniasSalida();
        JSplitPane splitVertical = new JSplitPane(JSplitPane.VERTICAL_SPLIT, this.pestaniasEditor, this.pestaniasSalida);
        splitVertical.setResizeWeight(0.62);
        splitVertical.setDividerSize(6);
        splitVertical.setContinuousLayout(true);

        jPanel3.add(splitVertical, BorderLayout.CENTER);
        jPanel3.revalidate();
        jPanel3.repaint();
    }

    private void construirPestaniasSalida() {
        this.areaConsola = new JTextArea();
        this.areaConsola.setEditable(false);
        this.areaConsola.setFont(new Font("Monospaced", Font.PLAIN, 13));
        this.areaConsola.setBackground(Color.WHITE);
        this.areaConsola.setForeground(Color.BLACK);
        this.areaConsola.setCaretColor(Color.BLACK);
        this.areaConsola.setMargin(new Insets(6, 8, 6, 8));

        JScrollPane scrollConsola = new JScrollPane(this.areaConsola);
        scrollConsola.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 0, new Color(210, 210, 210)));

        JPanel panelConsola = new JPanel(new BorderLayout());
        JPanel toolbarConsola = new JPanel(new FlowLayout(FlowLayout.RIGHT, 4, 2));

        JButton btnLimpiarConsola = new JButton("LIMPIAR CONSOLA");
        btnLimpiarConsola.setFont(new Font("SansSerif", Font.PLAIN, 11));
        btnLimpiarConsola.addActionListener(e -> areaConsola.setText(""));

        toolbarConsola.add(btnLimpiarConsola);

        this.campoEntrada = new JTextField();
        this.campoEntrada.setFont(new Font("Monospaced", Font.PLAIN, 13));
        this.campoEntrada.setEnabled(false);
        this.campoEntrada.setToolTipText("ENTRADA DEL PROGRAMA EN EJECUCION (ENTER PARA ENVIAR)");
        this.campoEntrada.addActionListener(e -> enviarEntradaAlPrograma());

        JPanel panelEntrada = new JPanel(new BorderLayout(6, 0));
        panelEntrada.setBorder(BorderFactory.createEmptyBorder(3, 6, 3, 6));
        JLabel etiquetaEntrada = new JLabel("ENTRADA:");
        etiquetaEntrada.setFont(new Font("SansSerif", Font.BOLD, 11));
        panelEntrada.add(etiquetaEntrada, BorderLayout.WEST);
        panelEntrada.add(this.campoEntrada, BorderLayout.CENTER);

        panelConsola.add(toolbarConsola, BorderLayout.NORTH);
        panelConsola.add(scrollConsola, BorderLayout.CENTER);
        panelConsola.add(panelEntrada, BorderLayout.SOUTH);

        this.tablaCuartetas = new JTable(this.modeloTablaCuartetas);
        configurarEstiloTabla(this.tablaCuartetas);

        this.areaC3DLineal = new JTextArea();
        this.areaC3DLineal.setEditable(false);
        this.areaC3DLineal.setFont(new Font("Monospaced", Font.PLAIN, 13));
        this.areaC3DLineal.setMargin(new Insets(6, 8, 6, 8));

        JScrollPane scrollTablaCuartetas = new JScrollPane(this.tablaCuartetas);
        JScrollPane scrollC3DLineal = new JScrollPane(this.areaC3DLineal);

        JSplitPane splitCuartetas = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scrollTablaCuartetas, scrollC3DLineal);
        splitCuartetas.setResizeWeight(0.55);
        splitCuartetas.setDividerSize(5);

        this.areaCodigoC = new JTextArea();
        this.areaCodigoC.setEditable(false);
        this.areaCodigoC.setFont(new Font("Monospaced", Font.PLAIN, 13));
        this.areaCodigoC.setMargin(new Insets(6, 8, 6, 8));

        JScrollPane scrollCodigoC = new JScrollPane(this.areaCodigoC);
        scrollCodigoC.setRowHeaderView(new NumeroLinea(this.areaCodigoC));

        JPanel panelCodigoC = new JPanel(new BorderLayout());
        JPanel toolbarCodigoC = new JPanel(new FlowLayout(FlowLayout.RIGHT, 4, 2));

        JButton btnCopiarC = new JButton("COPIAR CODIGO");
        btnCopiarC.setFont(new Font("SansSerif", Font.PLAIN, 11));
        btnCopiarC.addActionListener(e -> copiarAlPortapapeles(areaCodigoC.getText()));

        JButton btnGuardarC = new JButton("GUARDAR COMO .c");
        btnGuardarC.setFont(new Font("SansSerif", Font.PLAIN, 11));
        btnGuardarC.addActionListener(e -> accionGuardarArchivoC());

        toolbarCodigoC.add(btnCopiarC);
        toolbarCodigoC.add(btnGuardarC);
        panelCodigoC.add(toolbarCodigoC, BorderLayout.NORTH);
        panelCodigoC.add(scrollCodigoC, BorderLayout.CENTER);

        this.areaRiscV = new JTextArea();
        this.areaRiscV.setEditable(false);
        this.areaRiscV.setFont(new Font("Monospaced", Font.PLAIN, 13));
        this.areaRiscV.setMargin(new Insets(6, 8, 6, 8));

        JScrollPane scrollRiscV = new JScrollPane(this.areaRiscV);
        scrollRiscV.setRowHeaderView(new NumeroLinea(this.areaRiscV));

        JPanel panelRiscV = new JPanel(new BorderLayout());
        JPanel toolbarRiscV = new JPanel(new FlowLayout(FlowLayout.RIGHT, 4, 2));

        JButton btnCopiarRiscV = new JButton("COPIAR CODIGO");
        btnCopiarRiscV.setFont(new Font("SansSerif", Font.PLAIN, 11));
        btnCopiarRiscV.addActionListener(e -> copiarAlPortapapeles(areaRiscV.getText()));

        JButton btnGuardarRiscV = new JButton("GUARDAR COMO .s");
        btnGuardarRiscV.setFont(new Font("SansSerif", Font.PLAIN, 11));
        btnGuardarRiscV.addActionListener(e -> accionGuardarArchivoRiscV());

        toolbarRiscV.add(btnCopiarRiscV);
        toolbarRiscV.add(btnGuardarRiscV);
        panelRiscV.add(toolbarRiscV, BorderLayout.NORTH);
        panelRiscV.add(scrollRiscV, BorderLayout.CENTER);

        this.tablaErrores = new JTable(this.modeloTablaErrores);
        configurarEstiloTabla(this.tablaErrores);

        JScrollPane scrollErrores = new JScrollPane(this.tablaErrores);

        this.tablaSimbolos = new JTable(this.modeloTablaSimbolos);
        configurarEstiloTabla(this.tablaSimbolos);
        JScrollPane scrollSimbolos = new JScrollPane(this.tablaSimbolos);

        this.pestaniasSalida.addTab("CONSOLA", panelConsola);
        this.pestaniasSalida.addTab("CUARTETAS (C3D)", splitCuartetas);
        this.pestaniasSalida.addTab("RISC-V", panelRiscV);
        this.pestaniasSalida.addTab("CODIGO C", panelCodigoC);
        this.pestaniasSalida.addTab("TABLA DE ERRORES", scrollErrores);
        this.pestaniasSalida.addTab("TABLA DE SIMBOLOS", scrollSimbolos);
    }

    private void configurarEstiloTabla(JTable tabla) {
        tabla.setFont(new Font("SansSerif", Font.PLAIN, 12));
        tabla.setRowHeight(22);
        tabla.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 12));
        tabla.getTableHeader().setReorderingAllowed(false);

        DefaultTableCellRenderer renderCentro = new DefaultTableCellRenderer();
        renderCentro.setHorizontalAlignment(SwingConstants.CENTER);

        if (tabla.getColumnModel().getColumnCount() > 0) {
            tabla.getColumnModel().getColumn(0).setMaxWidth(50);
            tabla.getColumnModel().getColumn(0).setCellRenderer(renderCentro);
        }
    }

    public void crearNuevaPestania() {
        PestaniaEditor editor = new PestaniaEditor();
        String titulo = "nuevo_" + (pestaniasEditor.getTabCount() + 1) + ".pig";

        EncabezadoPestania enc = new EncabezadoPestania(titulo, () -> cerrarPestania(editor));
        editor.setEncabezado(enc);

        pestaniasEditor.addTab(null, editor);
        int indice = pestaniasEditor.indexOfComponent(editor);
        pestaniasEditor.setTabComponentAt(indice, enc);
        pestaniasEditor.setSelectedComponent(editor);
    }

    public void abrirArchivoEnPestania(Path archivo) {
        if (archivo == null || !Files.exists(archivo)) {
            return;
        }

        for (int i = 0; i < pestaniasEditor.getTabCount(); i++) {
            Component c = pestaniasEditor.getComponentAt(i);
            if (c instanceof PestaniaEditor editor) {
                if (archivo.equals(editor.getArchivoAsociado())) {
                    pestaniasEditor.setSelectedIndex(i);
                    return;
                }
            }
        }

        try {
            String contenido = Files.readString(archivo);
            PestaniaEditor editor = new PestaniaEditor(archivo, contenido);
            String titulo = archivo.getFileName().toString();

            EncabezadoPestania enc = new EncabezadoPestania(titulo, () -> cerrarPestania(editor));
            editor.setEncabezado(enc);

            pestaniasEditor.addTab(null, editor);
            int indice = pestaniasEditor.indexOfComponent(editor);
            pestaniasEditor.setTabComponentAt(indice, enc);
            pestaniasEditor.setSelectedComponent(editor);

            imprimirEnConsola("ARCHIVO ABIERTO: " + archivo.toAbsolutePath());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "ERROR AL LEER ARCHIVO:\n" + ex.getMessage(),
                    "ERROR DE LECTURA", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void cerrarPestania(PestaniaEditor editor) {
        if (editor == null) {
            return;
        }

        if (editor.isModificado()) {
            int opcion = JOptionPane.showConfirmDialog(this,
                    "EL ARCHIVO " + editor.getNombreArchivo() + " TIENE CAMBIOS NO GUARDADOS.\n¿QUIERES GUARDAR?",
                    "GUARDAR CAMBIOS", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);

            if (opcion == JOptionPane.YES_OPTION) {
                try {
                    if (editor.getArchivoAsociado() != null) {
                        editor.guardar();
                    } else {
                        accionGuardarComo();
                    }
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "ERROR AL GUARDAR: " + ex.getMessage(),
                            "ERROR", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } else if (opcion == JOptionPane.CANCEL_OPTION) {
                return;
            }
        }

        pestaniasEditor.remove(editor);
        if (pestaniasEditor.getTabCount() == 0) {
            crearNuevaPestania();
        }
    }

    public PestaniaEditor obtenerEditorActual() {
        Component c = pestaniasEditor.getSelectedComponent();
        if (c instanceof PestaniaEditor editor) {
            return editor;
        }
        return null;
    }

    // Recolorea los editores abiertos con los tipos (clases y estructuras) que encontro el compilador en todos los modulos
    private void colorearSegunCompilacion(ResultadoProyecto res) {
        Set<String> tipos = new HashSet<>();
        for (ResultadoAnalisis modulo : res.getModulos()) {
            ProgramNode programa = modulo.getPrograma();
            if (programa != null) {
                programa.getStructs().forEach(s -> tipos.add(s.getName()));
                programa.getClasses().forEach(c -> tipos.add(c.getName()));
            }
        }

        for (int i = 0; i < pestaniasEditor.getTabCount(); i++) {
            if (pestaniasEditor.getComponentAt(i) instanceof PestaniaEditor editor) {
                editor.aplicarTiposDelProyecto(tipos);
            }
        }
    }

    private void accionAbrirArchivo() {
        JFileChooser chooser = new JFileChooser(exploradorArchivos != null ? exploradorArchivos.getDirectorioRaiz() : null);
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("CODIGO PIG", "pig");
        chooser.setFileFilter(filtro);
        chooser.setDialogTitle("ABRIR ARCHIVO CODIGO");
        int res = chooser.showOpenDialog(this);
        if (res == JFileChooser.APPROVE_OPTION) {
            abrirArchivoEnPestania(chooser.getSelectedFile().toPath());
        }
    }

    private void accionAbrirCarpeta() {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("SELECCIONAR CARPETA PROYECTO");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int res = chooser.showOpenDialog(this);
        if (res == JFileChooser.APPROVE_OPTION) {
            if (exploradorArchivos != null) {
                exploradorArchivos.cargarDirectorio(chooser.getSelectedFile());
            }
        }
    }

    private void accionGuardar() {
        PestaniaEditor actual = obtenerEditorActual();
        if (actual == null) {
            return;
        }

        if (actual.getArchivoAsociado() == null) {
            accionGuardarComo();
            return;
        }

        try {
            actual.guardar();
            imprimirEnConsola("ARCHIVO GUARDADO CORRECTAMENTE: " + actual.getArchivoAsociado().toAbsolutePath());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "ERROR AL GUARDAR ARCHIVO:\n" + ex.getMessage(),
                    "ERROR AL GUARDAR", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void accionGuardarComo() {
        PestaniaEditor actual = obtenerEditorActual();
        if (actual == null) {
            return;
        }

        JFileChooser chooser = new JFileChooser(exploradorArchivos != null ? exploradorArchivos.getDirectorioRaiz() : null);
        chooser.setDialogTitle("GUARDARA ARCHIVO COMO...");
        chooser.setSelectedFile(new File(actual.getNombreArchivo()));

        int res = chooser.showSaveDialog(this);
        if (res == JFileChooser.APPROVE_OPTION) {
            try {
                Path destino = chooser.getSelectedFile().toPath();
                actual.guardarComo(destino);
                imprimirEnConsola("ARCHIVO GUARDADO COMO: " + destino.toAbsolutePath());
                if (exploradorArchivos != null) {
                    exploradorArchivos.refrescar();
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "ERROR AL GUARDAR ARCHIVO:\n" + ex.getMessage(),
                        "ERROR AL GUARDAR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void accionGuardarArchivoC() {
        if (areaCodigoC.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "NO HAY CODIGO GENERADO PARA GUARDAR",
                    "AVISO", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("GUARDAR CODIGO C");
        chooser.setSelectedFile(new File("salida.c"));

        int res = chooser.showSaveDialog(this);
        if (res == JFileChooser.APPROVE_OPTION) {
            try {
                Files.writeString(chooser.getSelectedFile().toPath(), areaCodigoC.getText());
                imprimirEnConsola("CODIGO C GUARDADO EN: " + chooser.getSelectedFile().getAbsolutePath());
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "ERROR AL GUARDAR C: " + ex.getMessage(),
                        "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void accionCompilarC3D() {
        PestaniaEditor editor = obtenerEditorActual();
        if (editor == null) {
            JOptionPane.showMessageDialog(this, "NO HAY NINGUN EDITOR ABIERTO", "AVISO", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (editor.getTexto().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "EL ARCHIVO ESTA VACIO", "AVISO", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Limpiar salidas previas (y detener un programa que siga corriendo)
        detenerProgramaEnEjecucion();
        limpiarResultados();

        // Si el archivo no ha sido guardado en disco, guardarlo o crear uno temporal
        Path archivoACompilar = editor.getArchivoAsociado();
        try {
            if (archivoACompilar == null) {
                // Guardar en un archivo temporal con la extension adecuada
                String ext = ".pig";
                Path temp = Files.createTempFile("temp_compilacion_", ext);
                Files.writeString(temp, editor.getTexto());
                archivoACompilar = temp;
            } else if (editor.isModificado()) {
                editor.guardar();
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "ERROR AL PREPARAR ARCHIVO AL COMPILAR: " + ex.getMessage(),
                    "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }

        this.ultimoArchivoCompilado = archivoACompilar;

        imprimirEnConsola("================================================================================");
        imprimirEnConsola(" INICIANDO COMPILACIÓN: " + archivoACompilar.getFileName());
        imprimirEnConsola("================================================================================");

        try {
            // FASE 1: Analisis Sintactico y Resolucion de Modulos
            // la carpeta abierta en el explorador es la raiz del proyecto: ahi se buscan las clases .z
            Path raizProyecto = exploradorArchivos != null && exploradorArchivos.getDirectorioRaiz() != null
                    ? exploradorArchivos.getDirectorioRaiz().toPath() : null;
            ResultadoProyecto res = CompiladorProyecto.compilar(archivoACompilar, raizProyecto);
            modeloTablaErrores.setErrores(res.getErrores());
            colorearSegunCompilacion(res);

            if (res.getGestorErrores().hayErrores()) {
                imprimirEnConsola("[ERROR] SE ENCONTRARON " + res.getErrores().size()
                        + " ERRORES DURANTE EL ANALISIS SINTACTICO: ");
                for (ErrorCompilacion err : res.getErrores()) {
                    imprimirEnConsola("  • " + err);
                }
                pestaniasSalida.setSelectedComponent(tablaErrores.getParent().getParent());
                return;
            }

            imprimirEnConsola("[OK] ANALISIS LEXICO/SINTACTICO PASADO");

            // FASE 2: Analisis Semantico y Verificacion de Tipos
            AnalizadorSemantico semantico = new AnalizadorSemantico(res.getGestorErrores());
            boolean semValido = semantico.analizar(res.getPrograma());

            // Actualizar tabla de simbolos y errores
            modeloTablaSimbolos.cargarDesdeTabla(semantico.getTablaSimbolos());
            modeloTablaErrores.setErrores(res.getErrores());
            colorearSegunCompilacion(res);

            if (!semValido || res.getGestorErrores().hayErrores()) {
                imprimirEnConsola("[ERROR] SE ENCONTRARON " + res.getErrores().size()
                        + " ERRORES SEMANTICOS: ");
                for (ErrorCompilacion err : res.getErrores()) {
                    imprimirEnConsola("  • " + err);
                }
                pestaniasSalida.setSelectedComponent(tablaErrores.getParent().getParent());
                return;
            }

            imprimirEnConsola("[OK] ANALISIS SEMANTICO PASADO");

            // FASE 3: Generacion de Codigo de Tres Direcciones (C3D)
            ControladorC3D controladorC3D = new ControladorC3D();
            GeneradorC3D genC3D = new GeneradorC3D(controladorC3D, semantico.getTablaSimbolos());
            genC3D.generar(res.getPrograma());

            modeloTablaCuartetas.setCuartetas(controladorC3D.getCuartetas());
            areaC3DLineal.setText(controladorC3D.obtenerCodigoC3D());

            imprimirEnConsola("[OK] GENERACION C3D " + controladorC3D.getCuartetas().size() + " CUARTETAS GENERADAS");

            // FASE 4: Traduccion a Codigo C
            String codigoC = TraductorC.traducir(controladorC3D);
            areaCodigoC.setText(codigoC);

            imprimirEnConsola("[OK] TRADUCCION A C COMPLETADA");

            // FASE 5: Traduccion de las cuartetas a RISC-V
            codigoRiscV = TraductorRiscV.traducir(controladorC3D);
            areaRiscV.setText(codigoRiscV);
            areaRiscV.setCaretPosition(0);
            btnEjecutar.setEnabled(true);

            imprimirEnConsola("[OK] TRADUCCION A RISC-V COMPLETADA (" + areaRiscV.getLineCount() + " LINEAS)");
            imprimirEnConsola("================================================================================");
            imprimirEnConsola("[OK] COMPILACION EXITOSA");
            imprimirEnConsola("================================================================================");

            // FASE 6: ensamblar y ejecutar el RISC-V dentro de la aplicacion
            pestaniasSalida.setSelectedIndex(0);
            ejecutarRiscV();
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "ERROR INESPERADO EN COMPILACION", ex);
            imprimirEnConsola("[ERROR INESPERADO] " + ex.getMessage());
            JOptionPane.showMessageDialog(this, "OCURRION UN ERROR DURANTE LA COMPILACION:\n" + ex.getMessage(),
                    "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }    

    // Ensambla el RISC-V generado y lo ejecuta en el simulador interno, en un hilo aparte
    // para que la interfaz siga respondiendo (entrada por teclado y boton DETENER)
    private void ejecutarRiscV() {
        if (codigoRiscV == null || codigoRiscV.isBlank()) {
            JOptionPane.showMessageDialog(this, "PRIMERO COMPILE UN PROGRAMA", "AVISO", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if (simuladorEnEjecucion != null) {
            detenerProgramaEnEjecucion();
            imprimirEnConsola("[AVISO] SE DETUVO LA EJECUCION ANTERIOR");
        }

        EnsambladorRiscV ensamblador = new EnsambladorRiscV();
        ProgramaRiscV programa = ensamblador.ensamblar(codigoRiscV);
        if (programa == null) {
            imprimirEnConsola("[ERROR] EL ENSAMBLADOR RISC-V ENCONTRO " + ensamblador.getErrores().size() + " ERROR(ES):");
            for (ErrorRiscV error : ensamblador.getErrores()) {
                imprimirEnConsola("  • " + error);
            }
            return;
        }

        imprimirEnConsola("[OK] RISC-V ENSAMBLADO: " + programa.getCantidadInstrucciones() + " INSTRUCCIONES");
        imprimirEnConsola("==================== EJECUCION (SIMULADOR RISC-V INTERNO) ====================");

        entradasPrograma.clear();
        SimuladorRiscV simulador = new SimuladorRiscV(programa, new ConsolaRiscV() {
            @Override
            public void escribir(String texto) {
                agregarAConsola(texto);
            }

            @Override
            public String leerLinea() throws InterruptedException {
                SwingUtilities.invokeLater(() -> campoEntrada.requestFocusInWindow());
                return entradasPrograma.take();
            }
        });
        simuladorEnEjecucion = simulador;
        campoEntrada.setEnabled(true);
        btnDetener.setEnabled(true);

        hiloEjecucion = new Thread(() -> {
            SimuladorRiscV.ResultadoEjecucion resultado = simulador.ejecutar();
            SwingUtilities.invokeLater(() -> finalizarEjecucion(simulador, resultado));
        }, "simulador-riscv");
        hiloEjecucion.setDaemon(true);
        hiloEjecucion.start();
    }

    private void finalizarEjecucion(SimuladorRiscV simulador, SimuladorRiscV.ResultadoEjecucion resultado) {
        if (simuladorEnEjecucion != simulador) {
            return; // ya empezo otra ejecucion
        }
        simuladorEnEjecucion = null;
        campoEntrada.setEnabled(false);
        btnDetener.setEnabled(false);

        String mensaje;
        if (resultado.error() == null) {
            mensaje = "[FIN] EL PROGRAMA TERMINO CON CODIGO " + resultado.codigoSalida();
        } else if (resultado.detenido()) {
            mensaje = "[DETENIDO] " + resultado.error();
        } else {
            mensaje = "[ERROR DE EJECUCION] " + resultado.error();
        }
        String texto = areaConsola.getText();
        String separador = texto.isEmpty() || texto.endsWith("\n") ? "" : "\n";
        imprimirEnConsola(separador + mensaje + " (" + resultado.instrucciones() + " INSTRUCCIONES EJECUTADAS)");
    }

    private void enviarEntradaAlPrograma() {
        if (simuladorEnEjecucion == null) {
            return;
        }
        String texto = campoEntrada.getText();
        campoEntrada.setText("");
        agregarAConsola(texto + "\n"); // eco de lo que escribio el usuario
        entradasPrograma.offer(texto);
    }

    private void detenerProgramaEnEjecucion() {
        SimuladorRiscV simulador = simuladorEnEjecucion;
        if (simulador == null) {
            return;
        }
        simulador.detener();
        Thread hilo = hiloEjecucion;
        if (hilo != null) {
            hilo.interrupt(); // despierta al programa si estaba esperando entrada
        }
    }

    private void accionGuardarArchivoRiscV() {
        if (areaRiscV.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "NO HAY CODIGO RISC-V PARA GUARDAR",
                    "AVISO", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        String nombre = "salida.s";
        if (ultimoArchivoCompilado != null) {
            String archivo = ultimoArchivoCompilado.getFileName().toString();
            int punto = archivo.lastIndexOf('.');
            nombre = (punto > 0 ? archivo.substring(0, punto) : archivo) + ".s";
        }
        JFileChooser chooser = new JFileChooser(ultimoArchivoCompilado != null
                ? ultimoArchivoCompilado.getParent().toFile() : null);
        chooser.setDialogTitle("GUARDAR CODIGO RISC-V");
        chooser.setSelectedFile(new File(nombre));

        if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                Files.writeString(chooser.getSelectedFile().toPath(), areaRiscV.getText());
                imprimirEnConsola("CODIGO RISC-V GUARDADO EN: " + chooser.getSelectedFile().getAbsolutePath());
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "ERROR AL GUARDAR RISC-V: " + ex.getMessage(),
                        "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void limpiarResultados() {
        areaConsola.setText("");
        areaC3DLineal.setText("");
        areaCodigoC.setText("");
        areaRiscV.setText("");
        codigoRiscV = "";
        btnEjecutar.setEnabled(false);
        modeloTablaErrores.limpiar();
        modeloTablaCuartetas.limpiar();
        modeloTablaSimbolos.limpiar();
    }

    private void imprimirEnConsola(String mensaje) {
        agregarAConsola(mensaje + "\n");
    }

    private void agregarAConsola(String texto) {
        SwingUtilities.invokeLater(() -> {
            areaConsola.append(texto);
            areaConsola.setCaretPosition(areaConsola.getDocument().getLength());
        });
    }

    private void copiarAlPortapapeles(String texto) {
        if (texto != null && !texto.isEmpty()) {
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(texto), null);
            JOptionPane.showMessageDialog(this, "TEXTO COPIADO.", "INFO", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 246, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 960, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 659, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    // End of variables declaration//GEN-END:variables
}
