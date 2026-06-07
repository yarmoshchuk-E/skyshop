package org.skypro.skyshop.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.exception.NoSuchProductException;
import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.SearchResult;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

public class BasketServiceTest {

    @Mock
    private ProductBasket productBasket;
    @Mock
    private StorageService storageService;
    @Mock
    private UserBasket userBasket;

    @InjectMocks
    private BasketService basketService;

    @Test
    public void givenNotExistingProduct_whenAddToBasket_ThenThrowException() {
        UUID invalidId = UUID.randomUUID();
        when(storageService.getProductById(invalidId)).thenReturn(Optional.empty());
        assertThrows(NoSuchProductException.class, () -> basketService.addProduct(invalidId));
    }

    @Test
    public void givenProduct_whenAddToBasket_ThenAddedProductInBasket() {
        UUID validId = UUID.randomUUID();
        Product product= new SimpleProduct(validId, "молоко", 100);
        when(storageService.getProductById(validId)).thenReturn( Optional.of(product));

        basketService.addProduct(validId);
        verify(productBasket, times(1)).addBasket(validId);
    }

    @Test
    public void givenEmptyUserBasket_whenAdd_thenProductBasketIsEmpty() {

        when(productBasket.getProductsInBasket()).thenReturn(Collections.emptyMap());
        basketService.getUserBasket();
        assertThat(userBasket.getItems()).isEmpty();
        assertThat(userBasket.getTotal()).isZero();
    }
    @Test
    public void givenValidBasket_whenAdd_thenProductIsInBasket() {
        UUID validId = UUID.randomUUID();
        Product product= new SimpleProduct(validId, "молоко", 100);

        when(storageService.getProductById(validId)).thenReturn( Optional.of(product));
        productBasket.addBasket(validId);
        basketService.addProduct(validId);
        Map<UUID, Integer> productMap = new HashMap<>();
        productMap.put(validId, 2);

        when(productBasket.getProductsInBasket()).thenReturn(productMap);

        basketService.getUserBasket();



    }


}
