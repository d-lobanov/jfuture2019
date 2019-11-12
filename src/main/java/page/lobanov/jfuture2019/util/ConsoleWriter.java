package page.lobanov.jfuture2019.util;

import java.util.List;

import de.vandermeer.asciitable.AsciiTable;
import lombok.experimental.UtilityClass;

/**
 * @author Dmitry Lobanov
 * @since 13.11.2019
 */
@UtilityClass
public class ConsoleWriter {

    public static void printTable(List<String> header, List<List<String>> body) {
        AsciiTable at = new AsciiTable();

        at.addRule();

        at.addRow(header);
        at.addRule();

        body.forEach(line -> {
            at.addRow(line);
            at.addRule();
        });

        System.out.println(at.render());
    }

}
