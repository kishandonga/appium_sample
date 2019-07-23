package com.example.appium_sample.test.testcase;

import com.example.appium_sample.test.utils.Const;
import com.example.appium_sample.test.utils.Providers;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

import static com.example.appium_sample.test.utils.Utils.makeXPath;
import static com.example.appium_sample.test.utils.Utils.printResult;
import static com.example.appium_sample.test.utils.Utils.printTc;

/*
 *   Created By Kishan Donga 3/23/19
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FruitDetailScreenTestCase {

    private Providers provider = Providers.getInstance();
    private WebElement addToCartFab;

    public FruitDetailScreenTestCase() {
    }

    @Test
    public void tc1_addFruitInCart() {
        provider.getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        printTc("add fruit in the cart");
        addToCartFab = provider.getDriver().findElementByXPath(makeXPath(Const.ImageButton, "fabAddToBucket"));
        addToCartFab.click();
    }

    @Test
    public void tc2_addInCartDialog() {
        provider.getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        printTc("add fruit in the cart dialog");

        WebElement dialogBtnCancel = provider.getDriver().findElementByXPath(makeXPath(Const.Button, "button2"));
        dialogBtnCancel.click();

        provider.getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        addToCartFab = provider.getDriver().findElementByXPath(makeXPath(Const.ImageButton, "fabAddToBucket"));
        addToCartFab.click();

        WebElement dialogBtnOk = provider.getDriver().findElementByXPath(makeXPath(Const.Button, "button1"));
        dialogBtnOk.click();
    }

    @Test
    public void tc3_checkBadgeCountOfDetailScr() {
        provider.getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        printTc("check Badge count of the detail screen");
        WebElement cartBadge = provider.getDriver().findElementByXPath(makeXPath(Const.TextView, "cart_badge"));
        printResult(String.valueOf(cartBadge.getText().equalsIgnoreCase("1")));
    }

    @Test
    public void tc3_closeDetailScreen() {
        provider.getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        printTc("Close Fruit detail screen and validate back button");
        WebElement backBtn = provider.getDriver().findElementByXPath(makeXPath(Const.ViewGroup, 0, Const.ImageButton));
        backBtn.click();
    }

    @Test
    public void tc4_checkBadgeCountOfSelectionScr() {
        provider.getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        printTc("check Badge count of the fruit selection screen");
        WebElement cartBadge = provider.getDriver().findElementByXPath(makeXPath(Const.TextView, "cart_badge"));
        printResult(String.valueOf(cartBadge.getText().equalsIgnoreCase("1")));
        cartBadge.click();
    }
}
