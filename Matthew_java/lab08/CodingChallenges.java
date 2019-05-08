public class CodingChallenges {

    /**
     * Return the missing number from an array of length N - 1 containing all
     * the values from 0 to N except for one missing number.
     */
    public static int missingNumber(int[] values) {
        // TODO
        for (int i = 0; i <= values.length; i++) {
            int count = 0;
            for (int j = 0; j < values.length; j++) {
                if (i == values[j]) {
                    count++;
                }
            }
            if (count == 0) {
                return i;
            }
        }
        return -1;
    }

    /** Returns true if and only if two integers in the array sum up to n. */
    public static boolean sumTo(int[] values, int n) {
        // TODO
        for (int i = 0; i < values.length; i++) {
            for (int j = 0; j < values.length; j++) {
                if (values[i] + values[j] == n) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Returns true if and only if s1 is a permutation of s2. s1 is a
     * permutation of s2 if it has the same number of each character as s2.
     */
    public static boolean isPermutation(String s1, String s2) {
        // TODO
        char[] s11 = s1.toCharArray();
        char[] s22 = s2.toCharArray();
        if (s11.length != s22.length) {
            return false;
        } else {
            int count = 0;
            for (int i = 0; i < s11.length; i++) {
                for (int j = 0; j < s11.length; j++) {
                    if (s11[i] == s22[j]) {
                        count++;
                    }
                }
            }
            return count == s11.length;
        }
    }
}
