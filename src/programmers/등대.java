import java.util.*;

class Solution {
    public List<List<Integer>> graph;
    public int answer;

    public int dfs(int curr, int prev) {
        List<Integer> linkedNodes = graph.get(curr);
        if (linkedNodes.size() == 1 && linkedNodes.get(0) == prev) return 0;

        boolean flag = false;

        for (Integer node : linkedNodes) {
            if (node != prev && dfs(node, curr) == 0) {
                flag = true;
            }
        }

        if (flag) answer++;

        return flag ? 1 : 0;
    }

    public int solution(int n, int[][] lighthouse) {
        graph = new ArrayList<>();
        answer = 0;

        for (int i = 0; i < n + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] path : lighthouse) {
            graph.get(path[0]).add(path[1]);
            graph.get(path[1]).add(path[0]);
        }

        dfs(1, 0);

        return answer;
    }
}