package jp.o2.service;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import jp.o2.logic.CalcNSplineCoef;
import jp.o2.logic.CalcNSplineValues;

public class MarketYieldService {

    private static final Logger logger = Logger.getLogger(MarketYieldService.class.getName());

    public List<Map<String, Object>> getNSplineValues(List<Map<String, String>> yieldArray) {
        logger.info(yieldArray.toString());

        int size = yieldArray.size();
        double[] x0 = new double[size];
        double[] y0 = new double[size];
        for (int i = 0; i < size; i++) {
            Map<String, String> yield = yieldArray.get(i);
            x0[i] = Double.valueOf(yield.get("year"));
            y0[i] = Double.valueOf(yield.get("rate"));
        }

        double[] coef = CalcNSplineCoef.execute(x0, y0);
        List<Map<String, Object>> values = CalcNSplineValues.execute(coef, x0);
        return values;
    }
}
