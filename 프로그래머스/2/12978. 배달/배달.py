import heapq

graph = []

class Node:
    def __init__(self, dest, weight):
        self.dest = dest
        self.weight = weight
        
    def __lt__(self, other):
        return self.weight < other.weight

def solution(N, road, K):
    answer = 0
    graph = [[] for i in range(N+1)]
    
    for row in road:
        src = row[0]
        dest = row[1]
        weight = row[2]
        graph[src].append((dest, weight))
        graph[dest].append((src,weight))
        
    queue = []
    distance = [-1 for i in range(N+1)]
    heapq.heappush(queue, Node(1, 0))
    distance[1] = 0
    
    
    while queue:
        cur = heapq.heappop(queue)
        # if distance[cur.dest] != -1 and cur.weight >= distance[cur.dest]:
        #     continue
            
        nexts = graph[cur.dest]
        for next in nexts:
            next_dist = cur.weight + next[1]
            if distance[next[0]] == -1 or next_dist < distance[next[0]]:
                distance[next[0]] = next_dist
                heapq.heappush(queue, Node(next[0], next_dist))
    
    print(distance)
    count = sum(1 for x in distance if x >= 0 and x <= K)
    
    return count