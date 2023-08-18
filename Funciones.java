package aed;

class Funciones {
    int cuadrado(int x) {
        // COMPLETAR
        return x*x;
    }

    double distancia(double x, double y) {
        // COMPLETAR
        return Math.sqrt(x*x+y*y);
    }

    boolean esPar(int n) {
        // COMPLETAR

        return n%2 == 0;
    }

    boolean esBisiesto(int n) {
        // COMPLETAR

        return n%4 == 0 && n%100 != 0 || n%400 == 0;
    }

    int factorialIterativo(int n) {
        // COMPLETAR
        int res = 1;
        while(n>0){
            res = res * n;
            n = n-1;
        }
        return res;
    }

    int factorialRecursivo(int n) {
        // COMPLETAR
        int res = 1;
        if (n==0){
            res = 1;
        }
        else if (n>0){
            res = n*factorialRecursivo(n-1);
        }
        return res;
    }

    boolean esPrimo(int n) {
        // COMPLETAR
        int res = 0;
        for(int i=1; i<=n;i++){
            if (n%i == 0){
                res++;
            }
        }
        return res == 2;
    }

    int sumatoria(int[] numeros) {
        // COMPLETAR
        int res = 0;
        for (int i=0;i<numeros.length;i++){
            res = res + numeros[i];
        }
        return res;
    }

    int busqueda(int[] numeros, int buscado) {
        // COMPLETAR
        int res = -1;
        int i=0;
        for( int x: numeros){
            if (x==buscado){
                res=i;
            }
            i++;
        }
        return res;
    }

    boolean tienePrimo(int[] numeros) {
        // COMPLETAR
        boolean res=false;
        for(int x:numeros){
            if(esPrimo(x)){
                res=true;
            }
        }
        return res;
    }

    boolean todosPares(int[] numeros) {
        // COMPLETAR
        boolean res=true;
        for(int x:numeros){
            if(!esPar(x)){
                res=false;
            }
        }
        return res;
    }

    boolean esPrefijo(String s1, String s2) {
        // COMPLETAR
        boolean res=true;
        if(s1.length() <= s2.length()){
            for(int i=0; i<s1.length();i++){
                if(s1.charAt(i) != s2.charAt(i)){
                    res = false;
                }
            }
        } else {
            res = false;
        }
        return res;
    }

    boolean esSufijo(String s1, String s2) {
        // COMPLETAR
        boolean res=true;
        if(s1.length() <= s2.length()){
            for(int i=0; i<s1.length();i++){
                if(s1.charAt(s1.length()-i-1) != s2.charAt(s2.length()-i-1)){
                    res = false;
                }
            }
        } else {
            res = false;
        }
        return res;
    }
}
