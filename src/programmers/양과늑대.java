class Solution {
    public int N;
    public int[] nodeInfo;
    public int answer;
    public boolean[][] matrix;

    public void dfs(int sheep, int wolf, int curr, int possibles) {
        boolean flag = true;

        //새로 갈 수 있는 노드 추가
        for (int i = 0; i < N; i++) {
            if (matrix[curr][i]) {
                possibles += 1 << i;
            }
        }

        //갈 수 있는 곳이 있는지
        for (int i = 0; i < N; i++) {
            if ((possibles & (1 << i)) != 0) {
                if (nodeInfo[i] == 0) {
                    flag = false;
                    dfs(sheep + 1, wolf, i, possibles - (1 << i));
                } else if (sheep - wolf > 1) {
                    flag = false;
                    dfs(sheep, wolf + 1, i, possibles - (1 << i));
                }
            }
        }

        //더 이상 갈 수 있는 노드가 없는 경우
        if (flag) {
            answer = Math.max(answer, sheep);
        }
    }

    public int solution(int[] info, int[][] edges) {
        N = info.length;
        nodeInfo = info;
        matrix = new boolean[N][N];

        for (int[] edge : edges) {
            matrix[edge[0]][edge[1]] = true;
        }

        dfs(1, 0, 0, 0);
        return answer;
    }
}