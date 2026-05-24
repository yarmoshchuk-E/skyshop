package org.skypro.skyshop.model.service;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.DiscountedProduct;
import org.skypro.skyshop.model.product.FixPriceProduct;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StorageService {

    private final Map<UUID, Product> products = new HashMap<>();
    private final Map<UUID, Article> articles = new HashMap<>();


    public Collection<Article> getAllArticles() {
        return Collections.unmodifiableCollection(articles.values());
    }

    public Collection<Product> getAllProducts() {
        return Collections.unmodifiableCollection(products.values());
    }

    public Collection<Searchable> getAllSearchables() {
        Collection<Searchable> allSearchables = new ArrayList<>(products.size() + articles.size());
        allSearchables.addAll(products.values());
        allSearchables.addAll(articles.values());
        return allSearchables;
    }

    public StorageService() {
        initializationTestData();
    }

    private void initializationTestData() {
        UUID productId1 = UUID.randomUUID();
        UUID productId2 = UUID.randomUUID();
        UUID productId3 = UUID.randomUUID();
        UUID productId4 = UUID.randomUUID();
        UUID productId5 = UUID.randomUUID();
        UUID productId6 = UUID.randomUUID();

        UUID articleId1 = UUID.randomUUID();
        UUID articleId2 = UUID.randomUUID();
        UUID articleId3 = UUID.randomUUID();
        UUID articleId4 = UUID.randomUUID();
        UUID articleId5 = UUID.randomUUID();

        products.put(productId1, new SimpleProduct(productId1, "молоко", 100));
        products.put(productId2, new SimpleProduct(productId2, "хлеб", 40));
        products.put(productId3, new FixPriceProduct(productId3, "зефир"));
        products.put(productId4, new DiscountedProduct(productId4, "сыр", 400, 20));
        products.put(productId5, new SimpleProduct(productId5, "конфеты", 450));
        products.put(productId6, new SimpleProduct(productId6, "чай", 150));

        articles.put(articleId1, new Article(articleId1, "хлеб", "хлеб темный Дарницкий"));
        articles.put(articleId2, new Article(articleId2, "чай", "чай чорный Цейлонский"));
        articles.put(articleId3, new Article(articleId3, "сыр", "сыр Голландский полутвёрдый"));
        articles.put(articleId4, new Article(articleId4, "молоко", "молоко пастеризованное, жирность 2,5%"));
        articles.put(articleId5, new Article(articleId5, "конфеты", "набор конфет для детских праздников"));
    }
}