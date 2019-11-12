package page.lobanov.jfuture2019.repository;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * @author Dmitry Lobanov
 * @since 12.11.2019
 */
public interface TitleCrewRepository {

    Map<String, Set<String>> getDirectorToTitleIdsMap(Collection<String> nconst);

}
