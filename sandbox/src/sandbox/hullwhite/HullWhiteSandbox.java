package sandbox.hullwhite;

import static java.lang.Math.*;
import static java.lang.System.*;

import java.util.HashMap;
import java.util.Map;

public class HullWhiteSandbox {

    private static final int STEP_SIZE = 13;
    private static final int BUFFER = 1;

    public static void main(String[] args) {

        // Def

        double a = 0.1;
        double sigma = 0.01;
        double delta_t = 0.25;

        double M = -1.0 * a * delta_t;
        double V = pow(sigma, 2.0) * delta_t;
        double delta_r = sqrt(3.0 * V);

        out.println("a = " + a);
        out.println("sigma = " + sigma);
        out.println("delta_t = " + delta_t);
        out.println("M = " + M);
        out.println("V = " + V);
        out.println("delta_r = " + delta_r);

        int j_max = (int) ceil(-0.184 / M);
        int j_min = -1 * j_max;

        // P norm, jmax, jmin

        Map<Integer, Double> Pnorm_u = newMap();
        Map<Integer, Double> Pnorm_m = newMap();
        Map<Integer, Double> Pnorm_d = newMap();
        for (int j = j_max - 1; j_min + 1 <= j; --j) {
            Pnorm_u.put(j, 1.0 / 6.0 + (pow(j, 2.0) * pow(M, 2.0) + j * M) / 2.0);
            Pnorm_m.put(j, 2.0 / 3.0 - pow(j, 2.0) * pow(M, 2.0));
            Pnorm_d.put(j, 1.0 / 6.0 + (pow(j, 2.0) * pow(M, 2.0) - j * M) / 2.0);
        }

        for (int j = j_max; j_min <= j; --j) {
            out.println("j = " + j + ", Pu = " + Pnorm_u.get(j) + ", Pm = " + Pnorm_m.get(j) + ", Pd = "
                    + Pnorm_d.get(j));
        }

        Map<Integer, Double> Pjmax_u = newMap();
        Map<Integer, Double> Pjmax_m = newMap();
        Map<Integer, Double> Pjmax_d = newMap();
        Pjmax_u.put(j_max, 7.0 / 6.0 + (pow(j_max, 2.0) * pow(M, 2.0) + 3.0 * j_max * M) / 2.0);
        Pjmax_m.put(j_max, -1.0 / 3.0 - pow(j_max, 2.0) * pow(M, 2.0) - 2.0 * j_max * M);
        Pjmax_d.put(j_max, 1.0 / 6.0 + (pow(j_max, 2.0) * pow(M, 2.0) + j_max * M) / 2.0);

        Map<Integer, Double> Pjmin_u = newMap();
        Map<Integer, Double> Pjmin_m = newMap();
        Map<Integer, Double> Pjmin_d = newMap();
        Pjmin_u.put(j_min, 1.0 / 6.0 + (pow(j_min, 2.0) * pow(M, 2.0) - j_min * M) / 2.0);
        Pjmin_m.put(j_min, -1.0 / 3.0 - pow(j_min, 2.0) * pow(M, 2.0) + 2.0 * j_min * M);
        Pjmin_d.put(j_min, 7.0 / 6.0 + (pow(j_min, 2.0) * pow(M, 2.0) - 3.0 * j_min * M) / 2.0);

        // Curve Data

        double[] df = new double[] { 1.0, 0.993841213, 0.987331092, 0.980146715, 0.972278502, 0.963812328, 0.954454998,
                0.945343173, 0.934944339, 0.924324335, 0.913255041, 0.901857442, 0.890286145, 0.878683607, 0.867132766,
                0.855679939, 0.844361572, 0.833072038, 0.822042351, 0.811233277, 0.800238368, 0.789244759, 0.778386074,
                0.767575191, 0.756729390, 0.746004352, 0.735665735, };

        // ArrowDeb, Alpha

        Map<Integer, Double>[] Q = newMapArray();
        double[] alpha = new double[STEP_SIZE];

        for (int m = 0; m < STEP_SIZE; ++m) {
            // Q
            if (m == 0) {
                Q[0].put(0, 1.0);
            } else {
                for (int j = m; -1 * m <= j; --j) {
                    double q = 0.0;
                    q += Q[m - 1].get(j + 2) * Pjmax_d.get(j + 2)
                            * exp(-1.0 * (alpha[m - 1] + (j + 2) * delta_r) * delta_t);
                    q += Q[m - 1].get(j + 1) * (Pjmax_m.get(j + 1) + Pnorm_d.get(j + 1))
                            * exp(-1.0 * (alpha[m - 1] + (j + 1) * delta_r) * delta_t);
                    q += Q[m - 1].get(j) * (Pjmax_u.get(j) + Pnorm_m.get(j) + Pjmin_d.get(j))
                            * exp(-1.0 * (alpha[m - 1] + j * delta_r) * delta_t);
                    q += Q[m - 1].get(j - 1) * (Pnorm_u.get(j - 1) + Pjmin_m.get(j - 1))
                            * exp(-1.0 * (alpha[m - 1] + (j - 1) * delta_r) * delta_t);
                    q += Q[m - 1].get(j - 2) * Pjmin_u.get(j - 1)
                            * exp(-1.0 * (alpha[m - 1] + (j - 2) * delta_r) * delta_t);
                    Q[m].put(j, q);
                }
            }

            // alpha
            double sum = 0.0;
            for (int j = m; -1 * m <= j; --j) {
                sum += Q[m].get(j) * exp(-1.0 * j * delta_r * delta_t);
            }
            alpha[m] = (log(sum) - log(df[m + 1])) / delta_t;
        }

        out.print("alpha = {");
        for (int m = 0; m < alpha.length; ++m) {
            out.print(alpha[m] + ", ");
        }
        out.println("}");

        out.println("Q = ");
        out.println(toString(Q));

        // Prob

        Map<Integer, Double>[] prob = newMapArray();
        prob[0].put(0, 1.0);
        for (int m = 1; m < STEP_SIZE; ++m) {
            for (int j = m; -1 * m <= j; --j) {
                double p = 0.0;
                p += prob[m - 1].get(j + 2) * Pjmax_d.get(j + 2);
                p += prob[m - 1].get(j + 1) * (Pjmax_m.get(j + 1) + Pnorm_d.get(j + 1));
                p += prob[m - 1].get(j) * (Pjmax_u.get(j) + Pnorm_m.get(j) + Pjmin_d.get(j));
                p += prob[m - 1].get(j - 1) * (Pnorm_u.get(j - 1) + Pjmin_m.get(j - 1));
                p += prob[m - 1].get(j - 2) * Pjmin_u.get(j - 1);
                prob[m].put(j, p);
            }
        }

        out.println("prob = ");
        out.println(toString(prob));

        // Short Rate

        Map<Integer, Double>[] rate = newMapArray();
        for (int m = 0; m < STEP_SIZE; ++m) {
            for (int j = m; -1 * m <= j; --j) {
                if (j < j_min || j_max < j) {
                    continue;
                }
                rate[m].put(j, alpha[m] + j * delta_r);
            }
        }

        out.println("rate = ");
        out.println(toString(rate));

    }

    private static Map<Integer, Double>[] newMapArray() {
        @SuppressWarnings("unchecked")
        Map<Integer, Double>[] mapArray = new HashMap[STEP_SIZE];
        for (int m = 0; m < STEP_SIZE; m++) {
            mapArray[m] = newMap();
        }
        return mapArray;
    }

    private static Map<Integer, Double> newMap() {
        Map<Integer, Double> map = new HashMap<Integer, Double>();
        for (int j = STEP_SIZE + BUFFER; -1 * (STEP_SIZE + BUFFER) <= j; --j) {
            map.put(j, 0.0);
        }
        return map;
    }

    private static final String toString(Map<Integer, Double>[] mapArray) {
        StringBuffer sb = new StringBuffer();
        for (int j = STEP_SIZE + BUFFER; -1 * (STEP_SIZE + BUFFER) <= j; --j) {
            sb.append("j = " + j + ", ");
            for (int m = 0; m < mapArray.length; ++m) {
                sb.append(mapArray[m].get(j) + ", ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
