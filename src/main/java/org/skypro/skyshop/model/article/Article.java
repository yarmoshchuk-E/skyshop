package org.skypro.skyshop.model.article;

import org.skypro.skyshop.model.search.Searchable;

import java.util.Objects;

public class Article implements Searchable {
    private final String articleTitle;
    private final String textOfTheArticle;

    public Article(String articleTitle, String textOfTheArticle) {
        this.articleTitle = articleTitle;
        this.textOfTheArticle = textOfTheArticle;
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
        return "\n"+ getArticleTitle() + "- " + getTextOfTheArticle();
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
}

