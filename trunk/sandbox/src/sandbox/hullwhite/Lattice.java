package sandbox.hullwhite;

public class Lattice {

    private double T;
    private int N;
    private double dt;
    private double[][] values;

    public Lattice(double _T, int _N) {
        T = _T;
        N = _N;
        dt = T / N;
        values = new double[N + 1][N * 2 + 1];
    }

    public double getDT() {
        return dt;
    }

    public void setValue(int n, int i, double value) {
        values[n][i + N] = value;
    }

    public double getValue(int n, int i) {
        return values[n][i + N];
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        for (int n = 0; n < values.length; ++n) {
            for (int i = 0; i < values[n].length; ++i) {
                sb.append(values[n][i] + ",");
            }
            sb.append("¥n");
        }
        return sb.toString();
    }
}
