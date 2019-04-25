/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api_project;

import javax.xml.parsers.SAXParserFactory;
import javax.xml.parsers.SAXParser;


import java.awt.Dimension;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JPanel;


public class API_project {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        String nbpAdress = "https://api.nbp.pl/api/exchangerates/tables/a/last/5/?format=xml";
//Window window = new Window();
    try{
        URL url = new URL(nbpAdress);
        URLConnection connection = url.openConnection();        
        InputStream dataStream=connection.getInputStream();
        SAXParserFactory factory = SAXParserFactory.newInstance(); //fabryka obiektow
        SAXParser saxParser = factory.newSAXParser(); //obiekt parser
        NewHandler handler = new NewHandler(); //obiekt handlera
        saxParser.parse(dataStream, handler);


    }catch(Exception e){
        System.out.println("Błąd\n");        
         e.printStackTrace();
    }

}
}
   
