package page.lobanov.jfuture2019.mapper;

import java.util.List;

import page.lobanov.jfuture2019.model.TitleCrew;

/**
 * @author Dmitry Lobanov
 * @since 12.11.2019
 */
public class TitleCrewCsvMapper {

    private static final int INDEX_OF_ID = 0;
    private static final int INDEX_OF_DIRECTORS = 1;

    public static TitleCrew toTitleCrew(String[] line) {
        return TitleCrew.builder()
            .titleId(line[INDEX_OF_ID])
            .directors(List.of(line[INDEX_OF_DIRECTORS].split(",")))
            .build();
    }
}
