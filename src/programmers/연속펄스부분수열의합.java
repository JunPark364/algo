import java.util.*;

class Solution {
    public long solution(int[] sequence) {
        int n = sequence.length;
        int[] pulse1 = Arrays.copyOf(sequence, n);
        int[] pulse2 = Arrays.copyOf(sequence, n);

        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                pulse1[i] *= -1;
            } else {
                pulse2[i] *= -1;
            }
        }

        long[] sum1 = new long[n];
        long[] sum2 = new long[n];

        sum1[0] = pulse1[0];
        sum2[0] = pulse2[0];
        long answer = Math.max(sum1[0], sum2[0]);

        for (int i = 1; i < n; i++) {
            sum1[i] = Math.max(pulse1[i], sum1[i - 1] + pulse1[i]);
            sum2[i] = Math.max(pulse2[i], sum2[i - 1] + pulse2[i]);
            answer = Math.max(answer, Math.max(sum1[i], sum2[i]));
        }

        return answer;
    }
}