package jp.o2.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CalcNSplineValues {

    private static final int MAX = 10;
    private static final int RATIO = 100;

    public static List<Map<String, Object>> execute(double[] coef, double[] x0) {

        if (coef.length != x0.length + 2) {
            // TODO
            return null;
        }

        List<Map<String, Object>> values = new ArrayList<Map<String, Object>>();
        for (int i = 0; i <= MAX * RATIO; i++) {
            double x = ((double) i) / ((double) RATIO);
            String term = "";
            if (i % RATIO == 0) {
                term = x + "Y";
            }

            double y = nSpline(x0, coef, x);

            Map<String, Object> value = new HashMap<String, Object>();
            value.put("term", term);
            value.put("rate", Double.valueOf(y));
            values.add(value);
        }

        return values;
    }

    private static double nSpline(double[] x0, double[] coef, double x) {
        double y = coef[0] + coef[1] * x;
        for (int j = 0; j < x0.length; j++) {
            y += coef[j + 2] * Math.pow(Math.max(x - x0[j], 0.0), 3.0);
        }
        return y;
    }

}
