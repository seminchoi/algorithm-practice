graph = {}

class Edge:
    def __init__(self, inbound, edges):
        self.inbound = inbound
        self.edges = edges
        
def solution(edges):
    init(edges)
    # 중앙 정점, 쉐입 갯수
    center_tuple = process_center()
    # 생성한 정점의 번호, 도넛 모양 그래프의 수, 막대 모양 그래프의 수, 8자 모양 그래프의 수

    answer = [ 0 for i in range(4) ]
    answer[0] = center_tuple[0]
    for src, edge in graph.items():
        if edge.inbound == 2 and len(edge.edges) == 2:
            answer[3] = answer[3] + 1
        if edge.inbound == 0:
            answer[2] = answer[2] + 1
    
    answer[1] = center_tuple[1] - answer[2] - answer[3]
    
        
    return answer

def init(edges):
    for edge in edges:
        value = graph.get(edge[0], Edge(0, []))
        value.edges.append(edge[1])
        
        graph[edge[0]] = value
        
        src = graph.get(edge[1], Edge(0, []))
        src.inbound = src.inbound + 1
        
        graph[edge[1]] = src
        
def process_center():
    center = None
    for src, edge in graph.items():
        if edge.inbound == 0 and len(edge.edges) >= 2:
            center = src
            break
    
    edge = graph.pop(center)
    for src in edge.edges:
        inbound = graph[src].inbound 
        graph[src].inbound = inbound - 1
    
    return (center, len(edge.edges))


    
            