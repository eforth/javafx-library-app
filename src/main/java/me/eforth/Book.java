package me.eforth;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.Serializable;

public class Book implements Serializable {
    private final StringProperty isbn;
    private final StringProperty title;
    private final StringProperty edition;
    private final StringProperty categories;
    private final StringProperty author;
    private final StringProperty publisher;

    public Book(String isbn, String title, String edition, String categories, String author, String publisher) {
        this.isbn =  new SimpleStringProperty(this, "isbn", isbn);
        this.title = new SimpleStringProperty(this, "title", title);
        this.edition = new SimpleStringProperty(this, "edition", edition);
        this.categories = new SimpleStringProperty(this, "categories", categories);
        this.author = new SimpleStringProperty(this, "author", author);
        this.publisher = new SimpleStringProperty(this, "publisher", publisher);
    }

    public String getIsbn() {
        return isbn.get();
    }

    public StringProperty isbnProperty() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn.set(isbn);
    }

    public String getTitle() {
        return title.get();
    }

    public StringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public String getEdition() {
        return edition.get();
    }

    public StringProperty editionProperty() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition.set(edition);
    }

    public String getCategories() {
        return categories.get();
    }

    public StringProperty categoriesProperty() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories.set(categories);
    }

    public String getAuthor() {
        return author.get();
    }

    public StringProperty authorProperty() {
        return author;
    }

    public void setAuthor(String author) {
        this.author.set(author);
    }

    public String getPublisher() {
        return publisher.get();
    }

    public StringProperty publisherProperty() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher.set(publisher);
    }

    @Override
    public String toString() {
        return String.format("Book{isbn=%s,title=%s,edition=%s,categories=%s,author=%s,publisher=%s}",
                isbn.get(),title.get(),edition.get(),categories.get(),author.get(),publisher.get());
    }
}
