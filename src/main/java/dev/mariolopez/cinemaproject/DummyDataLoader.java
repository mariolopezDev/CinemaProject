package dev.mariolopez.cinemaproject;

import dev.mariolopez.cinemaproject.models.Pelicula;
import dev.mariolopez.cinemaproject.models.Productora;
import dev.mariolopez.cinemaproject.services.DataManager;

public class DummyDataLoader {

    public static void loadDummyData() {
        DataManager dataManager = DataManager.getInstance();

        // Crear algunas productoras
        Productora productora1 = new Productora(dataManager.getNextProductoraId(), "Warner Bros.", 10);
        Productora productora2 = new Productora(dataManager.getNextProductoraId(), "Universal Pictures", 10);
        Productora productora3 = new Productora(dataManager.getNextProductoraId(), "Columbia Pictures", 10);

        // Agregar productoras al DataManager
        dataManager.addProductora(productora1);
        dataManager.addProductora(productora2);
        dataManager.addProductora(productora3);

        // Crear algunas películas y asignarlas a productoras
        productora1.enqueuePelicula(new Pelicula("Batman: El caballero de la noche asciende", "Acción", "Adultos"));
        productora1.enqueuePelicula(new Pelicula("Godzilla vs Kong", "Ciencia Ficción", "Juveniles"));

        productora2.enqueuePelicula(new Pelicula("Mi villano favorito", "Comedia", "Familiares"));
        productora2.enqueuePelicula(new Pelicula("Jurassic Park", "Ciencia Ficción", "Familiares"));
        productora2.enqueuePelicula(new Pelicula("Oppenheimer", "Drama", "Adultos"));

        productora3.enqueuePelicula(new Pelicula("Spider-Man", "Acción", "Juveniles"));
    }
}
