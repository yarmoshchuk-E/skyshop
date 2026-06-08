package org.skypro.skyshop.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.model.product.FixPriceProduct;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.model.search.Searchable;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SearchServiceTest {

    @Mock
    private StorageService storageService;

    @InjectMocks
    private SearchService searchService;

    @Test
    public void givenEmptyList_whenSearch_thenReturnEmptyStorage() {

        when(storageService.getAllSearchables()).thenReturn(Collections.emptyList());
        List<SearchResult> results = searchService.search("хлеб");
        assertThat(results.isEmpty());
    }

    @Test
    public void givenEmptyList_whenSearch_thenReturnInappropriateStorage() {

        List<Searchable> inappropriateStorage = List.of(
                new SimpleProduct(UUID.randomUUID(), "молоко", 100),
                new SimpleProduct(UUID.randomUUID(), "хлеб", 40),
                new FixPriceProduct(UUID.randomUUID(), "зефир")
        );

        when(storageService.getAllSearchables()).thenReturn(inappropriateStorage);
        List<SearchResult> results = searchService.search("конфеты");
        assertThat(results.isEmpty());
    }

    @Test
    public void givenResult_whenSearch_thenReturnNotEmptyStorage() {

        List<Searchable> NotEmptyStorage = List.of(
                new SimpleProduct(UUID.randomUUID(), "TestProduct", 100),
                new SimpleProduct(UUID.randomUUID(), "хлеб", 40),
                new FixPriceProduct(UUID.randomUUID(), "зефир")
        );

        when(storageService.getAllSearchables()).thenReturn(NotEmptyStorage);
        List<SearchResult> results = searchService.search("Test");
        assertThat(results)
                .isNotEmpty()
                .hasSize(1)
                .extracting(SearchResult::getName)
                .contains("TestProduct");
    }
}
