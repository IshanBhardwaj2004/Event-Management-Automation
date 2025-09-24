package SeleniumTestScripts;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.time.Duration;

public class FirstSeleniumTC {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\ISHAN\\Desktop\\chromedriver-win64 (1)\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void testLoginAndNavigate() throws InterruptedException {
        driver.get("http://localhost:4000/events");
        Thread.sleep(2000);

        try {
            WebElement menuBurger = wait.until(ExpectedConditions.elementToBeClickable(By.className("menuBurger")));
            Thread.sleep(2000);
            menuBurger.click();
            System.out.println("✅ Menu burger clicked successfully!");
            Thread.sleep(2500);

            WebElement profileIcon = wait.until(ExpectedConditions.elementToBeClickable(By.id("profileIcon")));
            Thread.sleep(2000);
            profileIcon.click();
            System.out.println("✅ Profile icon clicked successfully!");
        } catch (Exception e) {
            System.out.println("Could not find menu elements. Trying login URL...");
            driver.get("http://localhost:4000/login");
            Thread.sleep(2000);
        }

        Thread.sleep(2000);

        try {
            WebElement loginToggle = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[contains(@class, 'login_signUp_toggle')]/p[text()='LOGIN']")));
            loginToggle.click();
            System.out.println("✅ Selected LOGIN tab");
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println("Login toggle not found or already selected.");
        }

        WebElement emailInput = getElement(By.xpath("//div[contains(@class, 'loginEmail')]/input[@type='email']"),
                                          By.cssSelector(".loginEmail input[type='email']"),
                                          By.xpath("//input[@type='email']"));
        emailInput.clear();
        emailInput.sendKeys("ishan@gmail.com");
        System.out.println("✅ Email entered successfully!");

        WebElement passwordInput = getElement(By.xpath("//div[contains(@class, 'loginPassword')]/input[@type='password']"),
                                              By.cssSelector(".loginPassword input[type='password']"),
                                              By.xpath("//input[@type='password']"));
        passwordInput.clear();
        passwordInput.sendKeys("ishan123");
        System.out.println("✅ Password entered successfully!");

        WebElement loginButton = getElement(By.xpath("//input[@type='submit' and @value='Login']"),
                                            By.cssSelector(".loginBtn"),
                                            By.xpath("//input[@type='submit']"));
        loginButton.click();
        System.out.println("✅ Login button clicked successfully!");

        Thread.sleep(3000);

        WebElement menuBurgerAfterLogin = wait.until(ExpectedConditions.elementToBeClickable(By.className("menuBurger")));
        Thread.sleep(2000);
        menuBurgerAfterLogin.click();
        System.out.println("✅ Menu burger clicked after login!");

        Thread.sleep(2500);

        try {
            WebElement homeLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(), 'Home')]")));
            homeLink.click();
            System.out.println("✅ Home link clicked!");
        } catch (Exception e) {
            System.out.println("Home link not found.");
        }

        Thread.sleep(2000);

        try {
            WebElement hereElement = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[text()='Here']")));
            Thread.sleep(2000);
            hereElement.click();
            System.out.println("✅ 'Here' element clicked!");
        } catch (Exception e) {
            System.out.println("'Here' element not found: " + e.getMessage());
        }

        Thread.sleep(5000); // keep browser open to observe results
    }

    private WebElement getElement(By... locators) {
        for (By locator : locators) {
            try {
                return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            } catch (Exception ignored) {
            }
        }
        throw new RuntimeException("Element not found using given locators");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
        System.out.println("✅ Browser closed.");
    }
}