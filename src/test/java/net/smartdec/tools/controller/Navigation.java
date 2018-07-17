package net.smartdec.tools.controller;

import org.openqa.selenium.WebDriver;

public class Navigation {
    private WebDriver wd;
    private static final String mainPage = "http://tool.smartdec.net"; // here I will use hardcode

    public Navigation(WebDriver wd) {
        this.wd = wd;
        // init other properties from files etc
    }

    public void mainPage() {
        wd.get(mainPage);
    }
}
