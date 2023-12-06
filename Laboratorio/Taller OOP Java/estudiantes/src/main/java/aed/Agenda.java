package aed;

import java.util.Vector;

public class Agenda {

    private Fecha fechaActual;
    private Vector<Recordatorio> recordatorios;

    public Agenda(Fecha fechaActual) {
        this.fechaActual = new Fecha(fechaActual);
        this.recordatorios = new Vector<Recordatorio>();
    }

    public void agregarRecordatorio(Recordatorio recordatorio) {
        this.recordatorios.addElement(recordatorio);        
    }

    @Override
    public String toString() {
        StringBuffer sbuff = new StringBuffer();
        sbuff.append(this.fechaActual.toString());
        sbuff.append("\n");
        sbuff.append("=====");
        sbuff.append("\n");
        for (Recordatorio r : this.recordatorios) {
            sbuff.append(r.toString());
            sbuff.append("\n");
        }
        return sbuff.toString();
    }   

    public void incrementarDia() {
        this.fechaActual.incrementarDia();
    }

    public Fecha fechaActual() {
        return new Fecha(this.fechaActual);
    }

}
