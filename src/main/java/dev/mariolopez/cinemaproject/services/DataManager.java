package dev.mariolopez.cinemaproject.services;

import dev.mariolopez.cinemaproject.models.Productora;
import dev.mariolopez.cinemaproject.models.Pelicula;

public class DataManager {
    private static DataManager instance;
    private Productora[] productoras;
    private int count;  // Número actual de productoras almacenadas

    private DataManager() {
        productoras = new Productora[10];  // Capacidad inicial
        count = 0;
    }

    public static DataManager getInstance() { // Singleton
        if (instance == null) {
            instance = new DataManager();
        }
        return instance;
    }

    public void addProductora(Productora productora) {  // Agrega una productora al arreglo
        if (count >= productoras.length) {
            expandProductorasCapacity();
        }
        productoras[count++] = productora;
    }

    public boolean removeProductora(int id) { // Elimina una productora por ID
        for (int i = 0; i < count; i++) {
            if (productoras[i].getId() == id) {
                productoras[i] = productoras[count - 1];
                productoras[--count] = null;
                return true;
            }
        }
        return false;
    }

    private void expandProductorasCapacity() { // Duplica la capacidad del arreglo de productoras
        Productora[] newProductoras = new Productora[productoras.length * 2];
        System.arraycopy(productoras, 0, newProductoras, 0, count);
        productoras = newProductoras;
    }

    public Productora getProductoraById(int id) { // Busca una productora por ID
        for (int i = 0; i < count; i++) {
            if (productoras[i].getId() == id) {
                return productoras[i];
            }
        }
        return null;
    }

    public void trasladarPeliculas(int idOrigen, int idDestino) { // Traslada todas las películas de una productora a otra
        Productora origen = getProductoraById(idOrigen);
        Productora destino = getProductoraById(idDestino);

        if (origen == null || destino == null) {
            // Handle error: productora no encontrada            
            return;
        }
        if (origen == destino) {
            // Handle error: productoras iguales
            return;
        }
        for (int i = 0; i < origen.size(); i++) { // Trasladar todas las películas
            Pelicula pelicula = origen.dequeuePelicula();
            if (pelicula != null) {
                destino.enqueuePelicula(pelicula);
            } else {
                // Handle error: cola vacía
                break;
            }
        }
    }
    
    
    //getProductoras
    // Retorna una copia del arreglo de productoras 
    public Productora[] getProductoras() {
        Productora[] productoras = new Productora[count];
        System.arraycopy(this.productoras, 0, productoras, 0, count);
        return productoras;
    }

    //getNextProductoraId 
    // Retorna el siguiente ID disponible para una nueva productora
    public int getNextProductoraId() {
        if (count == 0) {
            return 1; // Primer ID
        } else
            return productoras[count - 1].getId() + 1; // ID siguiente al último
    }
    
    // removeLastProductora 
    // Elimina la última productora del arreglo si no tiene películas
    // Método para eliminar la última productora si no tiene películas
    public boolean removeLastProductora() {
        if (count == 0 || hasPeliculas()) {
            return false; // No hay productoras o la última productora tiene películas
        }
        productoras[--count] = null; // Elimina la última productora
        return true; // Productora eliminada con éxito
    }

    // Método para verificar si la última productora tiene películas
    public boolean hasPeliculas() {
        if (count == 0) {
            return false; // No hay productoras
        }
        Productora lastProductora = productoras[count - 1];
        return lastProductora.size() > 0; // Devuelve true si tiene películas, false si no
    }

}
