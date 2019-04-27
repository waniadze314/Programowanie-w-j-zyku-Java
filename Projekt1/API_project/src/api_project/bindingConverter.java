/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api_project;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.jdesktop.beansbinding.Converter;

/** 
 *
 * @author terg
 */
public class bindingConverter extends Converter<String, String> {
    
    private dataBaseConnection DB = new dataBaseConnection();
    private Statement st = DB.getAcces();
    private ResultSet r;
    private String result;
    @Override
    public String convertForward(String arg0){
        String order = "select nazwa, kurs, data from kursy where kod like '" + arg0 + "';";
        String waluta, kurs, data;
        
        if(arg0.length()==3){
        try{
        r=st.executeQuery(order);
        if(r.next()){
        waluta = r.getString("nazwa");
        kurs = r.getString("kurs");
        data = r.getString("data");        
        result = "Kurs " + waluta +" wynosi w dniu " + data + " : " + kurs;
        }

        }catch(SQLException se){
           return result = se.getMessage();       
        }
        }
        
        return result;
    }
    
    @Override
    public String convertReverse(String arg0){
        return arg0;
    }
            
    
}
