package page.lobanov.jfuture2019.dto;

import lombok.Value;

/**
 * @author Dmitry Lobanov
 * @since 12.11.2019
 */
@Value
public class TitleStatistic {

    private Integer year;

    private String region;

    private String genre;

    private Long count;

}
