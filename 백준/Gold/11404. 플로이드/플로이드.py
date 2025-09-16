import io

n = int(input())
m = int(input())

floyd = [[float("inf") for i in range(n+1)] for j in range(n+1)]

for i in range(m):
    route = list(map(int, input().split()))
    origin = floyd[route[0]][route[1]]
    floyd[route[0]][route[1]] = origin if route[2] >= origin else route[2]

for i in range(1, n + 1):
    floyd[i][i] = 0
    
for i in range(n + 1):
    for j in range(n + 1):
        for k in range(n + 1):
            floyd[j][k] = min(floyd[j][k], floyd[j][i] + floyd[i][k])
            
buf = io.StringIO()
for i in range(1,n+1):
    for j in range(1, n+1):
        buf.write(f"{0 if floyd[i][j] == float('inf') else floyd[i][j]} ")
    buf.write("\n")
    
print(buf.getvalue())