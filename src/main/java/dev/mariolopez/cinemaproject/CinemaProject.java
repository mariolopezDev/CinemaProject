package dev.mariolopez.cinemaproject;

import dev.mariolopez.cinemaproject.gui.MainFrame;

/**
 *
 * @mariolopez
 */
public class CinemaProject {

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            // Cargar datos de prueba
            //DummyDataLoader.loadDummyData();
            
            new MainFrame().setVisible(true);
        });
    }
}
