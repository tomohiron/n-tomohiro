package sandbox.csv;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import jp.sf.orangesignal.csv.Csv;
import jp.sf.orangesignal.csv.CsvConfig;
import jp.sf.orangesignal.csv.handlers.StringArrayListHandler;

public class CsvSample02 {

    private static final String KEY_MAX = "_max";
    private static final String KEY_MIN = "_min";

    public static void main(String[] args) throws Exception {
        new CsvSample02().execute();
    }

    public void execute() throws Exception {

        // String targetMonth = "2010/04";
        Map<String, String> map = new TreeMap<String, String>();

        List<String[]> list = Csv.load(new File("event.csv"), "SJIS", new CsvConfig(), new StringArrayListHandler());
        for (String[] array : list) {
            // System.out.println(array);
            if (array.length < 3) {
                continue;
            }

            String date = array[1];
            String time = array[2];
            if (time.length() == 7) {
                time = "0" + time;
            }

            String maxTime = map.get(date + KEY_MAX);
            if (maxTime == null || maxTime.compareTo(time) < 0) {
                map.put(date + KEY_MAX, time);
            }

            String minTime = map.get(date + KEY_MIN);
            if (minTime == null || minTime.compareTo(time) > 0) {
                map.put(date + KEY_MIN, time);
            }
        }

        System.out.println(map);
    }
}
