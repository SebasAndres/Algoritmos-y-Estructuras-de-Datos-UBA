package aed;

public class HeapSort{

    public Router[] arr;
    public int index;
    public int len;

    public HeapSort(Router[] list, int k, int umbral){
        
        this.arr = new Router[list.length];
        this.index = 0;

        for (int i = 0; i < list.length; i++) {
            if (list[i].getTrafico() > umbral) {
                this.arr[i] = list[i];
                this.len++;
            }
        }

        // Reordenamos el array $arr a partir del Algoritmo de Floyd: O(P)
        // Hacer una funcion del Algoritmo de Floyd

        this.HeapifyByFloyd();
    }

    public void HeapifyByFloyd() {
        // Recibe un array de nodos y lo reordena como un heap
        // aplicando el Algoritmo de Floyd.
        // Complejidad: O(n)
        for (int i = (this.len / 2) - 1; i >= 0; i--) {
            floydRecursivo(i);
        }
    }
  
    public void floydRecursivo(int i) {
        // Swapea el nodo i con su hijo de mayor prioridad
        int smallest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        // Si existe el hijo izquierdo y es mayor que el padre
        if (left < this.len && this.arr[left].getTrafico() > this.arr[smallest].getTrafico()) {
            smallest = left;
        }
        // Si existe el hijo derecho y es mayor que el padre
        if (right < this.len && this.arr[right].getTrafico() > this.arr[smallest].getTrafico()) {
            smallest = right;
        }

        // Si el mayor no es el padre, hacemos swap y seguimos bajando
        if (smallest != i) {
            // Intercambiamos con el hijo mayor
            Router temp = this.arr[i];
            this.arr[i] = this.arr[smallest];
            this.arr[smallest] = temp;
            floydRecursivo(smallest);
        }
    }

    public void BajarRaiz() {
        // Método para reubicar la raíz luego de haber sido dividida tras la asignacion
        // de una banca
        // Complejidad: O(log P)

        int pos = 0;
        int posHijoIzq = 2 * pos + 1;
        int posHijoDer = 2 * pos + 2;

        while (pos < this.len && !esHoja(pos) && tieneUnHijoMayor(pos)) {
            // Intercambiamos posicion con el hijo de mayor prioridad
            // Vemos cada caso...
            // [1] Caso tiene solamente hijo izquierdo e izq>padre
            if (posHijoDer >= this.len) {
                // Intercambiamos de posicion al hijo izquierdo con el padre
                Router aux = this.arr[posHijoIzq];
                this.arr[posHijoIzq] = this.arr[pos];
                this.arr[pos] = aux;
                pos = posHijoIzq;
            }
            // [2] Caso tiene ambos hijos y el izq>der
            else if (this.arr[posHijoIzq].getTrafico() > this.arr[posHijoDer].getTrafico()) {
                // Intercambiamos de posicion al hijo izquierdo con el padre
                Router aux = this.arr[posHijoIzq];
                this.arr[posHijoIzq] = this.arr[pos];
                this.arr[pos] = aux;
                pos = posHijoIzq;
            }
            // [3] Caso tiene ambos hijos y el der>=izq
            else {
                // Intercambiamos posicion al hijo derecho con el padre
                Router aux = this.arr[posHijoDer];
                this.arr[posHijoDer] = this.arr[pos];
                this.arr[pos] = aux;
                pos = posHijoDer;
            }
            // Actualizamos la posicion de los hijos
            posHijoIzq = 2 * pos + 1;
            posHijoDer = 2 * pos + 2;
        }
    }

    private boolean esHoja(int pos) {
        // Complejidad: O(1)
        // Es hoja si no tiene hijo izquierdo
        // Observacion: Esto solo es cierto porque el arrayHeap siempre está completo
        return 2 * pos + 1 >= this.len;
    }

    private boolean tieneUnHijoMayor(int pos) {
        // Devuelve True sii alguno de sus hijos tiene mas prioridad
        // Complejidad: O(1)
        int posHijoDer = 2 * pos + 2;
        int posHijoIzq = 2 * pos + 1;

        if (posHijoDer < this.len)
            return this.arr[pos].getTrafico() < this.arr[posHijoIzq].getTrafico() ||
                    this.arr[pos].getTrafico() < this.arr[posHijoDer].getTrafico();
        else
            return this.arr[pos].getTrafico() < this.arr[posHijoIzq].getTrafico();
    }


    public Router getNext(){

        // Caso especial
        if (this.len == 0)
            return null;

        // 1. Intercambiamos el primero con el último
        Router head = this.arr[0];
        this.arr[0] = this.arr[this.len-1];

        // 2. Disminuimos el len
        this.len--;

        // 3. Heapify()
        this.BajarRaiz();
        
        // 4. Return el head original
        return head;
    }
}