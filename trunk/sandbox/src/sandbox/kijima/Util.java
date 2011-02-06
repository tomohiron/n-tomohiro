package sandbox.kijima;

import static java.lang.Math.*;

public class Util {

    public static double normsdist(double x) {
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
