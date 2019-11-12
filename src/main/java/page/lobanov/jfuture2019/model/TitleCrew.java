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
public class TitleCrew {

    /**
     * Alphanumeric unique identifier of the title.
     */
    private String titleId;

    /**
     * Director(s) of the given title.
     */
    private List<String> directors;

}
