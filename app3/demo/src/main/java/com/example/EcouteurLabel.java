package com.example;

import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class EcouteurLabel implements EventHandler<MouseEvent> {

    App app;
    public EcouteurLabel(App app)
    {
        this.app = app;
    }

    @Override
    public void handle(MouseEvent e) {
        Label labelTarget = (Label) e.getSource();
        if(e.getEventType() == MouseEvent.MOUSE_ENTERED)
        {
           
            labelTarget.setTextFill(Color.color(1, 0, 0));
        } 
        else{
          
            labelTarget.setTextFill(Color.BLACK);
        }
        
        
    }


    
}
