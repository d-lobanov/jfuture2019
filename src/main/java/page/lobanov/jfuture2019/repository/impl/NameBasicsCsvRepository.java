package page.lobanov.jfuture2019.repository.impl;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import lombok.AllArgsConstructor;
import page.lobanov.jfuture2019.mapper.NameBasicsCsvMapper;
import page.lobanov.jfuture2019.model.NameBasics;
import page.lobanov.jfuture2019.repository.NameBasicsRepository;
import page.lobanov.jfuture2019.service.CsvReader;

import static java.util.stream.Collectors.toMap;

/**
 * @author Dmitry Lobanov
 * @since 12.11.2019
 */
@AllArgsConstructor
public class NameBasicsCsvRepository implements NameBasicsRepository {

    private CsvReader csvReader;

    @Override
    public Map<String, String> getNameIdsToPrimaryNameMap(Set<String> titleIds) {
        try (
            Stream<String[]> lines = csvReader.lines(true)
        ) {
            return lines
                .map(NameBasicsCsvMapper::toNameBasics)
                .filter(nb -> titleIds.contains(nb.getNameId()))
                .collect(toMap(NameBasics::getNameId, NameBasics::getPrimaryName));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
