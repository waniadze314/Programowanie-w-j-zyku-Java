import javax.xml.parsers.SAXParserFactory;
import javax.xml.parsers.SAXParser;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.awt.Dimension;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JPanel;

class Window extends JFrame{
    private JButton buttonShow;
    private JPanel panel;
    private JTable parseTable;
    private final String[] columnNames = {"Waluta", "Kurs"};

    Window(){
    panel = new JPanel();
    buttonShow = new JButton();
    parseTable = new JTable(null, columnNames);
    setPreferredSize(new Dimension(640, 480));
    setLocation(null);
    panel.add(buttonShow);
    panel.add(parseTable);
    getContentPane().add(panel);
        

    }


}

class NewHandler extends DefaultHandler{
    private boolean bCode = false;
    private boolean bMid = false;


@Override
public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
    //super.startElement(uri, localName, qName, attributes);
    //
    //ustawienie zmiennych na true powoduje wywołanie odpowiedniego ciągu instrukcji w metodzie characters
    
    if(qName.equalsIgnoreCase("Code")){
        bCode = true;
    }
    else if(qName.equalsIgnoreCase("Mid")){
        bMid = true;
    }

}

@Override
public void endElement(String uri, String localName, String qName) throws SAXException {
    // super.endElement(uri, localName, qName);
    
}

@Override
public void characters(char[] ch, int start, int length) throws SAXException {
    //super.characters(ch, start, length);
    if(bCode){
        System.out.print("Waluta: " + new String(ch, start, length) + ", ");
        bCode = false;
    }
    else if(bMid){
        System.out.print("Kurs: " + new String(ch,start,length) + "\n");
        bMid = false;
    }
}

}



public class parser{
public static void main(String args[]){
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