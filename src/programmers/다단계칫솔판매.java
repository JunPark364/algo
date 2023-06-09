import java.util.*;

class Solution {
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int N = enroll.length;
        int M = seller.length;
        Map<String, Integer> map = new HashMap<>();
        int[] answer = new int[N];

        for (int i = 0; i < N; i++) {
            map.put(enroll[i], i);
        }

        label: for (int i = 0; i < M; i++) {
            int curr = map.get(seller[i]);
            int profit = amount[i] * 100;
            while (profit >= 10) {
                answer[curr] += profit - (profit / 10);
                profit = profit / 10;
                if (referral[curr].equals("-")) {
                    continue label;
                }
                curr = map.get(referral[curr]);
            }
            answer[curr] += profit;
        }

        return answer;
    }
}