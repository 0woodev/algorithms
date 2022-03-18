function solution(numbers) {
    return (numbers.reduce((acc, x) => acc + x, 0) === 0) ? "0" : numbers.sort(compareFunc).join("");
}

const compareFunc = (a, b) => parseInt(`${b}${a}`) - parseInt(`${a}${b}`);