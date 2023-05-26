import java.util.*;
class Solution {
    public List<String> solution(String[][] plans) {
        List<String> answer = new ArrayList<>();
        for(String[] p:plans){
            String[] start = p[1].split(":");
            p[1] = (60* Integer.parseInt(start[0]) + Integer.parseInt(start[1])) +"";
        }
        PriorityQueue<String[]> pq =  new PriorityQueue<>(new Comparator<String[]>(){
            @Override
            public int compare(String[] o1, String[] o2){
                return Integer.parseInt(o1[1])-Integer.parseInt(o2[1]);
            }
        });
        for(int i=0;i<plans.length;i++){
            pq.add(plans[i]);
        }
        Stack<String[]> s = new Stack<>();
        while(!pq.isEmpty()){
            String[] start = pq.poll(); //시작할 과제
            if(pq.isEmpty()){ //마지막 과제이면 끝
                answer.add(start[0]);
                while(!s.empty()){
                    answer.add(s.pop()[0]);
                }
                break;
            }
            String[] next = pq.peek(); //다음 시작 과제
            
            int starts = Integer.parseInt(start[1]);
            int starte = Integer.parseInt(start[2]);
            int nexts = Integer.parseInt(next[1]);
            
            if(starts +starte <=nexts){//다음 과제 시작전에 끝나면
                answer.add(start[0]); // 시작과제 끝
                int rest = nexts - starts - starte;
                if(rest ==0) continue;
                while(!s.empty()){
                    start = s.pop();
                    if(rest >= Integer.parseInt(start[1])){
                        answer.add(start[0]);
                        rest -=Integer.parseInt(start[1]);
                        continue;
                    }
                    else{
                        s.push(new String[]{start[0],(Integer.parseInt(start[1]) -rest)+"" });
                        break;
                    }
                    
                }
                continue;
            }
            //다음 시작과제 전에 끝나지 않으면
            s.push(new String[]{start[0],(starte - (nexts-starts)  )+""});
            
            // start[1] = Integer.parseInt(next[]
            // pq.add(start)
            // System.out.println(Arrays.toString(pq.poll()));
        }
        return answer;
    }
}
