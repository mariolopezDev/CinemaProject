package dev.mariolopez.cinemaproject.gui;

import dev.mariolopez.cinemaproject.services.DataManager;
import dev.mariolopez.cinemaproject.models.Productora;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ProductoraFrame extends JFrame {
    private JTextField txtDescripcion;
    private JButton btnAgregar;
    private JTable tablaProductoras;
    private DefaultTableModel modeloTabla;

    public ProductoraFrame() {
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Registro de Productoras");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        add(createFormPanel(), BorderLayout.NORTH);
        add(createTablePanel(), BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null);
        updateTable();
    }

    private JPanel createFormPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 2));
        txtDescripcion = new JTextField();
        btnAgregar = new JButton("Agregar Productora");
        btnAgregar.addActionListener(e -> agregarProductora());
        panel.add(new JLabel("Descripción:"));
        panel.add(txtDescripcion);
        panel.add(btnAgregar);
        return panel;
    }

    private JScrollPane createTablePanel() {
        modeloTabla = new DefaultTableModel(new Object[]{"ID", "Descripción"}, 0);
        tablaProductoras = new JTable(modeloTabla);
        return new JScrollPane(tablaProductoras);
    }

    private void agregarProductora() {
        String descripcion = txtDescripcion.getText().trim();
        if (!descripcion.isEmpty()) {
            Productora productora = new Productora(DataManager.getInstance().getNextProductoraId(), descripcion, 10);
            DataManager.getInstance().addProductora(productora);
            updateTable();
            txtDescripcion.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "La descripción no puede estar vacía.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateTable() {
        modeloTabla.setRowCount(0);
        for (Productora productora : DataManager.getInstance().getProductoras()) {
            modeloTabla.addRow(new Object[]{productora.getId(), productora.getDescripcion()});
        }
    }

    @Override
    public void setVisible(boolean visible) {
        if (visible) {
            updateTable();
        }
        super.setVisible(visible);
    }
    
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            ProductoraFrame frame = new ProductoraFrame();
            frame.setVisible(true);
        });
    }

}
