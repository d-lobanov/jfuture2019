package page.lobanov.jfuture2019.model;

import lombok.Builder;
import lombok.Value;

/**
 * @author Dmitry Lobanov
 * @since 12.11.2019
 */
@Value
@Builder
public class NameBasics {

    /**
     * Alphanumeric unique identifier of the name/person.
     */
    private String nameId;

    /**
     * Name by which the person is most often credited.
     */
    private String primaryName;

}
