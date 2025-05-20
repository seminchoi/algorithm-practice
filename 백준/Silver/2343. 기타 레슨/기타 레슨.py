from bisect import bisect_left, bisect_right
import sys


def calculate_count(arr, mid):
    idx = 0
    count = 0
    prev = 0
    dest = mid
    while(idx < len(arr)):
        # 1 2
        prev = 0 if idx == 0 else arr[idx - 1]
        if(arr[idx] - prev > mid):
            return sys.maxsize
        dest = prev + mid
        idx = bisect_right(arr, dest)
        count += 1

    return count


n, m = map(int, input().split())
a = list(map(int, input().split()))

min_value, max_value = a[0], a[0]

for i in range(1, len(a)):
    min_value = max(min_value, a[i])
    a[i] = a[i-1] + a[i]

max_value = a[-1]

while(min_value < max_value):
    mid = (min_value + max_value) // 2
    count = calculate_count(a, mid)

    # m의 최솟값을 구해야함1
    # [3,2,2,1]

    if(count > m):
        min_value = mid + 1
    else:
        max_value = mid

print(min_value)

