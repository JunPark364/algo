import java.util.*;

class Solution {
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        int[] answer = new int[] {0, Integer.MAX_VALUE};
        Comparator<int[]> comparator = (p1, p2) -> p1[1] - p2[1];
        Map<Integer, List<int[]>> pathMap = new HashMap<>();
        PriorityQueue<int[]> pqueue;
        Set<Integer> gateSet = new HashSet<>();
        Set<Integer> summitSet = new HashSet<>();

        Arrays.sort(summits); // 산봉우리 배열 오름차순 정렬

        // 노드 인접 리스트 생성
        for (int[] path : paths) {
            if (!pathMap.containsKey(path[0])) {
                pathMap.put(path[0], new ArrayList<>());
            }

            if (!pathMap.containsKey(path[1])) {
                pathMap.put(path[1], new ArrayList<>());
            }

            pathMap.get(path[0]).add(new int[] {path[1], path[2]});
            pathMap.get(path[1]).add(new int[] {path[0], path[2]});
        }

        // 입구 HashSet 생성
        for (int i : gates) {
            gateSet.add(i);
        }

        // 산봉우리 HashSet 생성
        for (int i : summits) {
            summitSet.add(i);
        }

        for (int summit : summits) {
            boolean[] visited = new boolean[n + 1];
            pqueue = new PriorityQueue<>(comparator);
            pqueue.offer(new int[] {summit, 0});

            while (!pqueue.isEmpty()) {
                int[] curr = pqueue.poll();

                if (visited[curr[0]]) continue;

                visited[curr[0]] = true;

                // base case: 입구 노드에 도달할 경우
                if (gateSet.contains(curr[0])) {
                    if (curr[1] < answer[1]) {
                        answer[0] = summit;
                        answer[1] = curr[1];
                    }
                    break;
                }

                List<int[]> nodes = pathMap.get(curr[0]);
                for (int[] next : nodes) {
                    if (!visited[next[0]] && !summitSet.contains(next[0])) {
                        pqueue.offer(new int[] {next[0], Math.max(curr[1], next[1])});
                    }
                }
            }
        }

        return answer;
    }
}