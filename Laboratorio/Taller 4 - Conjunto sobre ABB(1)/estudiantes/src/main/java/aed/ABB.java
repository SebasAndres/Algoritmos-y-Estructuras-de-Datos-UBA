package aed;

import java.util.*;

// Todos los tipos de datos "Comparables" tienen el método compareTo()
// elem1.compareTo(elem2) devuelve un entero. Si es mayor a 0, entonces elem1 > elem2
public class ABB<T extends Comparable<T>> implements Conjunto<T> {
    // Agregar atributos privados del Conjunto

    Nodo raiz;
    int cardinal;
    T max;
    T min;

    private class Nodo {
        // Agregar atributos privados del Nodo
        T valor;
        Nodo izquierda;
        Nodo derecha;
        Nodo padre;
        
        Nodo (T v){
            this.valor = v;
            this.izquierda = null;
            this.derecha = null;
            this.padre = null;
        }

        int cantidadHijos(){
            int der = (this.derecha != null)?1:0;
            int izq = (this.izquierda != null)?1:0;
            return der+izq;
        }

        boolean tieneHijos(){
            return cantidadHijos() > 0;
        }
    }

    public ABB() {
        this.raiz = null;
        this.cardinal = 0;
    }

    public int cardinal() {
        return this.cardinal;
    }

    public T minimo(){
        return this.min;
    }

    public T maximo(){
        return this.max;
    }

    public void insertar(T elem){
        Nodo ult_nodo = buscar_nodo(this.raiz, elem);
        if (ult_nodo != null){
            // ahora valido si ult_nodo es el padre o el nodo con el elem 
            if (ult_nodo.valor.compareTo(elem) != 0){
                // es el padre, por lo tanto creo el nodo
                Nodo nuevo = new Nodo(elem);
                nuevo.padre = ult_nodo;
                // le agrego al padre el nodo
                if (elem.compareTo(ult_nodo.valor) > 0){
                    ult_nodo.derecha = nuevo;
                }
                else{
                    ult_nodo.izquierda = nuevo;
                }
                // le sumo un elemento al conjunto
                this.cardinal += 1;
            }
        }
        else{
            // primer nodo!
            this.raiz = new Nodo(elem);
            this.cardinal++;
        }

        // comparo minimo y maximo del ABB
        if (this.min == null){
            if (this.max == null){
                this.min = elem;
                this.max = elem;
            }
        }
        else if (this.min.compareTo(elem) >= 0){
            this.min = elem;
        }
        if (this.max.compareTo(elem) <= 0){
            this.max = elem;
        }

    }

    public Nodo buscar_nodo(Nodo _raiz, T elem){
        /*
         * Recibe la raiz del arbol y un elemento como parametro.
         * Si esta: Devuelve el nodo que cumple nodo.valor == elem
         * Si no esta: Devuelve el posible padre cuando el cardinal es > 1
         *              O devuelve null cuando el arbol esta vacio
         */
        if (_raiz == null){
            return null;
        }
        else if (
            _raiz.valor.compareTo(elem) == 0 // es el elemento
            || !_raiz.tieneHijos() // no tiene hijos
            )
        {  return _raiz; }
        else{
            Nodo next = _raiz.valor.compareTo(elem) > 0 ? _raiz.izquierda : _raiz.derecha;
            if (next == null) {
                return _raiz;
            }
            else{
                return buscar_nodo(next, elem);
            }
        }
    }


    public boolean pertenece(T elem){
        return busquedaRecursiva(this.raiz, elem);
    }

    public boolean busquedaRecursiva(Nodo _raiz, T elem){
        if (_raiz == null){
            return false;
        }
        else if (_raiz.valor.compareTo(elem) == 0){
            return true;
        }
        else{
            return busquedaRecursiva(
                _raiz.valor.compareTo(elem) > 0 ? _raiz.izquierda : _raiz.derecha,
                elem);
        }
    }

    public void eliminar(T elem){
        Nodo node = buscar_nodo(this.raiz, elem);
        if (node != null){     
            int n_hijos = node.cantidadHijos();
            if (n_hijos == 0){
                node = null;
            }
            else if (n_hijos == 1){
                if (node.derecha != null)
                    node = node.derecha;
                else
                    node = node.izquierda;
            }   
            else {
                // 1. Fijo node.valor = ult_sucesor.valor
                Nodo inmediato_sucesor = sucesorInmediato(node);
                node.valor = inmediato_sucesor.valor;
                // 2. Elimino ult_sucesor.valor del ABB
                inmediato_sucesor.valor = null;
            }
            this.cardinal--;
        }
    }

    Nodo sucesorInmediatoDER(Nodo node){
        // Agarro el minimo del subarbol derecho de node
        Nodo nPath = node.derecha;
        while(nPath.izquierda != null){
            nPath = nPath.izquierda;
        }
        return nPath;
    }

    Nodo sucesorInmediatoPADRE(Nodo node){
        // Agarro el minimo sucesor recorriendo el padre
        Nodo nPath = node;
        while( nPath.padre != null && nPath.padre.valor.compareTo(nPath.valor) >=0 )
        { nPath = nPath.padre; }
        return nPath;
    }

    public String toString(){
        StringBuffer response = new StringBuffer();
        Iterador<T> it = new ABB_Iterador(this.raiz, this.min);
        response.append("{");
        while (it.haySiguiente()){
            response.append(it.siguiente());
            if (it.haySiguiente())
                response.append(",");
        }
        response.append("}");
        return response.toString();
    }

    private class ABB_Iterador implements Iterador<T> {
        private Nodo _actual;

        ABB_Iterador(Nodo _raiz, T _min){
            this._actual = buscar_nodo(_raiz, _min);
        }

        public boolean haySiguiente() {            
            return _actual != max;
        }
    
        public T siguiente() {
            Nodo minPadre = sucesorInmediatoPADRE(_actual);
            Nodo minABB_der = sucesorInmediatoDER(_actual);
            if (minABB_der != null){
                if (vPadre.compareTo(minABB_der.valor) >= 0)
                    return minABB_der.valor;
            }
            return minABB_der.valor;
        }
    }

    public Iterador<T> iterador() {
        return new ABB_Iterador(this.raiz, this.min);
    }

}
