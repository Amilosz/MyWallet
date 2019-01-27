package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Rejestracja {
    @FXML
    private TextField username;
    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField passwordConfirm;
    @FXML
    private Label label;

    public void rejestruj (ActionEvent event) throws Exception
    {
        if (username.getText().isEmpty() || password.getText().isEmpty() || passwordConfirm.getText().isEmpty()
                || email.getText().isEmpty())
        {

            label.setText("Wpisz wszystkie dane");
        }
        else if (passwordConfirm!=password)
        {
            label.setText("Hasla są niepoprawne");
        }
        else
        {
            Parent root = FXMLLoader.load(getClass().getResource("ekranGlowny.fxml"));
            Scene ekranGlowny= new Scene (root);

            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(ekranGlowny);
            window.show();
        }
    }
    public void powrotDoLogowania (ActionEvent event) throws Exception
    {

        Parent root = FXMLLoader.load(getClass().getResource("logowanie.fxml"));
        Scene login= new Scene (root);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(login);
        window.show();
    }
}
