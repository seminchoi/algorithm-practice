#!/bin/python3

import math
import os
import random
import re
import sys

#
# Complete the 'activityNotifications' function below.
#
# The function is expected to return an INTEGER.
# The function accepts following parameters:
#  1. INTEGER_ARRAY expenditure
#  2. INTEGER d

def activityNotifications(expenditure, d):
    count = 0
    is_even = d % 2 == 0
    dd = d // 2

    sliced = expenditure[:d-1]
    sliced.sort()
    for i in range(d, len(expenditure)):
        in_idx = bisect(sliced, expenditure[i - 1])
        sliced.insert(in_idx, expenditure[i - 1])
        
        mid = None
        
        if is_even:
            mid = sliced[dd - 1] + sliced[dd] 
        else:
            mid = sliced[dd] * 2
        if expenditure[i] >= mid:
            count += 1
            
        pop_idx = bisect(sliced, expenditure[i - d])

        sliced.pop(pop_idx - 1)
    
    return count

def bisect(sliced, target):
    s = 0
    e = len(sliced)
    
    while s < e:
        mid = (s + e) // 2
        if sliced[mid] <= target:
            s = mid + 1
        else:
            e = mid
    return s

if __name__ == '__main__':
    fptr = open(os.environ['OUTPUT_PATH'], 'w')

    first_multiple_input = input().rstrip().split()

    n = int(first_multiple_input[0])

    d = int(first_multiple_input[1])

    expenditure = list(map(int, input().rstrip().split()))

    result = activityNotifications(expenditure, d)

    fptr.write(str(result) + '\n')

    fptr.close()
