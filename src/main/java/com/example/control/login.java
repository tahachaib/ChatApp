package com.example.control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

public class login {
    public Button login_btn;

    public login(){
    }

    @FXML
    private Button button;

    @FXML
    private TextField username;
    @FXML
    private PasswordField password;

    public void userLogin(ActionEvent event) throws IOException {
        checkLogin();
    }

    private void checkLogin() throws IOException{
        HelloApplication helloApplication = new HelloApplication();
        if (username.getText().toString().equals("taha") && password.getText().toString().equals("123")){

            helloApplication.changeScene("s2.fxml");
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR) ;
            alert.setTitle("authentification error ");
            alert.setHeaderText("username or password are not validated");
            alert.setContentText("you can retry !");
            alert.show();

        }
    }
}
