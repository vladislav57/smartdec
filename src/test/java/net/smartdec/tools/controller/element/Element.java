package net.smartdec.tools.controller.element;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Element {

    private WebDriver wd;

    public Element(WebDriver wd) {
        this.wd = wd;
    }

    public WebElement pasteCodeTextArea() {
        WebElement textArea = wd.findElement(By.id("codeblock"));
        Assert.assertNotNull("Не удалось найти поле codeblock для ввода кода", textArea);
        return textArea;
    }

    public Element isPresent() {
        return this;
    }

    public WebElement scanResultHeader() {
        WebElement scanResultHeader = wd.findElement(By.xpath("//div[@id='source-block']"));
        Assert.assertNotNull("На странице не найден ожидаемый блок с текстом No source", scanResultHeader);
        return scanResultHeader;
    }

    public WebElement scanResultSelectedBug() {
        WebElement scanResultSelectedBug = wd.findElement(By.xpath("//div[@id='source-block']"));
        Assert.assertNotNull("На странице не найден ожидаемый блок с текстом No selected bug", scanResultSelectedBug);
        return scanResultSelectedBug;
    }

    public WebElement scanResultFiles() {
        WebElement scanResultCodeBlockFile = wd.findElement(By.xpath("//div[@id='source-block']/following-sibling::*[1]"));
        Assert.assertNotNull("На странице не найден ожидаемый файд с результатами codeblock.sol", scanResultCodeBlockFile);
        return scanResultCodeBlockFile;
    }
}
