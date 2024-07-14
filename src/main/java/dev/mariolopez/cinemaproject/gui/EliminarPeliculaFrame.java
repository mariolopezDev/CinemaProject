package dev.mariolopez.cinemaproject.gui;

import dev.mariolopez.cinemaproject.services.DataManager;
import dev.mariolopez.cinemaproject.models.Productora;
import dev.mariolopez.cinemaproject.models.Pelicula;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class EliminarPeliculaFrame extends BaseFrame {
    private JComboBox<Productora> cbProductoras;
    private JButton btnEliminar;
    private JTable tablaPeliculas;
    private DefaultTableModel modeloTabla;

    public EliminarPeliculaFrame() {
        super("Eliminar Películas", 600, 400);
        add(createSelectionPanel(), BorderLayout.NORTH);
        add(createTablePanel(), BorderLayout.CENTER);
        
        pack();
        updateProductoraComboBox();
    }

    private JPanel createSelectionPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 3));
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
                showStatusMessage("Película eliminada: " + peliculaEliminada.getNombre(), Color.RED);
                updatePeliculaTable();
            } else {
                showStatusMessage("Productora: " + productoraSeleccionada.getDescripcion() + " no tiene películas para eliminar.", Color.RED);
            }
        } else {
            showStatusMessage("No hay productoras para eliminar películas.", Color.BLUE);
        }
    }

    private void updateProductoraComboBox() {
        cbProductoras.removeAllItems();
        for (Productora productora : DataManager.getInstance().getProductoras()) {
            if (productora != null) {
                cbProductoras.addItem(productora);
            } 
        }
    }

    private void updatePeliculaTable() {
        Productora productora = (Productora) cbProductoras.getSelectedItem();
        modeloTabla.setRowCount(0);
        if (productora != null) {
            for (Pelicula pelicula : productora.getPeliculas()) {
                if (pelicula != null) {
                    modeloTabla.addRow(new Object[]{pelicula.getDni(), pelicula.getNombre(), pelicula.getGenero(), pelicula.getTipoAudiencia()});
                } else {
                    showStatusMessage("No hay películas para mostrar.", Color.BLUE);
                }
            }
        } else if (cbProductoras.getItemCount() > 0) {
            showStatusMessage("No hay productoras para mostrar.", Color.BLUE);
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
