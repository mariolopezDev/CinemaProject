package dev.mariolopez.cinemaproject.gui;

import dev.mariolopez.cinemaproject.services.DataManager;
import dev.mariolopez.cinemaproject.models.Productora;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class EliminarProductoraFrame extends BaseFrame {
    private JButton btnEliminar;
    private JTable tablaProductoras;
    private DefaultTableModel modeloTabla;

    public EliminarProductoraFrame() {
        super("Eliminar Productoras", 500, 300);

        btnEliminar = new JButton("Eliminar Última Productora");
        btnEliminar.addActionListener(e -> eliminarProductora());
        add(btnEliminar, BorderLayout.NORTH);

        modeloTabla = new DefaultTableModel(new Object[]{"ID", "Descripción"}, 0);
        tablaProductoras = new JTable(modeloTabla);
        add(new JScrollPane(tablaProductoras), BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null);
        updateTable();
    }

    private void eliminarProductora() {
        if( DataManager.getInstance().getProductoras().length == 0) {
            showStatusMessage("No hay productoras para eliminar.", Color.BLUE);
            return;
        } else if (DataManager.getInstance().hasPeliculas()) {
            showStatusMessage("No se puede eliminar la última productora porque tiene películas.", Color.RED);
            return;
        } else {
            boolean removed = DataManager.getInstance().removeLastProductora();
            if (removed) {
                showStatusMessage("Productora eliminada con éxito.", Color.RED);
            } else {
                showStatusMessage("No se pudo eliminar la productora.", Color.RED);
            }
        }   
        updateTable();
    }

    private void updateTable() {
        modeloTabla.setRowCount(0);
        for (Productora productora : DataManager.getInstance().getProductoras()) {
            modeloTabla.addRow(new Object[]{productora.getId(), productora.getDescripcion()});
        }
    }

    public void setVisible(boolean visible) {
        if (visible) {
            updateTable();
        }
        super.setVisible(visible);
    }
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            EliminarProductoraFrame frame = new EliminarProductoraFrame();
            frame.setVisible(true);
        });
    }
}
