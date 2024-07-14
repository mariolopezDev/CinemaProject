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

    public static DataManager getInstance() {
        if (instance == null) {
            instance = new DataManager();
        }
        return instance;
    }

    public void addProductora(Productora productora) {
        if (count == productoras.length) {
            expandProductorasCapacity();
        }
        productoras[count++] = productora;
    }

    public boolean removeProductora(int id) {
        for (int i = 0; i < count; i++) {
            if (productoras[i].getId() == id) {
                productoras[i] = productoras[count - 1];
                productoras[--count] = null;
                return true;
            }
        }
        return false;
    }

    private void expandProductorasCapacity() {
        Productora[] newProductoras = new Productora[productoras.length * 2];
        System.arraycopy(productoras, 0, newProductoras, 0, count);
        productoras = newProductoras;
    }

    public Productora getProductoraById(int id) {
        for (int i = 0; i < count; i++) {
            if (productoras[i].getId() == id) {
                return productoras[i];
            }
        }
        return null;
    }

    public void trasladarPeliculas(int idOrigen, int idDestino) {
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
        for (int i = 0; i < origen.size(); i++) {
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
        return count + 1;
    }


    // removeLastProductora 
    // Elimina la última productora del arreglo si no tiene películas
    public boolean removeLastProductora() {
        if (count == 0) {
            return false;
        }
        Productora lastProductora = productoras[count - 1];
        if (lastProductora.size() == 0) {
            productoras[--count] = null;
            return true;
        }
        return false;
    }

}