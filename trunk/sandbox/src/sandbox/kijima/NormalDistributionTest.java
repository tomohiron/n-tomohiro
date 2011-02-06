package sandbox.kijima;

import static java.lang.Math.*;
import static sandbox.kijima.Util.*;

public class NormalDistributionTest {

    public static void main(String[] args) {
        for (double x = -5.0; x <= 5.0; x += 0.01) {
            double y1 = normsdist(x);
            double y2 = normalDens(x);
            System.out.println(x + "\t" + y1 + "\t" + y2);
        }
    }

    // standart normal distribution
    // http://gihyo.jp/dev/serial/01/java-calculation/0055
    private static double normalDens(double z) {
        return exp(-1 * pow(z, 2) / 2) / sqrt(2 * PI);
    }
}
