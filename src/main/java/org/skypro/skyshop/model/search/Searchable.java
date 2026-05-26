package org.skypro.skyshop.model.search;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.UUID;

public interface Searchable {

    String getName();


    String getSearchTerm();


    String getTypeOfContent();

    @JsonIgnore
    default String getStringRepresentation() {
        return getSearchTerm() + getTypeOfContent();
    }

    UUID getId();
}