# Heap Sort
## Es un algoritmo inestable
## Complejidad: O(n log n) en el peor caso

from structures.heap import Heap

def classicAsc(a, b):
    # Funcion de comparacion simple para un min-heap
    if a < b:
        return 1
    elif a == b:
        return 0
    else:
        return -1

def heapsort(arr):
    heap = Heap(arr, f=classicAsc, maxHeap=False)
    res = []
    for _ in range(len(arr)):
        res.append(heap.get())
 
    return res