package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class Bilans implements Initializable {
    @FXML
    private Label labelTxt;
    public String txt;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setTextArea(String text){
        labelTxt.setText(text);
        this.txt=text;

    }
    public void generuj(ActionEvent event) throws Exception
    {
        Random random = new Random();
        Integer path = random.nextInt(100000);

        String txtName=path.toString()+" bilans.txt";
        File plik = new File("txtName");
        try(BufferedWriter fileWriter = new BufferedWriter(new FileWriter(txtName)))
        {
            fileWriter.write(txt);
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(txtName);
        alert.setHeaderText(null);
        alert.setContentText("plik wygenerowany!");

        alert.showAndWait();
    }


}
