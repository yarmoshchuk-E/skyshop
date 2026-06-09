package org.skypro.skyshop.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.exception.NoSuchProductException;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;

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
        Product product = new SimpleProduct(validId, "молоко", 100);
        when(storageService.getProductById(validId)).thenReturn(Optional.of(product));

        basketService.addProduct(validId);
        verify(productBasket, times(1)).addBasket(validId);
    }

    @Test
    public void givenEmptyUserBasket_whenAdd_thenProductBasketIsEmpty() {

        when(productBasket.getProductsInBasket()).thenReturn(Collections.emptyMap());
        UserBasket result = basketService.getUserBasket();
        assertThat(result.getItems()).isEmpty();
        assertThat(result.getTotal()).isZero();
    }

    @Test
    public void givenValidBasket_whenAdd_thenProductIsInBasket() {
        UUID validId = UUID.randomUUID();
        SimpleProduct product = new SimpleProduct(validId, "молоко", 100);

        when(storageService.getProductById(product.getId())).thenReturn(Optional.of(product));

        Map<UUID, Integer> productMap = new HashMap<>();
        productMap.put(product.getId(), 2);

        when(productBasket.getProductsInBasket()).thenReturn(productMap);
        UserBasket userBasket = basketService.getUserBasket();

        assertEquals(product.getProductName(), userBasket.getItems().stream().iterator().next().getProduct().getProductName());
        assertEquals(200, userBasket.getTotal());
        assertEquals(1, userBasket.getItems().size());
    }
}
