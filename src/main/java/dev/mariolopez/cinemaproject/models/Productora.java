package dev.mariolopez.cinemaproject.models;

public class Productora {
    private int id;
    private String descripcion;
    private Pelicula[] peliculas; // La uso como cola
    private int head; // Índice del primer elemento
    private int tail; // Índice del último elemento
    private int capacity; // Capacidad actual del arreglo

    public Productora(int id, String descripcion, int capacity) {
        this.id = id;
        this.descripcion = descripcion;
        this.capacity = capacity;
        this.peliculas = new Pelicula[capacity];
        this.head = -1; // Cola vacía
        this.tail = -1; // Cola vacía
    }

    public void enqueuePelicula(Pelicula pelicula) {
        if (tail != -1 && (tail + 1) % peliculas.length == head) {
            expandCapacity();
        }
        if (head == -1) {
            head = 0;
        }
        tail = (tail + 1) % peliculas.length; // Mover el índice del último elemento
        peliculas[tail] = pelicula;
    }

    public Pelicula dequeuePelicula() {
        if (head == -1) {
            return null; // No hay elementos en la cola
        }
        Pelicula pelicula = peliculas[head];
        peliculas[head] = null; // Limpiar la referencia para evitar elementos huérfanos
        if (head == tail) {
            head = tail = -1; // Cola queda vacía después de dequeue
        } else {
            head = (head + 1) % peliculas.length; // Mover el índice del primer elemento
        }
        return pelicula;
    }

    private void expandCapacity() {
        int newSize = peliculas.length * 2;
        Pelicula[] newQueue = new Pelicula[newSize]; // Nuevo arreglo con el doble de capacidad
        for (int i = 0, j = head; i < size(); i++, j = (j + 1) % peliculas.length) {
            newQueue[i] = peliculas[j];
        }
        peliculas = newQueue;
        head = 0;
        tail = size() - 1; // Nuevo índice del último elemento
    }

    public int size() {
        if (head == -1) {
            return 0; // Cola vacía
        }
        if (tail >= head) {
            return tail - head + 1; // Caso normal
        }
        return capacity - head + tail + 1; // Caso circular (tail < head)
    }


    @Override
    public String toString() {
        return this.descripcion;  // Retorna la descripción como representación de la productora
    }
    
    // Getters y Setters
    public int getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }


    public Pelicula[] getPeliculas() {
        Pelicula[] currentPeliculas = new Pelicula[size()];
        int index = 0;
        if (head != -1) {
            for (int i = head; i != tail; i = (i + 1) % capacity) {
                currentPeliculas[index++] = peliculas[i];
            }
            currentPeliculas[index] = peliculas[tail]; // Agregar el último elemento
        }
        return currentPeliculas;
    }

}
