package jeelab;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonStructure;

public class JEELab {

    public static void main(String[] args) {
        try {
//            JsonReader reader = Json.createReader(new FileReader("D:\\ITI 9-Months\\API for XML\\JEELab\\src\\jeelab\\message2.json"));
//            JsonStructure jsonStructure = reader.read();
//            System.out.println(jsonStructure.getValueType());

            JsonObject model = Json.createObjectBuilder()
                    .add("id", 1234)
                    .add("name", "Alaa")
                    .add("gender", true)
                    .add("salary", 122.3)
                    .add("addresses", Json.createArrayBuilder()
                            .add(Json.createObjectBuilder()
                                    .add("number", 1)
                                    .add("street", "mohamed ali street")
                                    .add("region", "zag"))
                            .add(Json.createObjectBuilder()
                                    .add("number", 1)
                                    .add("street", "al ahrar")
                                    .add("region", "zag")))
                    .build();

            JsonStructure jsonStructure = model;
            JsonReader reader = Json.createReader(new FileReader("D:\\ITI 9-Months\\API for XML\\JEELab\\src\\jeelab\\message1.json"));
//            JsonStructure jsonStructure = reader.read();

            if (jsonStructure.getValueType() == JsonStructure.ValueType.ARRAY) {
                List<Employee> emplopees = new ArrayList<Employee>();
                JsonArray jsonArray = (JsonArray) jsonStructure;

                for (int i = 0; i < jsonArray.size(); i++) {
                    JsonObject jsonObject = (JsonObject) jsonArray.getJsonObject(i);
                    System.out.println(jsonObject);

                    Employee employee = new Employee();
                    employee.setId(jsonObject.getInt("id"));
                    employee.setName(jsonObject.getString("name"));
                    employee.setGender(jsonObject.getBoolean("gender"));
                    employee.setSalary(Float.parseFloat(jsonObject.get("salary").toString()));
                    JsonArray addressList = jsonObject.getJsonArray("addresses");
                    System.out.println(employee);

                    if (addressList != null) {
                        for (int y = 0; y < addressList.size(); y++) {
                            JsonObject addressValue = addressList.getJsonObject(y);
                            Address address = new Address();
                            address.setNumber(addressValue.getInt("number"));
                            address.setStreet(addressValue.getString("street"));
                            address.setRegion(addressValue.getString("region"));
                            employee.getAddresses().add(address);

                        }
                    }
                    emplopees.add(employee);

                }

            } else if (jsonStructure.getValueType() == JsonStructure.ValueType.OBJECT) {
                JsonObject jsonObject = (JsonObject) jsonStructure;
                System.out.println(jsonStructure);

                Employee employee = new Employee();
                employee.setId(jsonObject.getInt("id"));
                employee.setName(jsonObject.getString("name"));
                employee.setGender(jsonObject.getBoolean("gender"));
                employee.setSalary(Float.parseFloat(jsonObject.get("salary").toString()));
                JsonArray addressList = jsonObject.getJsonArray("addresses");
                System.out.println(employee);

                if (addressList != null) {
                    for (int y = 0; y < addressList.size(); y++) {
                        JsonObject addressValue = addressList.getJsonObject(y);
                        Address address = new Address();
                        address.setNumber(addressValue.getInt("number"));
                        address.setStreet(addressValue.getString("street"));
                        address.setRegion(addressValue.getString("region"));
                        employee.getAddresses().add(address);

                    }
                }

            }

        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        }

    }

}
