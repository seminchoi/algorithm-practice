import sys
input = sys.stdin.readline
sys.setrecursionlimit(2000000)

def find(parent, x):
    if parent[x] != x:
        parent[x] = find(parent, parent[x])
    return parent[x]

    
def union(parent, x, y):
    x = find(parent, x)
    y = find(parent, y)
    if x == y:
        return True
    elif x < y:
        parent[y] = x
    else:
        parent[x] = y
    return False

n, m = map(int, (input().split()))
parent = [i for i in range(n)]

answer = 0
for i in range(m):
    src, dst = map(int, (input().split()))
    if union(parent, src, dst):
        answer = i + 1
        break

print(answer)