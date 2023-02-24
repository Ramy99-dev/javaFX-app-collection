package com.example;

import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class EcouteurTextField  implements EventHandler<MouseEvent>{


    App app;
    public EcouteurTextField(App app)
    {
        this.app = app;
    }

    @Override
    public void handle(MouseEvent e) {
        TextField targetTextField= (TextField) e.getSource();

        if(e.getEventType() == MouseEvent.MOUSE_CLICKED)
        {
            this.app.helpLabel.setText("Help :" + targetTextField.getPromptText());
        }
        else{
            this.app.helpLabel.setText("Help :");
        }
    }
    
}
