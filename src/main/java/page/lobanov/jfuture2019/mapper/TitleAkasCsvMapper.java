package page.lobanov.jfuture2019.mapper;

import page.lobanov.jfuture2019.model.TitleAkas;

/**
 * @author Dmitry Lobanov
 * @since 12.11.2019
 */
public class TitleAkasCsvMapper {

    private static final int INDEX_OF_ID = 0;
    private static final int INDEX_OF_REGION = 3;

    public static TitleAkas toTitleAkas(String[] line) {
        return TitleAkas.builder()
            .titleId(line[INDEX_OF_ID])
            .region(line[INDEX_OF_REGION])
            .build();
    }

}
