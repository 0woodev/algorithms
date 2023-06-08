import java.util.*;
class Solution {
    public int solution(String[][] book_time) {
        int answer = 0;
        int n = book_time.length;
        // Map<Integer,Integer> room = new HashMap<>();
        List<Integer> room = new ArrayList<>();
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2){
                if(o1[0]==o2[0]) return o1[1]-o2[1];
                return o1[0]-o2[0];
            }
        });
        for(int i=0;i<n;i++){
            String[] start_time = book_time[i][0].split(":");
            String[] end_time = book_time[i][1].split(":");
            int start = Integer.parseInt(start_time[0]) *60+  Integer.parseInt(start_time[1]);
            int end = Integer.parseInt(end_time[0]) *60+  Integer.parseInt(end_time[1]);
            pq.add(new int[]{start,end+10});       
        }
        int[] first = pq.poll();
        room.add(first[1]);
        while(!pq.isEmpty()){
            int[] next =pq.poll();
            boolean f=false;
            for(int i=0;i<room.size();i++){
                if(room.get(i) <= next[0]){
                    room.set(i,next[1]);
                    f=true;
                    break;
                }
            }
            if(!f) room.add(next[1]);
        }
        
        return room.size();
    }
}
