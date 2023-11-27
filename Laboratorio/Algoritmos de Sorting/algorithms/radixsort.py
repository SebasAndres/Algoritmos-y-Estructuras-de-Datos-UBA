# Radix Sort
## Es un algoritmo estable
## Complejidad: O(n * d) donde n es el tamaño del array y d el número de dígitos

from .countingsort import countingsort, countingsort_bydigit
import math

def radixsort(array, digits=None):
    # Complejidad: O(n * d)
    if len(array) == 0:                    # O(1)
        return array                       # O(1)
    
    if digits is None:                     # O(1)
        digits = getMaxDigits(array)       # O(n)

    for i in range(digits):                # O(d)
        array = countingsort_bydigit(array, 10, i) # -> O(n)
    return array

def getMaxDigits(array):
    # Complejidad: O(n) donde n es el tamaño del array
    maxDigits = 0                                        # O(1)
    for i in array:                                      # O(n)
        digits = (math.log10(i) + 1).__floor__()         # -> O(1)
        if digits > maxDigits:                           # -> O(1)
            maxDigits = digits                           # -> O(1)
    return maxDigits                                     # O(1)