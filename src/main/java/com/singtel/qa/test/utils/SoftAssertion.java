package com.singtel.qa.test.utils;


import org.testng.asserts.SoftAssert;

import java.math.BigDecimal;

public class SoftAssertion {

    SoftAssert softAssertion = new SoftAssert();
    public SoftAssertion(){};

    public void assertAll(){ softAssertion.assertAll(); }

    public void assertMessage(String infoMessage) {
        String messageString = "Verified: " + infoMessage;
        System.out.println(messageString);
        FileOperation.writeFile(Constant.CUCUMBER_STEP,messageString);
    }

    public void assertCompareString(String actualValue, String expectedValue,  String verificationMessage  ){
        softAssertion.assertEquals(actualValue,expectedValue,verificationMessage);
        assertMessage(verificationMessage);
    }

    public void assertCompareObject(Object actualValue, Object expectedValue,  String verificationMessage ){
        softAssertion.assertEquals(actualValue,expectedValue,verificationMessage);
        assertMessage(verificationMessage);
    }
    public void assertCompareInteger(int actualValue, int expectedValue,  String verificationMessage ){
        softAssertion.assertEquals(actualValue,expectedValue,verificationMessage);
        assertMessage(verificationMessage);
    }

    public void assertCompareBigDecimal(BigDecimal actualValue, BigDecimal expectedValue, String verificationMessage  ){
        softAssertion.assertEquals(actualValue,expectedValue,verificationMessage);
        assertMessage(verificationMessage);
    }

    public  void assertTrue(boolean actualValue, String verificationMessage  ){
        softAssertion.assertTrue(actualValue,verificationMessage);
        assertMessage(verificationMessage);
    }

    public  void assertFalse(boolean actualValue, String verificationMessage  ){
        softAssertion.assertFalse(actualValue,verificationMessage);
        assertMessage(verificationMessage);
    }

}
