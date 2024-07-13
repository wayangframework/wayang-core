package id.web.gosoft.wayangframework;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Unit test for simple App.
 */
public class BaseTest {
    ChromeDriver chromeDriver;

    @BeforeEach
    public void seleniumTest() {
        chromeDriver = new ChromeDriver();
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
