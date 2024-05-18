package zad1;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.matches;

public class NewAcc {
    private WebDriver driver;

    @Given("Strona logowania sklepu CodersLab")
    public void stronaLogowaniaSklepuCodersLab() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://mystore-testlab.coderslab.pl/index.php?controller=authentication&back=my-account");

    }

    @When("Loguje sie")
    public void logujeSie() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAs("yxivomjpzmbxegzjzc@ckptr.com", "qwerty");
    }

    @When("Przejście do strony adresu")
    public void userGoesToTheAddressesSection() {
        driver.findElement(By.id("addresses-link")).click();
        driver.findElement(By.xpath("//*[@id=\"content\"]/div[3]/a/span")).click();
    }
    @When("Wypełnienie formularza danymi adresowymi {string} {string} {string} {string} {string} {string}")
    public void wypelnienieFormularzaDanymiAdresowymiAliasAddressCityZippostalCodeCountryPhone(String alias, String address, String city, String zippostalcode, String country, String phone) {
        driver.findElement(By.name("alias")).sendKeys(alias);
        driver.findElement(By.name("address1")).sendKeys(address);
        driver.findElement(By.name("city")).sendKeys(city);
        driver.findElement(By.name("postcode")).sendKeys(zippostalcode);
        driver.findElement(By.name("phone")).sendKeys(phone);
//        WebElement selectElement = driver.findElement(By.id("field-id_country"));
//        Select select = new Select(selectElement);
//        select.selectByVisibleText("country");
    }
    @Then("Sprawdzenie poprawności danych")
    public void sprawdzenieDanych() {
        String address = driver.findElement(By.name("address1")).getAttribute("value");

        String addressRegex = "^[a-zA-Z0-9\\s]";
        Pattern pattern = Pattern.compile(addressRegex);
        Matcher matcher = pattern.matcher(address);

        boolean isValidAddress = matcher.matches();

        if (isValidAddress) {
            System.out.println("Adres jest poprawny");
        } else {
            System.out.println("Adres jest niepoprawny. Dozwolone są tylko litery, cyfry i spacje.");
        }

    }



}