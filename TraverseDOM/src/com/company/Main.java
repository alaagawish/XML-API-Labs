package com.company;

import java.io.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class Main {
    public static void main(String[] args) {
        TraverseDOM existFile= new TraverseDOM("D:\\ITI 9-Months\\API for XML\\TraverseDOM\\src\\com\\company\\lab3.xml");
        TraverseDOM NotExistFile= new TraverseDOM("lab2.xml");

    }
}
class TraverseDOM{
    private   Document document;
    public TraverseDOM(String file){
        try {

            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            documentBuilderFactory.setValidating(true);
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

            documentBuilder.setErrorHandler(new ErrorHandler() {
                @Override
                public void warning(SAXParseException exception) throws SAXException {
                    System.out.println(exception.getException());
                }

                @Override
                public void error(SAXParseException exception) throws SAXException {
                    System.out.println(exception.getException());

                }

                @Override
                public void fatalError(SAXParseException exception) throws SAXException {
                    System.out.println(exception.getException());

                }
            });
            document = documentBuilder.parse(new File(file));

            processNode(document);




        }catch (SAXException saxException) {
            saxException.printStackTrace();
        } catch (FileNotFoundException fileNotFoundException){
            System.out.println("file not found");
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void processNode(Node currentNode){
        switch (currentNode.getNodeType()){
            case Node.DOCUMENT_NODE:
                Document document = (Document) currentNode;
                System.out.println("This is A Document Node");
                processChildNodes(document.getChildNodes());
                break;
            case Node.ELEMENT_NODE:
                System.out.println("This is An Element Node"+currentNode.getNodeName());
                NamedNodeMap attNodes = currentNode.getAttributes();

                for (int i = 0;i<attNodes.getLength();i++){
                    Attr attr=(Attr) attNodes.item(i);
                    System.out.println("Attribute is "+attr.getNodeName()+"\nValue = "+attr.getNodeValue());

                }
                processChildNodes(currentNode.getChildNodes());
                break;
            case Node.TEXT_NODE:
                Text text=(Text) currentNode;
                if(text.getNodeValue().trim().equals(""))
                    System.out.println("This is an empty text ="+text.getNodeValue());
                else
                    System.out.println("This is a text ="+text.getNodeValue());
                break;
            case Node.CDATA_SECTION_NODE:
            case Node.COMMENT_NODE:
                Comment comment=(Comment) currentNode;
                System.out.println("This is a comment >>"+comment.getNodeValue());
                break;
            default:
                System.out.println("this is a different node "+ currentNode.getNodeName());



        }
    }
    public void processChildNodes(NodeList children){
        if(children.getLength() !=0){
            for (int i=0;i<children.getLength();i++){
                processNode(children.item(i));

            }
        }
    }


}

