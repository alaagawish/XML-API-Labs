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

            Document document = documentBuilder.newDocument();

            Element elementFather = document.createElement("Father");
            Element elementChild = document.createElement("Child");
            Element elementChildren = document.createElement("Children");

            Attr attrFatherAge=document.createAttribute("Age");
            attrFatherAge.setValue("50");

            Attr attrFatherName=document.createAttribute("Name");
            attrFatherName.setValue("Mohamed");


            Attr attrChildAge=document.createAttribute("Age");
            attrChildAge.setValue("20");

            Attr attrChildName=document.createAttribute("Name");
            attrChildName.setValue("Karim");

            Text fatherChildText =document.createTextNode("I'm a father");
            Text childChildText =document.createTextNode("I'm a child");

            document.appendChild(elementFather);
            elementFather.appendChild(elementChild);
            elementChild.appendChild(elementChildren);
            elementChild.setAttributeNode(attrChildName);
            elementChild.setAttributeNode(attrChildAge);
            elementFather.setAttributeNode(attrFatherAge);
            elementFather.setAttributeNode(attrFatherName);

            elementChild.appendChild(childChildText);
            elementFather.appendChild(fatherChildText);

            StreamResult streamResult= new StreamResult(new FileOutputStream("BuildXML.xml"));

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
