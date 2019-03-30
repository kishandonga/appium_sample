package com.example.appium_sample.test.testcase;

import com.example.appium_sample.test.utils.Const;
import com.example.appium_sample.test.utils.Providers;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

import static com.example.appium_sample.test.utils.Utils.makeXPath;
import static com.example.appium_sample.test.utils.Utils.printResult;
import static com.example.appium_sample.test.utils.Utils.printTc;

/*
 *   Created By Kishan Donga 3/23/19
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FruitSelectScreenTestCase {

    private Providers provider = Providers.getInstance();

    public FruitSelectScreenTestCase() {
    }

    @Test
    public void tc1_openCart() {
        provider.getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        printTc("Open Cart and validate button click event");
        WebElement openCartActivity = provider.getDriver().findElementByXPath(makeXPath(Const.TextView, "action_open_cart"));
        openCartActivity.click();
    }

    @Test
    public void tc2_validateCartIsEmptyOrNot() {
        provider.getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        printTc("Validate cart is empty or not");
        WebElement cartTotal = provider.getDriver().findElementByXPath(makeXPath(Const.TextView, "tvCartTotal"));
        printResult(String.valueOf(cartTotal.getText().equals("$0.00")));
    }

    @Test
    public void tc3_closeCart() {
        provider.getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        printTc("Close Cart screen and validate back button");
        WebElement cartBackBtn = provider.getDriver().findElementByXPath(makeXPath(Const.ViewGroup, 0, Const.ImageButton));
        cartBackBtn.click();
    }

    @Test
    public void tc4_selectFruit() {
        provider.getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        printTc("Select Fruit from the list and validate click event");

        WebElement rvItem = provider.getDriver().findElementByXPath(makeXPath(Const.RecyclerView, 0, Const.FrameLayout));
        WebElement tvFruitName = rvItem.findElement(By.xpath(makeXPath(Const.TextView, "tvFruitName")));

        provider.put(Const.SELECTED_ITEM, tvFruitName.getText());
        rvItem.click();
    }
}
