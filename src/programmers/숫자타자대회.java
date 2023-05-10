import java.util.*;

class Solution {
    public int[][] costMatrix =
            {
                    {1, 7, 6, 7, 5, 4, 5, 3, 2, 3},
                    {7, 1, 2, 4, 2, 3, 5, 4, 5, 6},
                    {6, 2, 1, 2, 3, 2, 3, 5, 4, 5},
                    {7, 4, 2, 1, 5, 3, 2, 6, 5, 4},
                    {5, 2, 3, 5, 1, 2, 4, 2, 3, 5},
                    {4, 3, 2, 3, 2, 1, 2, 3, 2, 3},
                    {5, 5, 3, 2, 4, 2, 1, 5, 3, 2},
                    {3, 4, 5, 6, 2, 3, 5, 1, 2, 4},
                    {2, 5, 4, 5, 3, 2, 3, 2, 1, 2},
                    {3, 6, 5, 4, 5, 3, 2, 4, 2, 1}
            };

    public int solution(String numbers) {
        int numLength = numbers.length();
        int answer = Integer.MAX_VALUE;
        int[][][] dpMatrix = new int[numLength + 1][10][10];

        for (int i = 0; i <= numLength; i++) {
            for (int j = 0; j < 10; j++)
                Arrays.fill(dpMatrix[i][j], Integer.MAX_VALUE);
        }

        dpMatrix[0][4][6] = 0;

        for (int idx = 0; idx < numLength; idx++) {
            int currNum = numbers.charAt(idx) - '0';
            int[][] prev = dpMatrix[idx];
            int[][] curr = dpMatrix[idx + 1];

            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    if (i == j || prev[i][j] == Integer.MAX_VALUE) continue;

                    curr[currNum][j] = Math.min(prev[i][j] + costMatrix[i][currNum], curr[currNum][j]);
                    curr[i][currNum] = Math.min(prev[i][j] + costMatrix[j][currNum], curr[i][currNum]);
                }
            }
        }

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                answer = Math.min(dpMatrix[numLength][i][j], answer);
            }
        }

        return answer;
    }
}