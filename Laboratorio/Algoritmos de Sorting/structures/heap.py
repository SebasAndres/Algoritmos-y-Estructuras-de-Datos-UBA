class Heap:

    def classicDesc(a, b):
        # Funcion de comparacion simple para un max-heap
        if a > b:
            return 1
        elif a == b:
            return 0
        else:
            return -1

    def __init__(self, values, f, maxHeap=True):
        self.elems = values[:]
        self.size = len(values)
        self.f = f # Función de comparacion

        if maxHeap:
            self.type = "maxheap"
        else:
            self.type = "minheap"

        # Comienza desde el último nodo que no es una hoja
        for i in range(self.size // 2 - 1, -1, -1):
            self.heapify(i)

    def heapify(self, i):
        largest = i
        left = 2 * i + 1
        right = 2 * i + 2

        w = 1 if self.type == "maxheap" else -1

        # Comprueba si el hijo izquierdo del nodo raíz existe y es mayor que la raíz
        if left < self.size and self.f(self.elems[i], self.elems[left]) == w:
            largest = left

        # Comprueba si el hijo derecho del nodo raíz existe y es mayor que la raíz
        if right < self.size and self.f(self.elems[largest], self.elems[right]) == w:
            largest = right

        # Cambia la raíz si es necesario
        if largest != i:
            self.elems[i], self.elems[largest] = self.elems[largest], self.elems[i]  # intercambia
            # Heapify la raíz.
            self.heapify(largest)
    
    def get(self):
        res = self.elems[0]
        self.elems[0] = self.elems[self.size - 1]
        self.size -= 1
        self.heapify(0)
        return res
    
