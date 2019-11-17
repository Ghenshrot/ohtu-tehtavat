package ohtu;

import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Tester {
    
    private static Random random;

    public static void main(String[] args) {
        random = new Random();
        WebDriver driver = new ChromeDriver();

        driver.get("http://localhost:4567");
        sleep(5);

        testCorrectUsernameWrongPassword(driver);
        sleep(5);

        testBackToHome(driver);
        sleep(5);

        testCorrectUsernameCorrectPassword(driver);
        sleep(5);

        /*
        ** This test does not return to the main page after testing.
        ** If it was modified to do so, it would be the same as the other test (testLoggingOutAfterCreatingNewUser).
        ** So this test is not run alone, instead it is run as a part of the other test.
        */
        if (false) {
            testCreateNewUser(driver);
            sleep(5);
        }
        
        testLoggingOutAfterCreatingNewUser(driver);
        sleep(5);

        driver.quit();
    }
    
    private static void testCorrectUsernameCorrectPassword(WebDriver driver) {
        System.out.println("testCorrectUsernameCorrectPassword");
        WebElement element = driver.findElement(By.linkText("login"));
        element.click();

        sleep(2);

        element = driver.findElement(By.id("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.id("password"));
        element.sendKeys("akkep");
        element = driver.findElement(By.name("login"));
        
        sleep(2);
        element.submit();
        
        sleep(2);
        testHeader(driver, "Ohtu Application main page");
        
        testLogout(driver);
    }
    
    private static void testCorrectUsernameWrongPassword(WebDriver driver) {
        System.out.println("testCorrectUsernameWrongPassword");
        WebElement element = driver.findElement(By.linkText("login"));
        element.click();
        sleep(2);

        element = driver.findElement(By.id("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.id("password"));
        element.sendKeys("pekka");

        sleep(2); // For the user viewing, not needed otherwise.
        element = driver.findElement(By.name("login"));
        element.submit();
        
        driver.findElement(By.id("error"));
    }
    
    private static void testCreateNewUser(WebDriver driver) {
        System.out.println("testCreateNewUser");
        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();
        sleep(2);
        
        element = driver.findElement(By.id("username"));
        element.sendKeys("pekka" + random.nextInt());
        element = driver.findElement(By.id("password"));
        element.sendKeys("klsj17293178ojsf");

        sleep(2); // For the user viewing, not needed otherwise.
        element = driver.findElement(By.name("signup"));
        element.submit();
        sleep(2);

        testHeader(driver, "Welcome to Ohtu Application!");
    }
    
    private static void testLoggingOutAfterCreatingNewUser(WebDriver driver) {
        System.out.println("testLoggingOutAfterCreatingNewUser");
        testCreateNewUser(driver);
        
        WebElement element = driver.findElement(By.linkText("continue to application mainpage"));
        element.click();
        sleep(2);
        
        testLogout(driver);
    }
    
    private static void testLogout(WebDriver driver) {
        System.out.println("testLogout");
        WebElement element = driver.findElement(By.linkText("logout"));
        element.click();
        sleep(2);
        testHeader(driver, "Ohtu App");
    }
    
    private static void testBackToHome(WebDriver driver) {
        System.out.println("testBackToHome");
        WebElement element = driver.findElement(By.linkText("back to home"));
        element.click();
        sleep(2);
        
        testHeader(driver, "Ohtu App");
    }
    
    private static void testHeader(WebDriver driver, String header) {
        WebElement element = driver.findElement(By.xpath("//body/h1"));
        if (!element.getText().equals(header)) {
            throw new AssertionError("Failed to find h1 element \"" + header + "\".");
        }
    }
    
    private static void sleep(int n){
        try{
            Thread.sleep(n*1000);
        } catch(Exception e){}
    }
}
