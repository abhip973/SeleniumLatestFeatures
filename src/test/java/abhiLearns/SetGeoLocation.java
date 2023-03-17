package abhiLearns;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;

import java.util.HashMap;
import java.util.Map;

public class SetGeoLocation {
    public static void main(String[] args) throws InterruptedException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        ChromeDriver driver = new ChromeDriver(options);
        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        Map coordinates = new HashMap();

        coordinates.put("latitude", 40);
        coordinates.put("longitude", 3);
        coordinates.put("accuracy",1);
        driver.executeCdpCommand("Emulation.setGeolocationOverride",coordinates);

        driver.get("https://my-location.org/");

        System.out.println(driver.findElement(By.id("address")).getText());

        driver.close();
    }
}
