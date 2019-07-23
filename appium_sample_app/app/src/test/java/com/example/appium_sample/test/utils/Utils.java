package com.example.appium_sample.test.utils;

public class Utils {

    public static void printErr(String message) {
        System.out.println("Error : " + message);
    }

    public static void printResult(String message) {
        System.out.println("Result : " + message);
    }

    public static void printTc(String message) {
        System.out.println("Test Case : " + message);
    }

    public static void printDesc(String message) {
        System.out.println("Description : " + message);
    }

    public static String makeXPath(String clsName, String resourceId) {
        return "//" + clsName + "[contains(@resource-id,'" + resourceId + "')]";
    }

    public static String makeXPath(String clsName, int childIndex, String childClsName) {
        return "//" + clsName + "[@index='" + childIndex + "']/" + childClsName + "";
    }

    public static String scrollTo(String text) {
        return "new UiScrollable(new UiSelector().resourceId(\"com.example.appium_sample:id/rvFruit\")).scrollIntoView(new UiSelector().text(\"" + text + "\"))";
    }
}
