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


public class XSSTest {
    
    private WebDriver driver;
    private Map<String, Object> vars;
    JavascriptExecutor js;
    
    
    @Before
    public void setUp() {
        
        System.setProperty("webdriver.chrome.driver","C:\\chromedriver.exe");
        driver = new ChromeDriver();
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
            driver.findElement(By.id("searchQuery")).clear();
            driver.findElement(By.id("searchQuery")).sendKeys(texto);
            driver.findElement(By.id("searchQuery")).click();
          
            try {
                
                driver.findElement(By.id("xxsframe"));
                System.out.println("XXS INJECTION with payload: " + texto);

            } catch (Exception e) {
                ///e.printStackTrace();
            }

        }

    }
    
    public ArrayList cargarPayload() {

        //Cargar payload
        String path = "C:\\XSS.txt"; //path txt payload.
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
