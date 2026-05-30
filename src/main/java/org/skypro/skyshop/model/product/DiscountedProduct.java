package org.skypro.skyshop.model.product;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.UUID;

public class DiscountedProduct extends Product {
    private final int basePrice;
    private final int percentageDiscount;
    private final UUID id;

    public DiscountedProduct(UUID id, String productName, int basePrice, int percentageDiscount) {
        super(id, productName);
        this.id = id;
        if (basePrice <= 0) {
            throw new IllegalArgumentException("неверно указана базовая цена!");
        }
        this.basePrice = basePrice;
        if (percentageDiscount < 0 || percentageDiscount > 100) {
            throw new IllegalArgumentException("неверно указан размер скидки!");
        }
        this.percentageDiscount = percentageDiscount;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public String getProductName() {
        return super.getProductName();
    }

    @Override
    public int getProductPrice() {
        return basePrice - (basePrice * percentageDiscount / 100);
    }

    @Override
    public String toString() {
        return "<" + getProductName() + ": " + getProductPrice() + ">" + "(" + "<" + percentageDiscount + ">" + "%)";
    }

    @Override
    public String getName() {
        return getProductName();
    }


    @Override
    public String getSearchTerm() {
        return getProductName();
    }

    @Override
    public String getTypeOfContent() {
        return "PRODUCT";
    }

    @Override
    public UUID getId() {
        return id;
    }
}