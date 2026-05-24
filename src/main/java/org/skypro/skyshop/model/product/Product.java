package org.skypro.skyshop.model.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.model.search.Searchable;

import java.util.Objects;
import java.util.UUID;

public abstract class Product implements Searchable {

    private final String productName;
    private final UUID id;

    public Product(UUID id, String productName) {

        if (productName == null || productName.isBlank()) {
            throw new IllegalArgumentException("не указано имя продукта!");
        }
        this.productName = productName;
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public abstract int getProductPrice();

    @JsonIgnore
    public abstract boolean isSpecial();


    @Override
    public String getName() {
        return getProductName();
    }

    @Override
    public String toString() {
        return "\n" + "<" + getProductName() + ": " + getProductPrice() + ">";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(productName, product.productName);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(productName);
    }


    @Override
    public UUID getId() {
        return id;
    }
}