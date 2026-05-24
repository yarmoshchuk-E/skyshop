package org.skypro.skyshop.model.product;

import java.util.UUID;

public class FixPriceProduct extends Product {
    private static final int FIX_PRICE = 300;
    private final UUID id;

    public FixPriceProduct(UUID id, String productName) {
        super(id, productName);
        this.id = id;
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
        return FIX_PRICE;
    }

    @Override
    public String toString() {
        return "<" + getProductName() + ": " + getProductPrice() + ">" + "<" + FIX_PRICE + ">";
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