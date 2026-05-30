package org.skypro.skyshop.service;

import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class SearchService {

    private final StorageService storageService;

    public SearchService(StorageService storageService) {
        this.storageService = storageService;
    }

    public List<SearchResult> search(String pattern) {
        Collection<Searchable> allSearchables = storageService.getAllSearchables();

        return allSearchables.stream()
                .filter(searchable -> searchable.getSearchTerm().contains(pattern))
                .map(SearchResult::fromSearchable)
                .toList();
    }
}