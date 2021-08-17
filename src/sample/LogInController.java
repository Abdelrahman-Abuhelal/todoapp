package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.*;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LogInController implements Initializable {
    @FXML
    private Button button_sign_in;
    private TextField tf_username;
    private TextField tf_password;
    private Button button_sign_up;





    @Override
    public void initialize(URL location, ResourceBundle resources) {
    button_sign_in.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            Utils.logInUser(event,tf_username.getText(),tf_password.getText());
        }


    });
    }

    public void Login(ActionEvent event){
        try {
        if (loginModel.isLogin(tf_username.getText(),tf_password.getText())){
        }
        else{
        }
        }
        catch (Exception e){
        }
    }
}
