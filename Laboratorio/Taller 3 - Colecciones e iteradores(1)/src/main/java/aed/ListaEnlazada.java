package aed;

import java.util.*;

public class ListaEnlazada<T> implements Secuencia<T> {
    // Completar atributos privados

    private Nodo primero;
    private int size;

    private class Nodo {
       Nodo antecesor;
       T valor;
       Nodo sucesor;

       Nodo(T _valor){
         this.valor = _valor;
       }

    }

    public ListaEnlazada() {
        primero = null;
        size = 0;
    }

    public int longitud() {
        return size;
    }

    public void agregarAdelante(T elem) {
        Nodo nuevo = new Nodo(elem);
        nuevo.antecesor = null;
        nuevo.sucesor = primero;
        primero = nuevo;
        size++;
    }

    public void agregarAtras(T elem) {
        Nodo nuevo = new Nodo(elem);
        if (primero == null) {
            primero = nuevo;
        }
        else {
            Nodo actual = primero;
            while (actual.sucesor != null) {
                actual = actual.sucesor;
            }
            actual.sucesor = nuevo;
            nuevo.antecesor = actual;
        }  
        size++;
    }

    public T obtener(int i) {
        int j = 0;
        Nodo actual = primero;
        while (j < i){
            actual = actual.sucesor;
            j++;
        }
        return actual.valor;       
    }

    public void eliminar(int i) {        
        Nodo actual = primero;
        Nodo prev = primero;

        for (int j = 0; j < i; j++) {
            prev = actual;
            actual = actual.sucesor;
        }

        if (i == 0) {
            primero = actual.sucesor;
        }
        else {
            prev.sucesor = actual.sucesor;
        }
        
        size--;
    }

    public void modificarPosicion(int indice, T elem) {
        int j = 0;
        Nodo actual = primero;
        while (j < indice)
        {
            actual = actual.sucesor; 
            j++;
        }
        actual.valor = elem;
    }

    public ListaEnlazada<T> copiar() {
        ListaEnlazada<T> nuevaLista = new ListaEnlazada<>(null);
        Nodo actual = primero;
        while (actual != null){
            nuevaLista.agregarAtras(actual.valor);
            actual = actual.sucesor;
        }
        return nuevaLista;
    }

    public ListaEnlazada(ListaEnlazada<T> lista) {
        if (lista != null) {            
            ListaEnlazada<T> copiaLista = lista.copiar();
            this.primero = copiaLista.primero;
            this.size = lista.size;
        }
        else {
            this.primero = null;
            this.size = 0;
        }
    }
    
    @Override
    public String toString() {
        Nodo actual = primero;
        StringBuffer sbuff = new StringBuffer(null);
        sbuff.append("[");
        while (actual != null){
            sbuff.append(actual.valor.toString());
            if (actual.sucesor != null)
               sbuff.append(", ");
            actual = actual.sucesor;
        }
        sbuff.append("]");
        return sbuff.toString();
    }

    private class ListaIterador implements Iterador<T> {

        private Nodo actual;

        public boolean haySiguiente() { 
            return actual.sucesor != null;
        }
        
        public boolean hayAnterior() {  
            return actual.antecesor != null;
        }

        public T siguiente() {
            actual = actual.sucesor;            
            return actual.valor;
        }

        public T anterior() {
            actual = actual.antecesor;
            return actual.valor;
        }
    }

    public Iterador<T> iterador() {
        return new ListaIterador();
    }

}
