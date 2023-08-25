package aed;

import java.util.Scanner;
import java.io.PrintStream;

class Archivos {

    float[] leerVector(Scanner entrada, int largo) {
        float[] res = new float[largo];
        for (int q=0; q<largo; q++){
            res[q] = entrada.nextFloat();
        }
        return res;
    }

    float[][] leerMatriz(Scanner entrada, int filas, int columnas) {
        float[][] res = new float[filas][columnas];
        for (int row=0; row<filas; row++){
            res[row] = leerVector(entrada, columnas);
        }
        return res;
    }

    void imprimirPiramide(PrintStream salida, int alto) {
        
        if (alto == 0){
            salida.print("");
            return;
        }

        int longString = 2*alto-1;

        for(int row=0; row<alto; row++){

            int n_puntos = (1+2*row);
            int dif = longString - n_puntos;

            // Espacios al principio
            for (int k=0; k<dif/2; k++)
                salida.print(' ');
            
            // Estrellas
            for (int n=0; n<n_puntos; n++)
                salida.print('*');
            
            // Espacios al principio
            for (int k=0; k<dif/2; k++)
                salida.print(' ');
            
            // Cierro linea
            salida.println("");
        }

    }

    char[] ubicarPuntos(char[] actual, int nPuntos, int longString){
        int dif = longString - nPuntos;
        for (int q=0; q<nPuntos; q++){
            actual[(dif/2)+q] = '*'; 
        }
        return actual;
    }

}
