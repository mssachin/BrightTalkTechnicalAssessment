package com.qaexercise.brighttalk.base;

import com.jayway.restassured.RestAssured;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by Sachin on 20/05/2017.
 */
public class TestBase {

    private  String workingDirectory = System.getProperty("user.dir");
    private InputStream inputStream;
    private Properties properties;

    public TestBase() {
        try {
            inputStream = new FileInputStream(workingDirectory + "//src//main//java//com//qaexercise//brighttalk//config//config.properties");
            properties = new Properties();
            properties.load(inputStream);
            RestAssured.baseURI = properties.getProperty("baseHost");
            RestAssured.port = Integer.valueOf(properties.getProperty("basePort"));
        } catch (IOException IOE){
            IOE.printStackTrace();
        }
    }



}
