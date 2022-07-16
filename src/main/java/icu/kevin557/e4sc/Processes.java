package icu.kevin557.e4sc;

import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

public class Processes {

    private static String[] getStrings(File csv, int line) throws Exception {
        return Utils.csv2Strings(csv).get(line);
    }

    private static int findInLine(File csv, int line, String item) throws Exception {
        String[] items = getStrings(csv, line);
        for (int i = 0; i < items.length; i++) {
            if (("\"" + item + "\"").equals(items[i])) {
                return i;
            }
        }
        return -1;
    }

    public static int find(File csv, String header) throws Exception {
        return findInLine(csv, 0, header);
    }

    public static String get(File csv, String line, String header) throws Exception {
        try {
            int l = Integer.parseInt(line);
            int pos = findInLine(csv, 0, header);
            if (pos != -1) {
                String[] values = getStrings(csv, l);
                return values[pos];
            }
        } catch (NumberFormatException e) {
            Helper.notNum(line);
        }
        return null;
    }

    public static boolean set(File csv, String line, String header, String value) throws Exception {
        Utils.fileBak(csv);
        try {
            int l = Integer.parseInt(line);
            int pos = findInLine(csv, 0, header);
            if (pos != -1) {
                String[] values = getStrings(csv, l);
                if (get(csv, "1", header).equalsIgnoreCase("\"int\"")) {
                    int v = Integer.parseInt(value);
                    values[pos] = String.valueOf(v);
                } else values[pos] = "\"" + value + "\"";
                List<String[]> strings = Utils.csv2Strings(csv);
                strings.set(l, values);
                CSVWriter writer = new CSVWriter(new FileWriter(csv), ',', '\0', '\0', "\n");
                writer.writeAll(strings);
                writer.close();
                return true;
            }
        } catch (NumberFormatException e) {
            Helper.notNum(line);
        }
        return false;
    }

    public static void setAll(File csv, String header, String value) throws Exception {
        Utils.fileBak(csv);
        List<String[]> strings = Utils.csv2Strings(csv);
        int pos = findInLine(csv, 0, header);
        if (get(csv, "1", header).equalsIgnoreCase("\"int\"")) {
            for (int i = 2; i < strings.size(); i++) {
                int v = Integer.parseInt(value);
                strings.get(i)[pos] = String.valueOf(v);
            }
        } else for (int i = 2; i < strings.size(); i++) {
            strings.get(i)[pos] = "\"" + value + "\"";
        }
        CSVWriter writer = new CSVWriter(new FileWriter(csv), ',', '\0', '\0', "\n");
        writer.writeAll(strings);
        writer.close();
    }
}
