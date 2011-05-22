package sandbox.motecarlo;

import static java.lang.Math.*;

import java.util.Random;

public class PiCalc {

    public static void main(String[] args) {
        long start = System.nanoTime();
        Random random = new Random();
        long hit = 0;
        for (int total = 1; total <= 1E8; ++total) {
            double r1 = random.nextDouble();
            double r2 = random.nextDouble();
            if (pow(r1, 2) + pow(r2, 2) <= 1) {
                ++hit;
            }
            if (total % 1E7 == 0) {
                long time = (long) ((System.nanoTime() - start) / 1E6);
                double pi = 4d * hit / total;
                System.out.println("time=" + time + "[ms],total=" + total + ",hit=" + hit + ",pi=" + pi);
            }
        }
    }

}
