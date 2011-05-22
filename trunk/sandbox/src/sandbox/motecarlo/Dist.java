package sandbox.motecarlo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;

public class Dist {

    private static final String LS = System.getProperty("line.separator");

    private double min;
    private double max;
    private int grid;
    private double delta;
    private long[] array;
    private long total;

    private Dist() {
    }

    public Dist(double _min, double _max, int _grid) {
        min = _min;
        max = _max;
        grid = _grid;
        delta = (max - min) / grid;
        array = new long[grid];
        total = 0;
    }

    public void set(double value) {
        if (value < min || max < value) {
            return;
        }
        int index = (int) ((value - min) / delta);
        ++array[index];
        ++total;
    }

    public void toCSV(String filepath) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(filepath));
        bw.write(toString());
        bw.flush();
        bw.close();
    }

    public String toString() {
        DecimalFormat df = new DecimalFormat("#0.#################");
        StringBuffer sb = new StringBuffer();
        // sb.append("value,count" + LS);
        for (int i = 0; i < grid; ++i) {
            double value = min + delta * i;
            double ratio = (double) array[i] / total;
            sb.append(df.format(value) + "," + df.format(ratio) + LS);
        }
        return sb.toString();
    }
}
