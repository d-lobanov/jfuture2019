package page.lobanov.jfuture2019.mapper;

import page.lobanov.jfuture2019.model.TitleRating;

/**
 * @author Dmitry Lobanov
 * @since 12.11.2019
 */
public class TitleRatingCsvMapper {

    private static final int INDEX_OF_ID = 0;
    private static final int INDEX_OF_AVERAGE_RATING = 1;
    private static final int INDEX_OF_NUM_VOTES = 2;

    public static TitleRating toTitleRating(String[] line) {
        return TitleRating.builder()
            .titleId(line[INDEX_OF_ID])
            .averageRating(Float.valueOf(line[INDEX_OF_AVERAGE_RATING]))
            .numVotes(Integer.valueOf(line[INDEX_OF_NUM_VOTES]))
            .build();
    }

}
