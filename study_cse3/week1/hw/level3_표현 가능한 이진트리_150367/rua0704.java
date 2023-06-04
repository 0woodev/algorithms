import java.util.*;
//포화 이진트리 : 리프노드를 제외한 모든 노드가 2개의 자식을 가지고 모든 리프노드의 깊이가 같은 트리
class Solution {
    public String tree; //tree정보
    public int[] solution(long[] numbers) {
        int n = numbers.length;
        int[] answer = new int[n];
        
        for(int i=0;i<n;i++){
            String binary = Long.toBinaryString(numbers[i]);
            // System.out.println(depth+" "+Long.toBinaryString(numbers[i]));
            int len = binary.length();
            tree="";
            int tmp=1;
            int size=0;
            
            while(true){ //이진수의 노드들을 모두 담을 수 있는 가장 작은 이진트리 생성
                size = (int) Math.pow(2,tmp++) -1;  
                if(size >= len) break;
            }
            //포화 이진트리를 만들어주기위해 나머지노드들을 0으로 채워주고 이진수 저장
            //확장해도 전체 트리의 서브트리가 원본 트리일 것이고 부모와 자식관계만 볼 것이기 때문에 무관
            for(int j=0;j<size-len;j++) tree+="0";
            tree+=binary;
            
            int mid = tree.length() / 2; //root(부모) 노드 위치
            if(tree.charAt(mid)=='1' && func(0,tree.length()-1,true)) //루트가 1이면 부모=true
                answer[i]=1;
            else if(tree.charAt(mid)=='0' && func(0,tree.length()-1,false))//루트가 0이면 부모=false
                answer[i]=1;
        }

        return answer;
    }
    public boolean func(int start, int end, boolean parent){
        int mid = (end + start) /2;
        char node = tree.charAt(mid); //자식 노드 정보
       
        if(!parent && node=='1'){ //부모가 false(0)이고 자식이 1인경우 불가능
            return false;
        }
        
        if(start!=end){ //마지막 노드가 아니면 좌우 분할해서 탐색
            
            //부모 정보 갱신
            if(node == '1') parent = true;
            else parent=false;
            
            if(!func(start,mid-1,parent) || !func(mid+1,end,parent)) return false; //분할 했을 때 한쪽이라도 불가능이면 불가능
        }
        return true;
    } 
}
