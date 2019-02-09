package com.inadequate.web.automation.common;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public abstract class SportmasterTest {
    private Properties envProperties;

    @BeforeClass
    protected void init() {
        envProperties = readEnvironmetProperties();
        configureSelenide();
    }

    private void configureSelenide() {
        Configuration.baseUrl = envProperties.getProperty("baseUrl");
        Configuration.headless = false;
        Configuration.savePageSource = false;
        Configuration.browser = "chrome";
        Configuration.reportsFolder = "reports";

        // Tests running with Selenoid often fails with default timeouts on my machine due to performance
        if (Configuration.remote != null) {
            Configuration.timeout = 10000;
        }
    }

    private Properties readEnvironmetProperties() {
        Properties properties = new Properties();
        InputStream input = null;
        try {
            input = SportmasterTest.class.getClassLoader().getResourceAsStream(getEnvironment());
            properties.load(input);
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
        return properties;
    }

    private String getEnvironment() {
        String environment = System.getProperty("environment");
        if (environment != null) {
            switch (environment) {
                case "production":
                case "dev":
                case "qa":
                    return environment + ".properties";
                default:
                    return "production.properties";
            }
        }
        return "production.properties";
    }
}
