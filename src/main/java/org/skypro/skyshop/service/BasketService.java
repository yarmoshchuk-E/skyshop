package org.skypro.skyshop.service;

import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service

public class BasketService {
    private final ProductBasket basket;
    private final StorageService storageService;

    @Autowired
    public BasketService(ProductBasket basket, StorageService storageService) {
        this.basket = basket;
        this.storageService = storageService;
    }

    public void addProduct(UUID id) {
        Optional<Product> productOptional = storageService.getProductById(id);
        if (!productOptional.isPresent()) {
            throw new IllegalArgumentException("Продукт с ID " + id + " не найден в хранилище");
        }
        basket.addBasket(id);
    }

    public UserBasket getUserBasket() {
        List<BasketItem> items = basket.getProductsInBasket()
                .entrySet()
                .stream()
                .map(p -> new BasketItem(storageService.getProductById(p.getKey()).orElseThrow(), p.getValue()))
                .toList();
        return new UserBasket(items);
    }
}