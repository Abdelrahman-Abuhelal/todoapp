package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LogInController implements Initializable {

    @FXML
    private Button button_sign_in;
    @FXML
    private TextField tf_username;
    @FXML
    private TextField tf_password;
    @FXML
    private Button to_sign_up;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    button_sign_in.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            try {
                Utils.logInUser(event,tf_username.getText(),tf_password.getText());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    });

        to_sign_up.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Utils.changeScene(event, "sample/Sign-up.fxml","SignUp",null);
            }
        });

    }



}
