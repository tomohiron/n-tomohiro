package sandbox.csv;

import java.io.File;
import java.util.List;

import jp.sf.orangesignal.csv.Csv;
import jp.sf.orangesignal.csv.CsvConfig;
import jp.sf.orangesignal.csv.handlers.StringArrayListHandler;

public class CsvSample01 {

    public static void main(String[] args) throws Exception {
        new CsvSample01().execute();
    }

    public void execute() throws Exception {
        List<String[]> list = Csv.load(new File("event.csv"), "SJIS", new CsvConfig(), new StringArrayListHandler());
        for (String[] array : list) {
            for (String s : array) {
                System.out.print(s + " / ");
            }
            System.out.println();
        }
    }

}
