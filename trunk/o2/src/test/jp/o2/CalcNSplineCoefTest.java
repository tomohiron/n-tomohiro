package test.jp.o2;

import jp.o2.CalcNSplineCoef;
import junit.framework.Assert;

import org.junit.Test;

public class CalcNSplineCoefTest {

    @Test
    public void checkLenghAndValue() {
        final double[] x0 = { 0.5, 1.0, 2.0 };
        final double[] y0 = { 0.3, 0.2, 0.5 };
        double[] coef = CalcNSplineCoef.execute(x0, y0);
        Assert.assertEquals(coef.length, 5);
        Assert.assertEquals(coef[0], 0.44166666666666665);
    }

    @Test
    public void checkNull() {
        final double[] x0 = { 0.1, 1.0 };
        final double[] y0 = { 0.1 };
        double[] coef = CalcNSplineCoef.execute(x0, y0);
        Assert.assertNull(coef);
    }

}
