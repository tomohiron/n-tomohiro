package sandbox.euler;

public class ID0004 {

    public static void main(String[] args) {

        int max = 0;
        int n_max = 0;
        int m_max = 0;
        for (int m = 999; m >= 100; --m) {
            for (int n = 999; n >= m; --n) {
                int value = m * n;
                if (isPalindromic(value)) {
                    if (max < value) {
                        max = value;
                        n_max = n;
                        m_max = m;
                    }
                    break;
                }
            }
        }
        System.out.println(max);
        System.out.println(m_max);
        System.out.println(n_max);
    }

    private static boolean isPalindromic(int n) {
        if (10000 <= n && n < 100000) {
            return digit(n, 4) == digit(n, 0) && digit(n, 3) == digit(n, 1);
        } else if (n < 1000000) {
            return digit(n, 5) == digit(n, 0) && digit(n, 4) == digit(n, 1) && digit(n, 3) == digit(n, 2);
        }
        System.err.println("ERROR");
        return false;
    }

    private static final int digit(int n, int i) {
        int m = ((int) (n / Math.pow(10, i))) % 10;
        return m;
    }

}
