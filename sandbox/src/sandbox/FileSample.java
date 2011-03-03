package sandbox;

import static java.lang.System.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

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
            BufferedWriter bw = new BufferedWriter(new FileWriter(name.replaceAll(".txt", ".out")));
            BufferedReader br = new BufferedReader(new FileReader(file));
            while (true) {
                String line = br.readLine();
                if (line == null) {
                    break;
                }
                String[] words = line.split(",");
                if (words[0].equals("Kappa")) {
                    bw.write("Kappa=" + words[1]);
                } else if (words[0].equals("Eta")) {
                    bw.write("Eta=" + words[1]);
                } else if (words[0].equals("V_0")) {
                    bw.write("V_0=" + words[1]);
                } else if (words[0].equals("Corr_Rho")) {
                    bw.write("Corr_Rho=" + words[1]);
                } else if (words[0].equals("Corr_Zeta")) {
                    bw.write("Corr_Zeta=" + words[1]);
                } else if (words[0].equals("Corr_Gamma")) {
                    bw.write("Corr_Gamma=" + words[1]);
                } else if (words[0].equals("NumOfModelUnderlyings")) {
                    bw.write("NumOfModelUnderlyings=" + words[1]);
                } else if (words[0].equals("CapletOrder")) {
                    bw.write("CapletOrder=" + words[1]);
                } else if (words[0].equals("SwaptionOrder")) {
                    bw.write("SwaptionOrder=" + words[1]);
                } else if (words[0].equals("CalibMarket")) {
                    bw.write("CalibMarket=" + words[1]);
                } else if (words[0].equals("CapletSVI")) {
                    bw.write("CapletSVI=" + words[1]);
                } else if (words[0].equals("SwaptionSVI")) {
                    bw.write("SwaptionSVI=" + words[1]);
                }
                bw.newLine();
            }
            bw.flush();
            bw.close();
            br.close();
        }
    }
}
