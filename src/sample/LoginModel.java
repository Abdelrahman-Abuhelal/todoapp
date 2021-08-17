package sample;

import java.sql.*;

public class LoginModel {
    Connection conection;
    public LoginModel(){
        conection= DB.Connector();
        if (conection == null)
            System.out.println("Connection isn't successful");
            System.exit(1);
    }
        public boolean isDBconnected(){
            try {
                return !conection.isClosed();
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }

        }
        public boolean isLogin(String username,String pass) throws SQLException {
        PreparedStatement preparedStatement=null;
        ResultSet resultSet=null;
        String query="select * from UsersInfo where Username = ? and Password = ?";
        try{
        preparedStatement =conection.prepareStatement(query);
        preparedStatement.setString(1,username);
        preparedStatement.setString(2,pass);
        resultSet=preparedStatement.executeQuery();
        if (resultSet.next()){
            return true;
        }
        else{
            return false;
        }
        }catch (Exception e){
        return false;
        }finally {
            preparedStatement.close();
            resultSet.close();
        }
        }

}
