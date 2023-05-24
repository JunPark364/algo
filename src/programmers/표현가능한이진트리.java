class Solution {
    public boolean calc(String numStr) {
        int numLen = numStr.length();
        if (numLen == 1) return true;

        String left = numStr.substring(0, numLen / 2);
        String right = numStr.substring(numLen / 2 + 1, numLen);

        if (numStr.charAt(numLen / 2) == '0') {
            for (int i = 0; i < numLen / 2; i++) {
                if (left.charAt(i) == '1' || right.charAt(i) == '1') return false;
            }
            return true;
        } else {
            return calc(left) && calc(right);
        }

    }

    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            String binaryString = Long.toBinaryString(numbers[i]);
            int len = binaryString.length();
            int digit = 1;
            int num = 1;

            while (binaryString.length() > num) {
                digit *= 2;
                num += digit;
            }

            for (int j = 0; j < num - len; j++) {
                binaryString = "0" + binaryString;
            }

            answer[i] = calc(binaryString) ? 1 : 0;
        }
        return answer;
    }
}