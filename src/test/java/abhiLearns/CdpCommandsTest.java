package abhiLearns;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CdpCommandsTest {
    public static void main(String[] args) throws InterruptedException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        ChromeDriver driver = new ChromeDriver(options);

        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        Map deviceMetrics = new HashMap();

        deviceMetrics.put("width",600);
        deviceMetrics.put("height",1000);
        deviceMetrics.put("deviceScaleFactor",50);
        deviceMetrics.put("mobile",true);

        driver.executeCdpCommand("Emulation.setDeviceMetricsOverride", deviceMetrics);

        driver.get("https://rahulshettyacademy.com/angularAppdemo/");
        Thread.sleep(1000);

        driver.findElement(By.cssSelector("span.navbar-toggler-icon")).click();
        Thread.sleep(3000);
        List<WebElement> items = driver.findElements(By.cssSelector("li.nav-item"));
        items.stream().forEach(s -> System.out.println(s.getText()));
        driver.quit();

    }
}
