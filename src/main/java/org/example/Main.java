package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String json = readString("new_data.json");

        List<Employee> list = jsonToList(json);

        for (Employee employee : list) {
            System.out.println(employee);
        }
    }

    public static String readString(String file) {
        StringBuilder textAll = new StringBuilder();
        try (BufferedReader bf = new BufferedReader(new FileReader(file))){
            String text;
            while ((text = bf.readLine()) != null) {
                textAll.append(text);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return textAll.toString();
    }

    public static List<Employee> jsonToList(String json) {
        List<Employee> jsonList = new ArrayList<>();
        JSONParser parser = new JSONParser();
        try {
            JSONArray jsonArray = (JSONArray) parser.parse(json);
            for (Object partJsonArray : jsonArray) {
                GsonBuilder builder = new GsonBuilder();
                Gson gson = builder.create();
                Employee employee = gson.fromJson(String.valueOf(partJsonArray), Employee.class);
                jsonList.add(employee);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return jsonList;
    }
}