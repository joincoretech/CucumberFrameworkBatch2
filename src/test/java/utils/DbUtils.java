package utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DbUtils {


    public static Connection getConnection(){
        ConfigReader.readProperties(Constants.CONFIG_FILE_PATH);
        Connection connection=null;

        try {
            connection = DriverManager.getConnection(ConfigReader.getPropertiesValue("dbUrl"),
                    ConfigReader.getPropertiesValue("dbUserName"),
                    ConfigReader.getPropertiesValue("dbPassword"));

        }catch (SQLException e){
            e.printStackTrace();
        }
        return connection;
    }

  public  static ResultSet getResultSet(String query){
     ResultSet resultSet=null;

     try{
         resultSet=getConnection().createStatement().executeQuery(query);

     }catch (SQLException e){
         e.printStackTrace();
     }
    return resultSet;

  }

  public static List<Map<String , String>> getTableDataAsListOFMap(String query){
        ResultSet resultSet=getResultSet(query);
        ResultSetMetaData resultSetMetaData;

        List<Map<String, String>>  table= new ArrayList<>();

        try{
            resultSetMetaData=resultSet.getMetaData();
            while (resultSet.next()){
                Map<String, String> row = new HashMap<>();
                for (int i=1; i<=resultSetMetaData.getColumnCount(); i++){
                    row.put(resultSetMetaData.getColumnName(i), resultSet.getString(i) );
                }
               table.add(row);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                resultSet.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
      return table;
  }


}
