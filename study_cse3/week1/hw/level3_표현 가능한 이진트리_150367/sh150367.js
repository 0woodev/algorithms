function solution(numbers) {
    var answer = [];
    
    const possibleLengths = [];
    
    for(let i = 1; i <= 32; i++) {
        const num = Math.pow(2, i);
        possibleLengths.push(num - 1);
    }
    
    for(let number of numbers) {
        const binary = convertToBinary(number);
        let isPossible = 1;
        
        for(let possibleLength of possibleLengths){
            if(binary.length === possibleLength) {
                break;
            }
            else if(binary.length > possibleLength) {
                continue;
            }
            else {
                while(binary.length < possibleLength) {
                    binary.push(0);
                }
                break;
            }
        }
        
        isPossible = runChecker(binary, 0, binary.length-1) ? 1 : 0;
        
        answer.push(isPossible);
    }
    
    return answer;
}

function convertToBinary(number) {
    let binary = [];
    while(number > 0) {
        binary.push(number % 2);
        
        number /= 2;
        number = parseInt(number);
    }
    
    return binary;
}

function runChecker(binary, start, end) {
    const half = Math.floor((start + end)/2);
    
    if(half <= start) {
        return true;
    }
    
    const parent = binary[half];
    const leftChild = binary[Math.floor((start + half-1)/2)];
    const rightChild = binary[Math.floor((half+1 + end)/2)];
    
    if(!parent && (leftChild || rightChild)) {
        return false;
    }
    
    return runChecker(binary, start, half-1) && runChecker(binary, half+1, end);
}
