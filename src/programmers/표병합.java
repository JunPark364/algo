import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public String[] values;
    public int[] parents;

    public int find(int cell) {
        return parents[cell] == cell ? cell : find(parents[cell]);
    }

    public void union(int cell1, int cell2) {
        int root1 = find(cell1);
        int root2 = find(cell2);
        if (root1 == root2) return;
        if (values[root1].isBlank() && !values[root2].isBlank()) {
            parents[root1] = root2;
            values[root1] = "";
        } else {
            parents[root2] = root1;
            values[root2] = "";
        }
    }

    public String[] solution(String[] commands) {
        values = new String[2500];
        parents = new int[2500];
        List<String> answer = new ArrayList<>();
        Arrays.fill(values, "");
        Arrays.setAll(parents, i -> i);

        for (String command : commands) {
            String[] commandArr = command.split(" ");

            if (commandArr[0].equals("UPDATE")) {
                if (commandArr.length == 4) {
                    int nr = Integer.parseInt(commandArr[1]) - 1;
                    int nc = Integer.parseInt(commandArr[2]) - 1;
                    int root = find(nr * 50 + nc);
                    values[root] = commandArr[3];
                } else {
                    for (int i = 0; i < 2500; i++) {
                        if (values[i].equals(commandArr[1])) {
                            values[i] = commandArr[2];
                        }
                    }
                }
            } else if (commandArr[0].equals("MERGE")) {
                int nr1 = Integer.parseInt(commandArr[1]) - 1;
                int nc1 = Integer.parseInt(commandArr[2]) - 1;
                int nr2 = Integer.parseInt(commandArr[3]) - 1;
                int nc2 = Integer.parseInt(commandArr[4]) - 1;
                union(nr1 * 50 + nc1, nr2 * 50 + nc2);
            } else if (commandArr[0].equals("UNMERGE")) {
                int nr = Integer.parseInt(commandArr[1]) - 1;
                int nc = Integer.parseInt(commandArr[2]) - 1;
                int cell = nr * 50 + nc;
                int root = find(cell);
                String rootValue = values[root];
                List<Integer> deleteList = new ArrayList<>();
                for (int i = 0; i < 2500; i++) {
                    if (find(i) == root) {
                        deleteList.add(i);
                    }
                }
                for (Integer i : deleteList) {
                    parents[i] = i;
                }
                values[root] = "";
                values[cell] = rootValue;
            } else if (commandArr[0].equals("PRINT")) {
                int nr = Integer.parseInt(commandArr[1]) - 1;
                int nc = Integer.parseInt(commandArr[2]) - 1;
                int root = find(nr * 50 + nc);
                answer.add(values[root].isBlank() ? "EMPTY" : values[root]);
            }
        }

        return answer.toArray(new String[0]);
    }
}
