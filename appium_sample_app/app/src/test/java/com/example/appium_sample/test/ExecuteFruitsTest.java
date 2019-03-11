package com.example.appium_sample.test;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

/*
 *   Created By Kishan Donga 3/10/19
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ExecuteFruitsTest {

    private static AndroidDriver driver;
    private static DataProvider provider;

    @BeforeClass
    public static void setUp() throws MalformedURLException {

        provider = DataProvider.getInstance();

        // Created object of DesiredCapabilities class.
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "7.1.1");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.NO_RESET, true);
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        capabilities.setCapability("appPackage", "com.example.appium_sample");
        capabilities.setCapability("appActivity", "com.example.appium_sample.ui.LoginAct");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    @AfterClass
    public static void end() {
        driver.quit();
    }

    @Test
    public void stage1_testCashLogin() {

        WebElement email = driver.findElementByXPath("//android.widget.EditText[contains(@resource-id,'edEmail')]");
        WebElement pwd = driver.findElementByXPath("//android.widget.EditText[contains(@resource-id,'edPwd')]");
        WebElement btnLogin = driver.findElementByXPath("//android.widget.Button[contains(@resource-id,'btnLogin')]");

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

    @Test
    public void stage2_testCaseDashboard() {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement rvItem = driver.findElementByXPath("//android.support.v7.widget.RecyclerView[@index='0']/android.widget.TextView");
        provider.put(Const.SELECTED_ITEM, rvItem.getText());

        rvItem.click();
    }

    @Test
    public void stage3_testCaseVerifyData() {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement tvFruit = driver.findElementById("com.example.appium_sample:id/tvFruit");
        String fruit = tvFruit.getText();
        String selectedFruit = (String) provider.get(Const.SELECTED_ITEM);
        if (fruit.equals(selectedFruit)) {
            System.out.println("Both are Same Test Case Pass!");
        }
    }
}
