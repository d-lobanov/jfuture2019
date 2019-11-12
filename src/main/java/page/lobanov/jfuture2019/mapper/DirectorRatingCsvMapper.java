package page.lobanov.jfuture2019.mapper;

import java.util.List;
import java.util.stream.Collectors;

import page.lobanov.jfuture2019.dto.DirectorRating;

/**
 * @author Dmitry Lobanov
 * @since 12.11.2019
 */
public class DirectorRatingCsvMapper {

    public static List<List<String>> fromDirectorRatingList(List<DirectorRating> ratings) {
        return ratings.stream()
            .map(DirectorRatingCsvMapper::fromDirectorRating)
            .collect(Collectors.toUnmodifiableList());
    }

    private static List<String> fromDirectorRating(DirectorRating rating) {
        return List.of(
            rating.getName(),
            String.format("%1.2f", rating.getAverageRating()),
            String.valueOf(rating.getNumberOfMovies())
        );
    }
}
