package page.lobanov.jfuture2019.model;

import java.util.List;

import lombok.Builder;
import lombok.Value;

/**
 * @author Dmitry Lobanov
 * @since 12.11.2019
 */
@Value
@Builder
public class TitleBasic {

    /**
     * Alphanumeric unique identifier of the title.
     */
    private String titleId;

    /**
     * Represents the release year of a title. In the case of TV Series, it is the series start year.
     */
    private Integer startYear;

    /**
     * The type/format of the title (e.g. movie, short, tvseries, tvepisode, video, etc.
     */
    private String titleType;

    /**
     * Includes up to three genres associated with the title.
     */
    private List<String> genres;

    public boolean isMovie() {
        return titleType.equals("movie");
    }

}
