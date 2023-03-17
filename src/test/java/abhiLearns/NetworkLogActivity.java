package abhiLearns;

import org.asynchttpclient.Response;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v110.network.Network;
import org.openqa.selenium.devtools.v110.network.model.Request;

import java.sql.PreparedStatement;
import java.util.Optional;

public class NetworkLogActivity {
    public static void main(String[] args) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        ChromeDriver driver = new ChromeDriver(options);

        DevTools devTools = driver.getDevTools();
        devTools.createSession();
        devTools.send(Network.enable(Optional.empty(),Optional.empty(),Optional.empty()));

        devTools.addListener(Network.requestWillBeSent(),request ->{

            Request req = request.getRequest();
            System.out.println("Requested URL: "+req.getUrl());
        });

        //Event will get fired, for that we have listeners
        devTools.addListener(Network.responseReceived(), response -> {
            Response res = (Response) response.getResponse();
            System.out.println("Response URL"+res.getUri());
            System.out.println("Status Code"+res.getStatusCode());
        });

        driver.get("https://rahulshettyacademy.com/angularAppdemo/");
        driver.findElement(By.xpath("//button[@routerlink='/library' ]")).click();
        driver.close();
    }
}
