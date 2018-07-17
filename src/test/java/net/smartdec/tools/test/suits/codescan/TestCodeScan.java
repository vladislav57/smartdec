package net.smartdec.tools.test.suits.codescan;

import net.smartdec.tools.test.BaseTest;
import org.junit.Assert;
import org.junit.Test;

/**
 * В этом классе нужно разместить тесты, которые сканируют код, вставленный на форму сайта (code scan, paste code)
 * Test suite привязан к классу. Так получается при выборе Junit вместо TestNG
 */
public class TestCodeScan extends BaseTest {

    // вот так должен выглядеть тест, ради этого написан остальной код

    @Test
    public void testScanSimpleTextWithoutProgrammingErrors() {

        app.goTo().mainPage();
        app.button().startUsing().click();
        app.button().pasteCode().click();
        app.waitUntil().pasteCodePageLoaded();
        app.element().pasteCodeTextArea().sendKeys("my beautiful code");
        app.button().submitForScan().click();
        app.waitUntil().scanResultPageLoaded();

        Assert.assertTrue("Не найден заголовок No source на странице с результатами анализа кода",
                app.element().scanResultHeader().getText().contains("No Source"));
        Assert.assertTrue("Не найдена фраза No selected bug на странице с результатами анализа кода",
                app.element().scanResultSelectedBug().getText().contains("No selected bug"));
        Assert.assertTrue("На странице не найден ожидаемый файд с результатами codeblock.sol",
                app.element().scanResultFiles().getText().contains("codeblock.sol"));
    }
}
