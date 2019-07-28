package com.example.appium_sample.test;

import com.example.appium_sample.test.testcase.FruitCartScreenTestCase;
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
        FruitDetailScreenTestCase.class,
        FruitCartScreenTestCase.class})
public class FruitTestSuite {

    private static AndroidDriver driver;
    private static Providers provider = Providers.getInstance();

    @BeforeClass
    public static void setUp() throws MalformedURLException {

        // Created object of DesiredCapabilities class.
        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
        dc.setCapability(MobileCapabilityType.PLATFORM_VERSION, "7.1.1");
        dc.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        dc.setCapability(MobileCapabilityType.NO_RESET, true);
        dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        dc.setCapability("appPackage", "com.example.appium_sample");
        dc.setCapability("appActivity", "com.example.appium_sample.ui.SplashActivity");
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), dc);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        provider.setDriver(driver);
        System.out.print("Driver Setup Completed");
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
        System.out.print("Driver successfully teardown");
    }
}
