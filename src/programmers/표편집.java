import java.util.*;

class Solution {
    public String solution(int n, int k, String[] cmd) {
        boolean[] table = new boolean[n];
        int[] prev = new int[n];
        int[] next = new int[n];
        Deque<Integer> deleteStack = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();
        Arrays.fill(table, true);
        int curr = k;

        for (int i = 0; i < n - 1; i++) {
            prev[i + 1] = i;
            next[i] = i + 1;
        }
        prev[0] = -1;
        next[n - 1] = -1;

        for (String c : cmd) {
            char command = c.charAt(0);
            if (command == 'U') {
                int num = Integer.parseInt(c.substring(2));
                for (int i = 0; i < num; i++) {
                    curr = prev[curr];
                }
            } else if (command == 'D') {
                int num = Integer.parseInt(c.substring(2));
                for (int i = 0; i < num; i++) {
                    curr = next[curr];
                }
            } else if (command == 'C') {
                table[curr] = false;
                deleteStack.push(curr);
                int nextNode = next[curr];
                int prevNode = prev[curr];

                if (nextNode != -1) {
                    prev[nextNode] = prevNode;
                    curr = nextNode;
                } else {
                    curr = prevNode;
                }
                if (prevNode != -1) next[prevNode] = nextNode;
            } else if (command == 'Z') {
                int rollback = deleteStack.pop();
                table[rollback] = true;
                int nextNode = next[rollback];
                int prevNode = prev[rollback];
                if (prevNode != -1) next[prevNode] = rollback;
                if (nextNode != -1) prev[nextNode] = rollback;
            }
        }

        for (int i = 0; i < n; i++) {
            sb.append(table[i] ? 'O' : 'X');
        }

        return sb.toString();
    }
}