package com.example.appium_sample.test;

import com.example.appium_sample.test.testcase.FruitDetailScreenTestCase;
import com.example.appium_sample.test.testcase.FruitSelectScreenTestCase;
import com.example.appium_sample.test.testcase.LoginScreenTestCase;
import com.example.appium_sample.test.utils.Providers;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

/*
 *   Created By Kishan Donga 3/10/19
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({
        LoginScreenTestCase.class,
        FruitSelectScreenTestCase.class,
        FruitDetailScreenTestCase.class})
public class FruitTestSuite {

    private static AndroidDriver driver;
    private static Providers provider = Providers.getInstance();

    @BeforeClass
    public static void setUp() throws MalformedURLException {

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

        provider.setDriver(driver);
        System.out.print("Driver Setup Completed");
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
        System.out.print("Driver successfully teardown");
    }

    /*

    @Test
    public void stage3_testCaseVerifyData() {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement tvFruit = driver.findElementById("com.example.appium_sample:id/tvFruit");
        String fruit = tvFruit.getText();
        String selectedFruit = (String) provider.get(Const.SELECTED_ITEM);
        if (fruit.equals(selectedFruit)) {
            System.out.println("Both are Same Test Case Pass!");
        }
    }*/
}
