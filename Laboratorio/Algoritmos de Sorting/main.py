from algorithms.countingsort import countingsort
from algorithms.radixsort import radixsort
from algorithms.selectionsort import selectionsort
from algorithms.mergesort import mergesort
from algorithms.quicksort import quicksort
from algorithms.heapsort import heapsort
from algorithms.insertionsort import insertionsort
from algorithms.bucketsort import bucketsort
import random
import time
import pandas as pd

def is_sorted(ls, desc=False):
    for i in range(len(ls) - 1):
        if desc:
            if ls[i] < ls[i + 1]:
                return False
        else:
            if ls[i] > ls[i + 1]:
                return False
    return True

def test_sort_algorithms(x, f):
    start = time.time()
    y = f(x)
    end = time.time()
    state = "OK" if is_sorted(y) else "ERROR"
    return state, end - start

def orderBestAlgorithms(values):
    # Ordena los algoritmos por tiempo ascendiente como clave primaria
    # y por estabilidad como clave secundaria (si son estables mejor)

    # 1. Ordeno por estabilidad con bucketSort
    fbucket = lambda x: 1 if x[3] == "SI" else 0  
    values_ = bucketsort(values, fbucket, 2)            # O(n + 2*n lgn)
    
    # 2. Ordeno por tiempo con quicksort        
    values_ = quicksort(values_, lambda x: x[1])        # O(n lgn)
    return values_

size = 100000
random_ls = [random.randint(1, size) for _ in range(size)]
exceptUnstable = False
exceptN2 = True

algorithms = [
    ('counting sort', countingsort, "SI", False), 
    ('radix sort', radixsort, "SI", False),
    ('selection sort', selectionsort, "NO", True),
    ('merge sort', mergesort, "SI", False),
    ('quick sort', quicksort, "NO", False),
    ('heap sort', heapsort, "NO", False),
    ('insertion sort', insertionsort, "SI", True)
]
values = []

for name, algorithm, stability, isN2 in algorithms:
    if (exceptUnstable and stability == "NO") or (exceptN2 and isN2):
        continue
    state, dt = test_sort_algorithms(random_ls, algorithm)
    values += [(name, dt, state, stability)]

sortedValues = orderBestAlgorithms(values)
df = pd.DataFrame(sortedValues, columns=["Algoritmo", "Tiempo", "Estado", "Estable"])
print(df)

