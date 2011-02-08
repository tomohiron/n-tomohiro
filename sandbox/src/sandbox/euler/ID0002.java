package sandbox.euler;

public class ID0002 {

    private static final int MAX = 50;

    public static void main(String[] args) {

        long[] array = new long[MAX];
        array[0] = 1;
        array[1] = 2;

        long sum = array[1];
        for (int i = 2; i < 100; ++i) {
            array[i] = array[i - 1] + array[i - 2];
            System.out.println("a[" + i + "]=" + array[i]);
            if (array[i] > 4000000L) {
                break;
            }
            if (array[i] % 2 == 0) {
                sum += array[i];
            }
        }

        System.out.println("sum=" + sum);
    }
}
