package ma.ensao.gi3.gestCom;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MysqlConnector {

      //private String DB_NAME = "CeHGq0HbZN";
      //private String USERNAME = "CeHGq0HbZN";
      //private String PASSWORD = "F0j2lnI79p";
      public Connection con ;
	  //private  String HOST = "jdbc:mysql://remotemysql.com/"+DB_NAME;


     public Connection getConnection () throws Exception{
         String DB_NAME = "CeHGq0HbZN";
         String USERNAME = "CeHGq0HbZN";
         String PASSWORD = "F0j2lnI79p";
         String HOST = "jdbc:mysql://remotemysql.com/"+DB_NAME;
           Class.forName("com.mysql.cj.jdbc.Driver");

          con = DriverManager.getConnection(HOST,USERNAME,PASSWORD);

          return  con;
      }


	//private static Connection con;



}