from math import log2

a = 1
def solve(binary, parent):
    left, right = parent//2, parent + parent//2
    if left|right&parent:
        
    solve()
    parent

def solution(numbers):
    answer = []
    for binary, n in zip(map(lambda num: format(num, 'b'), numbers), numbers):
        while(log2(len(binary)+1) != int(log2(len(binary)+1))):
            binary = '0'+ binary
        answer.append(solve(binary, len(binary)//2))
    return answer