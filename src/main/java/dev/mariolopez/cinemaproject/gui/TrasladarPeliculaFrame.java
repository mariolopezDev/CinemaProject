package dev.mariolopez.cinemaproject.gui;

import dev.mariolopez.cinemaproject.services.DataManager;
import dev.mariolopez.cinemaproject.models.Productora;
import dev.mariolopez.cinemaproject.models.Pelicula;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;

public class TrasladarPeliculaFrame extends BaseFrame {
    private JComboBox<Productora> cbProductoraOrigen;
    private JComboBox<Productora> cbProductoraDestino;
    private JButton btnTrasladar;
    private JTable tablaPeliculasDestino;
    private DefaultTableModel modeloTabla;

    public TrasladarPeliculaFrame() {
        super("Trasladar Películas", 700, 400);
        add(createSelectionPanel(), BorderLayout.NORTH);
        add(createTablePanel(), BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null);
    }

    private JPanel createSelectionPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(new JLabel("Productora Origen:"));
        cbProductoraOrigen = new JComboBox<>();
        panel.add(cbProductoraOrigen);

        panel.add(new JLabel("Productora Destino:"));
        cbProductoraDestino = new JComboBox<>();
        panel.add(cbProductoraDestino);

        btnTrasladar = new JButton("Trasladar Películas");
        btnTrasladar.addActionListener(e -> trasladarPeliculas());
        panel.add(btnTrasladar);

        updateProductoraComboBoxes();

        return panel;
    }

    private JScrollPane createTablePanel() {
        modeloTabla = new DefaultTableModel(new Object[]{"Id", "Nombre", "Género", "Tipo de Audiencia"}, 0);
        tablaPeliculasDestino = new JTable(modeloTabla);
        return new JScrollPane(tablaPeliculasDestino);
    }

    private void updateProductoraComboBoxes() {
        cbProductoraOrigen.removeAllItems();
        cbProductoraDestino.removeAllItems();
        for (Productora productora : DataManager.getInstance().getProductoras()) {
            cbProductoraOrigen.addItem(productora);
            cbProductoraDestino.addItem(productora);
        }
    }

    private void trasladarPeliculas() {
        Productora origen = (Productora) cbProductoraOrigen.getSelectedItem();
        Productora destino = (Productora) cbProductoraDestino.getSelectedItem();
        if (origen != null && destino != null && origen != destino) {
            int contador = 0;
            for (Pelicula pelicula : origen.getPeliculas()) {
                if (pelicula != null) {
                    contador += 1;
                    destino.enqueuePelicula(pelicula);
                    origen.dequeuePelicula();
                }
            }

            actualizarTabla(origen);
            actualizarTabla(destino);
            showStatusMessage(contador + " películas trasladadas de " + origen.getDescripcion() + " a " + destino.getDescripcion(), Color.BLUE);
        } else {
            showStatusMessage("Debe seleccionar productoras diferentes.", Color.RED);
        }
    }

    private void actualizarTabla(Productora productora) {
        modeloTabla.setRowCount(0);
        for (Pelicula pelicula : productora.getPeliculas()) {
            if (pelicula != null){
            modeloTabla.addRow(new Object[]{pelicula.getDni(), pelicula.getNombre(), pelicula.getGenero(), pelicula.getTipoAudiencia()});
            }
        }
    }

    @Override
    public void setVisible(boolean visible) {
        if (visible) {
            updateProductoraComboBoxes();
        }
        super.setVisible(visible);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            TrasladarPeliculaFrame frame = new TrasladarPeliculaFrame();
            frame.setVisible(true);
        });
    }
}
