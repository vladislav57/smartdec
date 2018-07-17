package net.smartdec.tools.controller;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Wait {

    private WebDriver wd;
    private WebDriverWait pageLoadWait;

    public Wait(WebDriver wd) {
        this.wd = wd;
        pageLoadWait = new WebDriverWait(wd, 10);
    }

    public void pasteCodePageLoaded() {
        Boolean pasteCodeButtonPressResult = pageLoadWait.until(ExpectedConditions.urlToBe("http://tool.smartdec.net/newscan?type=codeblock"));
        Assert.assertTrue("Не удалось перейти на страницу отправки кода для сканирования", pasteCodeButtonPressResult);
    }

    public void scanResultPageLoaded() {
        Boolean scanResult = pageLoadWait.until(ExpectedConditions.urlMatches("http://tool.smartdec.net/scan/[0-9a-z]*"));
        Assert.assertTrue("Не удалось перейти на страницу с результатами анализа кода", scanResult);
        // здесь можно также сделать ожидание пока не исчезнет элемент, демонстрирующий загрузку
        WebElement saveButton = pageLoadWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[text()[contains(.,'save')]]")));
        Assert.assertNotNull("Не удалось дождаться появления кнопки save на странице с результатами анализа кода", saveButton);
    }
}
