# Insertion Sort
## Es un algoritmo estable
## Complejidad: O(n^2) en el peor caso

def insertionsort(arr):
    for i in range(1, len(arr)):
        j = i
        while j > 0 and arr[j-1] > arr[j]:
            arr[j-1], arr[j] = arr[j], arr[j-1]
            j -= 1
    return arr