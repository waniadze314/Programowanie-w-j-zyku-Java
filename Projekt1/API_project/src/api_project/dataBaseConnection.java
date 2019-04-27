/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api_project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author terg
 */
public class dataBaseConnection {
private Connection conn;
private Statement stat;
private ResultSet ress;
    public dataBaseConnection() {
         try{        
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/kursy_walut?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","12345");
        stat = conn.createStatement();
        System.out.println("Nawiązano połączenie z bazą danych");
     }catch(Exception e){      
         System.out.println("Błąd połączenia z bazą danych");
         System.out.println(e.getMessage());
     }
        
    }
public Statement getAcces(){
    return stat;
}

public ResultSet getResult(){
    return ress;
}
    
    
}
