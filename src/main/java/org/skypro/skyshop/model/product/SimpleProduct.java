package org.skypro.skyshop.model.product;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.UUID;

public class SimpleProduct extends Product {
    private final int productPrice;
    private final UUID id;

    public SimpleProduct(UUID id, String productName, int productPrice) {
        super(id, productName);
        if (productPrice <= 0) {
            throw new IllegalArgumentException("неверно указана цена!");
        }
        this.productPrice = productPrice;
        this.id = id;

    }

    @Override
    public boolean isSpecial() {
        return false;
    }

    @Override
    public String getProductName() {
        return super.getProductName();
    }

    @Override
    public int getProductPrice() {
        return productPrice;
    }


    @Override
    public String toString() {
        return super.toString();
    }

    @JsonIgnore
    @Override
    public String getSearchTerm() {
        return getProductName();
    }

    @JsonIgnore
    @Override
    public String getTypeOfContent() {
        return "PRODUCT";
    }

    @Override
    public UUID getId() {
        return id;
    }
}