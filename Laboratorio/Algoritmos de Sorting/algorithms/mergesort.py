def mergesort(array):
    if len(array) <= 1:
        return array
    
    left = array[:len(array) // 2]
    right = array[len(array) // 2:]

    leftOrdered = mergesort(left)
    rightOrdered = mergesort(right)
    return merge(leftOrdered, rightOrdered)

def merge(left, right):
    res = []
    while len(left) > 0 and len(right) > 0:
        if left[0] < right[0]:
            res.append(left.pop(0))
        else:
            res.append(right.pop(0))
    
    while len(left) > 0:
        res.append(left.pop(0))

    while len(right) > 0:
        res.append(right.pop(0))
    
    return res
