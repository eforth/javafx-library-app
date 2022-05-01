package me.eforth;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class BookDialogController {

    @FXML
    public TextField titleField;
    @FXML
    public TextField editionField;
    @FXML
    public TextField authorField;
    @FXML
    public TextField publisherField;
    @FXML
    public TextField categoriesField;
    @FXML
    public TextField isbnField;

    private ObservableList<Book> mainObservableList;

    @FXML
    public void onSave(MouseEvent mouseEvent) {
        Book book = getBook();
        // Add the book main observable list
        mainObservableList.addAll(book);
        // Close the dialog
        onCancel(mouseEvent);
    }

    @FXML
    public void onCancel(MouseEvent mouseEvent) {
        // Get source of event which is the button
        Node source = (Node) mouseEvent.getSource();
        // Get the stage
        Stage stage = (Stage) source.getScene().getWindow();
        // Close the stage
        stage.close();
    }

    private Book getBook() {
        String title = titleField.getText();
        String edition = editionField.getText();
        String author = authorField.getText();
        String publisher = publisherField.getText();
        String categories = categoriesField.getText();
        String isbn = isbnField.getText();

        // Create a book instance based on the data from the text fields
        return new Book(isbn, title, edition, categories, author, publisher);
    }

    public void setMainObservableList(ObservableList<Book> mainObservableList) {
        this.mainObservableList = mainObservableList;
    }
}
