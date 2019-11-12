package page.lobanov.jfuture2019.service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import page.lobanov.jfuture2019.dto.DirectorRating;
import page.lobanov.jfuture2019.model.TitleRating;
import page.lobanov.jfuture2019.repository.NameBasicsRepository;
import page.lobanov.jfuture2019.repository.TitleBasicRepository;
import page.lobanov.jfuture2019.repository.TitleCrewRepository;
import page.lobanov.jfuture2019.repository.TitleRatingRepository;

import static java.util.Comparator.comparingDouble;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

/**
 * @author Dmitry Lobanov
 * @since 12.11.2019
 */
@AllArgsConstructor
public class TopDirectorsService {

    /**
     * Min number of votes. Needs to get rid of movies that have a small amount of voters but high score.
     */
    private static final int MIN_NUM_OF_VOTERS = 1000;

    /**
     * Min number of movies per director.
     */
    private static final int MIN_NUM_OF_MOVIES = 5;

    /**
     * Number of records in top.
     */
    private static final int TOP_RECORDS_LIMIT = 5;

    private TitleBasicRepository titleBasicRepository;

    private TitleRatingRepository titleRatingRepository;

    private TitleCrewRepository titleCrewRepository;

    private NameBasicsRepository nameBasicsRepository;

    public List<DirectorRating> getTop() {
        Set<String> moviesTitleIds = titleBasicRepository.getMoviesTitleIds();

        Map<String, TitleRating> titleIdToTitleRatingMap = titleRatingRepository.getTitleIdToTitleRatingMap(moviesTitleIds, MIN_NUM_OF_VOTERS);

        List<DirectorRating> directorRatings = calcDirectorsRating(
            titleCrewRepository.getDirectorToTitleIdsMap(titleIdToTitleRatingMap.keySet()),
            titleIdToTitleRatingMap
        );

        Map<String, String> nameIdToPrimaryNameMap = nameBasicsRepository.getNameIdsToPrimaryNameMap(extractNameIds(directorRatings));

        return directorRatings.stream()
            .peek(r -> r.setName(nameIdToPrimaryNameMap.get(r.getNameId())))
            .collect(toList());
    }

    /**
     * Calculate rating for each director based on its titles map and common rating map.
     */
    private List<DirectorRating> calcDirectorsRating(Map<String, Set<String>> directorIdToTitleIdsMap, Map<String, TitleRating> titleIdToTitleRatingMap) {
        return directorIdToTitleIdsMap.entrySet().stream()
            .map(entry -> buildDirectorRating(entry.getKey(), entry.getValue(), titleIdToTitleRatingMap))
            .filter(r -> r.getNumberOfMovies() > MIN_NUM_OF_MOVIES)
            .sorted(comparingDouble(DirectorRating::getAverageRating).reversed())
            .limit(TOP_RECORDS_LIMIT)
            .collect(Collectors.toList());
    }

    /**
     * Build rating for specific director based on rating map.
     */
    private DirectorRating buildDirectorRating(String directorNameId, Set<String> directorTitleIds, Map<String, TitleRating> titleIdToTitleRatingMap) {
        Set<String> moviesIds = titleIdToTitleRatingMap.keySet();

        Long numberOfMovies = directorTitleIds.stream()
            .filter(moviesIds::contains)
            .count();

        return DirectorRating.builder()
            .nameId(directorNameId)
            .averageRating(calcAverageRating(titleIdToTitleRatingMap, directorTitleIds))
            .numberOfMovies(numberOfMovies)
            .build();
    }

    /**
     * Calculate average rating of director's titles based on rating map.
     */
    private Double calcAverageRating(Map<String, TitleRating> titleIdToTitleRatingMap, Set<String> directorTitleIds) {
        return directorTitleIds.stream()
            .filter(titleIdToTitleRatingMap::containsKey)
            .map(titleIdToTitleRatingMap::get)
            .mapToDouble(TitleRating::getAverageRating)
            .average()
            .orElse(0.0);
    }

    /**
     * Extract name ids from directors' rating.
     */
    private Set<String> extractNameIds(List<DirectorRating> directorRatings) {
        return directorRatings.stream()
            .map(DirectorRating::getNameId)
            .collect(toSet());
    }


}
