class Heap:

    def __init__(self, values, maxHeap=True):
        self.elems = values[:]
        self.size = len(values)
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

        if self.type == "maxheap":            
            # Comprueba si el hijo izquierdo del nodo raíz existe y es mayor que la raíz
            if left < self.size and self.elems[i] < self.elems[left]:
                largest = left

            # Comprueba si el hijo derecho del nodo raíz existe y es mayor que la raíz
            if right < self.size and self.elems[largest] < self.elems[right]:
                largest = right
        else:            
            # Comprueba si el hijo izquierdo del nodo raíz existe y es mayor que la raíz
            if left < self.size and self.elems[i] > self.elems[left]:
                largest = left

            # Comprueba si el hijo derecho del nodo raíz existe y es mayor que la raíz
            if right < self.size and self.elems[largest] > self.elems[right]:
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