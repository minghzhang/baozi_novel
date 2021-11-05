package com.louis.baozi.baozi_novel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.concurrent.TimeUnit;

/**
 * @date : 2021/11/5
 */
public class Test {

    @org.junit.jupiter.api.Test
    public void testPhantomJs() {
        DesiredCapabilities dcaps = new DesiredCapabilities();
        dcaps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, "/Users/landon.zhang/Downloads/phantomjs-2.1.1-macosx/bin/phantomjs");
        //创建无界面浏览器对象
        PhantomJSDriver driver = new PhantomJSDriver(dcaps);
        try {
            //设置等待时间
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            driver.get("https://search.jd.com/Search?keyword=%E6%89%8B%E6%9C%BA&enc=utf-8&wq=%E6%89%8B%E6%9C%BA&pvid=5d0555f9f8ba43ba82495d47679ff6ab");
            driver.executeScript("window.scrollTo(0, document.body.scrollHeight - 300)");
            Thread.sleep(2001);
            WebElement element = driver.findElementByCssSelector("div#J_goodsList");
            element.findElements(By.cssSelector("li.gl-item"))
                    .forEach(e -> System.out.println(e.getText()));
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            driver.close();
            driver.quit();
        }
    }

    @org.junit.jupiter.api.Test
    public void testChrome() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "/Users/landon.zhang/Downloads/chromedriver");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("--window-size=1920,1080");
        ChromeDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://search.jd.com/Search?keyword=%E6%89%8B%E6%9C%BA&enc=utf-8&wq=%E6%89%8B%E6%9C%BA&pvid=5d0555f9f8ba43ba82495d47679ff6ab");
        driver.executeScript("window.scrollTo(0, document.body.scrollHeight - 300)");
        Thread.sleep(2000);
        WebElement element = driver.findElementByCssSelector("div#J_goodsList");
        element.findElements(By.cssSelector("li.gl-item"))
                .forEach(e -> System.out.println(e.getText()));
        driver.close();
    }


}
