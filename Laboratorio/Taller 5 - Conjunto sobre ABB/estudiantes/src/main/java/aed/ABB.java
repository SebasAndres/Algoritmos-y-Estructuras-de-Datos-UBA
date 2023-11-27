package aed;

import java.util.*;

// Todos los tipos de datos "Comparables" tienen el método compareTo()
// elem1.compareTo(elem2) devuelve un entero. Si es mayor a 0, entonces elem1 > elem2

public class ABB<T extends Comparable<T>> implements Conjunto<T> {
    Nodo raiz;
    int cardinal;
    T max;
    T min;

    private class Nodo {
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

        boolean esHoja(){
            return cantidadHijos() == 0;
        }
    }

    public ABB() {
        this.raiz = null;
        this.cardinal = 0;
        this.min = null;
        this.max = null;
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

    public boolean pertenece(T elem){
        Nodo nodo = buscarNodo(this.raiz, elem);
        return nodo != null && nodo.valor.compareTo(elem) == 0;
    }

    public Nodo buscarNodo(Nodo _raiz, T elem){
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
            || _raiz.esHoja() // no tiene hijos
            )
        {  return _raiz; }
        else{
            Nodo next = _raiz.valor.compareTo(elem) > 0 ? _raiz.izquierda : _raiz.derecha;
            if (next == null) {
                return _raiz;
            }
            else{
                return buscarNodo(next, elem);
            }
        }
    }

    public void insertar(T elem){
        if (this.raiz == null){
            this.raiz = new Nodo(elem);
            this.min = elem;
            this.max = elem;
            this.cardinal++;
        }
        else {
            Nodo nodo = buscarNodo(this.raiz, elem);
            if (nodo.valor.compareTo(elem) != 0){
                Nodo nuevoNodo = new Nodo(elem);
                nuevoNodo.padre = nodo;
                if (nodo.valor.compareTo(elem) > 0)
                    nodo.izquierda = nuevoNodo;
                else
                    nodo.derecha = nuevoNodo;
                this.cardinal++;
            }
            
            if (this.min.compareTo(elem) > 0)
                this.min = elem;
                
            else if (this.max.compareTo(elem) < 0)
                this.max = elem;
        }
    }

    public Nodo buscarSucesorInmediato(Nodo nodo){
        Nodo sucesor = nodo.derecha;
        while (sucesor != null && sucesor.izquierda != null){
            sucesor = sucesor.izquierda;
        }
        return sucesor;
    }

    public void eliminar(T elem){
        Nodo nodo = buscarNodo(raiz, elem);
        if (nodo != null && nodo.valor.compareTo(elem) == 0){
            // Caso 1: nodo hoja (no tiene hijos)
            if (nodo.esHoja()){         
                // caso general
                if (nodo.padre != null){
                    if (nodo.padre.valor.compareTo(elem) > 0)
                        nodo.padre.izquierda = null;
                    else
                        nodo.padre.derecha = null;
                }
                // caso especial: la raíz       
                else {
                    this.raiz = null;
                }                
                cardinal--;
            }
            // Caso 2: nodo con un hijo
            else if (nodo.cantidadHijos() == 1){
                // caso especial: el nodo es la raiz y tiene un hijo
                if (nodo.padre == null){
                    if (nodo.derecha != null){
                        nodo.derecha.padre = null;
                        this.raiz = nodo.derecha;
                    }
                    else {
                        nodo.izquierda.padre = null;
                        this.raiz = nodo.izquierda;
                    }
                }
                // casos generales
                else {
                    if (nodo.derecha != null){
                        nodo.derecha.padre = nodo.padre;
                        if (nodo.padre.valor.compareTo(elem) > 0)
                            nodo.padre.izquierda = nodo.derecha;
                        else
                            nodo.padre.derecha = nodo.derecha;
                    }
                    else {
                        nodo.izquierda.padre = nodo.padre;
                        if (nodo.padre.valor.compareTo(elem) > 0)
                            nodo.padre.izquierda = nodo.izquierda;
                        else
                            nodo.padre.derecha = nodo.izquierda;
                    }
                }
                cardinal--;
            }    
            // Caso 3
            else {
                Nodo sucesor_inmediato = buscarSucesorInmediato(nodo);
                T valor = sucesor_inmediato.valor;
                eliminar(valor);
                nodo.valor = valor;
            }
        }
        else {
            // Caso 0: no esta
        }
    }

    public Nodo buscarSucesor(T elem){
        Nodo path = this.raiz;
        Nodo posibleSucesor = null;
        while (path.valor.compareTo(elem) != 0){
            if (path.derecha != null && path.valor.compareTo(elem) < 0){
                path = path.derecha;                
            }
            else if (path.izquierda != null && path.valor.compareTo(elem) > 0){
                posibleSucesor = path;
                path = path.izquierda;
            }
        }
        if (path.derecha != null){
            Nodo snd_path = path.derecha;
            while (snd_path.izquierda != null){
                snd_path = snd_path.izquierda;
            }
            return snd_path;
        }
        else {
            return posibleSucesor;
        }
    }

    public String toString(){
        StringBuffer response = new StringBuffer();
        Iterador<T> it = new ABB_Iterador(this.raiz, this.min);
        response.append("{");
        while (it.haySiguiente()){
            T v = it.siguiente();
            response.append(v);
            if (v != max)
                response.append(",");
        }
        response.append("}");
        return response.toString();
    }

    private class ABB_Iterador implements Iterador<T> {
        private Nodo _actual;

        ABB_Iterador(Nodo _raiz, T _min){
            _actual = buscarNodo(_raiz, _min);
        }

        public boolean haySiguiente() {            
            return _actual != null && _actual != max;
        }
    
        public T siguiente() {
            T valor = _actual.valor;
            _actual = buscarSucesor(valor);
            return valor;
        }
    }

    public Iterador<T> iterador() {
        return new ABB_Iterador(this.raiz, this.min);
    }

}
