// 정확성 15/15 효율성 15/15
// Object.Keys().length가 Keys들을 불러와서 그런지 시간을 많이 잡아먹나보다 상수로 관리하니 바로 통과;;;
function solution(gems) {
    var answer = [0, 0];
    let gemList = {};
    for(let gem of gems) {
        gemList[gem] = 1;
    }
    const gemCount = Object.keys(gemList).length;
    
    let start = 0;
    let end = gemCount-1;
    let min = gems.length;
    let buyList = {};
    for(let i = start; i <= end; i++) {
        if(!buyList[gems[i]]) {
            buyList[gems[i]] = 1;
        }
        else {
            buyList[gems[i]]++;
        }
    }

    let buyCount = Object.keys(buyList).length;
    
    while(start <= end && end < gems.length) {
        
        if(buyCount === gemCount) {
            if(buyList[gems[start]] > 1) {
                buyList[gems[start]]--;
                start++;
            }
            else if(end-start < min) {
                answer[0] = start + 1;
                answer[1] = end + 1;
                min = end - start;
                delete buyList[gems[start]];
                start++;
                buyCount--;
                
            }
            else {
                delete buyList[gems[start]];
                start++;
                buyCount--;
            }
        }
        else {
            end++;
            if(!buyList[gems[end]]) {
                buyList[gems[end]] = 1;
                buyCount++;
            }   
            else {
                buyList[gems[end]]++;
            }
        }
    }
    return answer;
}
