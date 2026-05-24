package org.skypro.skyshop.model.article;

import org.skypro.skyshop.model.search.Searchable;

import java.util.Objects;
import java.util.UUID;

public class Article implements Searchable {
    private final String articleTitle;
    private final String textOfTheArticle;
    private final UUID id;

    public Article(UUID id, String articleTitle, String textOfTheArticle) {
        this.articleTitle = articleTitle;
        this.textOfTheArticle = textOfTheArticle;
        this.id = id;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public String getTextOfTheArticle() {
        return textOfTheArticle;
    }

    @Override
    public String getName() {
        return getArticleTitle();
    }

    @Override
    public String toString() {
        return getArticleTitle() + "- " + getTextOfTheArticle();
    }

    @Override
    public String getSearchTerm() {
        return toString();
    }

    @Override
    public String getTypeOfContent() {
        return "ARTICLE";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return Objects.equals(articleTitle, article.articleTitle);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(articleTitle);
    }

    @Override
    public UUID getId() {
        return id;
    }
}