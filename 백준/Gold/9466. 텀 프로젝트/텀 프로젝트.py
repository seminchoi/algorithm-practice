import sys
input = sys.stdin.readline
sys.setrecursionlimit(10**6)

students = None
vis = None

def dfs(src):
    loop = 0
    rec = {}
    while True:
        vis[src] = True
        rec[src] = loop
        dst = students[src]
        if not vis[dst]:
            src = dst
            loop += 1
            continue
        else:
            cycle_start = rec.get(dst, None)
            if cycle_start is not None:
                return loop - cycle_start + 1
            break
    
    return 0
    
def solve():
    global students
    global vis
    n = int(input())
    students = [int(x) - 1 for x in input().split()]
    vis = [False for _ in range(n)]
    
    count = 0
    for i in range(n):
        if not vis[i]:
            count += dfs(i)
    
    result = n - count
    print(result)
    

t = int(input())
for i in range(t):
    solve()
