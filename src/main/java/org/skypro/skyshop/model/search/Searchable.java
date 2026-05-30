package org.skypro.skyshop.model.search;

import java.util.UUID;

public interface Searchable {

    String getName();

    String getSearchTerm();

    String getTypeOfContent();

    default String getStringRepresentation() {
        return getSearchTerm() + getTypeOfContent();
    }

    UUID getId();
}