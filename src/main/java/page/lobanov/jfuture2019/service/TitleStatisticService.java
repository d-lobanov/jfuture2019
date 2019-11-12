package page.lobanov.jfuture2019.service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import page.lobanov.jfuture2019.dto.TitleStatistic;
import page.lobanov.jfuture2019.model.TitleAkas;
import page.lobanov.jfuture2019.model.TitleBasic;
import page.lobanov.jfuture2019.repository.TitleAkasRepository;
import page.lobanov.jfuture2019.repository.TitleBasicRepository;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toUnmodifiableList;

/**
 * @author Dmitry Lobanov
 * @since 12.11.2019
 */
@AllArgsConstructor
public class TitleStatisticService {

    /**
     * Get statistic only for those regions.
     */
    private static final Set<String> REGIONS = Set.of("US", "CN");

    /**
     * Get statistic only for those genres.
     */
    private static final Set<String> GENRES = Set.of("Action", "Documentary", "Drama", "Fantasy", "Horror");

    private static final int MIN_YEAR = 2017;
    private static final int MAX_YEAR = 2020;

    private TitleAkasRepository titleAkasRepository;

    private TitleBasicRepository titleBasicRepository;

    public List<TitleStatistic> getStatistics() {
        Map<String, TitleBasic> titleBasicMap = titleBasicRepository.getMoviesTitleBasicMapByStartYear(MIN_YEAR, MAX_YEAR);
        List<TitleAkas> titleAkasList = titleAkasRepository.getAllByRegionsAndTitleIds(REGIONS, titleBasicMap.keySet());

        Map<StatisticKey, Long> statistics = titleAkasList.stream()
            .flatMap(ta -> generateKey(ta, titleBasicMap))
            .collect(groupingBy(k -> k, counting()));

        return statistics.entrySet().stream()
            .map(e -> new TitleStatistic(e.getKey().year, e.getKey().region, e.getKey().genre, e.getValue()))
            .sorted(comparing(TitleStatistic::getRegion).thenComparing(TitleStatistic::getGenre).thenComparingInt(TitleStatistic::getYear))
            .collect(toUnmodifiableList());
    }

    /**
     * Generate statistic key to be able to easily count how many records there are for each year, genre and region.
     */
    private Stream<StatisticKey> generateKey(TitleAkas titleAkas, Map<String, TitleBasic> titleBasicMap) {
        TitleBasic titleBasic = titleBasicMap.get(titleAkas.getTitleId());

        return titleBasic.getGenres()
            .stream()
            .filter(GENRES::contains)
            .map(genre -> new StatisticKey(titleBasic.getStartYear(), titleAkas.getRegion(), genre));
    }

    @Value
    @Builder
    private static class StatisticKey {

        private Integer year;

        private String region;

        private String genre;
    }


}
