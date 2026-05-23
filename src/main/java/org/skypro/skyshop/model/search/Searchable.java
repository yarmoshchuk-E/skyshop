package org.skypro.skyshop.model.search;
public interface Searchable {

    String getName();

    String getSearchTerm();

    String getTypeOfContent();

    default String getStringRepresentation() {
        return getSearchTerm() + getTypeOfContent();
    }
}
