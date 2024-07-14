package dev.mariolopez.cinemaproject.gui;

import dev.mariolopez.cinemaproject.services.DataManager;
import dev.mariolopez.cinemaproject.models.Productora;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ProductoraFrame extends BaseFrame {
    private JTextField txtDescripcion;
    private JButton btnAgregar;
    private JTable tablaProductoras;
    private DefaultTableModel modeloTabla;

    public ProductoraFrame() {
        super("Registro de Productoras", 500, 300);
        add(createFormPanel(), BorderLayout.NORTH);
        add(createTablePanel(), BorderLayout.CENTER);
        pack();
        updateTable();
    }

    private JPanel createFormPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 3, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    
        txtDescripcion = new JTextField();
        txtDescripcion.setFont(new Font("SansSerif", Font.BOLD, 14)); 
    
        btnAgregar = new JButton("Agregar Productora");
        btnAgregar.setFont(new Font("SansSerif", Font.BOLD, 14)); 
        btnAgregar.addActionListener(e -> agregarProductora());
    
        JLabel lblDescripcion = new JLabel("Descripción:");
        lblDescripcion.setFont(new Font("SansSerif", Font.PLAIN, 14));  
    
        panel.add(lblDescripcion);
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
            showStatusMessage("Productora id " + productora.getId() + " - " + descripcion + " agregada con éxito.", Color.blue);
            txtDescripcion.setText("");
        } else {
            showStatusMessage("La descripción no puede estar vacía.", Color.RED);
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
