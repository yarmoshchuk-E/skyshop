package org.skypro.skyshop.model.search;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.UUID;

public interface Searchable {
    @JsonIgnore
    String getName();

    @JsonIgnore
    String getSearchTerm();

    @JsonIgnore
    String getTypeOfContent();

    @JsonIgnore
    default String getStringRepresentation() {
        return getSearchTerm() + getTypeOfContent();
    }

    UUID getId();
}