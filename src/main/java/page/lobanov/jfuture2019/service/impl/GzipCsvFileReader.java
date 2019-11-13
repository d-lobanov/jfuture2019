package page.lobanov.jfuture2019.service.impl;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;
import java.util.zip.GZIPInputStream;

import lombok.AllArgsConstructor;
import page.lobanov.jfuture2019.service.CsvReader;

/**
 * @author Dmitry Lobanov
 * @since 12.11.2019
 */
@AllArgsConstructor
public class GzipCsvFileReader implements CsvReader {

    private String fileName;

    public Stream<String[]> lines(boolean skipHead) throws IOException {
        InputStream fileStream = new FileInputStream(fileName);
        InputStream gzipStream = new GZIPInputStream(fileStream);
        Reader decoder = new InputStreamReader(gzipStream, StandardCharsets.UTF_8);

        return new BufferedReader(decoder).lines()
            .skip(skipHead ? 1 : 0)
            .map(line -> line.split(VALUES_SEPARATOR));
    }

}
