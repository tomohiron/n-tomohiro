package sandbox.kijima;

import static java.lang.Math.*;

import java.util.Random;

public class Exercise_5_3 {

    private static final Random rand = new Random();

    public static void main(String[] args) {
        long start = System.nanoTime();
        int run = 100000;
        double c = calcMCLookbackCall(100, 0.001, 3, 0.2, 100, 1, run);
        System.out.println("time[ms]=" + (System.nanoTime() - start) / 1000000d);
        System.out.println("run=" + run);
        System.out.println("call=" + c);
    }

    private static double calcMCLookbackCall(double S, double r, double T, double sigma, int points, int method, int run) {

        double[][] x = new double[run + 1][points + 1];
        if (method == 1) {
            for (int i = 1; i <= run; ++i) {
                for (int j = 1; j <= points; ++j) {
                    x[i][j] = rand.nextGaussian();
                }
            }
        }
        // TODO else

        double deltaT = T / points;

        double[][] St = new double[run + 1][points + 1];
        double[] Smin = new double[run + 1];
        for (int i = 1; i <= run; ++i) {
            St[i][0] = S;
            Smin[i] = St[i][0];
            for (int j = 1; j <= points; ++j) {
                St[i][j] = St[i][j - 1] * exp(r * deltaT - pow(sigma, 2) / 2 * deltaT + sigma * x[i][j] * sqrt(deltaT));
                if (St[i][j] < Smin[i]) {
                    Smin[i] = St[i][j];
                }
            }
        }

        double sum = 0;
        for (int i = 1; i <= run; ++i) {
            double payoff = max(St[i][points] - Smin[i], 0);
            sum += payoff;
        }

        return exp(-r * T) * sum / run;
    }
}
