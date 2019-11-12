package page.lobanov.jfuture2019.model;

import lombok.Builder;
import lombok.Value;

/**
 * @author Dmitry Lobanov
 * @since 12.11.2019
 */
@Value
@Builder
public class TitleAkas {

    /**
     * An alphanumeric unique identifier of the title.
     */
    private String titleId;

    /**
     * The region for this version of the title.
     */
    private String region;

}
