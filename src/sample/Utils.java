package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.sql.*;

public class Utils {
    public static void changeScene(ActionEvent event, String fxmlFile, String title, String username) {
        Parent root = null;
        if (username != null) {
            try {
                FXMLLoader loader = new FXMLLoader(Utils.class.getResource(fxmlFile));
                root = loader.load();
                TodoHomeController todoHomeController = loader.getController();
                todoHomeController.setInformation(username);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                root = FXMLLoader.load(Utils.class.getResource(fxmlFile));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Stage stage= (Stage)((Node) event.getSource()).getScene().getWindow();
        stage.setTitle(title);
        stage.setScene(new Scene(root));
        stage.show();
    }
    public static void signUpUser(ActionEvent event,String username,String pass,String Gender){
        Connection connection=null;
        PreparedStatement psInsert=null;
        PreparedStatement psCheckUserExists=null;
        ResultSet resultSet=null;
        try {
            connection= DriverManager.getConnection("jdbc:sqlite:dataBase.db");
            psCheckUserExists =connection.prepareStatement("SELECT * FROM users WHERE username=?");
            psCheckUserExists.setString(1,username);
            resultSet =psCheckUserExists.executeQuery();
            if (resultSet.isBeforeFirst()){
                System.out.println("User already exists");
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setContentText("You can't use this username");
                alert.show();
            }else
            {
                psInsert =connection.prepareStatement("INSERT INTO UsersInfo (Username,Password ,Gender VALUES (?, ? ,?)");
                psInsert.setString(1,username);
                psInsert.setString(2,pass);
                psInsert.setString(3,Gender);
                psInsert.executeUpdate();

                changeScene(event,"mainPage.fxml","Welcome",username);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            if (resultSet!=null){
                try {
                    resultSet.close();
                }catch (SQLException e){
                e.printStackTrace();
            }
            }
            if (psCheckUserExists!=null){
                try {
                    psCheckUserExists.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if (psInsert!=null){
                try {
                    psInsert.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if (connection!=null){
                try {
                    connection.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }
    public static void logInUser(ActionEvent event,String username,String pass) throws SQLException {
        Connection connection=null;
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        try {
            connection=DriverManager.getConnection("jdbc:sqlite:dataBase.db");
            preparedStatement=connection.prepareStatement("SELEct Password,Gender FROM UsersInfo WHERE Username = ?");
            preparedStatement.setString(1,username);
            resultSet=preparedStatement.executeQuery();


        if (resultSet.isBeforeFirst()){
            System.out.println("User not found in database");
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Provided are incorrect");
            alert.show();
        }else {
            while (resultSet.next()){
                String retrievedPassword=resultSet.getString("Password");
                String retrievedGender=resultSet.getString("Gender");
                if (retrievedPassword.equals(pass)){
                    changeScene(event,"mainPage.fxml","Welcome",username);
                }
                else{
                    System.out.println("Password doesn't match");
                    Alert alert=new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("The provided isn't correct");
                    alert.show();
                }
            }

        }
    }catch (SQLException e){
            e.printStackTrace();
        }finally {
            if (resultSet!=null){
                try {
                    resultSet.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
            if (preparedStatement!=null){
                try {
                    preparedStatement.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }

            if (connection!=null){
                try {
                    connection.close();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }

        }
        }
}
