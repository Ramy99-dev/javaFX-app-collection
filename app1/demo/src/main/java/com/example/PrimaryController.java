package com.example;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PrimaryController {

    @FXML
    private Text date;


    @FXML
    private TextArea email;

    @FXML
    private TextArea password;

    @FXML
    private Text error;


    
    @FXML
    public void initialize() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        date.setText(dtf.format(now));
    }

    

    @FXML
    void close(ActionEvent event) {
        Platform.exit();

    }

    @FXML
    void validate(ActionEvent event) {
        if(password.getText().length() < 4)
        {
            error.setText("Password minimun length 4");
        }
        if(!(email.getText().contains("@")))
        {
            error.setText("Invalid email");
        }
        else
        {
            error.setText("");
            Stage stage = new Stage();
            stage.show();
            
        }
       
    }
}
