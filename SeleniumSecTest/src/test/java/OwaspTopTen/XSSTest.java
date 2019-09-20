package OwaspTopTen;
 
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hamcrest.core.IsNull;
import java.lang.String;
import static java.lang.Thread.sleep;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
        
public class XSSTest {
    
    private WebDriver driver;
    private String pathDriver = "C:\\chromedriver.exe";
    
    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver",pathDriver);
        driver = new ChromeDriver();
    }    
    
    @After
    public void tearDown() {
        driver.quit();
    }    
    
    @Test
    @SuppressWarnings("CallToPrintStackTrace")
    public void xSS() {           
            driver.get("https://juice-shop-trainning.herokuapp.com/#/");     
 
            WebElement myDynamicElement = (new WebDriverWait(driver, 1000))
                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".close-dialog")));
 
            WebElement btn = driver.findElement(By.cssSelector(".close-dialog"));
            btn.click();  
 
            (new WebDriverWait(driver, 1000)).until(ExpectedConditions.presenceOfElementLocated(By.id("searchQuery")));
 
            WebElement textbox = driver.findElement(By.id("searchQuery"));
            textbox.clear(); 
            textbox.sendKeys("<iframe src=\"javascript:alert('XSS')\">");           
 
            (new WebDriverWait(driver, 1000)).until(ExpectedConditions.presenceOfElementLocated(By.id("searchButton")));
 
            WebElement btnBuscar = driver.findElement(By.id("searchButton"));
            btnBuscar.click(); 
 
            (new WebDriverWait(driver, 1000)).until(ExpectedConditions.alertIsPresent());
 
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();            
                      
            assertTrue("Vulnerable", alertText.equals("XXS"));                        
        }    
}
