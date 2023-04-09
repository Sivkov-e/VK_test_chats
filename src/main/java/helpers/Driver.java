package helpers;

import com.codeborne.selenide.*;
import org.openqa.selenium.*;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

public class Driver {

    public static void initDriver() {
        TestConfig.initConfig();
        Configuration.pageLoadStrategy = "eager";
        Configuration.browserSize = "1600x1080";
        Configuration.holdBrowserOpen = false;
        Configuration.screenshots = false;

        if (TestConfig.isHeadless()) {
            Configuration.headless = true;
        } else {
            Configuration.headless = false;
        }

        switch (TestConfig.browser) {
            case "chrome":
                Configuration.browser = Browsers.CHROME;
                break;
            case "firefox":
                Configuration.browser = Browsers.FIREFOX;
                break;
            default:
                Configuration.browser = Browsers.CHROME;
                break;
        }
    }

    public static WebDriver currentDriver() {
        return WebDriverRunner.getSelenideDriver().getWebDriver();
    }

    public static void open(String url) {
        Selenide.open(url);
    }

    public static void waitForUrlContains(String urlChunk) {
        WebDriverWait wait = new WebDriverWait(currentDriver(), 10);
        wait.until(ExpectedConditions.urlContains(urlChunk));
    }

    public static void close() {
        currentDriver().quit();
    }


    public static List<LogEntry> getBrowserLogs() {
        LogEntries log = currentDriver().manage().logs().get("browser");
        List<LogEntry> logList = log.getAll();
        return logList;
    }
}