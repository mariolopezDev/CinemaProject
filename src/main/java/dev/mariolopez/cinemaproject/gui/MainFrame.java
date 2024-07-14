package dev.mariolopez.cinemaproject.gui;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;

import java.awt.*;

public class MainFrame extends JFrame {
    private ProductoraFrame productoraFrame;
    private PeliculaFrame peliculaFrame;
    private EliminarPeliculaFrame eliminarPeliculaFrame;
    private TrasladarPeliculaFrame trasladarPeliculaFrame;
    private EliminarProductoraFrame eliminarProductoraFrame;

    public MainFrame() {
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Sistema de Gestión de Productoras Cinematográficas");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Buttons Panel
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton btnRegistrarProductoras = new JButton("Registrar Productoras");
        JButton btnRegistrarPeliculas = new JButton("Registrar Películas");
        JButton btnEliminarPeliculas = new JButton("Eliminar Películas");
        JButton btnTrasladarPeliculas = new JButton("Trasladar Películas");
        JButton btnEliminarProductoras = new JButton("Eliminar Productoras");

        btnRegistrarProductoras.addActionListener(e -> showProductoraFrame());
        btnRegistrarPeliculas.addActionListener(e -> showPeliculaFrame());
        btnEliminarPeliculas.addActionListener(e -> showEliminarPeliculaFrame());
        btnTrasladarPeliculas.addActionListener(e -> showTrasladarPeliculaFrame());
        btnEliminarProductoras.addActionListener(e -> showEliminarProductoraFrame());

        buttonsPanel.add(btnRegistrarProductoras);
        buttonsPanel.add(btnRegistrarPeliculas);
        buttonsPanel.add(btnEliminarPeliculas);
        buttonsPanel.add(btnTrasladarPeliculas);
        buttonsPanel.add(btnEliminarProductoras);

        add(buttonsPanel, BorderLayout.CENTER);

        // Footer Panel
        JPanel footerPanel = new JPanel();
        footerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JEditorPane lblCredits = new JEditorPane("text/html", "<html>Desarrollado por Mario López - GitHub: <a href='https://github.com/mariolopezDev'>mariolopezDev</a></html>");
        lblCredits.setEditable(false);
        lblCredits.setOpaque(false);
        lblCredits.addHyperlinkListener(e -> {
            if (HyperlinkEvent.EventType.ACTIVATED.equals(e.getEventType())) {
                try {
                    Desktop.getDesktop().browse(e.getURL().toURI());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        lblCredits.setFont(new Font("Helvetica", Font.PLAIN, 12));
        footerPanel.add(lblCredits);
        add(footerPanel, BorderLayout.SOUTH);



        setLocationRelativeTo(null);
    }

    private void showProductoraFrame() {
        if (productoraFrame == null) {
            productoraFrame = new ProductoraFrame();
        }
        productoraFrame.setVisible(true);
    }

    private void showPeliculaFrame() {
        if (peliculaFrame == null) {
            peliculaFrame = new PeliculaFrame();
        }
        peliculaFrame.setVisible(true);
    }

    private void showEliminarPeliculaFrame() {
        if (eliminarPeliculaFrame == null) {
            eliminarPeliculaFrame = new EliminarPeliculaFrame();
        }
        eliminarPeliculaFrame.setVisible(true);
    }

    private void showTrasladarPeliculaFrame() {
        if (trasladarPeliculaFrame == null) {
            trasladarPeliculaFrame = new TrasladarPeliculaFrame();
        }
        trasladarPeliculaFrame.setVisible(true);
    }

    private void showEliminarProductoraFrame() {
        if (eliminarProductoraFrame == null) {
            eliminarProductoraFrame = new EliminarProductoraFrame();
        }
        eliminarProductoraFrame.setVisible(true);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}
