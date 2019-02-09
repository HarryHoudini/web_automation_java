package com.inadequate.web.automation.common;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.testng.ScreenShooter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Listeners({ ScreenShooter.class})
public abstract class SportmasterTest {
    private Properties envProperties;

    @BeforeClass
    protected void init() {
        envProperties = readEnvironmetProperties();
        configureSelenide();
    }

    private void configureSelenide() {
        Configuration.baseUrl = envProperties.getProperty("baseUrl");
        Configuration.savePageSource = false;
        Configuration.browser = "chrome";
        Configuration.headless = true;
        Configuration.reportsFolder = "reports";
        // Tests often fails with default timeout on my machine due to performance
        Configuration.timeout = 10000;

        ScreenShooter.captureSuccessfulTests = true;
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
