package page.lobanov.jfuture2019.mapper;

import page.lobanov.jfuture2019.model.NameBasics;

/**
 * @author Dmitry Lobanov
 * @since 12.11.2019
 */
public class NameBasicsCsvMapper {

    private static final int INDEX_OF_ID = 0;
    private static final int INDEX_OF_PRIMARY_NAME = 1;

    public static NameBasics toNameBasics(String[] line) {
        return NameBasics.builder()
            .nameId(line[INDEX_OF_ID])
            .primaryName(line[INDEX_OF_PRIMARY_NAME])
            .build();
    }

}
