from collections import deque
dx=[1,-1,0,0]
dy=[0,0,1,-1]

def solve(maps,st,end):
    X,Y = len(maps),len(maps[0]) 
    
    visited = set()
    q = deque([(st[0],st[1],0)])
    while q:
        x,y,cnt = q.popleft()
        if (x,y) in visited: 
            continue
        else: 
            visited.add((x,y))
            if maps[x][y] == "X":
                continue
            elif (x,y) == end: 
                return cnt
            else: 
                for i in range(4):
                    nx,ny = x+dx[i],y+dy[i]
                    if 0<=nx<X and 0<=ny<Y:
                        q.append((nx,ny,cnt+1))
    return -1 

def solution(maps):
    for i,row in enumerate(maps):
        if "S" in row:
            S = (i,row.find("S"))
        if "L" in row:
            L = (i,row.find("L"))
        if "E" in row:
            E = (i,row.find("E"))
            
    lever_cnt = solve(maps,S,L)
    if lever_cnt == -1: return -1
    exit_cnt = solve(maps,L,E)
    if exit_cnt == -1: return -1

    return exit_cnt+lever_cnt 
