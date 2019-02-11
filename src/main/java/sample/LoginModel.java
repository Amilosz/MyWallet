package sample;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class LoginModel {
    Connection connection;
    Statement statement;
    ResultSet rs;
    public LoginModel(){
        try{
            connection = ConnectionClass.Conector();
            statement = connection.createStatement();
        }catch (SQLException e){
            e.printStackTrace();
        }

        if (connection==null)
        {
            System.exit(1);
        }
    }
    public boolean auth(String login, String password)
    {
        String query = "select * from wallet where username = \'" + login + "\'";
        String loginDB="";
        String passwordDB="";
        try{
            rs = statement.executeQuery(query);

            if(rs.next()){
                loginDB = rs.getString("username");
                passwordDB = rs.getString("password");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        if (passwordDB.equals(password) && loginDB.equals(login))
            return true;
        else
            return false;
    }
    public void register(String username, String password, String email)
    {
        String query= "INSERT INTO WALLET (USERNAME, PASSWORD, EMAIL) VALUES (\'" +username+ "\',\'" +password+"\', \'"+email+"\')" ;
        try{
            statement.execute(query);
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
    public String pobierzBilans(String username)
    {
        String query = "select * from bilans where username = \'" + username + "\'";
        String linia="";
        try{
            rs = statement.executeQuery(query);

            while(rs.next()){
                linia = linia + rs.getString("kwota") +  ";";
                linia = linia + rs.getString("opis")  +  ";";
                linia = linia + rs.getString("data")  +  ";";
                linia = linia + "\n";
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return linia;
    }
    public List<String> getDate(String username)
    {
        List<String> listaDate= new LinkedList<String>();
        String query = "select * from bilans where username = \'" + username + "\'";
        try{
            rs = statement.executeQuery(query);

            while(rs.next()){
               listaDate.add(rs.getString("data"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return listaDate;
    }
    public List<String> getMoney(String username)
    {
        List<String> listaMoney= new LinkedList<String>();
        String query = "select * from bilans where username = \'" + username + "\'";
        try{
            rs = statement.executeQuery(query);

            while(rs.next()){
                listaMoney.add(rs.getString("kwota"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return listaMoney;
    }
    public Integer pobierzStanKonta(String username)
    {
        String query = "select * from bilans where username = \'" + username + "\'";
        Integer bilans=0;
        try{
            rs = statement.executeQuery(query);

            while(rs.next()){
                bilans = bilans + Integer.parseInt(rs.getString("kwota"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return bilans;
    }
    public void addToWallet(String username, String kwota, String opis, String data)
    {
        String query = "INSERT INTO BILANS (USERNAME, KWOTA, OPIS, DATA) VALUES " +
                "(\'" + username + "\'," +
                " \'" + kwota + "\'," +
                " \'" + opis + "\'," +
                " \'" + data    + "\')" ;
        try{
            statement.execute(query);
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }


    }
    public boolean isDbConnected()
    {
        try
        {
            return !connection.isClosed();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }

}
