//정확성 100  효율성 0
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

    while(start <= end && end < gems.length) {
        let buyCount = Object.keys(buyList).length;
        // console.log(buyList);
        if(buyCount === gemCount) {
            if(buyList[gems[start]] > 1) {
                buyList[gems[start]]--;
                start++;
            }
            else if(end-start < min) {
                answer[0] = start + 1;
                answer[1] = end + 1;
                min = end - start;
                start = start + 1;
                end = start;
                buyList = {};
                buyList[gems[start]] = 1; 
            }
            else {
            start = start + 1;
            end = start;
            buyList = {};
            buyList[gems[start]] = 1; 
            }
        }
        else {
            end++;
            if(!buyList[gems[end]]) {
                buyList[gems[end]] = 1;
            }   
            else {
                buyList[gems[end]]++;
            }
        }
    }
    return answer;
}
