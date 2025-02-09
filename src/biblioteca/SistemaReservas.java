
package biblioteca;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class SistemaReservas {
    private ArrayList<Reserva> reservas;

    public SistemaReservas() {
        this.reservas = new ArrayList<>();
    }

    public void agregarReserva(int idEstudiante, LocalDateTime fechaHora) {
        reservas.add(new Reserva(idEstudiante, fechaHora));
    }

    // 🔍 Busqueda Secuencial (O(n))
    public Reserva buscarReservaSecuencial(int idEstudiante) {
        for (Reserva reserva : reservas) {
            if (reserva.getIdEstudiante() == idEstudiante) {
                return reserva;
            }
        }
        return null;
    }

    // 🔍 Busqueda Binaria (O(log n)) - Solo si la lista esta ordenada
    public Reserva buscarReservaBinaria(int idEstudiante) {
        Collections.sort(reservas, Comparator.comparingInt(Reserva::getIdEstudiante)); // Asegurar orden
        int inicio = 0, fin = reservas.size() - 1;
        while (inicio <= fin) {
            int medio = inicio + (fin - inicio) / 2;
            int idMedio = reservas.get(medio).getIdEstudiante();
            if (idMedio == idEstudiante) return reservas.get(medio);
            if (idMedio < idEstudiante) inicio = medio + 1;
            else fin = medio - 1;
        }
        return null;
    }

    // 🔹 Ordenacion con Bubble Sort (O(n²))
    public void ordenarReservasBubbleSort() {
        int n = reservas.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (reservas.get(j).getFechaHora().isAfter(reservas.get(j + 1).getFechaHora())) {
                    Collections.swap(reservas, j, j + 1);
                }
            }
        }
    }

    // 🔹 Ordenacion con Merge Sort (O(n log n))
    public void ordenarReservasMergeSort() {
        reservas = mergeSort(reservas);
    }

    private ArrayList<Reserva> mergeSort(ArrayList<Reserva> lista) {
        if (lista.size() <= 1) return lista;
        int medio = lista.size() / 2;
        ArrayList<Reserva> izquierda = new ArrayList<>(lista.subList(0, medio));
        ArrayList<Reserva> derecha = new ArrayList<>(lista.subList(medio, lista.size()));

        izquierda = mergeSort(izquierda);
        derecha = mergeSort(derecha);

        return merge(izquierda, derecha);
    }

    private ArrayList<Reserva> merge(ArrayList<Reserva> izquierda, ArrayList<Reserva> derecha) {
        ArrayList<Reserva> resultado = new ArrayList<>();
        int i = 0, j = 0;
        while (i < izquierda.size() && j < derecha.size()) {
            if (izquierda.get(i).getFechaHora().isBefore(derecha.get(j).getFechaHora())) {
                resultado.add(izquierda.get(i++));
            } else {
                resultado.add(derecha.get(j++));
            }
        }
        while (i < izquierda.size()) resultado.add(izquierda.get(i++));
        while (j < derecha.size()) resultado.add(derecha.get(j++));
        return resultado;
    }

    public void mostrarReservas() {
        for (Reserva reserva : reservas) {
            System.out.println(reserva);
        }
    }
} 

