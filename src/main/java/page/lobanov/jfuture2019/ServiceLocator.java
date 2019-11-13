package page.lobanov.jfuture2019;

import lombok.AllArgsConstructor;
import page.lobanov.jfuture2019.config.DataSetPath;
import page.lobanov.jfuture2019.repository.NameBasicsRepository;
import page.lobanov.jfuture2019.repository.TitleAkasRepository;
import page.lobanov.jfuture2019.repository.TitleBasicRepository;
import page.lobanov.jfuture2019.repository.TitleCrewRepository;
import page.lobanov.jfuture2019.repository.TitleRatingRepository;
import page.lobanov.jfuture2019.repository.impl.NameBasicsCsvRepository;
import page.lobanov.jfuture2019.repository.impl.TitleAkasCsvRepository;
import page.lobanov.jfuture2019.repository.impl.TitleBasicCsvRepository;
import page.lobanov.jfuture2019.repository.impl.TitleCrewCsvRepository;
import page.lobanov.jfuture2019.repository.impl.TitleRatingCsvRepository;
import page.lobanov.jfuture2019.service.CsvReader;
import page.lobanov.jfuture2019.service.TitleStatisticService;
import page.lobanov.jfuture2019.service.TopDirectorsService;
import page.lobanov.jfuture2019.service.impl.GzipCsvFileReader;
import page.lobanov.jfuture2019.service.impl.GzipCsvWebReader;

/**
 * There is simple implementation to keep project lightweight and away of unnecessary dependencies.
 *
 * @author Dmitry Lobanov
 * @since 13.11.2019
 */
@AllArgsConstructor
class ServiceLocator {

    private boolean isSourceLocal;

    TitleStatisticService titleStatisticService() {
        return new TitleStatisticService(titleAkasRepository(), titleBasicRepository());
    }

    TopDirectorsService topDirectorsService() {
        return new TopDirectorsService(titleBasicRepository(), titleRatingRepository(), titleCrewRepository(), nameBasicsRepository());
    }

    private NameBasicsRepository nameBasicsRepository() {
        return new NameBasicsCsvRepository(gzipCsvReader(DataSetPath.NAME_BASICS));
    }

    private TitleAkasRepository titleAkasRepository() {
        return new TitleAkasCsvRepository(gzipCsvReader(DataSetPath.TITLE_AKAS));
    }

    private TitleBasicRepository titleBasicRepository() {
        return new TitleBasicCsvRepository(gzipCsvReader(DataSetPath.TITLE_BASICS));
    }

    private TitleCrewRepository titleCrewRepository() {
        return new TitleCrewCsvRepository(gzipCsvReader(DataSetPath.TITLE_CREW));
    }

    private TitleRatingRepository titleRatingRepository() {
        return new TitleRatingCsvRepository(gzipCsvReader(DataSetPath.TITLE_RATINGS));
    }

    private CsvReader gzipCsvReader(DataSetPath dataSetPath) {
        return isSourceLocal ? new GzipCsvFileReader(dataSetPath.getLocalPath()) : new GzipCsvWebReader(dataSetPath.getUrl());
    }

}
