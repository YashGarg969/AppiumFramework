package tests;

import base.BaseTest;
import org.testng.annotations.Test;

public class DemoTest extends BaseTest {

    @Test
    public void openAppTest() throws InterruptedException {
        System.out.println("Waiting");
        Thread.sleep(2000);
        System.out.println("App is opened");
    }
}
