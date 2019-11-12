package page.lobanov.jfuture2019.mapper;

import java.util.List;

import page.lobanov.jfuture2019.model.TitleBasic;

/**
 * @author Dmitry Lobanov
 * @since 12.11.2019
 */
public class TitleBasicCsvMapper {

    private static final int INDEX_OF_ID = 0;
    private static final int INDEX_OF_TITLE_TYPE = 1;
    private static final int INDEX_OF_START_YEAR = 5;
    private static final int INDEX_OF_GENRES = 8;

    private static final String GENRES_SEPARATOR = ",";

    public static TitleBasic toTitleBasic(String[] line) {
        String year = line[INDEX_OF_START_YEAR];

        return TitleBasic.builder()
            .titleId(line[INDEX_OF_ID])
            .titleType(line[INDEX_OF_TITLE_TYPE])
            .startYear(year.equals("\\N") ? -1 : Integer.valueOf(year))
            .genres(List.of(line[INDEX_OF_GENRES].split(GENRES_SEPARATOR)))
            .build();
    }

}
