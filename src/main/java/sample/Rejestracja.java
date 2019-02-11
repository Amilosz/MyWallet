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
    public LoginModel loginModel = new LoginModel();
    public void zarejestruj (ActionEvent event) throws Exception
    {
        if (username.getText().isEmpty() || password.getText().isEmpty() || passwordConfirm.getText().isEmpty()
                || email.getText().isEmpty())
        {

            label.setText("Wpisz wszystkie dane");
        }
        else if (!passwordConfirm.getText().equals(password.getText()))
        {
            label.setText("Hasla są niepoprawne");
        }
        else
        {
            loginModel.register(username.getText(), password.getText(), email.getText());
            Parent root = FXMLLoader.load(getClass().getResource("/logowanie.fxml"));
            Scene logowanie= new Scene (root);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(logowanie);
            window.show();
        }
    }
    public void powrotDoLogowania (ActionEvent event) throws Exception
    {

        Parent root = FXMLLoader.load(getClass().getResource("/logowanie.fxml"));
        Scene login= new Scene (root);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(login);
        window.show();
    }
}
