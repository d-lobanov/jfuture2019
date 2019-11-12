package page.lobanov.jfuture2019.repository;

import java.util.Map;
import java.util.Set;

/**
 * @author Dmitry Lobanov
 * @since 12.11.2019
 */
public interface NameBasicsRepository {

    Map<String, String> getNameIdsToPrimaryNameMap(Set<String> titleIds);

}
