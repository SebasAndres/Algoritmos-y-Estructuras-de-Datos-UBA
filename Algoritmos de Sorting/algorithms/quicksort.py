# Quick sort algorithm
## No es estable (por lo general)
## Complejidad: O(n log n)

def quicksort(arr, f=lambda x: x):
    if len(arr) <= 1:                           # O(1)
        return arr                              # -> O(1)

    pivot = f(arr[len(arr) // 2])                  # O(1)
    left = [x for x in arr if f(x) < pivot]        # O(n)
    middle = [x for x in arr if f(x) == pivot]     # O(n)
    right = [x for x in arr if f(x) > pivot]       # O(n)

    leftOrdered = quicksort(left)               # T(n/2)
    rightOrdered = quicksort(right)             # T(n/2)

    return leftOrdered + middle + rightOrdered