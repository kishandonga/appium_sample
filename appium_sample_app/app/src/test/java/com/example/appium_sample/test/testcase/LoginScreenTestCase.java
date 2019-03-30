package com.example.appium_sample.test.testcase;

import com.example.appium_sample.test.utils.Const;
import com.example.appium_sample.test.utils.Providers;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

import static com.example.appium_sample.test.utils.Utils.makeXPath;
import static com.example.appium_sample.test.utils.Utils.printTc;

/*
 *   Created By Kishan Donga 3/23/19
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LoginScreenTestCase {

    private WebElement email;
    private WebElement pwd;
    private WebElement btnLogin;

    public LoginScreenTestCase() {
        Providers provider = Providers.getInstance();
        email = provider.getDriver().findElementByXPath(makeXPath(Const.EditText, "edEmail"));
        pwd = provider.getDriver().findElementByXPath(makeXPath(Const.EditText, "edPwd"));
        btnLogin = provider.getDriver().findElementByXPath(makeXPath(Const.Button, "btnLogin"));
    }

    @Test
    public void tc1_checkEmail() {

        printTc("check email is valid or not");

        email.sendKeys("");
        btnLogin.click();

        email.sendKeys("kishan.donga.com");
        btnLogin.click();

        email.sendKeys("");
        btnLogin.click();

        email.sendKeys("kishan@donga.com");
        btnLogin.click();
    }

    @Test
    public void tc2_checkPassword() {

        printTc("check password is valid or not");

        pwd.sendKeys("");
        btnLogin.click();

        pwd.sendKeys("123");
        btnLogin.click();

        pwd.sendKeys("1234");
        btnLogin.click();
    }
}
