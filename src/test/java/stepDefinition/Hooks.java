package stepDefinition;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import java.io.File;
import java.io.IOException;
public class Hooks {
    protected static WebDriver driver;
    private long startTime;
    public static WebDriver getDriver() {
        return driver;
    }
    @Before
    public void setUpBrowser(){
        startTime = System.currentTimeMillis();
        String browserName = System.getProperty("browser", "chrome"); // Default to Chrome
        System.out.println("Browser is: " + browserName);
        switch (browserName){
            case "chrome" :
//                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--headless=new"); // Enables headless mode
                chromeOptions.addArguments("--disable-gpu");
                chromeOptions.addArguments("--window-size=1920,1080");
                this.driver = new ChromeDriver(chromeOptions);
                break;
            case "firefox" :
//                FirefoxOptions firefoxOptions = new FirefoxOptions();
//                firefoxOptions.addArguments("--headless");
////                WebDriverManager.firefoxdriver().setup();
                this.driver = new FirefoxDriver();
                break;
            case "safari" :
////                WebDriverManager.safaridriver().setup();
                this.driver = new SafariDriver();
                break;
            case "edge" :
//                EdgeOptions edgeOptions = new EdgeOptions();
//                edgeOptions.addArguments("--headless=new");
//                WebDriverManager.edgedriver().setup();
                this.driver = new EdgeDriver();
                break;
            default:
                System.out.println("plz supply the right browser...." + browserName);
        }
        this.driver.manage().window().maximize();
    }
    @After
    public void tearDown(Scenario scenario) {
        if(scenario.isFailed()){
            //to attach screenshot to the cucumber Report for the failed scenarios
            byte[] screenShotToAttachReport = ((TakesScreenshot)this.driver).getScreenshotAs(OutputType.BYTES);

            scenario.attach(screenShotToAttachReport,"image/png",scenario.getName());
            //to save screenshot to the folder for the failed scenarios
            takeScreenshot(scenario.getName());
        }
        long duration = System.currentTimeMillis() - startTime;
//
        String status = scenario.isFailed() ? "FAILED" : "PASSED";
        // Log the test result to InfluxDB
        InfluxLogger.logTestResult(scenario.getName(), status, duration);
        if (this.driver != null) {
            this.driver.quit();
        }
    }
    private void takeScreenshot(String scenarioName) {
//        TakesScreenshot screenshot = (TakesScreenshot) this.driver;
        File temp = ((TakesScreenshot)this.driver).getScreenshotAs(OutputType.FILE);
        File screenshotFile = new File("SCREENSHOT/"+scenarioName+".png");
        try {
            FileUtils.copyFile(temp,screenshotFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
