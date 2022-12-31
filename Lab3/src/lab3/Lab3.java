/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab3;

import demo.AddressType;
import demo.ObjectFactory;
import demo.PersonType;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class Lab3 {

    public static void main(String[] args) throws FileNotFoundException {
        try {
            JAXBContext context = JAXBContext.newInstance("demo");
            Unmarshaller unmarsh = context.createUnmarshaller();
            JAXBElement JAXBPerson = (JAXBElement) unmarsh.unmarshal(new File("D:\\ITI 9-Months\\API for XML\\Lab3\\src\\lab3\\demo.xml"));
            PersonType personType = (PersonType) JAXBPerson.getValue();

            System.out.println("Person name = " + personType.getName());
            AddressType address = (AddressType) personType.getAddress().get(0);
            System.out.println("First Address: " + "\nStreet = " + address.getStreet() + "\nNumber = " + address.getNumber());

            personType.setName("Khaled Mohamed");

            List addressList = personType.getAddress();
            addressList.clear();

            ObjectFactory factory = new ObjectFactory();
            AddressType newAddr = factory.createAddressType();
            newAddr.setStreet("Giza");
            newAddr.setNumber(5);
            addressList.add(newAddr);

            JAXBElement person = factory.createPerson(personType);
            Marshaller marsh = context.createMarshaller();
            marsh.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marsh.marshal(person, new FileOutputStream("output.xml"));

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

}
