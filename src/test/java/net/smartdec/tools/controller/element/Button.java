package net.smartdec.tools.controller.element;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Button extends Element{

    public Button(WebDriver wd) {
        super(wd);
    }

    public WebElement startUsing() {
        return getElement("Кнопка start using не найдена на странице",
                By.id("btn-start"));
    }

    public WebElement pasteCode() {
        return getElement("Кнопка paste code не найдена на странице",
                By.xpath("//*[@id='actions-ul']//*[text()='paste code']"));
    }

    public WebElement submitForScan() {
        return getElement("Не удалось найти кнопку submit for scan",
                By.xpath("//*[@id='codeblock']/..//span[contains(., 'submit for scan')]")); /* Запрос ищет не блок button
                 Дело в том, как устроен блок button на странице.
                 Кликабельная область занимает только часть блока button.
                 Если взять блок button и выполнить метод click(), он выполнится по центру элемента.
                 В кликабельную область не попадет */
    }
}
