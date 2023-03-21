package abhiLearns;

import com.google.common.collect.ImmutableList;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v110.network.Network;

import java.util.Optional;

public class BlockNetworkRequests {
    public static void main(String[] args) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        ChromeDriver driver = new ChromeDriver(options);

        DevTools devTools = driver.getDevTools();
        devTools.createSession();
        devTools.send(Network.enable(Optional.empty(),Optional.empty(),Optional.empty()));
        devTools.send(Network.setBlockedURLs(ImmutableList.of("*.jpg","*.css")));

        driver.get("https://rahulshettyacademy.com/angularAppdemo");

        driver.findElement(By.linkText("Browse Products")).click();
        driver.manage().window().maximize();
        driver.findElement(By.linkText("Selenium")).click();
        driver.findElement(By.cssSelector(".add-to-cart")).click();

        System.out.println(driver.findElement(By.cssSelector("p")).getText());

        driver.close();
    }
}
