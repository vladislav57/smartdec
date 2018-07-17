package net.smartdec.tools.test;

import net.smartdec.tools.controller.Application;
import org.junit.After;
import org.junit.Before;

public class BaseTest {
    public static Application app = new Application();

    @Before
    public void setUp() {
        app.init();
    }

    @After
    public void tearDown() {
        app.destroy();
    }
}
