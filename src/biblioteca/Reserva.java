package biblioteca;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Reserva {
    private int idEstudiante;
    private LocalDateTime fechaHora;

    public Reserva(int idEstudiante, LocalDateTime fechaHora) {
        this.idEstudiante = idEstudiante;
        this.fechaHora = fechaHora;
    }

    public int getIdEstudiante() {
        return idEstudiante;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    @Override
    public String toString() {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return "Reserva{" +
                "ID Estudiante=" + idEstudiante +
                ", Fecha y Hora=" + fechaHora.format(formato) +
                '}';
    }
} 

