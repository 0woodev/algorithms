def solution(targets):
    answer = 0
    jungbok = [0,0]
    for s, e in sorted(targets):
        if jungbok[1] <= s:
            answer+= 1
            jungbok = [s,e]
        else: 
            jungbok = [max(jungbok[0], s), min(jungbok[1], e)]
    return answer
