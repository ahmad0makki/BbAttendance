package bbattendance;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

public class Controller implements Initializable {    
    @FXML
    private TextField sectionTextField;
    @FXML
    private TextField sessionTextField;
    @FXML
    private TextField minMinutesTextField;
    
    @FXML
    private void importSession(ActionEvent event) {
        setText(sessionTextField, Model.chooseFilePath("Open Session File"));
    }
    @FXML
    private void importSection(ActionEvent event) {
        setText(sectionTextField, Model.chooseFilePath("Open Section File"));
    }
    
    private static void setText(TextField tf, String text) {
        if (text != null) {
            tf.setText(text);
        }
    }
    
    @FXML
    private void go() {
        try {
            Model.go(
                sectionTextField.getText(),
                sessionTextField.getText(),
                Double.parseDouble(minMinutesTextField.getText())
            );
        } catch (Exception e) {
            minMinutesTextField.deleteText(0, minMinutesTextField.getText().length());
        }
    }
    
    public static void fail(String message) {
        System.out.println("FAIL! " + message);
    }
    
    public static void success() {
        System.out.println("SUCCESS!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
}
