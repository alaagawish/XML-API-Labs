package com.company;

import java.io.*;
import javax.xml.parsers.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.*;
import javax.xml.transform.*;
import org.w3c.dom.*;

public class Main {

    public static void main(String[] args) {
        try {

            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            documentBuilderFactory.setValidating(true);
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

            Document document = documentBuilder.parse(new File("lab1.xml"));
            NodeList children= document.getElementsByTagName("message");

            if(children.getLength() !=0){
                for (int i=0 ; i<children.getLength() ; i++){
                Node currentNode=children.item(i);
                    switch (currentNode.getNodeType()){
                        case Node.ELEMENT_NODE:
                            System.out.println("This is An Element Node "+currentNode.getNodeName());

                            Text text =document.createTextNode("New Text At Line");
//                            currentNode.replaceChild(text,currentNode.getFirstChild());
Node n =currentNode.appendChild(text);
Node n2=currentNode.removeChild(currentNode.getFirstChild());
                            break;

                        default:
                            System.out.println("this is a different node "+currentNode.getNodeName());



                    }

                }
            }



//Second solution
//            NodeList nodeList=document.getElementsByTagName("message");
//            System.out.println(nodeList);
//            for (int i= nodeList.getLength()-1; i >=0 ; i--){
//                nodeList.item(i).getFirstChild().setTextContent("New Text At Line+i");
//            }

////First solution
//            NodeList nodeList=document.getElementsByTagName("message");
//
//            for (int i=0 ;i <nodeList.getLength() ; i++){
//                Text text =document.createTextNode("New Text At Line"+i);
//                nodeList.item(i).replaceChild(text,nodeList.item(i).getFirstChild());
//            }

            StreamResult streamResult= new StreamResult(new FileOutputStream("ReplaceText.xml"));

            DOMSource source=new DOMSource(document);

            TransformerFactory transformerFactory= TransformerFactory.newInstance();
            Transformer transformer=transformerFactory.newTransformer();

            transformer.transform(source,streamResult);
            streamResult.getOutputStream().close();



        } catch (Exception e) {


            e.printStackTrace();
        }
    }
}
