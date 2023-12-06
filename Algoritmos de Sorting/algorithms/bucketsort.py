# Bucket Sort
## Es un algoritmo estable
## Complejidad: O(n+B*g(b)), B: #buckets, g(b): Complejidad de ordenar un bucket

from .quicksort import quicksort

def bucketsort(arr, fbucket, nbuckets):
    # fbucket: function that returns the bucket number for a given element
    buckets = [[] for _ in range(nbuckets)]
    for x in arr:
        buckets[fbucket(x)].append(x)
    for bucket in buckets:
        bucket = quicksort(bucket)

    return [x for bucket in buckets for x in bucket]