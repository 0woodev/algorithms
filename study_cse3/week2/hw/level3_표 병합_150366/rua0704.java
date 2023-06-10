import java.util.*;
//1. map+list로 각 네임별 좌표를 담고 머지하면 위치를 합치는 식으로 접근 -> unmerge실패
//2. map+set으로 각 좌표별 merge된 좌표를 담고 머지하면 추가하는 식으로 접근 
//   -> 시간이 터질 것으로 보임(머지된 좌표들 끼리도 또 머지하고 또 머지하고...)
//3. 각 좌표마다 그룹을 부여하고 머지,언머지는 그룹을 수정하고 update는 group을 확인해서 네임을 수정
// 30분짜리 문제를 꼬여서 2시간 반을 풀었다...
class Solution{
    public List<String> solution(String[] commands){
        List<String> answer = new ArrayList<>();
        String[][] table=new String[51][51]; //table정보
        int[][] group = new int[51][51]; // 좌표별 그룹 정보
        
        //좌표별 기본 그룹 설정
        for(int i=1;i<51;i++){
            for(int j=1;j<51;j++){
                group[i][j]=50*(i-1) +j;
            }
        }
        
        for(String comm:commands){ //각 명령어를 보면서
            String[] command = comm.split(" ");

            if(command[0].equals("UPDATE")){
                if(command.length==4){//r,c에 value 입력
                    int r = Integer.parseInt(command[1]);
                    int c = Integer.parseInt(command[2]);
                    int group_num = group[r][c]; //그룹 확인 후
                    for(int i=1;i<51;i++){
                        for(int j=1;j<51;j++){
                            if(group_num==group[i][j]){ //같은 그룹인 네임들 수정
                                table[i][j]=command[3];
                            }
                        }
                    }
                }
                else{//value1인 값들을 value2로 변경
                    for(int i=1;i<51;i++){
                        for(int j=1;j<51;j++){
                            if(table[i][j]!=null && table[i][j].equals(command[1]))
                                table[i][j]=command[2];
                        }
                    }
                }
            }
            else if(command[0].equals("MERGE")){ //r1,c1 과 r2,c2을 병합(r1,c1값 우선)
                int r1=Integer.parseInt(command[1]);
                int c1=Integer.parseInt(command[2]);
                int r2=Integer.parseInt(command[3]);
                int c2=Integer.parseInt(command[4]);
                String word1 = table[r1][c1];
                String word2 = table[r2][c2];
                int group1 = group[r1][c1];
                int group2 = group[r2][c2];
                
                //더 작은 번호의 그룹으로 병합
                int merge_num = Math.min(group1,group2); 
                for(int i=1;i<51;i++){
                    for(int j=1;j<51;j++){
                        //합칠 그룹이면 작은 번호 그룹으로 설정 후 값 갱신
                        if(group[i][j]==group1 || group[i][j]==group2){
                            group[i][j]=merge_num;
                            if(word1==null) table[i][j]=word2;
                            else table[i][j]=word1;
                        }
                    }
                }
                                             
            }

            else if(command[0].equals("UNMERGE")){//r,c의 병합해제(r,c만 값을 가지고 나머진 공백)
                int r = Integer.parseInt(command[1]);
                int c = Integer.parseInt(command[2]);
                //자신을 제외하고 같은 그룹인 좌표들 정보 초기화
                int group_num = group[r][c];
                for(int i=1;i<51;i++){
                    for(int j=1;j<51;j++){
                        if(i==r && j==c) continue;
                        if(group_num == group[i][j]){
                            table[i][j]=null;
                            group[i][j]= 50*(i-1) +j;
                        }
                    }
                }
                //r,c좌표의 그룹을 재설정(안해주면 다른 초기화된 좌표의 그룹과 곂칠수 있어서 틀렸음)
                group[r][c]=50*(r-1) +c;
                
            }
            else{ //r,c 출력(비어있으면 EMPTY)
                int r = Integer.parseInt(command[1]);
                int c = Integer.parseInt(command[2]);
                if(table[r][c]==null) answer.add("EMPTY");
                else
                    answer.add(table[r][c]);
            }
        }
        return answer;
        
    }
}
