package test;

import org.apache.commons.math.linear.MatrixUtils;
import org.apache.commons.math.linear.RealMatrix;

public class MatrixSample {

	public static void main(String[] args) {

		double[][] m = { { 1, -1, 2, 1 }, { 2, 1, -1, 3 }, { 1, 3, 2, -2 },
				{ -3, 0, 1, 4 } };
		double[] yy = { 9, 6, 2, -3 };
		// 行列をつくる
		RealMatrix rm = MatrixUtils.createRealMatrix(m);
		// 逆行列
		RealMatrix grm = rm.inverse();
		// 逆行列で方程式を解く
		RealMatrix crm = MatrixUtils.createColumnRealMatrix(yy);
		RealMatrix an1 = grm.multiply(crm);
		System.out.println("逆行列を作って連立方程式を解く");
		for (int x = 0; x < an1.getRowDimension(); ++x) {
			for (int y = 0; y < an1.getColumnDimension(); ++y) {
				System.out.print(an1.getEntry(x, y) + "      ");
			}
			System.out.println();
		}
		System.out.println("逆行列を作らず方程式を解く");
		// 逆行列を作らず方程式を解く
		double[] an2 = rm.solve(yy);
		for (int y = 0; y < an2.length; ++y) {
			System.out.println(an2[y]);
		}
	}
}
