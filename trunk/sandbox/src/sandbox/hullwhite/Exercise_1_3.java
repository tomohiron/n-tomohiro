package sandbox.hullwhite;

import static java.lang.Math.PI;
import static java.lang.Math.abs;
import static java.lang.Math.exp;
import static java.lang.Math.log;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Exercise_1_3 {

    public static void main(String[] args) {
        double c = calcBSEuropeanCall(100, 80, 0.001, 3, 0.2);
        System.out.println("c = " + c);
    }

    private static double calcBSEuropeanCall(double S, double K, double r, double T, double sigma) {
        double d = (log(S / K) + r * T) / (sigma * sqrt(T)) + sigma * sqrt(T) / 2;
        double c = S * norm_1(d) - K * exp(-r * T) * norm_1(d - sigma * sqrt(T));
        return c;
    }

    // standart normal distribution
    // http://gihyo.jp/dev/serial/01/java-calculation/0055
    // private static double norm_1(double z) {
    // return exp(-1 * pow(z, 2) / 2) / sqrt(2 * PI);
    // }

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
