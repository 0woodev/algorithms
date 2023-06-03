import java.util.*;

class Solution {
    public List<Integer> solution(int[] fees, String[] records) {
        //fee - 기본 시간, 기본 요금, 단위 시간, 단위 요금
        //records - 시간, 번호, 내역
        List<Integer> ans = new ArrayList<>(); //정답 - 오름차순 차량번호 별 요금 리스트 
        
        Map<String,Integer> map = new HashMap<>(); // 각 차량별 주차시간 계산을 위한 입차,출차 정보 맵
        Map<String,Integer> answer = new HashMap<>(); //각 차량별 주차시간 저장을 위한 맵
        StringTokenizer st;
        for(String record:records){
            String[] tmp = record.split(" "); //차량 주차 정보
            String[] start = tmp[0].split(":"); //입차 시간
            int min = Integer.parseInt(start[0]) *60 + Integer.parseInt(start[1]); //시간을 분으로 변환
            if(tmp[2].equals("IN")){//입차면
                map.put(tmp[1],min);// 입차정보 입력
            }
            else{ //출차면
                int time = min - map.get(tmp[1]); //출차 분 - 입차 분
                if(answer.containsKey(tmp[1])){ //한번 주차 기록이 있으면 분을 더하고
                    answer.put(tmp[1],answer.get(tmp[1])+ time);
                }
                else //없으면 입력
                    answer.put(tmp[1],time);
                map.remove(tmp[1]); //입차와 출차 정보가 모두 있으면 삭제
            }
        }
        //23 * 60 +59 = 1439
        if(map.size()!=0){ //출차정보가 없는 차량이 있으면 출차시간 23:59로 주차시간 계산
            for(String s:map.keySet()){
                int time = 1439-map.get(s); //23:59(1439) - 입차 분 
              
                if(answer.containsKey(s)){
                    answer.put(s,answer.get(s)+ time);
                }
                else
                    answer.put(s,time);
            }
        }
        //차량번호 오름차순 정렬
        List<String> list = new ArrayList<>();
        for(String key:answer.keySet()){
            list.add(key);
        }
        Collections.sort(list);
      
        for (String key : list) { //각 차량 번호별 요금 계산
            int fee=0;
            int time = answer.get(key);
          
            if(time > fees[0]){//기본 시간보다 길면 추가요금 계산
                time-=fees[0];
                fee+= (int) ((time-1)/fees[2]+1)*fees[3];
            }
            ans.add(fee+fees[1]); //기본요금 추가하면 정답
        }
        return ans;
    }
}
