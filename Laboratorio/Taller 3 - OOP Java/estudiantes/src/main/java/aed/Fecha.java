package aed;

public class Fecha {
    public Fecha(int dia, int mes) {
        throw new UnsupportedOperationException("No implementada aun");
    }

    public Fecha(Fecha fecha) {
        throw new UnsupportedOperationException("No implementada aun");
    }

    public Integer dia() {
        throw new UnsupportedOperationException("No implementada aun");
    }

    public Integer mes() {
        throw new UnsupportedOperationException("No implementada aun");
    }

    public String toString() {
        throw new UnsupportedOperationException("No implementada aun");
    }

    @Override
    public boolean equals(Object otra) {
        throw new UnsupportedOperationException("No implementada aun");
    }

    public void incrementarDia() {
        throw new UnsupportedOperationException("No implementada aun");
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
