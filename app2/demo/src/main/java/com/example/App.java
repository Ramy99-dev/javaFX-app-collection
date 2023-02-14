package com.example;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    Label lb_title ;
    VBox vb;
    TitledPane tp1 , tp2;
    CheckBox cb1;
    HBox hb1 , hbInfo , hbInfoNaiss , hbgender , hbcomp;
    Button btnSend , btnCancel;
    GridPane gpInfo , gpComp;
    ComboBox day , month , year , formation , competence1 , competence2 , competence3;
    Image img;
    String IMG_URL = "https://cdn-icons-png.flaticon.com/512/149/149071.png";
    RadioButton r1 , r2;
    ToggleGroup tg ;
    TextArea ta;

    @Override
    public void start(Stage stage) throws IOException {
       vb = new VBox(3);
       vb.setAlignment(Pos.TOP_CENTER);

       lb_title = new Label("Curriculum vitæ");
       lb_title.setFont(Font.font("arial",32));
       lb_title.setTextFill(Paint.valueOf("Blue"));

       vb.getChildren().add(lb_title);

       hbInfo = new HBox(89);
       
       hbInfo.setAlignment(Pos.CENTER);
    
       
       tp1 = new TitledPane("Information Personnelle",hbInfo);

       gpInfo = new GridPane();
       gpInfo.setVgap(10); 
       gpInfo.setHgap(5); 
       gpInfo.add(new Label("Nom & Prénom"),0,0);
       gpInfo.add(new TextField(),1,0);
       gpInfo.add(new Label("e-mail"),0,1);
       gpInfo.add(new TextField(),1,1);
       gpInfo.add(new Label("Tél"),0,2);
       gpInfo.add(new TextField(),1,2);
       gpInfo.add(new Label("Date de naissance"),0,3);

       hbInfoNaiss = new HBox(5);
       day = new ComboBox<>();
       year = new ComboBox<>();
       month = new ComboBox<>();
       
       Collection<Integer> arr = new  ArrayList<Integer>();
       for(int i = 1 ; i < 32 ; i++ )
       {
         arr.add(i);
       }
       day.getItems().addAll(arr);

       arr = new  ArrayList<Integer>();
       for(int i = 1 ; i < 13 ; i++ )
       {
         arr.add(i);
       }
       month.getItems().addAll(arr);

       arr = new  ArrayList<Integer>();
       int years = Year.now().getValue();
       for(int i = years-20 ; i < years ; i++ )
       {
         arr.add(i);
       }
       year.getItems().addAll(arr);
    

       hbInfoNaiss.getChildren().add(day);
       hbInfoNaiss.getChildren().add(month);
       hbInfoNaiss.getChildren().add(year);

       

       gpInfo.add(hbInfoNaiss , 1,3);
       gpInfo.add(new Label("Sexe"),0,4);

       hbgender = new HBox(20);
       
       r1 = new RadioButton("Femme");
       r2 = new RadioButton("Homme");
       tg = new ToggleGroup();
       r1.setToggleGroup(tg);
       r2.setToggleGroup(tg);
       hbgender.getChildren().add(r1);
       hbgender.getChildren().add(r2);

      

       gpInfo.add(hbgender,1,4);

       gpInfo.add(new Label("Formation"),0,5);
       formation = new ComboBox<>();
       ArrayList<String> form =  new ArrayList<String>();
       form.add("Ingénieure");
       form.add("Technicien");

       formation.getItems().addAll(form);

       gpInfo.add(formation,1,5);


       
       img = new Image(IMG_URL,100,100,false,false);
       ImageView imgv = new ImageView(img);

      
      
       hbInfo.getChildren().add(gpInfo);
       hbInfo.getChildren().add(imgv);
       
       

       tp2 = new TitledPane();
       tp2.setText("Compétence technique");
       
    
       hbcomp = new HBox(20);
       gpComp = new GridPane();
       gpComp.setVgap(10); //vertical gap in pixels
       competence1 = new ComboBox<>();
       competence2 = new ComboBox<>();
       competence3 = new ComboBox<>();
       ScrollPane sp = new ScrollPane();
       ta = new TextArea();
       sp.setContent(ta);
       


       hbcomp.setAlignment(Pos.CENTER);

       ArrayList<String> competences = new ArrayList<>();
      
       competences.add("Confirmée");
       competences.add("Expert");
       competences.add("Débutant"); 

       competence1.getItems().addAll(competences);
       competence2.getItems().addAll(competences);
       competence3.getItems().addAll(competences);



       gpComp.add(new Label("JAVA"),0,0);
       gpComp.add(competence1,1,0);
       gpComp.add(new Label("Python"),0,1);
       gpComp.add(competence2,1,1);
       gpComp.add(new Label("Machine Learning"),0,2);
       gpComp.add(competence3,1,2);
       hbcomp.getChildren().add(gpComp);
      
       hbcomp.getChildren().add(sp);



       tp2.setContent(hbcomp);

       vb.getChildren().add(tp1);
       vb.getChildren().add(tp2);

       cb1 = new CheckBox();
       cb1.setText("Enregistrer une version PDF de la candidature avant d'envoyer");

       vb.getChildren().add(cb1);

       btnSend = new Button();
       btnCancel = new Button();
       btnSend.setText("Envoyer");
       btnCancel.setText("Annuler");

       hb1 = new HBox(3);

       hb1.getChildren().add(btnSend);
       hb1.getChildren().add(btnCancel);

       hb1.setAlignment(Pos.TOP_CENTER);

       vb.getChildren().add(hb1);

      
       
       

       Scene sc = new Scene(vb,700,600);


       stage.setTitle("Candidature PFE");
       stage.setScene(sc);
       stage.setResizable(false);

       stage.show();


       btnCancel.setOnAction(event -> handleCancel(event));
       btnSend.setOnAction(event -> handleSend(event));




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

    private void handleCancel(ActionEvent event) {
        Platform.exit();
        
    }
    private void handleSend(ActionEvent event) {
        if(cb1.isSelected())
        {
          File f = new File("test.html");
          try {
            FileWriter fw = new FileWriter(f,false);
            fw.write("<html>Rami</html>");
            fw.close();
            Runtime.getRuntime().exec(new String[] {"open", "test.html"});
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        }
        else 
        {
            Platform.exit();
        }
        
    }

}