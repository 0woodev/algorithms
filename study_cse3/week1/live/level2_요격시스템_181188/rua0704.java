import java.util.*;
class Solution {
    public int solution(int[][] targets) {
        int answer = 0;
        
        Arrays.sort(targets,new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2){
                if (o1[1]==o2[1]) return o1[0]-o2[0];
                return o1[1]-o2[1];
            }
        });
        Queue<int[]> q = new LinkedList<>();
        for(int[] t : targets){
            
            // System.out.println(Arrays.toString(t));
            q.add(t);
            
        }
        int[] tmp = q.poll();
        answer++;
        int end = tmp[1];
        while(!q.isEmpty()){
            tmp = q.peek();
            if(tmp[0] < end) {
                q.poll();
                continue;
            }
            end = tmp[1];
            answer++;
        }
        return answer;
    }
}
