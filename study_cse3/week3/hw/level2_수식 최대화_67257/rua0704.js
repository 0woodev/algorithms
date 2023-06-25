//구현 문제 조금 오래걸렸다 약 1시간반 꼬이지않았으면 1시간 정도로 풀었을듯

const operator = ["+", "-", "*"];
let visited = [0, 0, 0];
let answer=0;

function solution(expression) {
    const orderList=[];
    operatorOrder(0, orderList, expression);
    return answer;
}

function operatorOrder(idx, orderList, expression) {
    if(orderList.length==3) {
        const result = parsingExpression(orderList, expression);
        if(Math.abs(result) > answer) {
            answer = Math.abs(result);
        }
        return;
    }
    for(let i=0;i<3;i++) {
        if(!visited[i]) {
            visited[i]=1;
            orderList.push(operator[i]);
            operatorOrder(i+1,orderList,expression);
            orderList.pop();
            visited[i]=0;
        }
    }
}

function parsingExpression(orderList, expression) {
    //ex) 20 + 20 + 10 - 200 - 10 * 10 + 20 - 100 - 200
    //    + * -
    
    //expression = [20, 20, 10-200-10*10, 20-100-200]
    expression = expression.split(orderList[0]);
    for(let i = 0; i < expression.length; i++) {
        if(expression[i].includes(orderList[1])) {
            expression[i] = expression[i].split(orderList[1]); 
            //expression[i] = [10-200-10, 10]
            
            for(let j = 0; j < expression[i].length; j++) {      
                if(expression[i][j].includes(orderList[2])) {
                    expression[i][j] = expression[i][j].split(orderList[2]);
                    //expression[i][j] = [10, 200, 10]
                    
                    expression[i][j] = calculator(expression[i][j], orderList[2]);
                    //expression[i][j] = -200
                }
            }
            //expression[i] = [-200, 10]
            expression[i] = calculator(expression[i], orderList[1]);
            //expression[i] = -2000
            //expression = [20, 20, -2000, 20-100-200]
        }
        else if(expression[i].includes(orderList[2])) {
            expression[i] = expression[i].split(orderList[2]);
            //expression[i] = [20, 100, 200]
            
            expression[i] = calculator(expression[i], orderList[2]);
            //expression[i] = -280
        }
    }
    //expression = [20, 20, -2000, -280]
    expression = calculator(expression, orderList[0]);
    //expression = -2240
    return expression;
}

function calculator(expression, op) {
    let result = parseInt(expression[0]);
    switch(op) {
        case "+":
            for( let i = 1; i < expression.length; i++){
                result+= parseInt(expression[i]);
            }
            break;
        case "-":
            for( let i = 1; i < expression.length; i++){
                result-= parseInt(expression[i]);
            }
            break;
        case "*":
            for( let i = 1; i < expression.length; i++){
                result*= parseInt(expression[i]);
            }
            break;
    }
    return result;
}
