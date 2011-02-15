package sandbox.euler;

import java.util.HashMap;
import java.util.Map;

public class ID0014 {

    private static final long LIMIT = 1000000;

    public static void main(String[] args) {

        Map<Long, Long> cash = new HashMap<Long, Long>();
        long maxCount = 0;
        long maxInit = 0;

        for (long init = 1; init <= LIMIT; ++init) {

            long count = 0;
            long n = init;
            while (n != 1) {
                if (cash.containsKey(n)) {
                    count += cash.get(n);
                    break;
                }

                ++count;
                if (n % 2 == 0) {
                    n = n / 2;
                } else {
                    n = 3 * n + 1;
                }
            }

            cash.put(init, count);

            if (maxCount < count) {
                maxCount = count;
                maxInit = init;
                System.out.println("init=" + init + ", count=" + count);
            }
        }

        System.out.println("maxInit=" + maxInit + ", maxCount=" + maxCount);
    }
}
