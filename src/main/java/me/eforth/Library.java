package me.eforth;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Library {

    private final String FILENAME = "library.bat";
    private List<Book> books = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
        save();
    }

    public void save() {
        try (
                // Open file stream to modify file
                FileOutputStream fOutputStream = new FileOutputStream(FILENAME);
                // Open stream to writing purposes
                ObjectOutputStream outputStream = new ObjectOutputStream(fOutputStream);
        ) {
            // Cycle through each book in the list
            for (Book book : books) {
                // Write book to stream
                outputStream.writeObject(book);
            }

        } catch (Exception e) {
            System.out.println("Unable to save data.");
        }
    }

    public void load() {

        File file = new File(FILENAME);

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch(IOException e) {
                System.out.println("Unable to create library file");
                System.exit(0);
            }
        }

        try (
                FileInputStream fInputStream = new FileInputStream(FILENAME);
                ObjectInputStream oInputStream = new ObjectInputStream(fInputStream);
        ) {
            boolean hasMore = true;
            while (hasMore) {
                Object obj = oInputStream.readObject();
                if (obj != null) {
                    Book book = (Book) obj;
                    books.add(book);
                    hasMore = true;
                } else {
                    hasMore = false;
                }
            }
        } catch (EOFException e) {
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Unable to access file.");
        }
    }

    public void updateBook(Book book) {
        int index = books.indexOf(book);
        books.set(index, book);
        save();
    }

    public void removeBook(Book book) {
        books.remove(book);
        save();
    }

    public void removeBooks() {
        books.clear();
        save();
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
