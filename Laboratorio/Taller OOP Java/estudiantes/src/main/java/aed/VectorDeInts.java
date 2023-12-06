package aed;

import java.util.ArrayList;

class VectorDeInts implements SecuenciaDeInts {
    private static final int CAPACIDAD_INICIAL = 0;

    private int[] vector;
    private int size; 

    public VectorDeInts() {
        vector = new int[CAPACIDAD_INICIAL];
        size = CAPACIDAD_INICIAL; 
    }

    public VectorDeInts(VectorDeInts v){
        VectorDeInts copia = v.copiar();
        size = copia.size;
        vector = copia.vector;
    }

    public int longitud() {
        return size;
    }

    public void agregarAtras(int i) {
        int[] nvec = new int[size+1];
        for (int w=0; w<size; w++)
            nvec[w] = vector[w];
        nvec[size] = i;
        vector = nvec;
        size++;
    }

    public int obtener(int i) {
        return vector[i];
    }

    public void quitarAtras() {
        size--;
        int[] nvec = new int[size];
        for (int q=0; q<size; q++)
            nvec[q] = vector[q];
        vector = nvec;
    }

    public void modificarPosicion(int indice, int valor) {
        vector[indice] = valor;
    }

    public VectorDeInts copiar() {
        VectorDeInts copia = new VectorDeInts();
        for(int j=0; j<size; j++)
            copia.agregarAtras(vector[j]);
        return copia;
    }

}
