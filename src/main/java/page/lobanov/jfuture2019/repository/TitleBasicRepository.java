package page.lobanov.jfuture2019.repository;

import java.util.Map;
import java.util.Set;

import page.lobanov.jfuture2019.model.TitleBasic;

/**
 * @author Dmitry Lobanov
 * @since 12.11.2019
 */
public interface TitleBasicRepository {

    Set<String> getMoviesTitleIds();

    Map<String, TitleBasic> getMoviesTitleBasicMapByStartYear(int minYear, int maxYear);

}
