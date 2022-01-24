package quantum.ai.deprecated;

import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxDriverLogLevel;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import quantum.ai.person.Person;
import quantum.ai.person.PersonFacade;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class SeleniumRegistrationService {

    private PersonFacade personFacade = new PersonFacade();

    public SeleniumRegistrationService() {
        System.setProperty("webdriver.gecko.driver","C:\\Program Files\\selenium\\geckodriver.exe");


    }

    @SneakyThrows
    public void register() {
        String url = null;
        try {
            url = getUrl();
        } catch (final Throwable t) {
            return;
        }

        final FirefoxOptions options = new FirefoxOptions();
        options.setLogLevel(FirefoxDriverLogLevel.FATAL);

        final WebDriver driver = new FirefoxDriver(options);
        try {
            driver.get(url);
            final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("body form")));
            final Person person = personFacade.generatePerson();
            final WebElement form = driver.findElement(By.cssSelector("body form"));
            try {
                form.findElement(By.name("firstName")).sendKeys(person.getFirstname());
            } catch (final Throwable t) {
                form.findElement(By.name("first_name")).sendKeys(person.getFirstname());
            }
            try {
                form.findElement(By.name("lastName")).sendKeys(person.getLastname());
            } catch (final Throwable t) {
                form.findElement(By.name("last_name")).sendKeys(person.getLastname());
            }
            form.findElement(By.name("email")).sendKeys(person.getEmail());
            try {
                form.findElement(By.name("password")).sendKeys("1234Abcd");
            } catch (final Throwable t) {
                // skip
            }
            form.findElement(By.name("phone")).sendKeys(person.getPhone());
            form.submit();
            TimeUnit.SECONDS.sleep(15);
            System.out.println("registered: " + driver.getCurrentUrl());
        } catch (final Throwable t) {
            System.out.println("failed.");
        } finally {
            driver.close();
        }
    }

    @SneakyThrows
    private String getUrl() {
        return Jsoup.connect("https://www.google.com/search?q=quantum+ai").execute().parse().select("a.sVXRqc").first().attr("data-rw");
    }
}
