package com.inadequate.awg.common;

import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public abstract class SportmasterTest {
    protected String baseUrl;

    @BeforeClass
    protected void init() {
        Properties properties = new Properties();
        InputStream input = null;
        try {
            input = SportmasterTest.class.getClassLoader().getResourceAsStream(getEnvironment());
            properties.load(input);

            baseUrl = properties.getProperty("baseUrl");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private String getEnvironment() {
        String environment = System.getProperty("environment");
        switch (environment) {
            case "production":
            case "dev":
            case "qa":
                return environment + ".properties";
            default:
                return "production.properties";
        }
    }
}
