# Heap Sort
## Es un algoritmo inestable
## Complejidad: O(n log n) en el peor caso

from structures.heap import Heap

def heapsort(arr):
    heap = Heap(arr, False)
    res = []
    for _ in range(len(arr)):
        res.append(heap.get())
    return res