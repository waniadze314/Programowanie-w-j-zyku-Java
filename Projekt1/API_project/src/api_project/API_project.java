/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api_project;

import java.awt.Panel;
import java.awt.event.ActionListener;
import java.sql.Statement;

import java.util.Date;
import java.text.DateFormat;

import javax.xml.parsers.SAXParserFactory;
import javax.xml.parsers.SAXParser;

import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.Bindings;
import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.SwingBindings;


import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPanel;

/**
 *
 * @author terg
 */
public class API_project{   
    public static void main(String args[]){
        Interface gui = new Interface();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        //actualDate = new Date();
        //System.out.println(df.format(actualDate));
        
        dataBaseConnection db = new dataBaseConnection();
        Statement st = db.getAcces();
        try{
        st.executeUpdate("delete from kursy");
        System.out.println("Baza pusta");
        }catch(SQLException se){
            se.getMessage();
        }finally{
            
        }
        
        
    }
    
}
