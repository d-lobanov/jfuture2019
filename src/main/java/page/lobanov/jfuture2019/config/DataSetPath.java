package page.lobanov.jfuture2019.config;

import lombok.AllArgsConstructor;

/**
 * @author Dmitry Lobanov
 * @since 13.11.2019
 */
@AllArgsConstructor
public enum DataSetPath {

    NAME_BASICS("name.basics.tsv.gz"),
    TITLE_AKAS("title.akas.tsv.gz"),
    TITLE_BASICS("title.basics.tsv.gz"),
    TITLE_CREW("title.crew.tsv.gz"),
    TITLE_RATINGS("title.ratings.tsv.gz");

    private String fileName;

    public String getLocalPath() {
        return "datasets/" + fileName;
    }

    public String getUrl() {
        return "https://datasets.imdbws.com/" + fileName;
    }

}
