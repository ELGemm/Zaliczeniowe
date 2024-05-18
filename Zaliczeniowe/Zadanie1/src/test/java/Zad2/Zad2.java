package Zad2;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import zad1.LoginPage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.openqa.selenium.TakesScreenshot;
import java.time.Duration;


public class Zad2 {
    private WebDriver driver;

    @Given("Strona logowania sklepu CodersLab1")
    public void stronaLogowaniaSklepuCodersLab() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://mystore-testlab.coderslab.pl/index.php?controller=authentication&back=my-account");

    }

    @When("Loguje sie1")
    public void logujeSie() {
        zad1.LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAs("yxivomjpzmbxegzjzc@ckptr.com", "qwerty");
    }

    @When("Wybiera do zakupu Hummingbird Printed Sweater")
    public void wybieraDoZakupuHummingbirdPrintedSweater() {
        WebElement category = driver.findElement(By.xpath("//*[@id=\"category-3\"]/a"));
        category.click();
        driver.findElement(By.xpath("//*[@id=\"js-product-list\"]/div[1]/div[2]/article/div/div[2]/h2/a")).click();
    }

    @And("Wybiera rozmiar M i {int}szt i dodaje do koszyka")
    public void wybieraRozmiarMISztIDodajeDoKoszyka(int arg0) {
        WebElement selectElement = driver.findElement(By.xpath("//*[@id=\"group_1\"]"));
        Select select = new Select(selectElement);
        select.selectByVisibleText("M");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebElement quantityInput = driver.findElement(By.xpath("//*[@id=\"quantity_wanted\"]"));
        quantityInput.sendKeys(Keys.chord(Keys.CONTROL, "a"), "5");
        ((JavascriptExecutor) driver).executeScript("window.stop()");

    }

    @When("Przechodzi do okna chceckout i potwierdza adres")
    public void przechodziDoOknaChceckoutIPotwierdzaAdres() {
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"add-to-cart-or-refresh\"]/div[2]/div[1]/div[2]/button"))).click();
        driver.findElement(By.xpath("//*[@id=\"blockcart-modal\"]/div/div/div[2]/div/div[2]/div/div/a")).click();
        driver.findElement(By.xpath("//*[@id=\"main\"]/div/div[2]/div[1]/div[2]/div/a")).click();
        driver.findElement(By.xpath("//*[@id=\"checkout-addresses-step\"]/div/div/form/div[2]/button")).click();
    }

    @And("Wybór metody odbioru i płatności")
    public void wybórMetodyOdbioruIPłatności() {
        driver.findElement(By.xpath("//*[@id=\"js-delivery\"]/div/div[1]/div[1]/div/span/span")).click();
        driver.findElement(By.xpath("//*[@id=\"js-delivery\"]/button")).click();
        driver.findElement(By.xpath("//*[@id=\"payment-option-1-container\"]/label/span")).click();
        driver.findElement(By.xpath("//*[@id=\"conditions_to_approve[terms-and-conditions]\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"payment-confirmation\"]/div[1]/button")).click();
    }

    @Then("Wykonuje screenshot potwierdzenia zamówienia")
    public void wykonujeScreenshotPotwierdzeniaZamówienia() {
        WebElement orderCorfirm = driver.findElement(By.xpath("//*[@id=\"content-hook_payment_return\"]"));
        File screenshotFile = ((TakesScreenshot) orderCorfirm).getScreenshotAs(OutputType.FILE);
        String fileName = "screenshot_" + System.currentTimeMillis() + ".png";
        String folderPath = "C:\\CodersLab\\scrshoots";
        }


    }

