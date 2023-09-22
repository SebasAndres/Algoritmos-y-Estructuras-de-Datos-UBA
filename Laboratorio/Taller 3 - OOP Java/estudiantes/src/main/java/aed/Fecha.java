package aed;

public class Fecha {

    private int dia;
    private int mes;

    public Fecha(int dia, int mes) {
        this.dia = dia;
        this.mes = mes;
    }

    public Fecha(Fecha fecha) {
        int _dia = fecha.dia;
        int _mes = fecha.mes;
        this.dia = _dia;
        this.mes = _mes;
    }

    public Integer dia() {
        return this.dia;
    }

    public Integer mes() {
        return this.mes;
    }

    public String toString() {
        return Integer.toString(this.dia) + "/" +Integer.toString(this.mes);
    }

    @Override
    public boolean equals(Object otra) {
        boolean isNull = otra == null;
        if (isNull || otra.getClass() != this.getClass())
            return false;        
        Fecha fOtra = (Fecha) otra;
        return fOtra.dia == this.dia && fOtra.mes == this.mes; 
    }

    public void incrementarDia() {
        if (this.dia == this.diasEnMes(this.mes)){
            this.dia = 1;
            this.mes = (mes+1)%12;
        }
        else{
            this.dia++;
        }
    }

    private int diasEnMes(int mes) {
        int dias[] = {
                // ene, feb, mar, abr, may, jun
                31, 28, 31, 30, 31, 30,
                // jul, ago, sep, oct, nov, dic
                31, 31, 30, 31, 30, 31
        };
        return dias[mes - 1];
    }

}
