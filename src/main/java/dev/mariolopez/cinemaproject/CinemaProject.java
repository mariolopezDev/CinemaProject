package dev.mariolopez.cinemaproject;

import dev.mariolopez.cinemaproject.gui.MainFrame;

/**
 *
 * @mariolopez
 */
public class CinemaProject {

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {

            DummyDataLoader.loadDummyData(); // OPCIONAL: Carga de datos de prueba
            
            new MainFrame().setVisible(true); // Mostrar la ventana principal
        });
    }
}


