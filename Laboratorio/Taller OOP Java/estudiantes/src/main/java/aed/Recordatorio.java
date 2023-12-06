package aed;

public class Recordatorio {

    private String mensaje;
    private Fecha fecha;
    private Horario horario;

    public Recordatorio(String mensaje, Fecha fecha, Horario horario) {
        this.mensaje = mensaje;
        this.fecha = new Fecha(fecha);
        this.horario = new Horario(horario.hora(), horario.minutos());
    }

    public Horario horario() {
        return new Horario(this.horario.hora(), this.horario.minutos());
    }

    public Fecha fecha() {
        return new Fecha(fecha);
    }

    public String mensaje() {
        return this.mensaje;
    }

    @Override
    public String toString() {
        return this.mensaje
                   .concat(" @ ")
                   .concat(this.fecha.toString())
                   .concat(" ")
                   .concat(this.horario.toString());
    }

}
