package com.example;

import java.io.IOException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.converter.DefaultStringConverter;



/**
 * JavaFX App
 */
public class App extends Application {


    BorderPane bp ; 
    HBox topHbox;
    TextField firstNameField , lastNameField , pseudoField;
    Button submitBtn ;
    SplitPane sp;
    ListView<String> usersList;
    TabPane tab;
    Label lastNameLabel , pseudoLabel ,  firstNameLabel ;
    ObservableList<String> users = FXCollections.observableArrayList();
    Label helpLabel ;



    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        topHbox = new HBox(20);
        topHbox.setAlignment(Pos.CENTER);
        topHbox.setPadding( new Insets(20, 10, 20, 10));
        firstNameLabel = new Label("Nom");
        firstNameField = new TextField();
        firstNameField.setPromptText("Tapper votre nom");

        lastNameLabel =  new Label("Prénom");
        lastNameField = new TextField();
        lastNameField.setPromptText("Tapper votre prenom");

        pseudoLabel = new Label("Pseudo");
        pseudoField = new TextField();
        pseudoField.setPromptText("Tapper votre pseudo");

        submitBtn = new Button("Valider");


        topHbox.getChildren().add(firstNameLabel);
        topHbox.getChildren().add(firstNameField);

        topHbox.getChildren().add(lastNameLabel);
        topHbox.getChildren().add(lastNameField);

        topHbox.getChildren().add(pseudoLabel);
        topHbox.getChildren().add(pseudoField);

        topHbox.getChildren().add(submitBtn);



        usersList = new ListView<>(users);
        usersList.setEditable(true);
        ContextMenu contextMenu = new ContextMenu();
        MenuItem deleteItem = new MenuItem();
        MenuItem deleteAllItems = new MenuItem();
        MenuItem editItem = new MenuItem();

        deleteItem.setText("Supprimer");
        deleteAllItems.setText("Supprimer tous");
        editItem.setText("Renommer");

        deleteItem.setOnAction(event -> {
           usersList.getItems().remove(usersList.getSelectionModel().getSelectedItem());
            
        });

        deleteAllItems.setOnAction(event -> {
            users.removeAll(users);
             
        });

        editItem.setOnAction(event -> {
            StringConverter<String> converter = new DefaultStringConverter();
            usersList.setCellFactory(param -> new TextFieldListCell<>(converter));
             
        });

        contextMenu.getItems().add(deleteItem);
        contextMenu.getItems().add(deleteAllItems);
        contextMenu.getItems().add(editItem);
        usersList.setContextMenu(contextMenu);
        tab = new TabPane();
        


        sp = new SplitPane();
        sp.getItems().add(usersList);
        sp.getItems().add(tab);


        HBox bottomHBox = new HBox();
        helpLabel = new Label("Help");
        bottomHBox.getChildren().add(helpLabel);

    
        bp = new BorderPane();
        bp.setTop(topHbox);
        bp.setCenter(sp);
        bp.setBottom(bottomHBox);
        scene = new Scene(bp);
        stage.setScene(scene);
        stage.show();

        //Event section :
        lastNameLabel.setOnMouseEntered(new EcouteurLabel(this));
        firstNameLabel.setOnMouseEntered(new EcouteurLabel(this));
        pseudoLabel.setOnMouseEntered(new EcouteurLabel(this));

        lastNameLabel.setOnMouseExited(new EcouteurLabel(this));
        firstNameLabel.setOnMouseExited(new EcouteurLabel(this));
        pseudoLabel.setOnMouseExited(new EcouteurLabel(this));


        lastNameField.setOnMouseClicked(new EcouteurTextField(this));
        firstNameField.setOnMouseClicked(new EcouteurTextField(this));
        pseudoField.setOnMouseClicked(new EcouteurTextField(this));

        usersList.setOnMouseClicked(new EcouteurListView(this));


        
        submitBtn.setOnAction(event -> handleSubmit(event));
        //usersList.setOnMouseClicked(event -> handleSelectItem(event));
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

    private void handleSubmit(ActionEvent event) {
        
        if(users.contains(pseudoField.getText()))
        {

            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Erreur d'enregistrement ");
            alert.setHeaderText(null);
            alert.setContentText("Pseudo déja enregistrer!");
    
            alert.showAndWait();
        }
        else
        {
            users.add(pseudoField.getText());
        }

    }


    private void handleSelectItem(MouseEvent event)
    {
        usersList.getSelectionModel().getSelectedItem();
    }

}