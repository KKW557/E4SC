package icu.kevin557.e4sc;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.util.List;

public class Utils {
    public static List<String[]> csv2Strings(File file) throws IOException, CsvException {
        InputStreamReader is = new InputStreamReader(new FileInputStream(file));
        CSVParser parser = new CSVParserBuilder().withQuoteChar('\0').build();
        CSVReader reader = new CSVReaderBuilder(is).withCSVParser(parser).build();
        return reader.readAll();
    }

    public static void fileBak(File source) throws IOException {
        File bak = new File(source.getName() + ".bak");
        if (bak.exists()) {
            bak.delete();
        }
        Files.copy(source.toPath(), bak.toPath());
    }
}
