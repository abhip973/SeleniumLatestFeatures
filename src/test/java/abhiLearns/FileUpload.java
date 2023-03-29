package abhiLearns;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
//import org.openqa.selenium.devtools.v111.runtime.Runtime;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;

public class FileUpload {
    public static void main(String[] args) throws IOException, InterruptedException {
        String downloadDir = System.getProperty("user.dir");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.default_directory", downloadDir);

        options.setExperimentalOption("prefs", chromePrefs);
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        driver.get("https://smallpdf.com/pdf-to-jpg");
        driver.findElement(By.cssSelector("span.sc-8s01yt-5.gGeCVP")).click();

        Thread.sleep(2000);
        //Now we need to call .exe file
        Runtime.getRuntime().exec("C:\\Users\\punja\\OneDrive\\Documents\\Selenium\\uploadFile.exe");
        WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(10));
        w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Convert entire pages']")));
        driver.findElement(By.xpath("//div[text()='Convert entire pages']")).click();
        driver.findElement(By.xpath("//span[text()='Choose option']")).click();
        w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Download Zip File']")));
        driver.findElement(By.xpath("//span[text()='Download Zip File']")).click();
        Thread.sleep(5000);

        //To verify if the file is downloaded in our system
        File f = new File(downloadDir + "/Aadhar Card-images.zip");
        if (f.exists()) {
            System.out.println("File downloaded successfully");
        } else {
            System.out.println("File not downloaded");
        }
        if (f.delete()) {
            System.out.println("File Deleted");
        }
        driver.close();
    }
}
