package page.lobanov.jfuture2019.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @author Dmitry Lobanov
 * @since 12.11.2019
 */
@Data
@Builder
public class DirectorRating {

    private static final String UNKNOWN_NAME = "unknown";

    private String nameId;

    private String name;

    private Double averageRating;

    private Long numberOfMovies;

    public String getName() {
        return name == null ? UNKNOWN_NAME : name;
    }

}
