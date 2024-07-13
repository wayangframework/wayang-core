package id.web.gosoft.wayangframework;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Unit test for simple App.
 */
public class BaseTest {
    public ChromeDriver chromeDriver;
    public ChromeOptions options = new ChromeOptions();

    @BeforeEach
    public void seleniumTest() {
        setupLocalDriver();
        chromeDriver = new ChromeDriver(options);
    }
    private void setupLocalDriver(){
        options.addArguments("--no-sandbox");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-dev-shm-usage");
        if(Optional.ofNullable(System.getenv("CHROME_MODE")).orElse("").equalsIgnoreCase("headless")){
            options.addArguments("--headless");
            System.out.println("Running With headless mode");
        }else{
            System.out.println("Running Without headless mode");
        }
    }

    /**
     * initial Test
     */
    @Test
    public void testSelenium(){
        chromeDriver.get("http://gosoft.web.id/selenium");
        chromeDriver.findElement(By.id("new-tab")).click();
        List<String> windwoHandles = chromeDriver.getWindowHandles().stream().collect(Collectors.toList());
        chromeDriver.switchTo().window(windwoHandles.get(1));
        WebDriverWait wait = new WebDriverWait(chromeDriver, Duration.ofSeconds(20));
        By blogBtn = By.xpath("//*[@id=\"download\"]/a");
        wait.pollingEvery(Duration.ofMillis(500)).until(d ->d.findElement(blogBtn).isDisplayed());
        assertThat(chromeDriver.findElement(blogBtn).getText() , Matchers.containsString("Blog") );
    }

    @AfterEach
    public void tearDownTest(){
        chromeDriver.quit();
    }
}
