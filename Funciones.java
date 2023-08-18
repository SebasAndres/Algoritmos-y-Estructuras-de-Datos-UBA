package aed;

class Funciones {
    int cuadrado(int x) {
        return (int) Math.pow(x, 2);
    }

    double distancia(double x, double y) {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }

    boolean esPar(int n) {
        return n % 2 == 0;
    }

    boolean esBisiesto(int n) {
        return (n%4 == 0 && n%100 != 0) || (n%400 == 0);
    }

    int factorialIterativo(int n) {
        int res = 1;
        for (int i=1; i<=n; i++){
            res *= i;
        }
        return res;
    }

    int factorialRecursivo(int n) {
        if (n > 1){
            return n*factorialRecursivo(n-1);
        }
        return 1;
    }

    boolean esPrimo(int n) {
        int n_divisores = 0;
        
        for (int q=1; q<=n; q++){
            if (n % q == 0) {
                n_divisores += 1;
            }
        }
        return n_divisores == 2;
    }

    int sumatoria(int[] numeros) {
        int res = 0;
        for (int n: numeros){
            res += n;
        }
        return res;
    }

    int busqueda(int[] numeros, int buscado) {
        for (int i=0; i<numeros.length; i++){
            if (numeros[i] == buscado) {
                return i;
            }
        }
        return -1;
    }

    boolean tienePrimo(int[] numeros) {    
        for (int n: numeros){
            if (esPrimo(n)){
                return true;
            }
        }
        return false;
    }

    boolean todosPares(int[] numeros) {
        for (int n: numeros){
            if (n % 2 != 0){
                return false;
            }
        }
        return true;
    }

    boolean esPrefijo(String s1, String s2) {

        if (s1.length() > s2.length()){
            return false;
        }

        for (int w=0; w<s1.length(); w++){
            if (s1.charAt(w) != s2.charAt(w)){
                return false;
            }
        }
        
        return true;
    }   

    boolean esSufijo(String s1, String s2) {
        if (s1.length() > s2.length()){
            return false;
        }
        
        int s1_length = s1.length();
        int s2_length = s2.length();

        for (int j=0; j<s1.length(); j++){
            if (s1.charAt(j) !=s2.charAt(s2_length-s1_length+j)){
                return false;
            }
        }

        return true;
    }


}
