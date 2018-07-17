package net.smartdec.tools.controller;

import net.smartdec.tools.controller.element.Button;
import net.smartdec.tools.controller.element.Element;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.net.URL;

public class Application {
    private WebDriver wd;
    private Navigation navigation;
    private Button button;
    private Wait wait;
    private Element element;

    public void init() {
        URL url = getClass().getClassLoader().getResource("chromedriver.exe"); /* Не утверждаю, что так правильно.
        Возможно, на реальном проекте лучше хранить драйвер, например, на общем сетевом ресурсе.
        Места он занимает немного, поэтому здесь драйвер добавлен в ресурсы проекта для удобства проверяющего. */
        System.setProperty("webdriver.chrome.driver", url.getFile());
        wd = new ChromeDriver();

        navigation = new Navigation(wd);
        button = new Button(wd);
        wait = new Wait(wd);
        element = new Element(wd);
    }

    public Navigation goTo() {
        return navigation;
    }

    public void destroy() {
        wd.quit();
    }

    public Button button() {
        return button;
    }

    public Wait waitUntil() {
        return wait;
    }

    public Element element() {
        return element;
    }
}
