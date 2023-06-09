import java.util.*;

class Solution {
    public int[] solution(int target) {
        int[] singlePoints = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 50};
        int[] specialPoints = new int[] {21, 22, 24, 26, 27, 28, 30, 32, 33, 34, 36, 39, 40, 42, 45, 48, 51, 54, 57, 60};
        Comparator<int[]> comparator = (p1, p2) -> p1[1] == p2[1] ? p2[2] - p1[2] : p1[1] - p2[1];
        PriorityQueue<int[]> pqueue = new PriorityQueue<>(comparator);
        pqueue.offer(new int[] {0, 0, 0});
        Set<Integer> possibles = new HashSet<>();

        while (!pqueue.isEmpty()) {
            int[] curr = pqueue.poll();
            if (possibles.contains(curr[0])) continue;
            if (curr[0] == target) return new int[] {curr[1], curr[2]};

            possibles.add(curr[0]);

            for (int p : singlePoints) {
                pqueue.offer(new int[] {curr[0] + p, curr[1] + 1, curr[2] + 1});
            }

            for (int p : specialPoints) {
                pqueue.offer(new int[] {curr[0] + p, curr[1] + 1, curr[2]});
            }
        }

        return new int[] {0, 0};
    }
}