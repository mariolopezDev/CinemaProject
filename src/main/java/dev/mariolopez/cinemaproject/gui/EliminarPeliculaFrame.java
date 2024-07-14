package dev.mariolopez.cinemaproject.gui;

import dev.mariolopez.cinemaproject.services.DataManager;
import dev.mariolopez.cinemaproject.models.Productora;
import dev.mariolopez.cinemaproject.models.Pelicula;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class EliminarPeliculaFrame extends JFrame {
    private JComboBox<Productora> cbProductoras;
    private JButton btnEliminar;
    private JTable tablaPeliculas;
    private DefaultTableModel modeloTabla;

    public EliminarPeliculaFrame() {
        setTitle("Eliminar Películas");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        add(createSelectionPanel(), BorderLayout.NORTH);
        add(createTablePanel(), BorderLayout.CENTER);
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
        btnEliminar = new JButton("Eliminar Primera Película");
        btnEliminar.addActionListener(e -> eliminarPelicula());
        panel.add(btnEliminar);
        return panel;
    }

    private JScrollPane createTablePanel() {
        modeloTabla = new DefaultTableModel(new Object[]{"DNI", "Nombre", "Género", "Tipo de Audiencia"}, 0);
        tablaPeliculas = new JTable(modeloTabla);
        return new JScrollPane(tablaPeliculas);
    }

    private void eliminarPelicula() {
        Productora productoraSeleccionada = (Productora) cbProductoras.getSelectedItem();
        if (productoraSeleccionada != null) {
            Pelicula peliculaEliminada = productoraSeleccionada.dequeuePelicula();
            if (peliculaEliminada != null) {
                JOptionPane.showMessageDialog(this, "Película eliminada: " + peliculaEliminada.getNombre(), "Operación Exitosa", JOptionPane.INFORMATION_MESSAGE);
                updatePeliculaTable();
            } else {
                JOptionPane.showMessageDialog(this, "No hay películas para eliminar en la productora seleccionada", "Error", JOptionPane.ERROR_MESSAGE);
            }
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
        if (productora != null) {
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
            EliminarPeliculaFrame frame = new EliminarPeliculaFrame();
            frame.setVisible(true);
        });
    }
}
