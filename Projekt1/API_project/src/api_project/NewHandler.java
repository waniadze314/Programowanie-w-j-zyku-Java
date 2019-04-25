/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api_project;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

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
