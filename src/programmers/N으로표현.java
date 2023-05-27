import java.util.*;

class Solution {
    public int solution(int N, int number) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        Set<Integer> possibles = new HashSet<>();

        for (int i = 1; i <= 8; i++) {
            map.put(i, new HashSet<>());
            Set<Integer> newSet = new HashSet<>();

            newSet.add(Integer.parseInt(String.valueOf(N).repeat(i)));

            for (int j = 1; j <= i / 2; j++) {
                Set<Integer> set1 = map.get(j);
                Set<Integer> set2 = map.get(i - j);
                for (int x : set1) {
                    for (int y : set2) {
                        newSet.add(x + y);
                        newSet.add(Math.abs(x - y));
                        newSet.add(x * y);
                        if (Math.min(x, y) != 0) newSet.add(Math.max(x, y) / Math.min(x, y));
                    }
                }
            }

            if (newSet.contains(number)) return i;

            for (int j : newSet) {
                if (!possibles.contains(j)) {
                    map.get(i).add(j);
                    possibles.add(j);
                }
            }
        }

        return -1;
    }
}