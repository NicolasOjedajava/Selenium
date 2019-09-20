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
    private Map<String, Object> vars;
    JavascriptExecutor js;
    private String pathDriver = "/home/deloitte/Descargas/geckodriver-v0.25.0-linux64/geckodriver";
    
    
    @Before
    public void setUp() {
        
        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
        System.setProperty("webdriver.gecko.driver",pathDriver);
        driver = new FirefoxDriver(dc);
        js = (JavascriptExecutor) driver;
        vars = new HashMap<String, Object>();
        
    }
    
    
    @After
    public void tearDown() {
        driver.quit();
    }
    
    
    @Test
    @SuppressWarnings("CallToPrintStackTrace")
    public void xSS() {

        ArrayList<String> payloads = cargarPayload();

        for(String texto : payloads){
            
            driver.get("http://juice-shop-trainning.herokuapp.com/#/");
            driver.manage().window().setSize(new Dimension(1920, 1040));
            
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                Logger.getLogger(XSSTest.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            WebElement searchBox = driver.findElement(By.id("searchQuery"));
            WebElement searchButton = driver.findElement(By.id("searchButton"));
            
            WebDriverWait waitSeachBox = new WebDriverWait(driver,2000);
            waitSeachBox.until(ExpectedConditions.elementToBeClickable(searchBox));
            
            searchBox.clear();
            
            searchBox.sendKeys(texto);
            
            try {
                
                WebDriverWait waitButton = new WebDriverWait(driver,2000);
                waitButton.until(ExpectedConditions.elementToBeClickable(searchButton));
                
                searchButton.click();
                
                    try {
                        Thread.sleep(4000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(XSSTest.class.getName()).log(Level.SEVERE, null, ex);
                    }   
                
            } catch (UnhandledAlertException f) {
                
                try {
                        
                    Alert alert = driver.switchTo().alert();
                    String alertText = alert.getText();

                    if(!(alertText.equals("")) && !(alertText.equals(null))){
                        System.out.println("XXS INJECTION with payload: " + texto);
                    }

                    
                        
                } catch (NoAlertPresentException e) {
                    e.printStackTrace();
                }
            }

        }

    }
    
    public ArrayList cargarPayload() {

        //Cargar payload
        String path = "/home/deloitte/XSS-Payload.txt"; //path txt payload.
        String linea;
        ArrayList <String> payloads = new ArrayList();

        try {

            FileReader fs = new FileReader(new File(path));
            BufferedReader br = new BufferedReader(fs);

            try {

                while((linea=br.readLine())!=null){
                    payloads.add(linea);
                }

            } catch (IOException ex) {
                Logger.getLogger(XSSTest.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (FileNotFoundException ex) {
          Logger.getLogger(XSSTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return payloads;
    }
}
