package com.example.appium_sample.test.testcase;

import com.example.appium_sample.test.utils.Const;
import com.example.appium_sample.test.utils.Providers;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

import static com.example.appium_sample.test.utils.Utils.makeXPath;
import static com.example.appium_sample.test.utils.Utils.printResult;
import static com.example.appium_sample.test.utils.Utils.printTc;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FruitCartScreenTestCase {

    private Providers provider = Providers.getInstance();

    public FruitCartScreenTestCase() {
    }

    @Test
    public void tc1_cartValidate() {
        provider.getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        printTc("Validate cart is not empty");
        WebElement cartTotal = provider.getDriver().findElementByXPath(makeXPath(Const.TextView, "tvCartTotal"));
        printResult(String.valueOf(!cartTotal.getText().equals("$0.00")));
    }

    @Test
    public void tc2_cartRemoveItem() {
        provider.getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        printTc("remove item from the cart");

        WebElement rvItem = provider.getDriver().findElementByXPath(makeXPath(Const.RecyclerView, 0, Const.FrameLayout));
        new Actions(provider.getDriver()).clickAndHold(rvItem).perform();

        provider.getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement dialogBtnCancel = provider.getDriver().findElementByXPath(makeXPath(Const.Button, "button2"));
        dialogBtnCancel.click();

        rvItem = provider.getDriver().findElementByXPath(makeXPath(Const.RecyclerView, 0, Const.FrameLayout));
        new Actions(provider.getDriver()).clickAndHold(rvItem).perform();

        provider.getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement dialogBtnOk = provider.getDriver().findElementByXPath(makeXPath(Const.Button, "button1"));
        dialogBtnOk.click();
    }

    @Test
    public void tc3_cartValidateIsEmptyOrNot() {
        provider.getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        printTc("Validate cart is empty");
        WebElement cartTotal = provider.getDriver().findElementByXPath(makeXPath(Const.TextView, "tvCartTotal"));
        printResult(String.valueOf(cartTotal.getText().equals("$0.00")));
    }
}
