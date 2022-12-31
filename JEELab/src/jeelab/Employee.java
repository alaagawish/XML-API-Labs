/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jeelab;

import java.util.ArrayList;
import java.util.List;
import javax.json.JsonArray;
import javax.json.JsonObject;

/**
 *
 * @author Java-ahmed
 */
public class Employee {

    private int id;
    private String name;
    private boolean gender;
    private float salary;
    private List<Address> addresses = new ArrayList<>();

    public Employee() {
    }

    public Employee(JsonObject object) {
        wrappe(object);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public void wrappe(JsonObject object) {
        this.id = object.getInt("id");
        this.name = object.getString("name");
        this.gender = object.getBoolean("gender");
        this.salary = Float.parseFloat(object.get("salary").toString());
        JsonArray adressList = object.getJsonArray("addresses");
        if (adressList != null) {
            for (int i = 0; i < adressList.size(); i++) {
                JsonObject addressValue = adressList.getJsonObject(i);
                Address address = new Address();
                address.wrappe(addressValue);
                this.addresses.add(address);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("***** Employee Details *****\n");
        sb.append("ID=" + this.id + "\n");
        sb.append("Name=" + this.name + "\n");
        sb.append("Gender=" + this.gender + "\n");
        sb.append("Salary=" + this.salary + "\n");
        for (Address address : addresses) {
            sb.append(address);
        }
        sb.append("*****************************\n");
        return sb.toString();
    }
}
