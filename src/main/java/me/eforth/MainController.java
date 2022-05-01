package me.eforth;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    public TableView<Book> bookTable;
    @FXML
    public TableColumn<Book, String> titleColumn;
    @FXML
    public TableColumn<Book, String> editionColumn;
    @FXML
    public TableColumn<Book, String> authorColumn;
    @FXML
    public TableColumn<Book, String> publisherColumn;
    @FXML
    public TableColumn<Book, String> categoriesColumn;
    @FXML
    public TableColumn<Book, String> isbnColumn;

    private Library library;
    private final ObservableList<Book> bookObservableList = FXCollections.observableArrayList();

    @FXML
    public void onNew(ActionEvent actionEvent) throws IOException {
        // Create dialog
        Dialog<String> dialog = new Dialog<>();
        // Create an FXML loader that points to the FXML file
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/book-dialog.fxml"));
        // Load FXML file as a JavaFX control
        HBox root = fxmlLoader.load();
        // Get an instance of the BookDialogController
        BookDialogController controller = fxmlLoader.getController();
        // Set the observable list in the dialog
        controller.setMainObservableList(bookObservableList);
        // Set the title of the dialog
        dialog.setTitle("Add Book");
        // Add HBox to dialog content
        dialog.getDialogPane().setContent(root);
        // Show the dialog
        dialog.showAndWait();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Create an instance of the library object
        library = new Library();
        // Load book from file
        library.load();
        // Add list of books to observable list
        bookObservableList.addAll(library.getBooks());
        // Link columns to data
        titleColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
        editionColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("edition"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("author"));
        publisherColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("publisher"));
        categoriesColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("categories"));
        isbnColumn.setCellValueFactory(new PropertyValueFactory<Book, String>("isbn"));
        // Set list as datasource for table view
        bookTable.setItems(bookObservableList);

        bookObservableList.addListener(new ListChangeListener<Book>() {
            @Override
            public void onChanged(Change<? extends Book> change) {
                while (change.next()) {
                    if (change.wasAdded() || change.wasRemoved() || change.wasUpdated()) {
                        library.save();
                    }
                }
            }
        });

        // table view selection listener
        // https://www.demo2s.com/java/javafx-tableview-creating-and-working-with-tables.html
        // when a row is selected set the selected book in the BookDialogController by calling setBook(Book book).
    }

    public void onEdit(ActionEvent actionEvent) throws IOException {
        // Create dialog
        Dialog<String> dialog = new Dialog<>();
        // Create an FXML loader that points to the FXML file
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/book-dialog.fxml"));
        // Load FXML file as a JavaFX control
        HBox root = fxmlLoader.load();
        // Get an instance of the BookDialogController
        BookDialogController controller = fxmlLoader.getController();
        // Set the observable list in the dialog
        controller.setMainObservableList(bookObservableList);
        // Set the title of the dialog
        dialog.setTitle("Edit Book");
        // Add HBox to dialog content
        dialog.getDialogPane().setContent(root);
        // Show the dialog
        dialog.showAndWait();
    }
}
