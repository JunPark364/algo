package kakao_blind_recruitment;

public class 미로탈출명령어 {
    class Solution {
        public String solution(int n, int m, int x, int y, int r, int c, int k) {
            //d, l, r, u
            int minMove = Math.abs(x-r) + Math.abs(y-c);

            if ((minMove > k) || (minMove % 2 != k % 2)) return "impossible";

            StringBuilder answer = new StringBuilder();
            int currX = x;
            int currY = y;

            for (int i = 0; i < k; i++) {
                int leftMove = Math.abs(currX - r) + Math.abs(currY - c);
                if ((currX < r) || (currX < n && (k - i) > leftMove)) {
                    currX++;
                    answer.append('d');
                } else if ((currY > c) || (currY > 1 && (k - i) > leftMove)) {
                    currY--;
                    answer.append('l');
                } else if ((currY < c) || (k - i) > leftMove) {
                    currY++;
                    answer.append('r');
                } else {
                    currX--;
                    answer.append('u');
                }
            }

            return answer.toString();
        }
    }
}
