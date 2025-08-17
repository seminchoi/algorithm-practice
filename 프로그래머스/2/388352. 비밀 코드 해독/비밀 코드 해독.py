def solution(n, q, ans):
    sq = [mask(e) for e in q]
    
    answer = make_code(n, 0, 0, sq, ans, 0)
    return answer

def mask(arr):
    mask_num = 0
    for i in arr:
        mask_num = mask_num | 1 << i
    
    return mask_num

def make_code(n, start_i, code, q, ans, depth):
    if depth == 5:
        if is_possible(code, q, ans):
            return 1
        return 0
        
    answer = 0
    
    for i in range(start_i, n):
        bit_i = 1 << (i + 1)
        code =  code | bit_i
        answer += make_code(n, i + 1, code, q, ans, depth + 1)
        code = code ^ bit_i
        
    return answer
        
def is_possible(code, q, ans):
    for i in range(0, len(q)):
        count = bin(q[i] & code).count('1')
        
        if count != ans[i]:
            return False
    
    return True