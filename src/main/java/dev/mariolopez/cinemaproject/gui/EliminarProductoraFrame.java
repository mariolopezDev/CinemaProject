package dev.mariolopez.cinemaproject.gui;

import dev.mariolopez.cinemaproject.services.DataManager;
import dev.mariolopez.cinemaproject.models.Productora;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class EliminarProductoraFrame extends JFrame {
    private JButton btnEliminar;
    private JTable tablaProductoras;
    private DefaultTableModel modeloTabla;

    public EliminarProductoraFrame() {
        setTitle("Eliminar Productoras");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

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
        if (DataManager.getInstance().removeLastProductora()) {
            JOptionPane.showMessageDialog(this, "Última productora eliminada exitosamente.", "Eliminación Exitosa", JOptionPane.INFORMATION_MESSAGE);
            updateTable();
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo eliminar la última productora. Puede tener películas asociadas o no hay productoras registradas.", "Error", JOptionPane.ERROR_MESSAGE);
        }
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
