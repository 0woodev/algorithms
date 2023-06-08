from collections import deque

def solution(x, y, n): 
    q = deque() 
    q.append([y,0])
    visited = [False] * 1000001
    visited[y] = True
    while(q): 
        y, cnt = q.popleft()
        visited[y] = True
        if x == y: return cnt
        if y%3==0 and y//3 >= x and not visited[y//3]: q.append([y//3, cnt+1]) 
        if y%2==0 and y//2 >= x and not visited[y//2]: q.append([y//2, cnt+1]) 
        if y-n >= x and not visited[y-n]: q.append([y-n, cnt+1]) 
    return -1