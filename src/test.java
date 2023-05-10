import java.util.*;

class test {
    public int solution(int n) {
        ArrayList<Long> dp = new ArrayList<>();
        dp.add(0L);
        dp.add(1L);
        dp.add(3L);
        dp.add(10L);
        for (int i = 4; i <= n; i++) {
            long next = dp.get(1) * dp.get(n - 1) + dp.get(2) * dp.get(n - 2) + dp.get(3) * dp.get(n - 3);
            dp.add(next);
        }
        long answer = dp.get(n) % 1000000007L;
        return (int) answer;
    }
}
