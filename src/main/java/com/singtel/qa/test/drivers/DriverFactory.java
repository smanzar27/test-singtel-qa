package com.singtel.qa.test.drivers;

public class DriverFactory {

    public  static DriverManager driverManager;
    public  static DriverFactory driverFactory;

    public  static  DriverFactory getInstance( ) {
        driverFactory = new DriverFactory();
        return driverFactory;
    }

    DriverFactory() { }

    public DriverManager getDriverManager(String driverType){
        switch (DriverType.valueOf(driverType)){
            case CHROME: return driverManager = new ChromeWebDriver();
            case FIREFOX: System.out.println("FIREFOX PLACE HOLDER");
            default: throw new RuntimeException("INVALID DRIVER: " +  driverType);
        }
    }
}

enum DriverType {
    FIREFOX,
    CHROME;

    DriverType() {}
}

