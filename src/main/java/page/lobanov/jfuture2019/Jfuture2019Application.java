package page.lobanov.jfuture2019;

import java.util.List;

import page.lobanov.jfuture2019.dto.DirectorRating;
import page.lobanov.jfuture2019.dto.TitleStatistic;

import static page.lobanov.jfuture2019.mapper.DirectorRatingCsvMapper.fromDirectorRatingList;
import static page.lobanov.jfuture2019.mapper.TitleStatisticCsvMapper.fromTitleStatisticList;
import static page.lobanov.jfuture2019.util.ConsoleWriter.printTable;

/**
 * @author Dmitry Lobanov
 * @since 12.11.2019
 */
public class Jfuture2019Application {

    public static void main(String[] args) {
        boolean isSourceLocal = args.length > 0 && args[0] != null && args[0].equals("--local");

        ServiceLocator serviceLocator = new ServiceLocator(isSourceLocal);

        System.out.println("Task #1: display dynamics of the release of movies of 5 any genres of China and US");

        List<TitleStatistic> statistics = serviceLocator.titleStatisticService().getStatistics();
        printTable(List.of("REGION", "GENRE", "YEAR", "NUMBER OF MOVIES"), fromTitleStatisticList(statistics));

        System.out.println();
        System.out.println("Task #2: top 5 directors");

        List<DirectorRating> ratings = serviceLocator.topDirectorsService().getTop();
        printTable(List.of("NAME", "AVERAGE RATING", "NUMBER OF MOVIES"), fromDirectorRatingList(ratings));
    }
}
