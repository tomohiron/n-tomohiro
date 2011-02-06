package sandbox.hullwhite;

import static java.lang.Math.*;

public class Exercise_4_1 {

    private static double dt;
    private static double dx;

    public static void main(String[] args) {
        for (int i = 1; i < 500; ++i) {
            double c = calcBSEuropeanCall(100, 80, 0.001, 3, 0.2, i);
            System.out.println(i + "\t" + c);
        }
    }

    private static double calcBSEuropeanCall(double S, double K, double r, double T, double sigma, int step) {
        dt = T / step;
        dx = sigma * sqrt(3 * dt);

        double prob = pow(sigma, 2) * dt / 2 / pow(dx, 2);

        double[][] value = new double[step + 1][step * 2 + 1];
        for (int i = -step; i <= step; ++i) {
            value[step][i + step] = max(getS(step, i, S, r, sigma) - K, 0);
        }

        for (int n = step - 1; 0 <= n; --n) {
            for (int i = -n; i <= n; ++i) {
                value[n][i + step] = (prob * value[n + 1][i + 1 + step] + (1 - 2 * prob) * value[n + 1][i + step] + prob
                        * value[n + 1][i - 1 + step])
                        * exp(-r * dt);
            }
        }

        // print(value);

        return value[0][0 + step];
    }

    private static double getS(int n, int i, double S, double r, double sigma) {
        return exp(log(S) + i * dx + (r - (pow(sigma, 2) / 2)) * n * dt);
    }

    private static void print(double[][] value) {
        for (int i = 0; i < value.length; ++i) {
            for (int j = 0; j < value[i].length; ++j) {
                System.out.print(value[i][j] + ",");
            }
            System.out.println();
        }
    }
}
