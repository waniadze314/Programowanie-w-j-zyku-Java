/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api_project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.Bindings;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 *
 * @author terg
 */
public class Interface {
    private JFrame window;
    private JPanel panel;
    private JTextField currency_input;
    private JTextField actual_currency_value;
    private JButton refreshBtn, cleanBtn;
    private String actualDate;
    private DateFormat df;
    
    Interface(){
        df = new SimpleDateFormat("yyyy-MM-dd");
        actualDate = df.format(new Date());
        System.out.println(actualDate);
        window = new JFrame("Kursy walut");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(640, 480);
        panel = new JPanel();
        refreshBtn = new JButton("Zaktualizuj bazę danych");
        refreshBtn.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(java.awt.event.ActionEvent evt){
              

        try{
            String nbpAdress = "https://api.nbp.pl/api/exchangerates/tables/a/?format=xml";
            URL url = new URL(nbpAdress);
            URLConnection connection = url.openConnection();
            InputStream dataStream=connection.getInputStream();
            //status = "Połączono z serwerem";
            SAXParserFactory factory = SAXParserFactory.newInstance(); //fabryka obiektow
            SAXParser saxParser = factory.newSAXParser(); //obiekt parser
            NewHandler handler = new NewHandler(); //obiekt handlera
            saxParser.parse(dataStream, handler);
        }catch(Exception e){
            System.out.println("Błąd połączenia z serwerem");
            //status = "Błąd połączenia z serwerem";
            e.printStackTrace();
        }            

    }
           }
        );
      
        cleanBtn = new JButton("Wyczyść");
        cleanBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currency_input.setText("");
                actual_currency_value.setText("");
            }
        });
        currency_input = new JTextField(5);
        actual_currency_value = new JTextField(50);
        panel.add(refreshBtn);
        panel.add(currency_input);
        panel.add(actual_currency_value);        
        panel.add(cleanBtn);
        window.getContentPane().add(panel);
        window.setVisible(true);  
        window.pack();
        initDatabindings();
    }
    
   

    
     private void initDatabindings(){
   
      BeanProperty<JTextField, String> fwdProperty = BeanProperty.create("text");
      BeanProperty<JTextField, String> bwdProperty = BeanProperty.create("text");
      AutoBinding<JTextField, String, JTextField, String> autoBinding = 
            Bindings.createAutoBinding(AutoBinding.UpdateStrategy.READ, currency_input, fwdProperty, actual_currency_value, 
            bwdProperty,"binding");
      autoBinding.setConverter(new bindingConverter());
      autoBinding.bind();
 
    }
    
}
