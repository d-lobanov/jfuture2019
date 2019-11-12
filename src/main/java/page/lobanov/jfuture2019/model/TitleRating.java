package page.lobanov.jfuture2019.model;

import lombok.Builder;
import lombok.Value;

/**
 * @author Dmitry Lobanov
 * @since 12.11.2019
 */
@Value
@Builder
public class TitleRating {

    /**
     * Alphanumeric unique identifier of the title.
     */
    private String titleId;

    /**
     * Weighted average of all the individual user ratings.
     */
    private Float averageRating;

    /**
     * Number of votes the title has received.
     */
    private Integer numVotes;

}
