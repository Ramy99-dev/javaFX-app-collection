package com.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Screen;

public class MyVbox extends VBox{
    ComboBox<String> difficulties  ;
    HBox hbCateg , hboptions ;
    Profile profile;

    public MyVbox(Profile profile)
    {
        this.profile = profile;
        Label lb = new Label("Bienvenue " +profile.pseudo);
        lb.setTextFill(Color.WHITE);

        HBox hb = new HBox(lb);
        hb.setPadding(new Insets(20,20,20,20));
        hb.setAlignment(Pos.TOP_CENTER);
        hb.setBackground(new Background(new BackgroundFill(Color.GREEN,CornerRadii.EMPTY, Insets.EMPTY)));

        TitledPane tp1 = new TitledPane();
        tp1.setText("Difficulté");
        TitledPane tp2 = new TitledPane();
        tp2.setText("Options");
        VBox vb1 = new VBox(10);

        difficulties  = new ComboBox<>();
        ObservableList<String> diffList =  FXCollections.observableArrayList();
        diffList.add("Débutant");
        diffList.add("Intermédiaire");
        diffList.add("Professionnelle");
        difficulties.setItems(diffList);

        hbCateg = new HBox();
        hbCateg.getChildren().add(new Label("Choisir la/les categorie(s) : "));
        hbCateg.setAlignment(Pos.TOP_CENTER);
        for (int i = 1; i < 8; i++) {
  

            CheckBox c = new CheckBox(i+"");
            hbCateg.getChildren().add(c);
            
        }

        vb1.getChildren().add(difficulties);
        vb1.getChildren().add(hbCateg);
        vb1.setAlignment(Pos.TOP_CENTER);

        tp1.setContent(vb1);


        List<String> options = new ArrayList<>();
        options.add("Emettre son");
        options.add("Afficher score");
        options.add("Plein ecran");
        options.add("Ombre");

        hboptions = new HBox();
        for (int i = 0; i < options.size(); i++) {
  

            CheckBox c = new CheckBox(options.get(i));
            hboptions.getChildren().add(c);
            
        }
        tp2.setContent(hboptions);

        Button submitBtn = new Button("Valider");
        VBox vbButton = new VBox(submitBtn);
        vbButton.setAlignment(Pos.CENTER);
        vbButton.setMargin(vbButton, new Insets(100, 0, 0, 0));

        
        
        


        
  

        this.getChildren().add(hb);
        this.getChildren().add(tp1);
        this.getChildren().add(tp2);
        this.getChildren().add(vbButton);

        submitBtn.setOnAction(event -> handleSubmit(event));
    }


    private void handleSubmit(ActionEvent event) 
    {
        String categories = "";
        String options = "";
        for (int i = 0; i < hbCateg.getChildren().size(); i++) {

            if(!(hbCateg.getChildren().get(i) instanceof Label))
            {
                if(((CheckBox)(hbCateg.getChildren().get(i))).isSelected())
                {
                    System.out.println("Clicked");
                    categories += "<li>"+"Categorie "+i+"</li>";
                }
            }
        }


        for (int i = 0; i < hboptions.getChildren().size(); i++) {

            if(!(hboptions.getChildren().get(i) instanceof Label))
            {
                CheckBox selectedCheckBox = ((CheckBox)(hboptions.getChildren().get(i)));
                
                    
                    options += "<li>"+selectedCheckBox.getText() +":"+selectedCheckBox.isSelected() +"</li>";
                
            }
        }
        File f = new File("config.html");
        try {
        FileWriter fw = new FileWriter(f,false);
        fw.write("<html>"+
                "<fieldset>"+
                "<legend>"+"Information personnelle"+  "</legend>"+
                "<ul>"+
                "<li>"+"Nom : " +profile.nom  +"</li>"+
                "<li>"+"Prenom : " +profile.prenom  +"</li>"+
                "<li>"+"Pseudo : " +profile.pseudo  +"</li>"+
                "</ul>"+
                "</fieldset>"+
                "<fieldset>"+
                "<legend>"+"Configuration"+  "</legend>"+
                "<fieldset>"+
                "<legend>"+"Difficulté :" +difficulties.getValue()+"</legend>"+
                "<ul>"+
                categories+
                "</ul>"+
                "</fieldset>"+
                "<fieldset>"+
                "<legend>"+"Options"+"</legend>"+
                "<ul>"+
                options+
                "</ul>"+
                "</fieldset>"+
                "</fieldset>"+
                "</html>");
        fw.close();
        Runtime.getRuntime().exec(new String[] {"open", "test.html"});
    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }

        
        
    }
    
}
