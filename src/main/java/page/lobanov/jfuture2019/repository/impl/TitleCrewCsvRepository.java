package page.lobanov.jfuture2019.repository.impl;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import lombok.AllArgsConstructor;
import page.lobanov.jfuture2019.mapper.TitleCrewCsvMapper;
import page.lobanov.jfuture2019.model.TitleCrew;
import page.lobanov.jfuture2019.repository.TitleCrewRepository;
import page.lobanov.jfuture2019.service.GzipCsvReader;

import static java.util.stream.Collectors.toList;

/**
 * @author Dmitry Lobanov
 * @since 12.11.2019
 */
@AllArgsConstructor
public class TitleCrewCsvRepository implements TitleCrewRepository {

    private GzipCsvReader csvReader;

    @Override
    public Map<String, Set<String>> getDirectorToTitleIdsMap(Collection<String> titleIds) {
        Map<String, Set<String>> directorToTitleIds = new HashMap<>();

        getAll(titleIds).forEach(crew -> crew.getDirectors().forEach(director -> {
            directorToTitleIds.computeIfAbsent(director, v -> new HashSet<>()).add(crew.getTitleId());
        }));

        return directorToTitleIds;
    }

    private List<TitleCrew> getAll(Collection<String> titleIds) {
        try (
            Stream<String[]> lines = csvReader.lines(true)
        ) {
            return lines
                .map(TitleCrewCsvMapper::toTitleCrew)
                .filter(crew -> titleIds.contains(crew.getTitleId()))
                .collect(toList());
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
