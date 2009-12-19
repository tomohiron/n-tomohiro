package jp.o2.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class MarketYieldService {

    private static final Logger logger = Logger.getLogger(MarketYieldService.class.getName());

    public List<Double> getNSplineCoef(List<Map<String, String>> yeildArray) {
        logger.info(yeildArray.toString());

        for (Map<String, String> yield : yeildArray) {
            String id = (String) yield.get("id");
            double year = Double.valueOf(yield.get("year"));
            double rate = Double.valueOf(yield.get("rate"));
            logger.info("id=" + id);
            logger.info("year=" + year);
            logger.info("rate=" + rate);
        }

        List<Double> spline = new ArrayList<Double>();
        spline.add(Double.valueOf("0.44166667"));
        spline.add(Double.valueOf("-0.28333333"));
        spline.add(Double.valueOf("0.33333333"));
        spline.add(Double.valueOf("-0.50000000"));
        spline.add(Double.valueOf("0.16666667"));

        logger.info(spline.toString());

        return spline;
    }
}
