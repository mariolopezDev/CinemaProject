package dev.mariolopez.cinemaproject.gui;

import javax.swing.*;
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
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

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

        add(btnRegistrarProductoras);
        add(btnRegistrarPeliculas);
        add(btnEliminarPeliculas);
        add(btnTrasladarPeliculas);
        add(btnEliminarProductoras);

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
