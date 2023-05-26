import java.util.*;
class Solution {
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    public int solution(String[] maps) {
        int answer = -1;
        int r = maps.length;
        int c = maps[0].length();
        char[][] map = new char[r][c];
        int[] start=new int[2];
        int[] end = new int[2];
        int[] lever = new int[2];
        boolean[][] visited = new boolean[r][c];
        for(int i=0;i<r;i++){
            for(int j=0;j<c;j++){
                char tmp = maps[i].charAt(j);
                if(tmp == 'S')
                    start = new int[]{i,j};
                else if(tmp == 'L') 
                    lever = new int[]{i,j};
                else if(tmp == 'E')
                    end = new int[]{i,j};
                map[i][j] =tmp;
            }
        } 
        Queue<int[]> q = new LinkedList<>();
        visited[start[0]][start[1]]=true;
        q.add(new int[]{start[0],start[1],0});
        boolean f = false;
        while(!q.isEmpty()){
            int[] now = q.poll();
            // System.out.println(Arrays.toString(now));
            if(f && now[0]==end[0] && now[1] == end[1]) {
                answer = now[2];
                break;
            }
            if(!f && now[0] == lever[0] && now[1] == lever[1]){
                f=true;
                visited= new boolean[r][c];
                q.clear();
                visited[now[0]][now[1]]=true;
                q.add(new int[]{now[0],now[1],now[2]});
                continue;
            }
            for(int k=0;k<4;k++){
                int nx = now[0]+dx[k];
                int ny = now[1]+dy[k];
                if(nx <0 || nx >=r || ny <0 || ny >=c || visited[nx][ny] || map[nx][ny]=='X') continue;
                
                visited[nx][ny]=true;
                q.add(new int[]{nx,ny,now[2]+1});
            }
        }
        return answer;
    }
}
