package com.example;

import java.util.ArrayList;
import java.util.List;

import javafx.event.EventHandler;
import javafx.scene.control.Tab;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class EcouteurListView implements EventHandler<MouseEvent> {

    App app;
    public EcouteurListView(App app)
    {
        this.app = app;
    }
    @Override
    public void handle(MouseEvent e) {
      
        if(e.getClickCount() == 2 && e.getButton() == MouseButton.PRIMARY )
        {
            Profile profile = new Profile(this.app.firstNameField.getText(),this.app.lastNameField.getText(), this.app.pseudoField.getText());
            List<Tab> tabs = new ArrayList<>();
            Tab tab1 = new Tab(app.pseudoField.getText(),new MyVbox(profile));
            tabs.add(tab1);
            this.app.tab.getTabs().addAll(tabs);
        }
        
    }
}
