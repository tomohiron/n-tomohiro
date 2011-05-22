package sandbox.motecarlo;

import java.util.Random;

public class DistTest {

    public static void main(String[] args) throws Exception {
        Dist dist = new Dist(-3, 3, 1000);
        Random random = new Random();

        for (int i = 0; i < 1000000; ++i) {
            // dist.set(random.nextDouble());
            dist.set(random.nextGaussian());
        }

        System.out.println(dist);
        dist.toCSV("./dist.csv");
    }

}
