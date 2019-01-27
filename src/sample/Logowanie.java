package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.stage.Stage;



public class Logowanie {
    @FXML
    private Label status;
    @FXML
    private TextField username;
    @FXML
    private TextField password;

    public void login(ActionEvent event) throws Exception
    {
        if (username.getText().equals("user") && password.getText().equals("pass"))
        {
            Parent root = FXMLLoader.load(getClass().getResource("ekranGlowny.fxml"));
            Scene ekranGlownyScene= new Scene (root);

            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(ekranGlownyScene);
            window.show();
        }
        else
        {
            status.setText("Login lub haslo jest nieprawidlowe");
        }
    }
    public void rejestracja (ActionEvent event) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("rejestracja.fxml"));
        Scene rejestracja= new Scene (root);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(rejestracja);
        window.show();
    }
}
