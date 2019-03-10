package com.example.appium_sample.test;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.remote.MobileCapabilityType;

/*
 *   Created By Kishan Donga 3/10/19
 */

public class ExecuteFruitsTest {

    private static WebDriver driver;

    @BeforeClass
    public static void setUp() throws MalformedURLException {
        // Created object of DesiredCapabilities class.
        DesiredCapabilities capabilities = new DesiredCapabilities();

        // Set android deviceName desired capability. Set your device name.
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");

        // Set android VERSION desired capability. Set your mobile device's OS version.
        capabilities.setCapability(CapabilityType.VERSION, "7.1.1");

        // Set android platformName desired capability. It's Android in our case here.
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");

        // Set android appPackage desired capability. It is
        // com.android.calculator2 for calculator application.
        // Set your application's appPackage if you are using any other app.
        capabilities.setCapability("appPackage", "com.example.appium_sample");

        // Set android appActivity desired capability. It is
        // com.android.calculator2.Calculator for calculator application.
        // Set your application's appPackage if you are using any other app.
        capabilities.setCapability("appActivity", "com.example.appium_sample.ui.LoginAct");

        // Created object of RemoteWebDriver will all set capabilities.
        // Set appium server address and port number in URL string.
        // It will launch calculator app in android device.
        driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    @AfterClass
    public static void end() {
        driver.quit();
    }

    @Test
    public void testCashLogin() {

        WebElement email = driver.findElement(By.xpath("//android.widget.EditText[contains(@resource-id,'edEmail')]"));
        WebElement pwd = driver.findElement(By.xpath("//android.widget.EditText[contains(@resource-id,'edPwd')]"));
        WebElement btnLogin = driver.findElement(By.xpath("//android.widget.Button[contains(@resource-id,'btnLogin')]"));

        email.sendKeys("");
        btnLogin.click();

        email.sendKeys("kishan.donga.com");
        btnLogin.click();

        email.sendKeys("");
        btnLogin.click();

        email.sendKeys("kishan@donga.com");
        btnLogin.click();

        pwd.sendKeys("");
        btnLogin.click();

        pwd.sendKeys("123");
        btnLogin.click();

    }

    private void testCaseDashboard() {

        driver.findElement(By.xpath("//android.support.v7.widget.RecyclerView[0]/android.widget.TextView")).click();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }
}
