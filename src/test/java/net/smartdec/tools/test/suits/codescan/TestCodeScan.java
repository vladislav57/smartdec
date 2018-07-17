package net.smartdec.tools.test.suits.codescan;

import net.smartdec.tools.test.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 * В этом классе нужно разместить тесты, которые сканируют код, вставленный на форму сайта (code scan, paste code)
 * Test suite привязан к классу. Так получается при выборе Junit вместо TestNG
 */
public class TestCodeScan extends BaseTest {

    @Test
    public void testScanSimpleJavaProgramWithoutErrors() {

        // переходим на стартовую страницу
        app.goTo().mainPage();

        // TODO убрать дублирование кода в функцию
        // нажимаем на кнопку start using
        app.button().startUsing().click();

        // нажимаем на кнопку paste code
        app.button().pasteCode().click();

        // ожидаем загрузки и проверяем страницу для отправки кода на сканирование
        app.waitUntil().pasteCodePageLoaded();

        // вставляем код в textarea
        app.element().pasteCodeTextArea().sendKeys("my beautiful code");

        // нажимаем кнопку submit for scan
        app.button().submitForScan().click();

        // ожидаем загрузки страницы
        app.waitUntil().scanResultPageLoaded();

        // проверяем результат

        // 1 - проверяем, что на странице выведено No Source и No selected bug
        Assert.assertTrue("Не найден заголовок No source на странице с результатами анализа кода",
                app.element().scanResultHeader().getText().contains("No Source"));
        Assert.assertTrue("Не найдена фраза No selected bug на странице с результатами анализа кода",
                app.element().scanResultSelectedBug().getText().contains("No selected bug"));

        // 2 - проверяем, что на странице доступен файл codeblock.sol с результатами
        Assert.assertTrue("На странице не найден ожидаемый файд с результатами codeblock.sol",
                app.element().scanResultFiles().getText().contains("codeblock.sol"));
    }
}
