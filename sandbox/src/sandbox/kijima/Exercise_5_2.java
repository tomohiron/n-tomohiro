package sandbox.kijima;

import static java.lang.Math.*;

import java.util.Random;

public class Exercise_5_2 {

    private static final Random rand = new Random();

    public static void main(String[] args) {
        long start = System.nanoTime();
        int run = 10000000;
        double c = calcBSEuropeanCall(100, 80, 0.001, 3, 0.2, 2, run);
        System.out.println("time[ms]=" + (System.nanoTime() - start) / 1000000d);
        System.out.println("run=" + run);
        System.out.println("call=" + c);
    }

    private static double calcBSEuropeanCall(double S, double K, double r, double T, double sigma, int method, int run) {
        double[] y = new double[run + 1];
        if (method == 1) {
            for (int i = 1; i <= run; ++i) {
                // y[i] = normrand();
                y[i] = rand.nextGaussian();
            }
        } else if (method == 2) {
            double[] x = new double[run + 1];
            int N = (int) (run / 2);
            for (int i = 1; i <= N; ++i) {
                x[i] = rand.nextGaussian();
            }
            for (int i = 1; i <= N; ++i) {
                y[i] = x[i];
                y[N + i] = -x[i];
            }
        }

        double sum = 0;
        for (int i = 1; i <= run; ++i) {
            double ST = S * exp(r * T - pow(sigma, 2) / 2 * T + sigma * y[i] * sqrt(T));
            double payoff = max(ST - K, 0);
            sum += payoff;
        }

        return exp(-r * T) * sum / run;
    }

    // private static double normrand() {
    // double u1 = rand.nextDouble();
    // double u2 = rand.nextDouble();
    // return sqrt(-2 * log(u1)) * sin(2 * PI * u2);
    // }
}
