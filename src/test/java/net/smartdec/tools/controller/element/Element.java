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
        return getElement("Не удалось найти поле codeblock для ввода кода",
                By.id("codeblock"));
    }

    public WebElement scanResultHeader() {
        return getElement("На странице не найден ожидаемый блок с текстом No source",
                By.xpath("//div[@id='source-block']"));
    }

    public WebElement scanResultSelectedBug() {
        return getElement("На странице не найден ожидаемый блок с текстом No selected bug",
                By.xpath("//div[@id='source-block']"));
    }

    public WebElement scanResultFiles() {
        return getElement("На странице не найден ожидаемый файд с результатами codeblock.sol",
                By.xpath("//div[@id='source-block']/following-sibling::*[1]"));
    }

    public WebElement getElement(String errorMsg, By locator) {
        WebElement element = wd.findElement(locator);
        Assert.assertNotNull(errorMsg, element);
        return element;
    }
}
