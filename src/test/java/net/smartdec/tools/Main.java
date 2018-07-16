package net.smartdec.tools;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public class Main {

    @Test
    public void testMainPage() {

        // подготовка к тесту
        URL url = getClass().getClassLoader().getResource("chromedriver.exe"); /* Не утверждаю, что так правильно.
        Возможно, на реальном проекте лучше хранить драйвер, например, на общем сетевом ресурсе.
        Места он занимает немного, поэтому здесь так сделано для удобства проверяющего. */
        System.setProperty("webdriver.chrome.driver", url.getFile());
        WebDriver wd = new ChromeDriver();

        // переходим на стартовую страницу
        wd.get("http://tool.smartdec.net");

        // нажимаем на кнопку start using
        WebElement startUsingButton = wd.findElement(By.id("btn-start"));
        Assert.assertNotNull("Кнопка start using не найдена на странице", startUsingButton);
        startUsingButton.click();

        // нажимаем на кнопку paste code
        WebElement pasteCodeButton = wd.findElement(By.xpath("//*[@id='actions-ul']//*[text()='paste code']"));
        Assert.assertNotNull("Кнопка paste code не найдена на странице", pasteCodeButton);
        pasteCodeButton.click();
        Assert.assertEquals("Не удалось перейти на страницу отправки кода для сканирования",
                "http://tool.smartdec.net/newscan?type=codeblock", wd.getCurrentUrl());

        // вставляем код в textarea
        WebElement textArea = wd.findElement(By.id("codeblock"));
        Assert.assertNotNull("Не удалось найти поле codeblock для ввода кода", textArea);
        textArea.sendKeys("my beautiful code");

        // нажимаем кнопку submit for scan
        WebElement submitForScanButton = wd.findElement(
                By.xpath("//*[@id='codeblock']/..//span[contains(., 'submit for scan')]")); /* Внимательный читатель
                 спросит, зачем так сделано. Дело в том, как устроен блок button на странице.
                 Кликабельная область занимает только часть блока button.
                 Если взять блок button и выполнить метод click(), он выполнится по центру элемента.
                 В кликабельную область не попадет */
        Assert.assertNotNull("Не удалось найти кнопку submit for scan", submitForScanButton);
        submitForScanButton.click();

        // ожидаем загрузки страницы
        WebDriverWait wait = new WebDriverWait(wd, 10);
        Boolean scanResult = wait.until(ExpectedConditions.urlMatches("http://tool.smartdec.net/scan/[0-9a-z]*"));
        Assert.assertTrue("Не удалось перейти на страницу с результатами анализа кода", scanResult);

        // завершаем работу
        wd.quit();
    }
}
