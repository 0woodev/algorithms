1
2
3
4
5
6
7
8
9
10
11
12
13
14
15
16
17
18
19
20
21
22
23
24
25
26
27
28
29
30
31
32
33
34
35
36
37

function solution(priorities, location) {
    const order = [];

    let maxPriority = -1;

    let startIdx = 0;

    while (order.length < priorities.length) {
        // startIdx 부터 maxIdx 를 구한다. ->  함수화
        let maxIdx = getMaxPriorityIdx(priorities, startIdx);
        // maxIdx 값을 order 제일 뒤에 넣어준다.
        order.push(maxIdx);
        // maxIdx 의 priorities 를 -1 로 만들어준다.
        priorities[maxIdx] = -1;
        // startIdx 는 maxIdx 다음으로 한다.
        startIdx = (maxIdx + 1) % priorities.length;
    }


    return order.indexOf(location) + 1;
}

const getMaxPriorityIdx = (arr, startIdx) => {
    let maxP = -1;
    let maxIdx = 0;

    for (let i = 0; i < arr.length; i++) {
        if (maxP < arr[startIdx]) {
            maxIdx = startIdx;
            maxP = arr[maxIdx];
        }
        startIdx = (startIdx + 1) % arr.length;
    }

    return maxIdx;
}