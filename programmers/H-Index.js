let numbers = [1, 2, 2, 4, 5, 6, 7, 100];

const acc = {}; // new Array(10000).fill(0);

let count = 0;
numbers
    .sort((a, b) => a - b)
    .forEach(x => acc[x] = ++count);

console.log(acc);

let setOfNumbers = Object.keys(acc).sort((a, b) => a - b);

let result = 0;

setOfNumbers.forEach((x, idx) => {
    let overOrEqualStandard = numbers.length - acc[setOfNumbers[idx - 1]];
    let underOrEqualStandard = acc[x];

    if (underOrEqualStandard <= x && overOrEqualStandard >= x) result = x;
});

console.log(result);