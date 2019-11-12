package page.lobanov.jfuture2019.repository;

import java.util.List;
import java.util.Set;

import page.lobanov.jfuture2019.model.TitleAkas;

/**
 * @author Dmitry Lobanov
 * @since 12.11.2019
 */
public interface TitleAkasRepository {

    List<TitleAkas> getAllByRegionsAndTitleIds(Set<String> regions, Set<String> titleIds);

}
