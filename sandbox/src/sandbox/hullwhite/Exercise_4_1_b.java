package sandbox.hullwhite;

import static java.lang.Math.*;

public class Exercise_4_1_b {

    private static double dt;
    private static double dx;

    public static void main(String[] args) {
        for (int i = 1; i < 500; ++i) {
            double c = calcBSEuropeanCall(100, 80, 0.001, 3, 0.2, i);
            System.out.println(i + "\t" + c);
        }
    }

    private static double calcBSEuropeanCall(double S, double K, double r, double T, double sigma, int step) {
        Lattice lattice = new Lattice(T, step);

        dt = lattice.getDT();
        dx = sigma * sqrt(3 * dt);

        double prob = pow(sigma, 2) * dt / 2 / pow(dx, 2);

        for (int i = -step; i <= step; ++i) {
            lattice.setValue(step, i, max(getS(step, i, S, r, sigma) - K, 0));
        }

        for (int n = step - 1; 0 <= n; --n) {
            for (int i = -n; i <= n; ++i) {
                lattice.setValue(n, i,
                        (prob * lattice.getValue(n + 1, i + 1) + (1 - 2 * prob) * lattice.getValue(n + 1, i) + prob
                                * lattice.getValue(n + 1, i - 1))
                                * exp(-r * dt));
            }
        }

        // System.out.println(lattice);

        return lattice.getValue(0, 0);
    }

    private static double getS(int n, int i, double S, double r, double sigma) {
        return exp(log(S) + i * dx + (r - (pow(sigma, 2) / 2)) * n * dt);
    }
}
