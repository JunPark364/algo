package programmers_practice;

public class 억억단을외우자 {
    class Solution {
        public int count(int num) {
            int result = 0;
            for (int i = 1; i * i <= num; i++) {
                if (i * i == num) result += 1;
                else if (num % i == 0) result += 2;
            }
            return result;
        }

        public int[] solution(int e, int[] starts) {
            int[] answer = new int[starts.length];
            int[] countArr = new int[e + 1];
            int[] maxArr = new int[e + 1];
            int maxNum = e;
            int minNum = e;
            int maxCount = count(e);
            maxArr[e] = maxNum;

            for (int i = 1; i <= e; i++) {
                for (int j = 1; j <= e / i; j++) {
                    countArr[i * j]++;
                }
            }

            for (int i : starts) {
                if (i < minNum) {
                    minNum = i;
                }
            }

            for (int i = e - 1; i >= minNum; i--) {
                if (countArr[i] >= maxCount) {
                    maxNum = i;
                    maxCount = countArr[i];
                }

                maxArr[i] = maxNum;
            }

            for (int i = 0; i < starts.length; i++) {
                answer[i] = maxArr[starts[i]];
            }

            return answer;
        }
    }
}
