package sandbox.hullwhite;

import static java.lang.Math.*;
import static sandbox.hullwhite.Util.*;

public class Exercise_1_3 {

    public static void main(String[] args) {
        double c = calcBSEuropeanCall(100, 80, 0.001, 3, 0.2);
        System.out.println("c = " + c);
    }

    private static double calcBSEuropeanCall(double S, double K, double r, double T, double sigma) {
        double d = (log(S / K) + r * T) / (sigma * sqrt(T)) + sigma * sqrt(T) / 2;
        double c = S * normsdist(d) - K * exp(-r * T) * normsdist(d - sigma * sqrt(T));
        return c;
    }
}
