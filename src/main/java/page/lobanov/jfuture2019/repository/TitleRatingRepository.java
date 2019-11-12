package page.lobanov.jfuture2019.repository;

import java.util.Map;
import java.util.Set;

import page.lobanov.jfuture2019.model.TitleRating;

/**
 * @author Dmitry Lobanov
 * @since 12.11.2019
 */
public interface TitleRatingRepository {

    Map<String, TitleRating> getTitleIdToTitleRatingMap(Set<String> titleIds, int minNumberOfVoters);

}
