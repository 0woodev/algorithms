import java.util.*;

class Solution {
    public final int IN = 0;
    public final int OUT = 1;
    public final int MIN = 60;

    public int[] solution(int[] fees, String[] records) {
        Map<Integer, ParkingLog> logMap = new HashMap<>();
        Map<Integer, Fee> feeMap = new HashMap<>();
        int defaultFee = fees[1];

        Arrays.stream(records).map(ParkingLog::new).forEach(log-> {
            int serialNo = log.getSerialNo();
            if (logMap.containsKey(serialNo)) {
                // 입차한 기록이 있다면
                ParkingLog inLog = logMap.get(serialNo);
                int parkingTime = inLog.getParkingTime(log);
                Fee fee = new Fee(parkingTime, serialNo);
                // 이미 방문했었던 기록이 있어 요금이 있었다면
                if (feeMap.containsKey(serialNo)) {
                    Fee beforeFee = feeMap.get(serialNo);
                    beforeFee.addTime(fee);
                } else {
                    // 방문한 적이 없어 오늘 첫 요금을 계산해야한다면
                    feeMap.put(serialNo, fee);
                }

                // 기존의 입차기록을 지워준다.
                logMap.remove(serialNo);
            } else {
                // 입차한 기록이 없다면
                logMap.put(serialNo, log);
            }
        });

        if (!logMap.isEmpty()) {
            List<Integer> inSerialNoList = new ArrayList<>(logMap.keySet());
            for (Integer serialNo : inSerialNoList) {
                int parkingTime = logMap.get(serialNo).getParkingTime(null);
                Fee fee = new Fee(parkingTime, serialNo);

                if (feeMap.containsKey(serialNo)) {
                    Fee beforeFee = feeMap.get(serialNo);
                    beforeFee.addTime(fee);
                } else {
                    // 방문한 적이 없어 오늘 첫 요금을 계산해야한다면
                    feeMap.put(serialNo, fee);
                }
            }
        }

        List<Fee> feeList =  new ArrayList<>(feeMap.values());
        feeList.sort(Comparator.comparingInt(Fee::getSerialNo));

        return feeList.stream().map(fee -> fee.calculateFee(fees)).mapToInt(i->i).toArray();
    }



    class ParkingLog {
        private int hour;
        private int min;
        private int type; // IN or OUT
        private int serialNo;

        public ParkingLog(int hour, int min, int type, int serialNo) {
            this.hour = hour;
            this.min = min;
            this.type = type;
            this.serialNo = serialNo;
        }

        public ParkingLog(String record) {
            String[] tokens = record.split(" ");
            String[] time = tokens[0].split(":");

            this.hour = Integer.parseInt(time[0]);
            this.min = Integer.parseInt(time[1]);
            this.type = tokens[2] == "IN" ? IN : OUT;
            this.serialNo = Integer.parseInt(tokens[1]);
        }

        public int getHour() {
            return hour;
        }

        public int getMin() {
            return min;
        }

        public int getSerialNo() {
            return serialNo;
        }

        public int getParkingTime(ParkingLog out) {
            if (out == null) {
                return (23 - this.hour) * MIN + (MIN - 1 - this.min);
            }

            if (out.getMin() < this.min) {
                return (out.getHour() - 1 - this.hour) * MIN + (MIN + out.getMin() - this.min);
            } else {
                return (out.getHour() - this.hour) * MIN + (out.getMin() - this.min);
            }
        }
    }

    class Fee {
        private int time;
        private int serialNo;

        public Fee(int time, int serialNo) {
            this.time = time;
            this.serialNo = serialNo;
        }

        public int calculateFee(int[] fees) {
            int defaultTime = fees[0];
            int defaultFee = fees[1];
            int unitTime = fees[2];
            int unitFee = fees[3];

            int fee = defaultFee;
            if (this.time > defaultTime) {
                fee += ((int) Math.ceil((double)(this.time - defaultTime) / unitTime)) * unitFee;
            }
            return fee;
        }

        public int getTime() {
            return time;
        }

        public int getSerialNo() {
            return serialNo;
        }

        public void addTime(Fee fee) {
            this.time += fee.getTime();
        }
    }
}