import java.util.*;
class Solution {
    public int solution(int x, int y, int n) {
        int answer = -1;
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{y,0});
        if(x==y) return 0;
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int next = cur[0];
            int cnt = cur[1];
            
            if(next == x) return cnt;
            
            if(next-n > 0) {
                q.add(new int[]{next-n,cnt+1});
            }
            if(next%2==0){
                q.add(new int[]{next/2,cnt+1});
            }
            if(next%3==0){
                q.add(new int[]{next/3,cnt+1});
            }
        }
        return -1;
    }
}
