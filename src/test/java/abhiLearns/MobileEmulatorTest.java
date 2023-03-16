package abhiLearns;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v110.emulation.Emulation;

import java.util.List;
import java.util.Optional;

public class MobileEmulatorTest {
    public static void main(String[] args) throws InterruptedException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        ChromeDriver driver = new ChromeDriver(options);

        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        devTools.send(Emulation.setDeviceMetricsOverride(600, 1000, 50, true, Optional.empty(), Optional.<Integer>empty()
                , Optional.<Integer>empty(), Optional.empty(), Optional.empty(), Optional.empty(),
                Optional.empty(), Optional.empty(), Optional.empty()));

        driver.get("https://rahulshettyacademy.com/angularAppdemo/");
        Thread.sleep(1000);

        driver.findElement(By.cssSelector("span.navbar-toggler-icon")).click();
        Thread.sleep(3000);
        List<WebElement> items = driver.findElements(By.cssSelector("li.nav-item"));
        items.stream().forEach(s -> System.out.println(s.getText()));
        driver.quit();
    }
}
