package com.singtel.qa.test.configs;

import com.singtel.qa.test.exception.InvalidUserInputException;

import java.util.Properties;

public class APIConfig extends ConfigReader{

    private final Properties properties;
    String fileName="api-config.properties";

    public APIConfig(){
        super();
        properties=getProperties(fileName);
    }

    public String getAPIBaseURI() {
        String api_base_uri = properties.getProperty("api-base-uri");
        if(api_base_uri != null) return api_base_uri;
        else throw new InvalidUserInputException("api_base_uri not specified in the api-config.properties file.");
    }

    public String getAPIKey()  {
        String api_key = properties.getProperty("api-key");
        if(api_key != null) return api_key;
        else throw new InvalidUserInputException("api_key not specified in the api-config.properties file.");
    }

}
