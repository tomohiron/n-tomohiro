package sandbox.csv;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import jp.sf.orangesignal.csv.Csv;
import jp.sf.orangesignal.csv.CsvConfig;
import jp.sf.orangesignal.csv.handlers.ColumnNameMapListHandler;

public class CsvSample03 {

    private static final String KEY_MAX = "_max";
    private static final String KEY_MIN = "_min";

    public static void main(String[] args) throws Exception {
        new CsvSample03().execute();
    }

    public void execute() throws Exception {

        // String targetMonth = "2010/04";
        Map<String, String> result = new TreeMap<String, String>();

        List<Map<String, String>> list = Csv.load(new File("event.csv"), "SJIS", new CsvConfig(),
                new ColumnNameMapListHandler());
        for (Map<String, String> row : list) {

            String date = row.get("日付");
            if (date == null) {
                continue;
            }
            String time = row.get("時刻");
            if (time.length() == 7) {
                time = "0" + time;
            }

            String maxTime = result.get(date + KEY_MAX);
            if (maxTime == null || maxTime.compareTo(time) < 0) {
                result.put(date + KEY_MAX, time);
            }

            String minTime = result.get(date + KEY_MIN);
            if (minTime == null || minTime.compareTo(time) > 0) {
                result.put(date + KEY_MIN, time);
            }
        }

        System.out.println(result);
    }
}
