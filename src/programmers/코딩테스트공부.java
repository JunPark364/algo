import java.util.*;

class Solution {
    public int max_alp, max_cop, answer;
    public List<int[]> problemList;

    public void solve(int alp, int cop, int result) {
        //기저조건 : 모든 문제를 풀 수 있는 경우
        if (alp >= max_alp && cop >= max_cop) {
            answer = Math.min(answer, result);
            return;
        }

        if (result < answer) {
            // 부족한 능력치를 공부로 해결하는 경우
            int extraCost = 0;
            if (alp < max_alp) extraCost += max_alp - alp;
            if (cop < max_cop) extraCost += max_cop - cop;
            solve(max_alp, max_cop, result + extraCost);

            //문제 풀기
            //아직 못푸는 문제라면 해당 문제를 풀기 위해 공부를 한 후 풀기
            for (int[] problem : problemList) {
                int currAlp = alp < problem[0] ? problem[0] : alp;
                int currCop = cop < problem[1] ? problem[1] : cop;

                solve(currAlp + problem[2], currCop + problem[3], result + problem[4] + currAlp + currCop - alp - cop);
            }
        }
    }

    public int solution(int alp, int cop, int[][] problems) {
        max_alp = 0;
        max_cop = 0;
        problemList = new ArrayList<>();

        for (int[] problem : problems) {
            max_alp = Math.max(max_alp, problem[0]);
            max_cop = Math.max(max_cop, problem[1]);
            if (problem[2] + problem[3] > problem[4]) {
                problemList.add(problem);
            }
        }

        answer = Integer.MAX_VALUE;

        solve(alp, cop, 0);

        return answer;
    }
}
