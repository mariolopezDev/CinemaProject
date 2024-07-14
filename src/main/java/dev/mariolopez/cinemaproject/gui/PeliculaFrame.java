package dev.mariolopez.cinemaproject.gui;

import dev.mariolopez.cinemaproject.services.DataManager;
import dev.mariolopez.cinemaproject.models.Pelicula;
import dev.mariolopez.cinemaproject.models.Productora;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PeliculaFrame extends JFrame {
    private JComboBox<Productora> cbProductoras;
    private JTextField txtNombre;
    private JComboBox<String> cbGenero, cbTipoAudiencia;
    private JButton btnAgregar;
    private JTable tablaPeliculas;
    private DefaultTableModel modeloTabla;

    public PeliculaFrame() {
        setTitle("Registro de Películas");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        add(createSelectionPanel(), BorderLayout.NORTH);
        add(createFormPanel(), BorderLayout.CENTER);
        add(createTablePanel(), BorderLayout.SOUTH);
        pack();
        setLocationRelativeTo(null);
    }

    private JPanel createSelectionPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 2));
        cbProductoras = new JComboBox<>();
        updateProductoraComboBox();
        cbProductoras.addActionListener(e -> updatePeliculaTable());
        panel.add(new JLabel("Seleccionar Productora:"));
        panel.add(cbProductoras);
        return panel;
    }

    private JPanel createFormPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 2));
        txtNombre = new JTextField();
        cbGenero = new JComboBox<>(new String[]{"Acción", "Aventura", "Catástrofe", "Ciencia Ficción", "Comedia",                                        "Documental", "Drama", "Fantasía", "Musical", "Suspenso", "Terror"});
        cbTipoAudiencia = new JComboBox<>(new String[]{"Infantiles", "Juveniles", "Adultos"});
        btnAgregar = new JButton("Agregar Película");
        btnAgregar.addActionListener(e -> agregarPelicula());

        panel.add(new JLabel("Nombre:"));
        panel.add(txtNombre);
        panel.add(new JLabel("Género:"));
        panel.add(cbGenero);
        panel.add(new JLabel("Tipo de Audiencia:"));
        panel.add(cbTipoAudiencia);
        panel.add(btnAgregar);
        return panel;
    }

    private JScrollPane createTablePanel() {
        modeloTabla = new DefaultTableModel(new Object[]{"DNI", "Nombre", "Género", "Tipo de Audiencia"}, 0);
        tablaPeliculas = new JTable(modeloTabla);
        return new JScrollPane(tablaPeliculas);
    }

    private void agregarPelicula() {
        Productora productora = (Productora) cbProductoras.getSelectedItem();
        if (productora != null && !txtNombre.getText().trim().isEmpty()) {
            String genero = (String) cbGenero.getSelectedItem();
            String tipoAudiencia = (String) cbTipoAudiencia.getSelectedItem();
            Pelicula pelicula = new Pelicula(txtNombre.getText().trim(), genero, tipoAudiencia);
            productora.enqueuePelicula(pelicula);
            updatePeliculaTable();
            txtNombre.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Todos los campos deben ser completados.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateProductoraComboBox() {
        cbProductoras.removeAllItems();
        for (Productora productora : DataManager.getInstance().getProductoras()) {
            cbProductoras.addItem(productora);
        }
    }

    private void updatePeliculaTable() {
        Productora productora = (Productora) cbProductoras.getSelectedItem();
        modeloTabla.setRowCount(0);
        if (productora != null && productora.getPeliculas() != null) {
            for (Pelicula pelicula : productora.getPeliculas()) {
                if (pelicula != null) {
                    modeloTabla.addRow(new Object[]{pelicula.getDni(), pelicula.getNombre(), pelicula.getGenero(), pelicula.getTipoAudiencia()});
                }
            }
        }
    }
    @Override
    public void setVisible(boolean visible) {
        if (visible) {
            updateProductoraComboBox();
            updatePeliculaTable();
        }
        super.setVisible(visible);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            PeliculaFrame frame = new PeliculaFrame();
            frame.setVisible(true);
        });
    }
}