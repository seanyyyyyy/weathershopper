import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NewTest {
    private WebDriver driver;

    @Test
    public void testEasy() {
        // open page
        driver.get("http://weathershopper.pythonanywhere.com/");

        // parse temperature value
        WebElement temp = driver.findElement(By.id("temperature"));
            //remove trail chars and whitespace
            String cuttemp = temp.getText().substring(0, temp.getText().length()-2);
            cuttemp = cuttemp.replaceAll("\\s","");
            System.out.println("Temperature today is: "+cuttemp+".");

        // if temperature < 19 add moisturisers, > 34 add sunscreens
        if (Integer.parseInt(cuttemp) < 19) {
            driver.findElement(By.linkText("Buy moisturizers")).click();
            System.out.println("Time to buy moisturizers!");
            addMoisturisers();
        } else if (Integer.parseInt(cuttemp) > 34) {
            driver.findElement(By.linkText("Buy sunscreens")).click();
            System.out.println("Time to buy sunscreens!");
            addSunscreens();
        }
        else {
            System.out.println("WTF");
        }

        printCartItems();

        payForItems();



    }

    public void addMoisturisers() {
        //Grab list of WebElements and convert to list of ShopItem objects
        List<WebElement> resultsList = driver.findElements(By.xpath(".//button[contains(@class,'btn btn-primary')]"));
        List<ShopItem> onClicks = new ArrayList<>();
        for(WebElement e:resultsList){
            String path = e.getAttribute("onclick"); // example getAttribute("onclick") = addToCart('Mikhail Natural Almond Moisturizer',220)
            // Parse item name
            String name = path.substring(11);
            name = name.substring(0,name.length()-6);
            // Parse item price
            String price = path.substring(path.length()-4);
            price = price.substring(0,price.length()-1);
            onClicks.add( new ShopItem(name, Integer.parseInt(price)));
        }

        // Check - print out Unsorted List
        for(ShopItem e:onClicks) {
            System.out.println(e.toString());
        }
        // Sort List by price
        Collections.sort(onClicks);
        System.out.println("Sorting..");
        for(ShopItem e:onClicks) {
            System.out.println(e.toString());
        }

        // Check if cheapest item is Aloe or Almond
        if(onClicks.get(0).getName().contains("Aloe") || onClicks.get(0).getName().contains("aloe")){
            // Check
            System.out.println("Aloe is cheapest");
            // Add cheapest Aloe
            driver.findElement(By.xpath(".//button[contains(@onclick,'"+onClicks.get(0).getName()+"')]")).click();

            // Run through list and find first Almond
            for(ShopItem e:onClicks){
                if (e.getName().contains("Almond")|| e.getName().contains("almond")) {
                    // Click on add button
                    driver.findElement(By.xpath(".//button[contains(@onclick,'"+e.getName()+"')]")).click();
                    break;
                }
            }
            // Click on cart
            driver.findElement(By.xpath(".//button[contains(@onclick, 'goToCart()')]")).click();
        }
        else if (onClicks.get(0).getName().contains("Almond") || onClicks.get(0).getName().contains("almond")) {
            // Check
            System.out.println("Almond is cheapest");
            // Find and click add button
            driver.findElement(By.xpath(".//button[contains(@onclick,'"+onClicks.get(0).getName()+"')]")).click();
            for(ShopItem e:onClicks){
                if (e.getName().contains("Aloe")|| e.getName().contains("aloe")) {
                    // Click on add button
                    driver.findElement(By.xpath(".//button[contains(@onclick,'"+e.getName()+"')]")).click();
                    break;
                }
            }
            // Click on cart
            driver.findElement(By.xpath(".//button[contains(@onclick, 'goToCart()')]")).click();
        }
    }

    public void addSunscreens() {
        //Grab list of WebElements and convert to list of ShopItem objects
        List<WebElement> resultsList = driver.findElements(By.xpath(".//button[contains(@class,'btn btn-primary')]"));
        List<ShopItem> onClicks = new ArrayList<>();
        for(WebElement e:resultsList){
            String path = e.getAttribute("onclick"); // example getAttribute("onclick") = addToCart('Mikhail Natural Almond Moisturizer',220)
            // Parse item name
            String name = path.substring(11);
            name = name.substring(0,name.length()-6);
            // Parse item price
            String price = path.substring(path.length()-4);
            price = price.substring(0,price.length()-1);
            onClicks.add(new ShopItem(name, Integer.parseInt(price)));
        }

        // Check - print out Unsorted List
        for(ShopItem e:onClicks) {
            System.out.println(e.toString());
        }
        // Sort List by price
        Collections.sort(onClicks);
        System.out.println("Sorting..");
        for(ShopItem e:onClicks) {
            System.out.println(e.toString());
        }

        // Check if cheapest item is SPF 30 or 50
        if(onClicks.get(0).getName().contains("SPF-30") || onClicks.get(0).getName().contains("spf-30")){
            System.out.println("SPF-30 is cheapest");
            driver.findElement(By.xpath(".//button[contains(@onclick,'"+onClicks.get(0).getName()+"')]")).click();
            for(ShopItem e:onClicks){
                if (e.getName().contains("SPF-50")|| e.getName().contains("spf-50")) {
                    // Click on add button
                    driver.findElement(By.xpath(".//button[contains(@onclick,'"+e.getName()+"')]")).click();
                    break;
                }
            }
            // Click on cart
            driver.findElement(By.xpath(".//button[contains(@onclick, 'goToCart()')]")).click();
        }
        else if (onClicks.get(0).getName().contains("SPF-50") || onClicks.get(0).getName().contains("spf-50")) {
            System.out.println("50 is cheapest");
            driver.findElement(By.xpath(".//button[contains(@onclick,'"+onClicks.get(0).getName()+"')]")).click();
            for(ShopItem e:onClicks){
                if (e.getName().contains("SPF-30") || e.getName().contains("spf-30")) {
                    // Click on add button
                    driver.findElement(By.xpath(".//button[contains(@onclick,'"+e.getName()+"')]")).click();
                    break;
                }
            }
            // Click on cart
            driver.findElement(By.xpath(".//button[contains(@onclick, 'goToCart()')]")).click();
        }
    }

    public void printCartItems() {
        // Print out items in cart
        List<WebElement> allRows = driver.findElements(By.xpath("//table[contains(@class,'table table-striped')]"));
        for(WebElement row : allRows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            for(WebElement cell:cells) {
                System.out.println(cell.getText());
            }
        }
    }

    public void  payForItems() {
        driver.findElement(By.xpath(".//button[contains(@class,'stripe-button-el')]")).click();

        WebElement element = driver.findElement(By.xpath(".//div[contains(@class,'Textbox-inputRow')]"));

        WebDriverWait wait = new WebDriverWait(driver,5);
        wait.until(ExpectedConditions.visibilityOf(element));
        element.sendKeys("test@test.com");
    }

    @BeforeTest
    public void beforeTest() {
        //Set firefox browser path
        System.setProperty("webdriver.firefox.bin", "C:\\Users\\sliaw\\AppData\\Local\\Mozilla Firefox\\firefox.exe");
        //Set firefox driver system property and path
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\sliaw\\Documents\\Selenium\\geckodriver-v0.24.0-win64\\geckodriver.exe");
        //Set chromedriver path
        System.setProperty("webdriver.chrome.driver","C:\\Users\\sliaw\\Documents\\Selenium\\chromedriver_win32\\chromedriver.exe");

        //Set firefox proxy to autodetect
        Proxy proxy = new Proxy();
        proxy.setProxyType(Proxy.ProxyType.AUTODETECT);
        FirefoxOptions options = new FirefoxOptions();
        options.setProxy(proxy);

        //Browsers
        driver = new FirefoxDriver(options);
        //driver = new ChromeDriver();
    }

    @AfterTest
    public void afterTest() {
        driver.quit();
    }
}