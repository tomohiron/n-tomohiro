package sandbox.hullwhite;

import static java.lang.Math.PI;
import static java.lang.Math.abs;
import static java.lang.Math.exp;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class NormalDistributionTest {

    public static void main(String[] args) {
        for (double x = -5.0; x <= 5.0; x += 0.01) {
            double y1 = norm_1(x);
            double y2 = norm_gihyo(x);
            System.out.println(x + "\t" + y1 + "\t" + y2);
        }
    }

    // standart normal distribution
    // http://gihyo.jp/dev/serial/01/java-calculation/0055
    private static double norm_gihyo(double z) {
        return exp(-1 * pow(z, 2) / 2) / sqrt(2 * PI);
    }

    private static double norm_1(double x) {
        double p = 0.2316419;
        double b1 = 0.31938153;
        double b2 = -0.356563782;
        double b3 = 1.781477937;
        double b4 = -1.821255978;
        double b5 = 1.330274429;

        double T = 1 / (1 + p * abs(x));
        double B = 1 / sqrt(2 * PI) * exp(-pow(x, 2) / 2)
                * (b1 * T + b2 * pow(T, 2) + b3 * pow(T, 3) + b4 * pow(T, 4) + b5 * pow(T, 5));
        if (x < 0) {
            return B;
        } else {
            return 1 - B;
        }
    }

}
