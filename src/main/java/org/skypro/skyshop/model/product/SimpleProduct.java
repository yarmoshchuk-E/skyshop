package org.skypro.skyshop.model.product;

public class SimpleProduct extends Product {
    private final int productPrice;

    public SimpleProduct(String productName, int productPrice) {
        super(productName);
        if (productPrice <= 0) {
            throw new IllegalArgumentException("неверно указана цена!");
        }
        this.productPrice = productPrice;
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
    @Override
    public String getSearchTerm() {
        return getProductName();
    }

    @Override
    public String getTypeOfContent() {
        return "PRODUCT";
    }
}

