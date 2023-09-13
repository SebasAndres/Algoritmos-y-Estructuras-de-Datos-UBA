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
        // Caso excepcional
        if (alto == 0){
            salida.print("");
            return;
        }

        int long_string = 2*alto-1;

        // Guardamos linea a linea
        for(int row=0; row<alto; row++){
            
            // La idea es que cada linea tiene 2*row+1 puntos
            // con (longString-n_puntos)/2 espacios entre punto(s)
            int n_puntos = (1+2*row);
            int dif = long_string - n_puntos;

            // Agregamos los espacios al principio (' ')
            for (int k=0; k<dif/2; k++)
                salida.print(' ');
            
            // Agregamos las estrellas (*)
            for (int n=0; n<n_puntos; n++)
                salida.print('*');
            
            // Agregamos los espacios del final (' ')
            for (int k=0; k<dif/2; k++)
                salida.print(' ');
            
            // Salto de linea
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
