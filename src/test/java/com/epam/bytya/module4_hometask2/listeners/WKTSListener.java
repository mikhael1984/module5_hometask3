package com.epam.bytya.module4_hometask2.listeners;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

/**
 * Created by Mikhail_Mereminskiy on 8/9/2016.
 */
public class WKTSListener implements IInvokedMethodListener {

    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        System.out.println("Method started: " + method.getTestMethod().getDescription());

    }

    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        System.out.println("Method finished [" + testResult.getStatus() + "]: " + method.getTestMethod().getDescription() + "\n");
    }
}

