package page.lobanov.jfuture2019.mapper;

import java.util.List;
import java.util.stream.Collectors;

import page.lobanov.jfuture2019.dto.TitleStatistic;

/**
 * @author Dmitry Lobanov
 * @since 12.11.2019
 */
public class TitleStatisticCsvMapper {

    public static List<List<String>> fromTitleStatisticList(List<TitleStatistic> statistics) {
        return statistics.stream()
            .map(TitleStatisticCsvMapper::fromTitleStatistic)
            .collect(Collectors.toUnmodifiableList());
    }

    private static List<String> fromTitleStatistic(TitleStatistic statistic) {
        return List.of(
            statistic.getRegion(),
            statistic.getGenre(),
            String.valueOf(statistic.getYear()),
            String.valueOf(statistic.getCount())
        );
    }

}
