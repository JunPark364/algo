class Solution {
    public int N, M;

    public int[][] rotate(int[][] key) {
        int[][] result = new int[M][M];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                result[j][M - i - 1] = key[i][j];
            }
        }
        return result;
    }

    public boolean solution(int[][] key, int[][] lock) {
        M = key.length;
        N = lock.length;
        int hole = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (lock[i][j] == 0) hole++;
            }
        }

        int[][] currKey = key;
        for (int i = 0; i < 4; i++) {
            for (int x = 1 - M; x < N; x++) {
                flag : for (int y = 1 - M; y < N; y++) {
                    int count = 0;
                    for (int r = 0; r < M; r++) {
                        for (int c = 0; c < M; c++) {
                            if (r + x >= 0 && r + x < N && y + c >= 0 && y + c < N) {
                                if ((lock[r + x][y + c] == 0 && currKey[r][c] == 0) || (lock[r + x][y + c] == 1 && currKey[r][c] == 1)) {
                                    continue flag;
                                } else if (lock[r + x][y + c] == 0 && currKey[r][c] == 1) {
                                    count++;
                                }
                            }
                        }
                    }

                    if (count == hole) return true;
                }
            }

            currKey = rotate(currKey);
        }

        return false;
    }
}