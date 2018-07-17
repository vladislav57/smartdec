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

/**
 * В этом классе нужно разместить тесты, которые сканируют код, вставленный на форму сайта (code scan, paste code)
 */
public class TestCodeScan {

    @Test
    public void testScanSimpleJavaProgramWithoutErrors() {

        // подготовка к тесту
        // TODO отделить метод setUp который выполняется до теста
        URL url = getClass().getClassLoader().getResource("chromedriver.exe"); /* Не утверждаю, что так правильно.
        Возможно, на реальном проекте лучше хранить драйвер, например, на общем сетевом ресурсе.
        Места он занимает немного, поэтому здесь драйвер добавлен в ресурсы проекта для удобства проверяющего. */
        System.setProperty("webdriver.chrome.driver", url.getFile());
        WebDriver wd = new ChromeDriver();
        WebDriverWait pageLoadWait = new WebDriverWait(wd, 10);

        // переходим на стартовую страницу
        wd.get("http://tool.smartdec.net");

        // TODO убрать дублирование кода в функцию
        // нажимаем на кнопку start using
        WebElement startUsingButton = wd.findElement(By.id("btn-start"));
        Assert.assertNotNull("Кнопка start using не найдена на странице", startUsingButton);
        startUsingButton.click();

        // нажимаем на кнопку paste code
        WebElement pasteCodeButton = wd.findElement(By.xpath("//*[@id='actions-ul']//*[text()='paste code']"));
        Assert.assertNotNull("Кнопка paste code не найдена на странице", pasteCodeButton);
        pasteCodeButton.click();

        // ожидаем загрузки и проверяем страницу для отправки кода на сканирование
        Boolean pasteCodeButtonPressResult = pageLoadWait.until(ExpectedConditions.urlToBe("http://tool.smartdec.net/newscan?type=codeblock"));
        Assert.assertTrue("Не удалось перейти на страницу отправки кода для сканирования", pasteCodeButtonPressResult);

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
        Boolean scanResult = pageLoadWait.until(ExpectedConditions.urlMatches("http://tool.smartdec.net/scan/[0-9a-z]*"));
        Assert.assertTrue("Не удалось перейти на страницу с результатами анализа кода", scanResult);

        // проверяем результат

        // 1 - проверяем, что на странице выведено No Source и No selected bug/source
        WebElement scanResultHeader = wd.findElement(By.xpath("//div[@id='source-block']//*[text()[contains(.,'No Source')]]"));
        Assert.assertNotNull("На странице не найден ожидаемый блок с текстом No source", scanResultHeader);
        WebElement scanResultSelectedBug = wd.findElement(By.xpath("//div[@id='source-block']//*[text()[contains(.,'No selected bug')]]"));
        Assert.assertNotNull("На странице не найден ожидаемый блок с текстом No selected bug", scanResultSelectedBug);

        // 2 - проверяем, что на странице доступен файл codeblock.sol с результатами
        WebElement scanResultCodeBlockFile = wd.findElement(By.xpath("//span[text()='codeblock.sol']"));
        Assert.assertNotNull("На странице не найден ожидаемый файд с результатами codeblock.sol", scanResultCodeBlockFile);

        // завершаем работу
        // TODO отделить метод tearDown который выполняется после теста
        wd.quit();
    }
}
