def mergesort(array, f=lambda x: x):
    if len(array) <= 1:
        return array
    
    left = array[:len(array) // 2]
    right = array[len(array) // 2:]

    leftOrdered = mergesort(left, f)
    rightOrdered = mergesort(right, f)
    return merge(leftOrdered, rightOrdered, f)

def merge(left, right, f):
    res = []
    while len(left) > 0 and len(right) > 0:
        if f(left[0]) < f(right[0]):
            res.append(left.pop(0))
        else:
            res.append(right.pop(0))
    
    while len(left) > 0:
        res.append(left.pop(0))

    while len(right) > 0:
        res.append(right.pop(0))
    
    return res
