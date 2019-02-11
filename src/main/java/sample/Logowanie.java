package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class Logowanie implements Initializable  {
    @FXML
    private Label status;
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private Label isConnected;
    public LoginModel loginModel = new LoginModel();
    public void initialize(URL location, ResourceBundle resourceBundle)
    {
        if(loginModel.isDbConnected())
        {
            isConnected.setText("Połączono");
        }
        else {
            isConnected.setText("Brak polączenia z bazą danych");
        }
    }


    public void login(ActionEvent event) throws Exception
    {
        if (loginModel.auth(username.getText(),password.getText()))
        {
           // Parent root = FXMLLoader.load(getClass().getResource("/ekranGlowny.fxml"));
            //Scene ekranGlownyScene= new Scene (root);


            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/ekranGlowny.fxml"));
                AnchorPane ekranGlowny = loader.load();

                EkranGlowny controller = loader.<EkranGlowny>getController();
                controller.setUsername(username.getText());

                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
                window.setScene(new Scene(ekranGlowny));
                window.show();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }

        }
        else
        {
            status.setText("Login lub haslo jest nieprawidlowe");
        }
    }
    public void rejestracja (ActionEvent event) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("/rejestracja.fxml"));
        Scene rejestracja= new Scene (root);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(rejestracja);
        window.show();
    }
}
