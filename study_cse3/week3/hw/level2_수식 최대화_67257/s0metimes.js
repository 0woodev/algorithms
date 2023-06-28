/*
    * > + > -
    * > - > +
    + > * > -
    + > - > *
    - > + > *
    - > * > +
    
    위 수식 전부 다 해보고 그 중 max 구하기
*/

console.log(solution("100-200*300-500+20"));

function solution(expression) {
    var answer = 0;

    const nums = getNums(expression);
    const ops = getOps(expression);
    
    answer = Math.max(
        execute(nums, ops, ["*", "-", "+"]),
        execute(nums, ops, ["*", "+", "-"]),
        execute(nums, ops, ["+", "-", "*"]),
        execute(nums, ops, ["+", "*", "-"]),
        execute(nums, ops, ["-", "+", "*"]),
        execute(nums, ops, ["-", "*", "+"]),
    );

    return answer;
}

function execute(nums, ops, [op1, op2, op3]) {
    return Math.abs(calculate(calculate(calculate([nums, ops], op1), op2), op3).flat()[0]);
}

function calculate([nums, ops], opStr) {
    const newNums = [...nums];
    const newOps = [...ops];
    for(let i = 0; i < newOps.length; i++) {
        const op = newOps[i];

        if(op === opStr) {
            newNums.splice(i, 2, eval(newNums[i] + op + newNums[i+1]));
            newOps.splice(i, 1);
            i--;
        }
    }
    
    return [newNums, newOps];
}

function getNums(expression) {
    const re = /\*|\-|\+/;
    const arr = expression.split(re);
    return arr.map(numStr => parseInt(numStr));
}

function getOps(expression) {
    const re = /\*|\-|\+/;
    const arr = [];
    for(let char of expression) {
        if(char.match(re)) {
            arr.push(char);
        }
    }

    return arr;
} 