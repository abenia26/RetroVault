package es.izquierdofp.retrovault.vista;

import es.izquierdofp.retrovault.datos.JuegoDAO;
import es.izquierdofp.retrovault.modelo.Juego;

public class VentanaPrincipal extends javax.swing.JFrame {

    private final JuegoDAO dao = new JuegoDAO();
    private JuegosTableModel modeloTabla;

    public VentanaPrincipal() {
        initComponents();
        configurarTabla();
        configurarPopup();
        cargarDatos();
        setLocationRelativeTo(null);
    }

    private void configurarTabla() {
        modeloTabla = new JuegosTableModel(new java.util.ArrayList<>());
        tablaJuegos.setModel(modeloTabla);

        ConsolaColorRenderer renderer = new ConsolaColorRenderer();
        for (int i = 0; i < tablaJuegos.getColumnCount(); i++) {
            tablaJuegos.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }

        tablaJuegos.getColumnModel().getColumn(0).setPreferredWidth(35);
        tablaJuegos.getColumnModel().getColumn(1).setPreferredWidth(200);
        tablaJuegos.getColumnModel().getColumn(2).setPreferredWidth(110);
        tablaJuegos.getColumnModel().getColumn(3).setPreferredWidth(100);
        tablaJuegos.getColumnModel().getColumn(4).setPreferredWidth(50);
        tablaJuegos.getColumnModel().getColumn(5).setPreferredWidth(110);
        tablaJuegos.getColumnModel().getColumn(6).setPreferredWidth(100);

        tablaJuegos.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (e.getClickCount() == 2) editarSeleccionado();
            }
        });
    }

    private void configurarPopup() {
        tablaJuegos.setComponentPopupMenu(popupTabla);
        tablaJuegos.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {
                int fila = tablaJuegos.rowAtPoint(e.getPoint());
                if (fila >= 0) tablaJuegos.setRowSelectionInterval(fila, fila);
            }
        });
    }

    private void cargarDatos() {
        modeloTabla.setJuegos(dao.obtenerTodos());
        actualizarEstadisticas();
    }

    private void actualizarEstadisticas() {
        int total = dao.contarTotal();
        int comp = dao.contarCompletados();
        double med = dao.puntuacionMedia();
        lblTotal.setText(" Total: " + total);
        lblCompletados.setText(" | Completados: " + comp);
        lblMedia.setText(" | Media: "
                + (total == 0 ? "–" : String.format("%.1f ★", med)));
    }

    private void editarSeleccionado() {
        int fila = tablaJuegos.getSelectedRow();
        if (fila < 0) return;
        Juego j = modeloTabla.getJuego(fila);
        DialogoJuego dlg = new DialogoJuego(this, j);
        dlg.setVisible(true);
        if (dlg.isGuardado()) {
            dao.actualizar(dlg.getJuego());
            cargarDatos();
        }
    }

    private void eliminarSeleccionado() {
        int fila = tablaJuegos.getSelectedRow();
        if (fila < 0) return;
        Juego j = modeloTabla.getJuego(fila);
        int r = javax.swing.JOptionPane.showConfirmDialog(this,
                "¿Eliminar \"" + j.getTitulo() + "\" de tu biblioteca?",
                "Confirmar eliminación", javax.swing.JOptionPane.YES_NO_OPTION,
                javax.swing.JOptionPane.WARNING_MESSAGE);
        if (r == javax.swing.JOptionPane.YES_OPTION) {
            dao.eliminar(j.getId());
            cargarDatos();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popupTabla = new javax.swing.JPopupMenu();
        popAnyadir = new javax.swing.JMenuItem();
        popEditar = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        popEliminar = new javax.swing.JMenuItem();
        panelFiltros = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cmbConsola = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        cmbEstado = new javax.swing.JComboBox<>();
        btnFiltrar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnAnyadir = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaJuegos = new javax.swing.JTable();
        panelEstadisticas = new javax.swing.JPanel();
        lblTotal = new javax.swing.JLabel();
        lblCompletados = new javax.swing.JLabel();
        lblMedia = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        mniSalir = new javax.swing.JMenuItem();
        mnJuegos = new javax.swing.JMenu();
        mniAnyadir = new javax.swing.JMenuItem();
        mniEditar = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        mniEliminar = new javax.swing.JMenuItem();
        mnVista = new javax.swing.JMenu();
        mniActualizar = new javax.swing.JMenuItem();

        popAnyadir.setText("Añadir juego…");
        popAnyadir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnyadirActionPerformed(evt);
            }
        });
        popupTabla.add(popAnyadir);

        popEditar.setText("Editar juego…");
        popEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniEditarActionPerformed(evt);
            }
        });
        popupTabla.add(popEditar);
        popupTabla.add(jSeparator2);

        popEliminar.setText("Eliminar juego");
        popEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniEliminarActionPerformed(evt);
            }
        });
        popupTabla.add(popEliminar);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("RetroVault — Biblioteca de Videojuegos Retro");
        setMinimumSize(new java.awt.Dimension(900, 600));

        panelFiltros.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel1.setText("Consola:");
        panelFiltros.add(jLabel1);

        cmbConsola.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todas", "NES", "SNES", "Mega Drive", "Game Boy", "PlayStation", "Nintendo 64", "Game Gear", "Arcade" }));
        panelFiltros.add(cmbConsola);

        jLabel2.setText("Estado:");
        panelFiltros.add(jLabel2);

        cmbEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Pendiente", "Jugando", "Completado", "Abandonado" }));
        panelFiltros.add(cmbEstado);

        btnFiltrar.setText("Filtrar");
        btnFiltrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltrarActionPerformed(evt);
            }
        });
        panelFiltros.add(btnFiltrar);

        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });
        panelFiltros.add(btnLimpiar);

        btnAnyadir.setText("+ Añadir");
        btnAnyadir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnyadirActionPerformed(evt);
            }
        });
        panelFiltros.add(btnAnyadir);

        getContentPane().add(panelFiltros, java.awt.BorderLayout.PAGE_START);

        tablaJuegos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tablaJuegos.setGridColor(new java.awt.Color(60, 60, 60));
        tablaJuegos.setRowHeight(26);
        tablaJuegos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(tablaJuegos);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        panelEstadisticas.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        lblTotal.setText(" Total: 0");
        panelEstadisticas.add(lblTotal);

        lblCompletados.setText(" | Completados: 0");
        panelEstadisticas.add(lblCompletados);

        lblMedia.setText(" | Puntuación media: –");
        panelEstadisticas.add(lblMedia);

        getContentPane().add(panelEstadisticas, java.awt.BorderLayout.PAGE_END);

        jMenu1.setText("Archivo");

        mniSalir.setText("Salir");
        mniSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniSalirActionPerformed(evt);
            }
        });
        jMenu1.add(mniSalir);

        jMenuBar1.add(jMenu1);

        mnJuegos.setText("Juegos");

        mniAnyadir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        mniAnyadir.setText("Añadir juego…");
        mniAnyadir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnyadirActionPerformed(evt);
            }
        });
        mnJuegos.add(mniAnyadir);

        mniEditar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        mniEditar.setText("Editar juego…");
        mniEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniEditarActionPerformed(evt);
            }
        });
        mnJuegos.add(mniEditar);
        mnJuegos.add(jSeparator1);

        mniEliminar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_DELETE, 0));
        mniEliminar.setText("Eliminar juego");
        mniEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniEliminarActionPerformed(evt);
            }
        });
        mnJuegos.add(mniEliminar);

        jMenuBar1.add(mnJuegos);

        mnVista.setText("Vista");

        mniActualizar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, 0));
        mniActualizar.setText("Actualizar");
        mniActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniActualizarActionPerformed(evt);
            }
        });
        mnVista.add(mniActualizar);

        jMenuBar1.add(mnVista);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAnyadirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnyadirActionPerformed
        DialogoJuego dlg = new DialogoJuego(this, null);
        dlg.setVisible(true);
        if (dlg.isGuardado()) {
            dao.insertar(dlg.getJuego());
            cargarDatos();
        }
    }//GEN-LAST:event_btnAnyadirActionPerformed

    private void mniEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniEditarActionPerformed
        editarSeleccionado();
    }//GEN-LAST:event_mniEditarActionPerformed

    private void mniEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniEliminarActionPerformed
        eliminarSeleccionado();
    }//GEN-LAST:event_mniEliminarActionPerformed

    private void btnFiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltrarActionPerformed
        String cons = (String) cmbConsola.getSelectedItem();
        String est = (String) cmbEstado.getSelectedItem();
        java.util.List<Juego> lista;
        if (!"Todas".equals(cons)) lista = dao.obtenerPorConsola(cons);
        else if (!"Todos".equals(est)) lista = dao.obtenerPorEstado(est);
        else lista = dao.obtenerTodos();
        modeloTabla.setJuegos(lista);
    }//GEN-LAST:event_btnFiltrarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        cmbConsola.setSelectedIndex(0);
        cmbEstado.setSelectedIndex(0);
        cargarDatos();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void mniActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniActualizarActionPerformed
        cargarDatos();
    }//GEN-LAST:event_mniActualizarActionPerformed

    private void mniSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniSalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_mniSalirActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnyadir;
    private javax.swing.JButton btnFiltrar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JComboBox<String> cmbConsola;
    private javax.swing.JComboBox<String> cmbEstado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JLabel lblCompletados;
    private javax.swing.JLabel lblMedia;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JMenu mnJuegos;
    private javax.swing.JMenu mnVista;
    private javax.swing.JMenuItem mniActualizar;
    private javax.swing.JMenuItem mniAnyadir;
    private javax.swing.JMenuItem mniEditar;
    private javax.swing.JMenuItem mniEliminar;
    private javax.swing.JMenuItem mniSalir;
    private javax.swing.JPanel panelEstadisticas;
    private javax.swing.JPanel panelFiltros;
    private javax.swing.JMenuItem popAnyadir;
    private javax.swing.JMenuItem popEditar;
    private javax.swing.JMenuItem popEliminar;
    private javax.swing.JPopupMenu popupTabla;
    private javax.swing.JTable tablaJuegos;
    // End of variables declaration//GEN-END:variables
}
