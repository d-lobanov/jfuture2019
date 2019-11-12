package page.lobanov.jfuture2019.service;

import java.io.IOException;
import java.util.stream.Stream;

/**
 * @author Dmitry Lobanov
 * @since 13.11.2019
 */
public interface GzipCsvReader {

    String VALUES_SEPARATOR = "\t";

    /**
     * Creates stream of lines that CSV file contains.
     */
    Stream<String[]> lines(boolean skipHead) throws IOException;

}
