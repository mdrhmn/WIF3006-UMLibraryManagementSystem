package com.lmsspringclient;

import java.io.IOException;

import javafx.beans.property.SimpleStringProperty;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.ArrayList;
import java.net.URL;

import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXML;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import com.lmsspringbeans.BookReservation;
import com.lmsspringbeans.BookStorage;
import com.lmsspringbeans.IssuedBooks;
import com.lmsspringbeans.Reservation;
import com.lmsspringbeans.Librarian;
import com.lmsspringbeans.Member;
import com.lmsspringbeans.Book;
import com.lmsspringbeans.ReservationInformation;
import com.lmsspringbeans.User;
import java.util.HashMap;
import javafx.stage.Modality;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author muhdrahiman
 */
public class GUIController implements Initializable {

    /**
     * Client creates an application context appropriately (File or ClassPath or
     * Web). In this project, the client uses the ClassPathXmlApplicationContext
     * API that helps in loading the Beans.xml from the CLASSPATH (the XML file
     * must be available in the CLASSPATH).
     *
     * The context plays an important role to get the reference of the bean
     * because there is no interface.
     *
     * Client obtains a bean reference using the getBean() method of the
     * application context object. The Spring container returns an instance of
     * the bean.
     */
    ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");

    /**
     * Client obtains a bean reference using the getBean() method of the
     * application context object. The Spring container returns an instance of
     * the bean.
     */
    ReservationInformation reservationInformation = (ReservationInformation) context.getBean("reservationInformation");
    BookReservation bookReservation = (BookReservation) context.getBean("bookReservation");
    BookStorage bookStorage = (BookStorage) context.getBean("bookStorage");
    IssuedBooks issuedBooks = (IssuedBooks) context.getBean("issuedBooks");
    Reservation reservation = (Reservation) context.getBean("reservation");
    Librarian librarian1 = (Librarian) context.getBean("librarian");
    Member member1 = (Member) context.getBean("member");
    Member member2 = (Member) context.getBean("member");
    User user = (User) context.getBean("user");
    Book book = (Book) context.getBean("book");

    /**
     * Static variables initialisation
     */
//    static ArrayList<User> userList = new ArrayList<>();
    static Boolean initialisationFlag = false;
    static User loggedUser = null;
    Boolean foundUser = false;

    /**
     * FXML components declarations (Sign In Menu)
     */
    @FXML
    public TextField username;
    public PasswordField password;
    public MenuItem about_app_item;

    /**
     * FXML components declarations (Buttons)
     */
    @FXML
    public Button cancel_member_selected_reservation_btn;
    public Button reset_member_selected_reservation_btn;
    public Button manage_librarian_reservations_btn;
    public Button cancel_selected_reservation_btn;
    public Button manage_member_reservations_btn;
    public Button cancel_selected_book_btn;
    public Button manage_issued_books_btn;
    public Button approve_reservation_btn;
    public Button cancel_borrow_book_btn;
    public Button reject_reservation_btn;
    public Button cancel_return_book_btn;
    public Button view_issued_books_btn;
    public Button manage_books_btn;
    public Button borrow_books_btn;
    public Button reserve_book_btn;
    public Button update_book_btn;
    public Button delete_book_btn;
    public Button return_book_btn;
    public Button sign_out_btn;
    public Button add_book_btn;
    public Button sign_in_btn;

    /**
     * FXML components declarations (TextField)
     */
    @FXML
    public TextField user_full_name_reservations_field;
    public TextField book_name_reservations_field;
    public TextField username_reservations_field;
    public TextField user_id_reservations_field;
    public TextField book_id_reservations_field;
    public TextField book_quantity_field;
    public TextField book_author_field;
    public TextField book_name_field;
    public TextField book_year_field;
    public TextField book_id_field;

    /**
     * FXML components declarations (View Books Table)
     */
    @FXML
    public TableView<Book> view_books_table;
    public TableColumn<Book, Integer> book_id_column;
    public TableColumn<Book, String> book_name_column;
    public TableColumn<Book, String> book_author_column;
    public TableColumn<Book, Integer> book_published_year_column;
    public TableColumn<Book, Integer> book_quantity_column;

    /**
     * FXML components declarations (View Book Reservations Table)
     */
    @FXML
    public TableView<BookReservation> view_reservations_table;
    public TableColumn<BookReservation, String> user_id_reservation_column;
    public TableColumn<BookReservation, String> username_reservation_column;
    public TableColumn<BookReservation, String> full_name_reservation_column;
    public TableColumn<BookReservation, String> book_id_reservation_column;
    public TableColumn<BookReservation, String> book_name_reservation_column;

    /**
     * FXML components declarations (View Member Book Reservations Table)
     */
    @FXML
    public TableView<BookReservation> view_member_reservations_table;
    public TableView<BookReservation> view_issued_books_table;
    public TableView<Book> view_member_issued_books_table;
    public TableView<Book> view_borrow_books_table;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        try {
            initialisation();
            aboutAppController();
            authenticationController();
            menuController();
            librarianBookController();
            librarianReservationsController();
            librarianIssuedBooksController();
            memberReservationsController();
            memberIssuedBooksController();
            memberBorrowBooksController();
        } catch (Exception e) {
        }
    }

    /**
     * Singleton-ish method for one-time initialisation of certain variables
     */
    public void initialisation() {

        if (initialisationFlag == false) {

            try {
//                member1 = new Member("Ajwad Alias", "ajwadalias", "ajwad123", "Member");
//                member2 = new Member("Ashraf Alias", "ashrafalias", "ashraf123", "Member");
//                librarian1 = new Librarian("Muhd Rahiman", "mdrhmn", "ray123", "Librarian");
//
//                userList.add(member1);
//                userList.add(member2);
//                userList.add(librarian1);

//                for (int i = 1; i <= 5; i++) {
//                    book = new Book("Book " + i, "Author " + i, 2000 + i, 3, 0);
//                    bookStorage.books.add(book);
//                }
                user.setApplicationContext(context);
                bookStorage.setApplicationContext(context);
                initialisationFlag = true;

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Controller for About Us menu item
     */
    public void aboutAppController() {
        about_app_item.setOnAction((t) -> {

            // Create new stage
            Stage stage = new Stage();

            // Create new container
            HBox container = new HBox();
            // Create new TextArea and set its configurations
            TextArea text = new TextArea();
            text.setEditable(false);
            text.setWrapText(true);

            // Set text context of TextArea
            text.setText("Developers:\n");
            text.appendText("1. Muhammad Rahiman bin Abdulmanab (L)\n2. Ajwad bin Alias\n3. Nur Faidz Hazirah binti Nor'Azman\n4. Syahruddin bin Syahrir\n5. Nur Hazirah binti Abdul Razak\n6. Farhan Sadiq\n\n");

            // Add TextArea to container
            container.getChildren().addAll(text);

            // Set scene
            Scene scene = new Scene(container);
            stage.setResizable(false);
            stage.sizeToScene();
            stage.setTitle("About Our App");
            stage.setScene(scene);
            stage.show();
        });
    }

    /**
     * Controller for authentication
     */
    public void authenticationController() {

        if (sign_in_btn != null) {

            // Event listener for Sign In
            sign_in_btn.setOnAction((t) -> {
                for (User user : user.userList) {
                    if (user.getUsername().equals(username.getText()) && user.getPassword().equals(password.getText())) {
                        switch (user.getRole()) {
                            case "Member": {
                                // Set loggedUser to user found
                                loggedUser = user;
                                foundUser = true;
                                try {
                                    // Close Sign In window
                                    ((Stage) sign_in_btn.getScene().getWindow()).close();

                                    // Set FXML stage and scene configurations
                                    Parent root = FXMLLoader.load(getClass().getResource("/fxml/GUI_CBSE_MENU_MEMBER.fxml"));
                                    root.getStylesheets().add(getClass().getResource("/css/bootstrap.css").toString());
                                    Scene scene = new Scene(root);
                                    Stage stage = new Stage();

                                    stage.setResizable(false);
                                    stage.setScene(scene);
                                    stage.setTitle("UM Library Management System: Member Menu");
                                    stage.show();

                                    // Show success alert
                                    Alert alert = new Alert(AlertType.INFORMATION);
                                    alert.setTitle("Success");
                                    alert.setHeaderText("Welcome, Member " + loggedUser.getName() + "!");
                                    alert.show();

                                    break;
                                } catch (IOException ex) {
                                    Logger.getLogger(GUIController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }

                            case "Librarian": {
                                // Set loggedUser to user found
                                loggedUser = user;
                                foundUser = true;

                                try {
                                    // Close Sign In window
                                    ((Stage) sign_in_btn.getScene().getWindow()).close();

                                    // Set FXML stage and scene configurations
                                    Parent root = FXMLLoader.load(getClass().getResource("/fxml/GUI_CBSE_MENU_LIBRARIAN.fxml"));
                                    root.getStylesheets().add(getClass().getResource("/css/bootstrap.css").toString());
                                    Scene scene = new Scene(root);
                                    Stage stage = new Stage();

                                    stage.setResizable(false);
                                    stage.setScene(scene);
                                    stage.setTitle("UM Library Management System: Librarian Menu");
                                    stage.show();

                                    // Show success alert
                                    Alert alert = new Alert(AlertType.INFORMATION);
                                    alert.setTitle("Success");
                                    alert.setHeaderText("Welcome, Librarian " + loggedUser.getName() + "!");
                                    alert.show();

                                    break;
                                } catch (IOException ex) {
                                    Logger.getLogger(GUIController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                            default:
                                throw new IllegalArgumentException("Unexpected value");
                        }
                    }
                }

                // If user not found (invalid username/password) = display error alert
                if (!foundUser) {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Wrong username and password");
                    alert.setContentText("Please try and sign in again with the correct credentials.");
                    alert.show();
                }
            });
        }

        if (sign_out_btn != null) {
            sign_out_btn.setOnAction((t) -> {
                try {
                    loggedUser = null;

                    // Close Menu window
                    ((Stage) sign_out_btn.getScene().getWindow()).close();

                    Parent root = FXMLLoader.load(getClass().getResource("/fxml/GUI_CBSE_LOGIN.fxml"));
                    root.getStylesheets().add(getClass().getResource("/css/bootstrap.css").toString());
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();

                    stage.setResizable(false);
                    stage.setScene(scene);
                    stage.setTitle("UM Library Management System: Sign In Menu");
                    stage.show();

                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setHeaderText("You have signed out successfully.");
                    alert.show();
                } catch (IOException ex) {
                    Logger.getLogger(GUIController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        }
    }

    /**
     * Controller for main menu for both Member and Librarian
     */
    public void menuController() {

        // Event listener for Librarian's Manage Books window
        if (manage_books_btn != null) {
            manage_books_btn.setOnAction((t) -> {
                try {

                    // Set FXML stage and scene configurations
                    Parent root = FXMLLoader.load(getClass().getResource("/fxml/GUI_CBSE_MANAGE_BOOKS.fxml"));
                    root.getStylesheets().add(getClass().getResource("/css/bootstrap.css").toString());
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();

                    // Set the modality to APPLICATION_MODAL to prevent the application from opening any new window until the current active one is closed
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.setResizable(false);
                    stage.setScene(scene);
                    stage.setTitle("UM Library Management System: Manage Books");
                    stage.show();

                } catch (IOException ex) {
                    Logger.getLogger(GUIController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        }

        // Event listener for Librarian's Manage Book Reservations window
        if (manage_librarian_reservations_btn != null) {
            manage_librarian_reservations_btn.setOnAction((t) -> {
                try {
                    // Set FXML stage and scene configurations
                    Parent root = FXMLLoader.load(getClass().getResource("/fxml/GUI_CBSE_MANAGE_RESERVATIONS.fxml"));
                    root.getStylesheets().add(getClass().getResource("/css/bootstrap.css").toString());
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();

                    // Set the modality to APPLICATION_MODAL to prevent the application from opening any new window until the current active one is closed
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.setResizable(false);
                    stage.setScene(scene);
                    stage.setTitle("UM Library Management System: Manage Reservations");
                    stage.show();

                } catch (IOException ex) {
                    Logger.getLogger(GUIController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        }

        // Event listener for Librarian's View All Issued Books window
        if (view_issued_books_btn != null) {
            view_issued_books_btn.setOnAction((t) -> {
                try {
                    // Set FXML stage and scene configurations
                    Parent root = FXMLLoader.load(getClass().getResource("/fxml/GUI_CBSE_MANAGE_ISSUED_BOOKS.fxml"));
                    root.getStylesheets().add(getClass().getResource("/css/bootstrap.css").toString());
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();

                    // Set the modality to APPLICATION_MODAL to prevent the application from opening any new window until the current active one is closed
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.setResizable(false);
                    stage.setScene(scene);
                    stage.setTitle("UM Library Management System: View Issued Books");
                    stage.show();

                } catch (IOException ex) {
                    Logger.getLogger(GUIController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        }

        // Event listener for Member's Borrow Books window
        if (borrow_books_btn != null) {
            borrow_books_btn.setOnAction((t) -> {
                try {
                    // Set FXML stage and scene configurations
                    Parent root = FXMLLoader.load(getClass().getResource("/fxml/GUI_CBSE_BORROW_BOOKS.fxml"));
                    root.getStylesheets().add(getClass().getResource("/css/bootstrap.css").toString());
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();

                    // Set the modality to APPLICATION_MODAL to prevent the application from opening any new window until the current active one is closed
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.setResizable(false);
                    stage.setScene(scene);
                    stage.setTitle("UM Library Management System: Borrow Books");
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(GUIController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        }

        // Event listener for Member's Manage Book Reservations window
        if (manage_member_reservations_btn != null) {
            manage_member_reservations_btn.setOnAction((t) -> {
                try {
                    // Set FXML stage and scene configurations
                    Parent root = FXMLLoader.load(getClass().getResource("/fxml/GUI_CBSE_MANAGE_RESERVATIONS_MEMBER.fxml"));
                    root.getStylesheets().add(getClass().getResource("/css/bootstrap.css").toString());
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();

                    // Set the modality to APPLICATION_MODAL to prevent the application from opening any new window until the current active one is closed
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.setResizable(false);
                    stage.setScene(scene);
                    stage.setTitle("UM Library Management System: Manage Book Reservations");
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(GUIController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        }

        // Event listener for Member's Manage Issued Books window
        if (manage_issued_books_btn != null) {
            manage_issued_books_btn.setOnAction((t) -> {
                try {
                    // Set FXML stage and scene configurations
                    Parent root = FXMLLoader.load(getClass().getResource("/fxml/GUI_CBSE_MANAGE_ISSUED_BOOKS_MEMBER.fxml"));
                    root.getStylesheets().add(getClass().getResource("/css/bootstrap.css").toString());
                    Scene scene = new Scene(root);
                    Stage stage = new Stage();

                    // Set the modality to APPLICATION_MODAL to prevent the application from opening any new window until the current active one is closed
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.setResizable(false);
                    stage.setScene(scene);
                    stage.setTitle("UM Library Management System: Manage Issued Books");
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(GUIController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
        }

    }

    public void librarianBookController() {

        if (add_book_btn != null) {
            add_book_btn.setOnAction((t) -> {
                if (book_name_field.getText().equals("") || book_author_field.getText().equals("") || book_year_field.getText().equals("") || book_quantity_field.getText().equals("")) {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Please fill in all required fields!");
                    alert.show();
                } else {
                    Book newBook = new Book(book_name_field.getText(), book_author_field.getText(), Integer.parseInt(book_year_field.getText()), Integer.parseInt(book_quantity_field.getText()), 0);
                    bookStorage.addBook(newBook);

                    String bookName = book_name_field.getText();

                    book_name_field.setText("");
                    book_author_field.setText("");
                    book_year_field.setText("");
                    book_quantity_field.setText("");

                    updateBooksTableView();

                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setHeaderText(bookName + " added successfully.");
                    alert.show();

                    add_book_btn.setDisable(false);
                    update_book_btn.setDisable(true);
                    delete_book_btn.setDisable(true);
                }
            });
        }

        if (update_book_btn != null) {
            update_book_btn.setOnAction((t) -> {
                bookStorage.updateBook(Integer.parseInt(book_id_field.getText()), book_name_field.getText(), book_author_field.getText(), Integer.parseInt(book_year_field.getText()), Integer.parseInt(book_quantity_field.getText()));

                String bookName = book_name_field.getText();

                book_id_field.setText("");
                book_name_field.setText("");
                book_author_field.setText("");
                book_year_field.setText("");
                book_quantity_field.setText("");

                updateBooksTableView();

                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(bookName + " updated successfully.");
                alert.show();

                add_book_btn.setDisable(false);
                update_book_btn.setDisable(true);
                delete_book_btn.setDisable(true);
            });
        }

        if (delete_book_btn != null) {
            delete_book_btn.setOnAction((t) -> {
                bookStorage.deleteBook(Integer.parseInt(book_id_field.getText()));

                String bookName = book_name_field.getText();

                book_id_field.setText("");
                book_name_field.setText("");
                book_author_field.setText("");
                book_year_field.setText("");
                book_quantity_field.setText("");

                updateBooksTableView();

                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(bookName + " deleted successfully.");
                alert.show();

                add_book_btn.setDisable(false);
                update_book_btn.setDisable(true);
                delete_book_btn.setDisable(true);
            });
        }

        if (cancel_selected_book_btn != null) {
            cancel_selected_book_btn.setOnAction((t) -> {
                book_id_field.setText("");
                book_name_field.setText("");
                book_author_field.setText("");
                book_year_field.setText("");
                book_quantity_field.setText("");

                add_book_btn.setDisable(false);
                update_book_btn.setDisable(true);
                delete_book_btn.setDisable(true);
            });
        }

        if (view_books_table != null) {
            book_id_column.setCellValueFactory(new PropertyValueFactory<>("id"));
            book_name_column.setCellValueFactory(new PropertyValueFactory<>("name"));
            book_author_column.setCellValueFactory(new PropertyValueFactory<>("author"));
            book_published_year_column.setCellValueFactory(new PropertyValueFactory<>("year"));
            book_quantity_column.setCellValueFactory(new PropertyValueFactory<>("availableQuantity"));
            view_books_table.setItems(FXCollections.observableArrayList(
                    bookStorage.getBooks()
            ));

            view_books_table.setOnMouseClicked((MouseEvent event) -> {
                if (event.getClickCount() == 2) //Checking double click
                {
                    book_id_field.setText(String.valueOf(view_books_table.getSelectionModel().getSelectedItem().getId()));
                    book_name_field.setText(String.valueOf(view_books_table.getSelectionModel().getSelectedItem().getName()));
                    book_author_field.setText(String.valueOf(view_books_table.getSelectionModel().getSelectedItem().getAuthor()));
                    book_year_field.setText(String.valueOf(view_books_table.getSelectionModel().getSelectedItem().getYear()));
                    book_quantity_field.setText(String.valueOf(view_books_table.getSelectionModel().getSelectedItem().getAvailableQuantity()));

                    add_book_btn.setDisable(true);
                    update_book_btn.setDisable(false);
                    delete_book_btn.setDisable(false);
                }
            });

            add_book_btn.setDisable(false);
            update_book_btn.setDisable(true);
            delete_book_btn.setDisable(true);
        }

    }

    public void memberBorrowBooksController() {
        if (view_borrow_books_table != null) {
            book_id_column.setCellValueFactory(new PropertyValueFactory<>("id"));
            book_name_column.setCellValueFactory(new PropertyValueFactory<>("name"));
            book_author_column.setCellValueFactory(new PropertyValueFactory<>("author"));
            book_published_year_column.setCellValueFactory(new PropertyValueFactory<>("year"));
            book_quantity_column.setCellValueFactory(new PropertyValueFactory<>("availableQuantity"));
            view_borrow_books_table.setItems(FXCollections.observableArrayList(
                    bookStorage.getBooks()
            ));

            view_borrow_books_table.setOnMouseClicked((MouseEvent event) -> {
                if (event.getClickCount() == 2) //Checking double click
                {
                    book_id_field.setText(String.valueOf(view_borrow_books_table.getSelectionModel().getSelectedItem().getId()));
                    book_name_field.setText(String.valueOf(view_borrow_books_table.getSelectionModel().getSelectedItem().getName()));
                    book_author_field.setText(String.valueOf(view_borrow_books_table.getSelectionModel().getSelectedItem().getAuthor()));
                    book_year_field.setText(String.valueOf(view_borrow_books_table.getSelectionModel().getSelectedItem().getYear()));
                    book_quantity_field.setText(String.valueOf(view_borrow_books_table.getSelectionModel().getSelectedItem().getAvailableQuantity()));
                    reserve_book_btn.setDisable(false);
                }
            });

            reserve_book_btn.setDisable(true);
        }

        if (cancel_borrow_book_btn != null) {
            cancel_borrow_book_btn.setOnAction((t) -> {
                book_id_field.setText("");
                book_name_field.setText("");
                book_author_field.setText("");
                book_year_field.setText("");
                book_quantity_field.setText("");

                reserve_book_btn.setDisable(true);
            });
        }

        if (reserve_book_btn != null) {
            reserve_book_btn.setOnAction((t) -> {
                reservation.makeReservation((Member) loggedUser, bookStorage.getBookByID(Integer.parseInt(book_id_field.getText())));

                String reservedBookName = book_name_field.getText();
                book_id_field.setText("");
                book_name_field.setText("");
                book_author_field.setText("");
                book_year_field.setText("");
                book_quantity_field.setText("");

                updateMemberBorrowBooksTableView();

                System.out.println("All reservations: " + reservation.getAllReservationsList());
                System.out.println("Current user's reservations: " + reservation.getAllMemberReservationsList(loggedUser.getId()));

                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(reservedBookName + " reserved successfully.");
                alert.show();

                reserve_book_btn.setDisable(true);
            });
        }
    }

    public void memberReservationsController() {
        if (view_member_reservations_table != null) {

            user_id_reservation_column.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getUserIDInString()));
            username_reservation_column.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().username));
            full_name_reservation_column.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().fullName));
            book_id_reservation_column.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getBookIDInString()));
            book_name_reservation_column.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().bookName));

            view_member_reservations_table.setItems(FXCollections.observableArrayList(reservation.getAllMemberReservationsList(loggedUser.getId())));
            cancel_member_selected_reservation_btn.setDisable(true);
            reset_member_selected_reservation_btn.setDisable(true);

            view_member_reservations_table.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if (event.getClickCount() == 2) //Checking double click
                    {
                        user_id_reservations_field.setText(String.valueOf(view_member_reservations_table.getSelectionModel().getSelectedItem().getUserID()));
                        username_reservations_field.setText(String.valueOf(view_member_reservations_table.getSelectionModel().getSelectedItem().getUsername()));
                        user_full_name_reservations_field.setText(String.valueOf(view_member_reservations_table.getSelectionModel().getSelectedItem().getFullName()));
                        book_id_reservations_field.setText(String.valueOf(view_member_reservations_table.getSelectionModel().getSelectedItem().getBookID()));
                        book_name_reservations_field.setText(String.valueOf(view_member_reservations_table.getSelectionModel().getSelectedItem().getBookName()));
                        cancel_member_selected_reservation_btn.setDisable(false);
                        reset_member_selected_reservation_btn.setDisable(false);
                    }
                }
            });
        }

        if (reset_member_selected_reservation_btn != null) {
            reset_member_selected_reservation_btn.setOnAction((t) -> {
                user_id_reservations_field.setText("");
                username_reservations_field.setText("");
                user_full_name_reservations_field.setText("");
                book_id_reservations_field.setText("");
                book_name_reservations_field.setText("");
                cancel_member_selected_reservation_btn.setDisable(true);
            });
        }

        if (cancel_member_selected_reservation_btn != null) {
            cancel_member_selected_reservation_btn.setOnAction((t) -> {
                reservation.cancelReservation((Member) loggedUser, bookStorage.getBookByID(Integer.parseInt(book_id_reservations_field.getText())));
                reservation.bookReservationsList.remove(reservation.getBookReservation(Integer.parseInt(user_id_reservations_field.getText()), Integer.parseInt(book_id_reservations_field.getText())));

                String deletedBookReservationName = book_name_reservations_field.getText();
                user_id_reservations_field.setText("");
                username_reservations_field.setText("");
                user_full_name_reservations_field.setText("");
                book_id_reservations_field.setText("");
                book_name_reservations_field.setText("");

                cancel_member_selected_reservation_btn.setDisable(true);
                updateMemberReservationsTableView();

                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(deletedBookReservationName + " reservation cancelled successfully.");
                alert.show();
            });
        }

    }

    public void librarianReservationsController() {

        if (cancel_selected_reservation_btn != null) {
            cancel_selected_reservation_btn.setOnAction((t) -> {
                user_id_reservations_field.setText("");
                username_reservations_field.setText("");
                user_full_name_reservations_field.setText("");
                book_id_reservations_field.setText("");
                book_name_reservations_field.setText("");

                approve_reservation_btn.setDisable(true);
                reject_reservation_btn.setDisable(true);
            });
        }

        if (approve_reservation_btn != null) {
            approve_reservation_btn.setOnAction((t) -> {
                System.out.println("BEFORE APPROVE:");
                System.out.println("Issued: " + issuedBooks.issuedBookReservationsList);
                System.out.println("Reserved: " + reservation.bookReservationsList);

                Member selectedMember = issuedBooks.getMember(Integer.parseInt(user_id_reservations_field.getText()), reservation);
                int selectedBookIndex = reservationInformation.getBookIndexById(Integer.parseInt(book_id_reservations_field.getText()));

                issuedBooks.approveReservations(selectedMember, reservation, selectedBookIndex);

                issuedBooks.issuedBookReservationsList.add(reservation.getBookReservation(Integer.parseInt(user_id_reservations_field.getText()), Integer.parseInt(book_id_reservations_field.getText())));
                reservation.bookReservationsList.remove(reservation.getBookReservation(Integer.parseInt(user_id_reservations_field.getText()), Integer.parseInt(book_id_reservations_field.getText())));

                System.out.println("AFTER APPROVE:");
                System.out.println("Issued: " + issuedBooks.issuedBookReservationsList);
                System.out.println("Reserved: " + reservation.bookReservationsList);

                String bookReservationName = book_name_reservations_field.getText();
                String bookReservationFullName = user_full_name_reservations_field.getText();

                user_id_reservations_field.setText("");
                username_reservations_field.setText("");
                user_full_name_reservations_field.setText("");
                book_id_reservations_field.setText("");
                book_name_reservations_field.setText("");

                approve_reservation_btn.setDisable(true);
                reject_reservation_btn.setDisable(true);

                updateLibrarianReservationsTableView();

                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(bookReservationName + " reservation  for " + bookReservationFullName + " approved successfully.");
                alert.show();
            });
        }

        if (reject_reservation_btn != null) {
            reject_reservation_btn.setOnAction((t) -> {
                Member selectedMember = issuedBooks.getMember(Integer.parseInt(user_id_reservations_field.getText()), reservation);

                System.out.println("BEFORE REJECT:");
                System.out.println("Issued: " + issuedBooks.issuedBookReservationsList);
                System.out.println("Reserved: " + reservation.bookReservationsList);

                int selectedBookIndex = reservationInformation.getBookIndexById(Integer.parseInt(book_id_reservations_field.getText()));
                issuedBooks.rejectReservations(selectedMember, reservation, selectedBookIndex);
                reservation.bookReservationsList.remove(reservation.getBookReservation(Integer.parseInt(user_id_reservations_field.getText()), Integer.parseInt(book_id_reservations_field.getText())));

                System.out.println("AFTER REJECT:");
                System.out.println("Issued: " + issuedBooks.issuedBookReservationsList);
                System.out.println("Reserved: " + reservation.bookReservationsList);

                String bookReservationName = book_name_reservations_field.getText();
                String bookReservationFullName = user_full_name_reservations_field.getText();

                user_id_reservations_field.setText("");
                username_reservations_field.setText("");
                user_full_name_reservations_field.setText("");
                book_id_reservations_field.setText("");
                book_name_reservations_field.setText("");

                approve_reservation_btn.setDisable(true);
                reject_reservation_btn.setDisable(true);

                updateLibrarianReservationsTableView();

                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(bookReservationName + " reservation  for " + bookReservationFullName + " rejected successfully.");
                alert.show();
            });
        }

        if (view_reservations_table != null) {
            user_id_reservation_column.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getUserIDInString()));
            username_reservation_column.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().username));
            full_name_reservation_column.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().fullName));
            book_id_reservation_column.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getBookIDInString()));
            book_name_reservation_column.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().bookName));

            view_reservations_table.setItems(FXCollections.observableArrayList(reservation.bookReservationsList));
            approve_reservation_btn.setDisable(true);
            reject_reservation_btn.setDisable(true);

            // Event listener for double clicking row
            view_reservations_table.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if (event.getClickCount() == 2) //Checking double click
                    {
                        // Display each column values to their respective TextFields
                        user_id_reservations_field.setText(String.valueOf(view_reservations_table.getSelectionModel().getSelectedItem().getUserID()));
                        username_reservations_field.setText(String.valueOf(view_reservations_table.getSelectionModel().getSelectedItem().getUsername()));
                        user_full_name_reservations_field.setText(String.valueOf(view_reservations_table.getSelectionModel().getSelectedItem().getFullName()));
                        book_id_reservations_field.setText(String.valueOf(view_reservations_table.getSelectionModel().getSelectedItem().getBookID()));
                        book_name_reservations_field.setText(String.valueOf(view_reservations_table.getSelectionModel().getSelectedItem().getBookName()));

                        // Enable both buttons
                        approve_reservation_btn.setDisable(false);
                        reject_reservation_btn.setDisable(false);
                    }
                }
            });
        }

    }

    public void memberIssuedBooksController() {
        if (view_member_issued_books_table != null) {
            // Disable return book button by default
            return_book_btn.setDisable(true);

            // Enable cancel return book button by default
            cancel_return_book_btn.setDisable(false);

            // Configure TableView column properties for view_member_issued_books_table
            book_id_column.setCellValueFactory(new PropertyValueFactory<>("id"));
            book_name_column.setCellValueFactory(new PropertyValueFactory<>("name"));
            book_author_column.setCellValueFactory(new PropertyValueFactory<>("author"));
            book_published_year_column.setCellValueFactory(new PropertyValueFactory<>("year"));

            if ((issuedBooks.getAllMemberIssuedBooksList(loggedUser) != null)) {
                view_member_issued_books_table.setItems(FXCollections.observableArrayList(issuedBooks.getAllMemberIssuedBooksList(loggedUser)));
            }

            // Event listener for double clicking row
            view_member_issued_books_table.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if (event.getClickCount() == 2) //Checking double click
                    {
                        // Display each column values to their respective TextFields
                        book_id_field.setText(String.valueOf(view_member_issued_books_table.getSelectionModel().getSelectedItem().getId()));
                        book_name_field.setText(String.valueOf(view_member_issued_books_table.getSelectionModel().getSelectedItem().getName()));
                        book_author_field.setText(String.valueOf(view_member_issued_books_table.getSelectionModel().getSelectedItem().getAuthor()));
                        book_year_field.setText(String.valueOf(view_member_issued_books_table.getSelectionModel().getSelectedItem().getYear()));

                        // Enable both buttons
                        return_book_btn.setDisable(false);
                        cancel_return_book_btn.setDisable(false);
                    }
                }
            });
        }

        // Event listener for Return Books
        if (return_book_btn != null) {
            return_book_btn.setOnAction((t) -> {

                System.out.println("BEFORE RETURN:");
                System.out.println("Issued: " + issuedBooks.issuedBookReservationsList);
                System.out.println("Issued: " + reservationInformation.issuedBooksList);

                // Get returned book instance from Book Storage
                Book returnedBook = bookStorage.getBookByID(Integer.parseInt(book_id_field.getText()));

                // Invoke returnIssuedBooks method to return books
                issuedBooks.returnIssuedBooks((Member) loggedUser, reservation, returnedBook);

                // Remove BookReservation instance of returned book from issuedBookReservationsList
                issuedBooks.issuedBookReservationsList.remove(issuedBooks.getIssuedBookReservation(loggedUser.getId(), Integer.parseInt(book_id_field.getText())));
                // Remove BookReservation instance of returned book from issuedBooksList
                reservationInformation.issuedBooksList.remove(issuedBooks.getIssuedBookReservation(loggedUser.getId(), Integer.parseInt(book_id_field.getText())));

                System.out.println("AFTER RETURN:");
                System.out.println("Issued: " + issuedBooks.issuedBookReservationsList);
                System.out.println("Issued: " + reservationInformation.issuedBooksList);

                // Get returned book name
                String returnedBookName = book_name_field.getText();

                // Set all TextFields to ""
                book_id_field.setText("");
                book_name_field.setText("");
                book_author_field.setText("");
                book_year_field.setText("");

                // Set return book button back to disabled
                return_book_btn.setDisable(true);

                // Update TableView
                updateMemberIssuedBooksTableView();

                // Display success alert
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(returnedBookName + " returned successfully.");
                alert.show();
            });
        }

        if (cancel_return_book_btn != null) {
            cancel_return_book_btn.setOnAction((t) -> {
                // Set all TextFields to ""
                book_id_field.setText("");
                book_name_field.setText("");
                book_author_field.setText("");
                book_year_field.setText("");

                // Set return book button back to disabled
                return_book_btn.setDisable(true);
            });
        }
    }

    public void librarianIssuedBooksController() {
        if (view_issued_books_table != null) {

            // Configure TableView column properties for view_issued_books_table
            user_id_reservation_column.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getUserIDInString()));
            username_reservation_column.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().username));
            full_name_reservation_column.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().fullName));
            book_id_reservation_column.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getBookIDInString()));
            book_name_reservation_column.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().bookName));

            // Set items for view_issued_books_table using issuedBookReservationsList (act as bridge class)
            view_issued_books_table.setItems(FXCollections.observableArrayList(issuedBooks.issuedBookReservationsList));

            // Update TableView
            updateLibrarianIssuedBooksTableView();
        }
    }

    private void updateBooksTableView() {

        // Clear table by default
        view_books_table.getItems().clear();

        // Re-set items using bookStorage.getBooks() list
        view_books_table.setItems(FXCollections.observableArrayList(
                bookStorage.getBooks()
        ));
    }

    private void updateMemberBorrowBooksTableView() {

        // Clear table by default
        view_borrow_books_table.getItems().clear();

        // Re-set items using bookStorage.getBooks() list
        view_borrow_books_table.setItems(FXCollections.observableArrayList(
                bookStorage.getBooks()
        ));
    }

    private void updateLibrarianReservationsTableView() {

        // Clear table by default
        view_reservations_table.getItems().clear();

        // Re-set items using bookReservationsList list
        view_reservations_table.setItems(FXCollections.observableArrayList(reservation.bookReservationsList));
    }

    private void updateMemberReservationsTableView() {

        // Clear table by default
        view_member_reservations_table.getItems().clear();

        // Re-set items using filtered bookReservationsList list
        view_member_reservations_table.setItems(FXCollections.observableArrayList(reservation.getAllMemberReservationsList(loggedUser.getId())));
    }

    private void updateLibrarianIssuedBooksTableView() {

        // Clear table by default
        view_issued_books_table.getItems().clear();

        // Re-set items using filtered bookReservationsList list
        view_issued_books_table.setItems(FXCollections.observableArrayList(issuedBooks.issuedBookReservationsList));
    }

    private void updateMemberIssuedBooksTableView() {
        view_member_issued_books_table.getItems().clear();
        if ((issuedBooks.getAllMemberIssuedBooksList(loggedUser) != null)) {
            view_member_issued_books_table.setItems(FXCollections.observableArrayList(issuedBooks.getAllMemberIssuedBooksList(loggedUser)));
        }
    }
}
