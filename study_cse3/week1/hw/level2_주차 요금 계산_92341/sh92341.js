function calculateTimeDiff(carInTime, carOutTime) {
    const [inHour, inMinute] = carInTime.split(":");
    const [outHour, outMinute] = carOutTime.split(":");

    return (parseInt(outHour) - parseInt(inHour)) * 60 + parseInt(outMinute) - parseInt(inMinute);
}

function calculateFee(timeDiff, fees) {
    const defaultTime = fees[0];    // minutes
    const defaultFee = fees[1];
    const unitTime = fees[2];   // minutes
    const unitFee = fees[3];

    if(timeDiff <= defaultTime) {
        return defaultFee;
    }
    else {
        return defaultFee + Math.ceil((timeDiff - defaultTime) / unitTime) * unitFee;
    }
}

function solution(fees, records) {
    var answer = [];
    const parkRecordObject = {};

    for(let record of records) {
        const recordArray = record.split(" ");
        const time = recordArray[0];
        const carNum = parseInt(recordArray[1]);
        const inOut = recordArray[2];

        if(!parkRecordObject[carNum]) {
            parkRecordObject[carNum] = [];
        }

        parkRecordObject[carNum].push({time, inOut});
    }

    let carNums = Object.keys(parkRecordObject);

    carNums = carNums.sort((a, b) => a - b); // 오름차순 정렬

    for(let carNum of carNums) {
        // 길이가 홀수이면 끝에 OUT 이 안찍힌 케이스임. 따라서 추가해줌.
        if(parkRecordObject[carNum].length % 2 === 1) {
            parkRecordObject[carNum].push({time: "23:59", inOut: "out"});
        }

        let totalTimeDiff = 0;
        for(let i = 0; i < parkRecordObject[carNum].length; i += 2) {
            totalTimeDiff += calculateTimeDiff(
                parkRecordObject[carNum][i].time, 
                parkRecordObject[carNum][i+1].time
            );
        }

        answer.push(calculateFee(totalTimeDiff, fees));
    }

    return answer;
}
