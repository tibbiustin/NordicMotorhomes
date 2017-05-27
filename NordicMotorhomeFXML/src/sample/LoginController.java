package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import static sample.DatabaseConnection.getConnection;

public class LoginController extends AdminController {

    @FXML
    private javafx.scene.control.TextField usernameField;
    @FXML
    private TextField passwordField ;
    @FXML
    private ChoiceBox choiceboxLogin;
    @FXML
    private javafx.scene.control.Button loginButton;


    private ObservableList<String> choiceLogin = FXCollections.observableArrayList("Admin", "Mechanic");

    @FXML
    public void initialize(){
        choiceboxLogin.setItems(choiceLogin);

    }

    public boolean loginAdmin(String username, String password){
        boolean login = false;
        try{
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("select * from admin");
            while(result.next()){
                if ((result.getString(2).equals(username)) && (result.getString(3).equals(password))){
                    login = true;
                }
                if(login == true){
                    return login;
                }
            }

        }catch(Exception e){System.out.println(e);}
        return login;
    }

    public boolean loginMechanic(String username, String password){
        boolean login = false;
        try{
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("select * from mechanic");
            while(result.next()){
                if ((result.getString(2).equals(username)) && (result.getString(3).equals(password))){
                    login = true;
                }
                if(login == true){
                    return login;
                }
            }

        }catch(Exception e){System.out.println(e);}
        return login;
    }

    @FXML
    public void login(ActionEvent event) throws Exception{
        if((choiceboxLogin.getValue().equals("Admin") && (loginAdmin(usernameField.getText(),passwordField.getText())==true))){
            Node node=(Node) event.getSource();
            Stage stage=(Stage) node.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("windowAdmin.fxml"));
            Scene scene = new Scene(root,1024,720);
            stage.setScene(scene);
            stage.show();

        }
        else
            if ((choiceboxLogin.getValue().equals("Mechanic") && (loginMechanic(usernameField.getText(),passwordField.getText())==true))){
            Node node=(Node) event.getSource();
            Stage stage=(Stage) node.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("windowMechanic.fxml"));
            Scene scene = new Scene(root,1024,720);
            stage.setScene(scene);
            stage.show();
        }

    }

}
