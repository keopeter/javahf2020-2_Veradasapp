package javaFX;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class Controller {


    @FXML
    private Button exitBtn;

    @FXML
    private Button openBtn ;

    public void initialize() {
        // initialization here, if needed...
    }

    @FXML
    private void handleButtonClick(ActionEvent event) {
        if (event.getSource() == exitBtn) {
            exitBtn.getScene().getWindow().hide();
        } else if (event.getSource() == openBtn) {
            // do open action...
        }



    }
}
