/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package googleapilab;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.ProcessBuilder.Redirect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LAPTOP
 */
public class GoogleAPILab {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {

        Employee emp = new Employee(123, "alaa", true);
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        System.out.println(gson.toJson(emp));

        Gson gsonObj = new Gson();
        Employee employee = gsonObj.fromJson(new FileReader("D:\\ITI 9-Months\\API for XML\\GoogleAPILab\\src\\googleapilab\\message1.json"), Employee.class);
        System.out.println(employee);

        Gson gsonList = new Gson();
        java.lang.reflect.Type listType = new TypeToken<ArrayList<Employee>>() {
        }.getType();
        List<Employee> employees = gsonList.fromJson(new FileReader("D:\\ITI 9-Months\\API for XML\\GoogleAPILab\\src\\googleapilab\\message2.json"), listType);
        for (int i = 0; i < employees.size(); i++) {
            System.out.println(employees.get(i));
        }

    }

}
