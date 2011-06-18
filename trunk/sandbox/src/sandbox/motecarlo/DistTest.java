package sandbox.motecarlo;

import java.util.Random;

public class DistTest {

    public static void main(String[] args) throws Exception {
        Dist dist = new Dist(-5, 5, 1000);
        Random random = new Random();

        for (int i = 0; i < 1E8; ++i) {
            // dist.set(random.nextDouble());
            dist.set(random.nextGaussian());
            // dist.set(Math.exp(random.nextGaussian()));
        }

        System.out.println(dist);
        dist.toCSV("./dist.csv");
    }

}
