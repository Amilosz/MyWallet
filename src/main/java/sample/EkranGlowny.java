package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;




public class EkranGlowny implements Initializable {
    ObservableList<String> operacje = FXCollections.observableArrayList("dochod", "wydatek");
    @FXML
    private Label userHellow;
    @FXML
    private ChoiceBox typOperacji;
    @FXML
    private TextField enterText;
    @FXML
    private Label log;
    @FXML
    private Label currentData;
    @FXML
    private DatePicker data;
    @FXML
    private Label opis;
    @FXML
    private TextField opisInput;
    @FXML
    private Label stanKonta;
    @FXML
    private BarChart<String, Number> wykres1;

    LoginModel loginModel = new LoginModel();
    private String user;

    public void setUsername(String user){
        this.user=user;
        userHellow.setText("Witaj " +user);
        stanKonta.setText((loginModel.pobierzStanKonta(user)).toString());
        XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
        List<String> dateChar = loginModel.getDate(user);
        List<String> moneyChar = loginModel.getMoney(user);
        if (dateChar.size()>0){
            for (int i=0;  i < dateChar.size() ; i++)
            {
                Integer intObj = new Integer(moneyChar.get(i));
                Number numObj = (Number)intObj;

                series.getData().add(new XYChart.Data<String, Number>(dateChar.get(i), numObj));
            }
            wykres1.getData().add(series);
        }


    }
    public void initialize(URL location, ResourceBundle resources)
    {
        typOperacji.setValue("typ");
        typOperacji.setTooltip(new Tooltip("Wybierz typ"));
        typOperacji.setItems(operacje);
        stanKonta.setText((loginModel.pobierzStanKonta(user)).toString());
    }
    public void dodaj(ActionEvent event)  throws Exception
    {
        LocalDate ld = data.getValue();
        String data = ld.toString();
        currentData.setText(data);
        String kwota=enterText.getText().toString();
        XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();

        opis.setText(opisInput.getText().toString());
        if (typOperacji.getValue().toString().equals("dochod"))
        {
            log.setTextFill(Color.web("#008000"));
            log.setText(kwota);
            Integer intObj = new Integer(kwota);
            Number numObj = (Number)intObj;
            series.getData().add(new XYChart.Data<String, Number>(data, numObj));
            wykres1.getData().add(series);
        }
        if (typOperacji.getValue().toString().equals("wydatek"))
        {
            kwota ="-" + kwota;
            log.setTextFill(Color.web("ff0000"));
            log.setText(kwota);
            Integer intObj = new Integer(kwota);
            Number numObj = (Number)intObj;
            series.getData().add(new XYChart.Data<String, Number>(data, numObj));
            wykres1.getData().add(series);
        }
        loginModel.addToWallet(user,kwota, opisInput.getText().toString(), data );
        stanKonta.setText((loginModel.pobierzStanKonta(user)).toString());
    }
    public void generujPlik(ActionEvent event) throws Exception
    {
        stanKonta.setText((loginModel.pobierzStanKonta(user)).toString());
        String txt = loginModel.pobierzBilans(user);
        //System.out.println(txt);
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/bilans.fxml"));
            AnchorPane bilans = loader.load();

            Bilans controller = loader.<Bilans>getController();
            controller.setTextArea(txt);

            Stage window = new Stage();
            window.setScene(new Scene(bilans));
            window.show();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
