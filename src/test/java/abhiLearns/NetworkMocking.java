package abhiLearns;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v110.fetch.Fetch;

import java.util.Optional;

public class NetworkMocking {
    public static void main(String[] args) throws InterruptedException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        ChromeDriver driver = new ChromeDriver(options);

        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        devTools.send(Fetch.enable(Optional.empty(), Optional.empty()));

        devTools.addListener(Fetch.requestPaused(), requestPaused -> {
            if (requestPaused.getRequest().getUrl().contains("shetty")) {
                String mockURL = requestPaused.getRequest().getUrl().replace("=shetty", "=BadGuy");
                System.out.println(mockURL);

                devTools.send(Fetch.continueRequest(requestPaused.getRequestId(), Optional.of(mockURL), Optional.of(requestPaused.getRequest().getMethod()),
                        Optional.empty(), Optional.empty(), Optional.empty()));
            } else {
                devTools.send(Fetch.continueRequest(requestPaused.getRequestId(), Optional.of(requestPaused.getRequest().getUrl()),
                        Optional.of(requestPaused.getRequest().getMethod()), Optional.empty(), Optional.empty(),
                        Optional.empty()));
            }
        });

        driver.get("https://rahulshettyacademy.com/angularAppdemo");

        driver.manage().window().maximize();

        driver.findElement(By.cssSelector("button[routerlink*='library']")).click();

        Thread.sleep(3000);

        System.out.println(driver.findElement(By.cssSelector("p")).getText());

        driver.close();
    }
}
