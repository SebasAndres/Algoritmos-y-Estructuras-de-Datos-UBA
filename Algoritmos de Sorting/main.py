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
import matplotlib.pyplot as plt
from argparse import ArgumentParser

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
    fbucket = lambda x: 0 if x[3] == "SI" else 1  
    values_ = bucketsort(values, fbucket, 2)            # O(n + 2*n lgn)

    # 2. Ordeno por tiempo con mergesort        
    values_ = mergesort(values_, lambda x: x[1])        # O(n lgn)
    return values_

if __name__ == "__main__":
    parser = ArgumentParser()
    parser.add_argument("-s", "--size", dest="size", default=100000, type=int, help="Size of the random list")
    parser.add_argument("-u", "--unstable", dest="unstable", default=False, action="store_true", help="Exclude unstable algorithms")
    parser.add_argument("-n", "--n2", dest="n2", default=False, action="store_true", help="Exclude O(n^2) algorithms")
    parser.add_argument("-a", "--action", default="compare", help="Action to perform")
    parser.add_argument("-l", "--algorithm", default='mergesort', help="Plot this algorithm time execution")
    args = parser.parse_args()

    size = args.size
    exceptUnstable = args.unstable
    exceptN2 = args.n2
    action = args.action

    if action == 'compare':
        random_ls = [random.randint(1, size) for _ in range(size)]
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

        print("-"*50)
        print("Mencion especial al bucket sort!")
        print("-"*50)

    elif action == 'plot':
        algorithms = args.algorithm.split(',')
        colors = ['red', 'blue', 'green', 'yellow', 'black', 'orange']
        start = 100
        end = 5000
        jump = 500
        M = range(start, end, jump)
        random_ls = [[random.randint(1, size) for _ in range(size)] for m in M] 
        j = 0
        for algorithm in algorithms:
            algo_times = []
            for ls in random_ls:
                _, dt = test_sort_algorithms(ls, eval(algorithm))
                algo_times.append(dt)
            plt.scatter(M, algo_times, label=algorithm, color=colors[j])
            j+=1
        plt.legend(algorithms)
        plt.xlabel("Tamaño de la lista")
        plt.ylabel("Tiempo de ejecución")
        plt.show()