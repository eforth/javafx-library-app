package me.eforth;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException() {
        super("Book not found");
    }

    public String getMessage() {
        return super.getMessage();
    }
}
