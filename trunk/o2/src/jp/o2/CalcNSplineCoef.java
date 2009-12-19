package jp.o2;

import org.apache.commons.math.linear.MatrixUtils;
import org.apache.commons.math.linear.RealMatrix;

public class CalcNSplineCoef {

    public static double[] execute(double[] x0, double[] y0) {

        if (x0.length != y0.length) {
            // TODO
            return null;
        }

        int length = x0.length;

        double[][] a = new double[length + 2][length + 2];

        for (int row = 0; row < length; row++) {
            a[row][0] = 1.0;
            a[row][1] = x0[row];
            for (int i = 0; i < length; i++) {
                a[row][i + 2] = Math.pow(Math.max(x0[row] - x0[i], 0.0), 3.0);
            }
        }

        a[length][0] = 0.0;
        a[length][1] = 0.0;
        for (int i = 0; i < length; i++) {
            a[length][i + 2] = 1.0;
        }

        a[length + 1][0] = 0.0;
        a[length + 1][1] = 0.0;
        for (int i = 0; i < length; i++) {
            a[length + 1][i + 2] = x0[i];
        }

        double[] y = new double[length + 2];
        for (int i = 0; i < length; i++) {
            y[i] = y0[i];
        }
        y[length] = 0.0;
        y[length + 1] = 0.0;

        RealMatrix ma = MatrixUtils.createRealMatrix(a);
        RealMatrix mai = ma.inverse();

        RealMatrix my = MatrixUtils.createColumnRealMatrix(y);
        RealMatrix mx = mai.multiply(my);

        return mx.getColumn(0);
    }

}
