package com.singtel.qa.test.utils;

import org.testng.Assert;

public class HardAssertion {


    public static void assertMessage(String infoMessage) {
        String messageString = "Verified: " + infoMessage;
        System.out.println(messageString);
        FileOperation.writeFile(Constant.CUCUMBER_STEP,messageString);
    }

    public static void assertCompareString(String actualString, String expectedString,  String verificationMessage  ){
        Assert.assertEquals(actualString,expectedString,verificationMessage);
        assertMessage(verificationMessage);
    }

    public static void assertTrue(boolean actualValue, String verificationMessage  ){
        Assert.assertTrue(actualValue,verificationMessage);
        assertMessage(verificationMessage);
    }

    public static void assertFalse(boolean actualValue, String verificationMessage  ){
        Assert.assertFalse(actualValue,verificationMessage);
        assertMessage(verificationMessage);
    }

    public static void assertCompareInteger(int actualValue, int expectedValue,  String verificationMessage  ){
        Assert.assertEquals(actualValue,expectedValue,verificationMessage);
        System.out.println("Verified: " + verificationMessage);
    }

    public static void assertFail(String errorMessage  ){
        Assert.fail(errorMessage);
        System.out.println("Verified: " + errorMessage);
    }

    public static void assertCompareObject(Object actualValue, Object expectedValue,  String verificationMessage ){
        Assert.assertEquals(actualValue,expectedValue,verificationMessage);
        assertMessage(verificationMessage);
    }
}
