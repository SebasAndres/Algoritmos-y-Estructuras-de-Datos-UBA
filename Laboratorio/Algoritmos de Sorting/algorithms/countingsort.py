# Counting sort
## Es un algoritmo estable
## Complejidad: O(n + k) donde n es el tamaño del array y k el rango de valores  

def countingsort(array, k=None):
    # Complejidad: O(n + k)
    if len(array) == 0:             # O(1)
        return array                # O(1)
    
    if k is None:                   # O(1)
        k = getMaxElem(array)       # O(n)

    res = []                        # O(1)
    count = [0] * k                 # O(k)
    for i in array:                 # O(n)
        count[i] += 1               # -> O(1)

    for i in range(k):              # O(k)
        res += [i] * count[i]       # -> O(1)

    return res

def countingsort_bydigit(array, base, digit):
    # Complejidad: O(n)
    res = []                        # O(1)
    count = [0] * base              # O(base)
    for i in array:                 # O(n)
        count[(i // base ** digit) % base] += 1

    for i in range(base):           # O(base)
        res += [i] * count[i]       # -> O(1)

    return res

def getMaxElem(array):
    # Complejidad: O(n) donde n es el tamaño del array
    maxElem = array[0]              # O(1)
    for i in array:                 # O(n)
        if i > maxElem:             # -> O(1)
            maxElem = i             # -> O(1)
    return maxElem + 1  