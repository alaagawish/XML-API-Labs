package com.company;

import org.xml.sax.*;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;


public class Main {

    public static void main(String[] args) {
	// write your code here
        boolean  validate =true;
        SAXParserFactory saxParserFactory =SAXParserFactory.newInstance();
        saxParserFactory.setValidating(validate);
        try {
            SAXParser saxParser=saxParserFactory.newSAXParser();
            saxParser.parse("D:\\ITI 9-Months\\API for XML\\Tree\\src\\com\\company\\MyConfig.xml",new Tree());
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException saxException) {
            saxException.printStackTrace();
        }
    }
}
class Tree extends HandlerBase{
    int counter=0;
    public void setDocumentLocator(Locator locator){
        System.out.println("XML file URL: "+ locator.getSystemId());
    }

    public void startDocument() throws SAXException{
        System.out.println("[ Document Start ]");
    }

    public void endDocument() throws SAXException{
        System.out.println("[ Document End ]");
    }

    public void startElement(String name, AttributeList attributeList) throws SAXException{

        for (int i=0;i<counter;i++)
            System.out.print("\t");

        if(attributeList != null){
            System.out.println();
            for (int i=0;i<counter;i++)
                System.out.print("\t");
            System.out.print("<"+name );
            for(int i = 0;i <attributeList.getLength(); i++){
                System.out.print(" "+attributeList.getName(i)+
                        "=\""+attributeList.getValue(i)+"\"");
                System.out.print(" ");
            }
            System.out.print(">");
        }
        counter++;

    }


    public void endElement(String name) throws SAXException{
        counter--;
        for (int i=1;i<counter;i++)
            System.out.print("\t");
        if (counter==1){
            System.out.println();
            for (int i=0;i<counter;i++)
                System.out.print("\t");
            System.out.println("</"+name +">");
        }
        else if (counter>1)
        System.out.print("</"+name +">");

        else
            System.out.println("</"+name +">");


    }

    public void processingInstruction(String target,String value) throws SAXException{
        System.out.println("+-[ proc-inst : "+target + "] \"" + value +"\"");
    }

    public void characters(char buffer[],int offset,int length) throws SAXException{
        if(length >0 ){
            String temp = new String(buffer,offset,length);
            System.out.print(temp);
        }
    }

    public void ignorableWhiteSpace(char buffer[],int offset , int length){
        if (length >0){
            System.out.println("+- [ Ignorable white space ]");
        }
    }

    public void error(SAXParseException saxParseException) throws SAXParseException{
        throw saxParseException;
    }

    public void warning(SAXParseException saxParseException) throws SAXParseException{
        System.err.println("warning: "+saxParseException.getMessage());
    }
}