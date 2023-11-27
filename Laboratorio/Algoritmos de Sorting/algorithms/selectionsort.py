# Selection Sort
## Es un algoritmo inestable
## Complejidad: O(n^2) donde n es el tamaño del array

def selectionsort(arr):
    res = []
    for _ in range(len(arr)):
        minIndex = getMinIndex(arr)
        res.append(arr.pop(minIndex))
    return res

def getMinIndex(ls):
    minIndex = 0
    for i in range(len(ls)):
        if ls[i] < ls[minIndex]:
            minIndex = i
    return minIndex