package page.lobanov.jfuture2019.repository.impl;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.AllArgsConstructor;
import page.lobanov.jfuture2019.mapper.TitleBasicCsvMapper;
import page.lobanov.jfuture2019.model.TitleBasic;
import page.lobanov.jfuture2019.repository.TitleBasicRepository;
import page.lobanov.jfuture2019.service.GzipCsvReader;

import static java.util.stream.Collectors.toMap;
import static java.util.stream.Collectors.toSet;

/**
 * @author Dmitry Lobanov
 * @since 12.11.2019
 */
@AllArgsConstructor
public class TitleBasicCsvRepository implements TitleBasicRepository {

    private GzipCsvReader csvReader;

    @Override
    public Set<String> getMoviesTitleIds() {
        return getMovies().stream()
            .map(TitleBasic::getTitleId)
            .collect(toSet());
    }

    @Override
    public Map<String, TitleBasic> getMoviesTitleBasicMapByStartYear(int minYear, int maxYear) {
        return getMovies().stream()
            .filter(tb -> minYear <= tb.getStartYear() && tb.getStartYear() < maxYear)
            .collect(toMap(TitleBasic::getTitleId, tb -> tb));
    }

    private List<TitleBasic> getMovies() {
        try (
            Stream<String[]> lines = csvReader.lines(true)
        ) {
            return lines
                .map(TitleBasicCsvMapper::toTitleBasic)
                .filter(TitleBasic::isMovie)
                .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
