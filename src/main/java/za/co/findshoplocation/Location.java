package za.co.findshoplocation;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Location {

    public static void main(String args[]) throws IOException {
        String os = System.getProperty("os.name").toLowerCase();
        String path = System.getProperty("user.dir");

        FileWriter writer = new FileWriter(path+"\\location.csv");
        ChromeDriver driver = new ChromeDriver();

            List<String> locationValues = new ArrayList<String>();
            List<String> phoneNumbers = new ArrayList<String>();

        if(os.contains("mac"))
        {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"chromedriver");
        }
        else
        {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\chromedriver.exe");

        }
        int count = 0;
        driver.get("https://www.chick-fil-a.com/");

        for(ELocation eLocation : ELocation.values())
        {

            System.out.println(count);
            count++;

            driver.findElement(By.xpath("//*[@id=\"find-location\"]")).click();
            driver.findElement(By.xpath("//*[@id=\"location-search\"]")).sendKeys(eLocation.name);
            driver.findElement(By.xpath("/html/body/div[1]/div[1]/header/div/div/div[1]/div[2]/div[2]/form/button")).click();
            driver.switchTo().frame("locatoriframe");
            new WebDriverWait(driver, 25).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"js-yl-12104270\"]/article/div[2]/div[1]/div[2]/a")));
            driver.findElement(By.xpath("//*[@id=\"js-yl-12104270\"]/article/div[2]/div[1]/div[2]/a")).click();

            String address = driver.findElementById("LocationFrameId").findElement(By.xpath("//*[@id=\"LocationFrameId\"]/div[1]/div/div[2]/div[1]/p[1]")).getText();
            String phoneNumber = driver.findElementById("LocationFrameId").findElement(By.xpath("//*[@id=\"LocationFrameId\"]/div[1]/div/div[2]/div[2]/div[1]/div[2]/p")).getText();
            System.out.println("Address : "+address + " Phone : " +phoneNumber);
            locationValues.add(address + " "+phoneNumber);
            System.out.println(count);

        }

        String collect = String.join(",", locationValues);
        writer.write(collect);
        writer.close();


    }
}
