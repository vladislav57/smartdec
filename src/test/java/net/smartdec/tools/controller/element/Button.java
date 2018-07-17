package net.smartdec.tools.controller.element;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Button {
    private WebDriver wd;

    public Button(WebDriver wd) {
        this.wd = wd;
    }

    public WebElement startUsing() {
        return getElement("Кнопка start using не найдена на странице",
                By.id("btn-start"));
    }

    public WebElement pasteCode() {
        return getElement("Кнопка paste code не найдена на странице",
                By.xpath("//*[@id='actions-ul']//*[text()='paste code']"));
    }

    public WebElement getElement(String errorMsg, By locator) {
        WebElement element = wd.findElement(locator);
        Assert.assertNotNull(errorMsg, element);
        return element;
    }

    public WebElement submitForScan() {
        WebElement submitForScanButton = wd.findElement(
                By.xpath("//*[@id='codeblock']/..//span[contains(., 'submit for scan')]")); /* Внимательный читатель
                 спросит, зачем так сделано. Дело в том, как устроен блок button на странице.
                 Кликабельная область занимает только часть блока button.
                 Если взять блок button и выполнить метод click(), он выполнится по центру элемента.
                 В кликабельную область не попадет */
        Assert.assertNotNull("Не удалось найти кнопку submit for scan", submitForScanButton);
        return submitForScanButton;
    }
}
