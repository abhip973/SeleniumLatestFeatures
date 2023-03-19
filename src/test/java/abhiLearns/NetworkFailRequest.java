package abhiLearns;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v110.fetch.Fetch;
import org.openqa.selenium.devtools.v110.fetch.model.RequestPattern;
import org.openqa.selenium.devtools.v110.network.model.ErrorReason;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class NetworkFailRequest {
    public static void main(String[] args) throws InterruptedException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        ChromeDriver driver = new ChromeDriver(options);

        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        Optional<List<RequestPattern>> patterns = Optional.of(Arrays.asList(new RequestPattern(Optional.of("*GetBook*"), Optional.empty(),
                Optional.empty())));
        devTools.send(Fetch.enable(patterns, Optional.empty()));

        devTools.addListener(Fetch.requestPaused(), paused -> {
            Fetch.failRequest(paused.getRequestId(), ErrorReason.FAILED);
        });

        driver.get("https://rahulshettyacademy.com/angularAppdemo");

        driver.manage().window().maximize();

        driver.findElement(By.cssSelector("button[routerlink*='library']")).click();
        Thread.sleep(1000);

        driver.close();
    }
}
