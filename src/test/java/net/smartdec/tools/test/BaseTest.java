package net.smartdec.tools.test;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public class BaseTest {
    public static WebDriver wd;
    public static WebDriverWait pageLoadWait;

    @Before
    public void setUp() {
        URL url = getClass().getClassLoader().getResource("chromedriver.exe"); /* Не утверждаю, что так правильно.
        Возможно, на реальном проекте лучше хранить драйвер, например, на общем сетевом ресурсе.
        Места он занимает немного, поэтому здесь драйвер добавлен в ресурсы проекта для удобства проверяющего. */
        System.setProperty("webdriver.chrome.driver", url.getFile());
        wd = new ChromeDriver();
        pageLoadWait = new WebDriverWait(wd, 10);
    }

    @After
    public void tearDown() {
        wd.quit();
    }
}
