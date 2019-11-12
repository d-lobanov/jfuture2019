package page.lobanov.jfuture2019.repository.impl;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import lombok.AllArgsConstructor;
import page.lobanov.jfuture2019.mapper.TitleRatingCsvMapper;
import page.lobanov.jfuture2019.model.TitleRating;
import page.lobanov.jfuture2019.repository.TitleRatingRepository;
import page.lobanov.jfuture2019.service.GzipCsvReader;

import static java.util.stream.Collectors.toMap;

/**
 * @author Dmitry Lobanov
 * @since 12.11.2019
 */
@AllArgsConstructor
public class TitleRatingCsvRepository implements TitleRatingRepository {

    private GzipCsvReader csvReader;

    @Override
    public Map<String, TitleRating> getTitleIdToTitleRatingMap(Set<String> titleIds, int minNumberOfVoters) {
        try (
            Stream<String[]> lines = csvReader.lines(true)
        ) {
            return lines
                .map(TitleRatingCsvMapper::toTitleRating)
                .filter(r -> titleIds.contains(r.getTitleId()))
                .filter(r -> r.getNumVotes() > minNumberOfVoters)
                .collect(toMap(TitleRating::getTitleId, raring -> raring));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
