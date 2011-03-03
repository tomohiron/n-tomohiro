package sandbox;

import static java.lang.System.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class FileSample {

    public static void main(String[] args) throws Exception {
        File root = new File("/Users/tomohiro");
        File[] files = root.listFiles();
        for (File file : files) {
            String name = file.getName();
            if (!name.endsWith(".txt")) {
                continue;
            }
            out.println(name);
            BufferedReader br = new BufferedReader(new FileReader(file));
            while (true) {
                String line = br.readLine();
                if (line == null) {
                    break;
                }
                String[] words = line.split(",");
                if (words[0].equals("Kappa")) {
                    out.println("Kappa=" + words[1]);
                } else if (words[0].equals("Eta")) {
                    out.println("Eta=" + words[1]);
                } else if (words[0].equals("V_0")) {
                    out.println("V_0=" + words[1]);
                } else if (words[0].equals("Corr_Rho")) {
                    out.println("Corr_Rho=" + words[1]);
                } else if (words[0].equals("Corr_Zeta")) {
                    out.println("Corr_Zeta=" + words[1]);
                } else if (words[0].equals("Corr_Gamma")) {
                    out.println("Corr_Gamma=" + words[1]);
                } else if (words[0].equals("NumOfModelUnderlyings")) {
                    out.println("NumOfModelUnderlyings=" + words[1]);
                } else if (words[0].equals("CapletOrder")) {
                    out.println("CapletOrder=" + words[1]);
                } else if (words[0].equals("SwaptionOrder")) {
                    out.println("SwaptionOrder=" + words[1]);
                } else if (words[0].equals("CalibMarket")) {
                    out.println("CalibMarket=" + words[1]);
                } else if (words[0].equals("CapletSVI")) {
                    out.println("CapletSVI=" + words[1]);
                } else if (words[0].equals("SwaptionSVI")) {
                    out.println("SwaptionSVI=" + words[1]);
                }
            }
            br.close();
        }
    }
}
