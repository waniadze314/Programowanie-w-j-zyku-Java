/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api_project;

import java.sql.Statement;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

class NewHandler extends DefaultHandler{ 
    dataBaseConnection db = new dataBaseConnection();
    Statement s = db.getAcces();
    String currency, code, mid, date, order;
    
    private boolean bCurrency = false;
    private boolean bCode = false;
    private boolean bMid = false;
    private boolean bEffectiveDate = false;


@Override
public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
    //super.startElement(uri, localName, qName, attributes);
    //
    //ustawienie zmiennych na true powoduje wywołanie odpowiedniego ciągu instrukcji w metodzie characters
    if(qName.equals("EffectiveDate")){
        bEffectiveDate = true;
    }
    else if(qName.equals("Currency")){
        bCurrency = true;
    }
     else if(qName.equalsIgnoreCase("Code")){
        bCode = true;
    }
    else if(qName.equalsIgnoreCase("Mid")){
        bMid = true;
    }

}

@Override
public void endElement(String uri, String localName, String qName) throws SAXException {
    // super.endElement(uri, localName, qName);
    if(qName.equalsIgnoreCase("Rate")){
        
          order = "insert into kursy(nazwa, kod, kurs, data) values('" + 
                currency + "','" +
                code + "','" +
                mid +"','" +
                date + "')";
        try{
            s.executeUpdate(order);
            System.out.println(order);
        }catch(Exception e){
            //System.err.println("Błąd połączenia z bazą danych:");
            e.getMessage();
        }
    }
    
}

@Override
public void characters(char[] ch, int start, int length) throws SAXException {
    //super.characters(ch, start, length);
    if(bEffectiveDate){
        date = new String(ch, start, length);
        bEffectiveDate = false;
    }
    else if(bCurrency){
        currency = new String(ch, start, length);
        
        bCurrency = false;
    }
    else if(bCode){
        code = new String(ch, start, length);
        bCode = false;
        
    }
    else if(bMid){
        mid = new String(ch, start, length);             
        bMid = false;
    }

    
    
}

}
