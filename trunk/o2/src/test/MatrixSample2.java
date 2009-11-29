package test;

import org.apache.commons.math.linear.MatrixUtils;
import org.apache.commons.math.linear.RealMatrix;

public class MatrixSample2 {

	public static void main(String[] args) {

		double[][] a = { { 5, 3, 1 }, { 4, 5, 2 }, { 1, 3, 6 } };
		double[] b = { 3, 4, 6 };

		RealMatrix ma = MatrixUtils.createRealMatrix(a);
		RealMatrix mai = ma.inverse();

		RealMatrix mb = MatrixUtils.createColumnRealMatrix(b);
		RealMatrix mx1 = mai.multiply(mb);

		System.out.println("‹ts—ñ‚ğì‚Á‚Ä˜A—§•û’ö®‚ğ‰ğ‚­");
		for (int x = 0; x < mx1.getRowDimension(); ++x) {
			for (int y = 0; y < mx1.getColumnDimension(); ++y) {
				System.out.print(mx1.getEntry(x, y) + "      ");
			}
			System.out.println();
		}

		System.out.println("‹ts—ñ‚ğì‚ç‚¸•û’ö®‚ğ‰ğ‚­");
		// ‹ts—ñ‚ğì‚ç‚¸•û’ö®‚ğ‰ğ‚­
		double[] x2 = ma.solve(b);
		for (int y = 0; y < x2.length; ++y) {
			System.out.println(x2[y]);
		}
	}
}
