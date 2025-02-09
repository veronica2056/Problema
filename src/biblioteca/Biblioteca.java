package biblioteca;

import java.time.LocalDateTime;
import java.util.Random;

public class Biblioteca {
    public static void main(String[] args) {
         SistemaReservas sistema = new SistemaReservas();
        Random rand = new Random();

        // Agregar reservas aleatorias
        for (int i = 0; i < 1000; i++) {
            int idEstudiante = rand.nextInt(5000);
            LocalDateTime fechaHora = LocalDateTime.now().plusHours(rand.nextInt(100));
            sistema.agregarReserva(idEstudiante, fechaHora);
        }

        // Escoger un ID aleatorio para buscar
        int idBuscar = rand.nextInt(5000);
        System.out.println("Buscando reserva para estudiante ID: " + idBuscar);

        // Medir tiempo de busqueda secuencial
        medirTiempoBusqueda("Busqueda Secuencial", () -> sistema.buscarReservaSecuencial(idBuscar));

        // Medir tiempo de busqueda binaria
        medirTiempoBusqueda("Busqueda Binaria", () -> sistema.buscarReservaBinaria(idBuscar));

        // Medir tiempo de ordenacion con Bubble Sort
        medirTiempoOrdenacion("Bubble Sort", () -> sistema.ordenarReservasBubbleSort());

        // Medir tiempo de ordenacion con Merge Sort
        medirTiempoOrdenacion("Merge Sort", () -> sistema.ordenarReservasMergeSort());
    }

    private static void medirTiempoBusqueda(String metodo, BusquedaLambda busqueda) {
        long inicio = System.nanoTime();
        Reserva resultado = busqueda.ejecutar();
        long fin = System.nanoTime();
        System.out.println(metodo + ": " + (resultado != null) + " - Tiempo: " + (fin - inicio) + " ns");
    }

    private static void medirTiempoOrdenacion(String metodo, Runnable ordenacion) {
        long inicio = System.nanoTime();
        ordenacion.run();
        long fin = System.nanoTime();
        System.out.println(metodo + " - Tiempo: " + (fin - inicio) + " ns");
    }

    @FunctionalInterface
    interface BusquedaLambda {
        Reserva ejecutar();
    }
}
    
    

