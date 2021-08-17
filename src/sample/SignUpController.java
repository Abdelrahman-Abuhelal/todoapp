package sample;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;

import java.awt.*;

public class SignUpController {
    public SignUpModel signUpModel=new SignUpModel();

    @FXML
    private TextField tf_username;
    private TextField tf_password;
    private RadioButton rb_male;
    private RadioButton rb_female;
    private Button button_sign_up;
    private Button to_sign_in;



}
