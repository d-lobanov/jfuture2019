package page.lobanov.jfuture2019.repository.impl;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.AllArgsConstructor;
import page.lobanov.jfuture2019.mapper.TitleAkasCsvMapper;
import page.lobanov.jfuture2019.model.TitleAkas;
import page.lobanov.jfuture2019.repository.TitleAkasRepository;
import page.lobanov.jfuture2019.service.GzipCsvReader;

/**
 * @author Dmitry Lobanov
 * @since 12.11.2019
 */
@AllArgsConstructor
public class TitleAkasCsvRepository implements TitleAkasRepository {

    private GzipCsvReader csvReader;

    @Override
    public List<TitleAkas> getAllByRegionsAndTitleIds(Set<String> regions, Set<String> titleIds) {
        try (
            Stream<String[]> lines = csvReader.lines(true)
        ) {
            return lines.map(TitleAkasCsvMapper::toTitleAkas)
                .filter(ta -> regions.contains(ta.getRegion()))
                .filter(ta -> titleIds.contains(ta.getTitleId()))
                .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
