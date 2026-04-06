package StepDefination;

import Resources.JsonUtils;
import Resources.Utility;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;

import static Resources.Utility.scrollAndClick;
import static Resources.Utility.switchToNewWindow;
import static StepDefination.Base.driver;

public class Gluecode {

    private String CurrentTc;

    @Given("User opens Amazon website")
    public void user_opens_amazon_website() {
       driver.get("https://www.amazon.in");
    }
    @When("User enters username and password for {string}")
    public void user_enters_and(String tc) {
        CurrentTc=tc;
        String email= JsonUtils.get(CurrentTc,"username");
        String password= JsonUtils.get(CurrentTc,"password");

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.findElement(By.xpath("//span[@id='nav-link-accountList-nav-line-1']")).click();
       driver.findElement(By.xpath("//input[@id='ap_email_login']")).sendKeys(email);
       driver.findElement(By.xpath("//input[@type='submit']")).click();
       driver.findElement(By.xpath("//input[@id='ap_password']")).sendKeys(password);

    }
    @When("User clicks on login button")
    public void user_clicks_on_login_button() {
    driver.findElement(By.xpath("//input[@id='signInSubmit']")).click();

    }
    @Then("User should see the homepage")
    public void user_should_see_the_homepage() {

        String bodyText = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue(bodyText.contains("Hello, Karan"));
    }
    @Then("Logout Application")
    public void Logout_Application() {

        WebElement accountMenu = driver.findElement(By.xpath("//span[@id='nav-link-accountList-nav-line-1']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(accountMenu).perform();

        // click Sign Out
        driver.findElement(By.linkText("Sign Out")).click();
        }

    @Then("Search the product")
    public void search_the_product() {
            String product= JsonUtils.get(CurrentTc,"product");
        driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']")).sendKeys(product);
        driver.findElement(By.xpath("//input[@id='nav-search-submit-button']")).click();
    }

    @Then("Add product to cart")
    public void add_product_to_cart() {
        String product= JsonUtils.get(CurrentTc,"product detailSpec");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

        driver.findElement(By.xpath("//span[contains(text(),'" + product + "')]")).click();
        switchToNewWindow(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

        scrollAndClick(driver,driver.findElement(By.xpath("(//input[@id='add-to-cart-button'])")));
    }

    @Then("Proceed to checkout")
    public void proceed_to_checkout() {
        driver.findElement(By.xpath("//*[@id=\"attach-cart-info-content\"]/div/div[2]/div[2]/span/span/input")).click();
driver.findElement(By.xpath("//input[@name='proceedToRetailCheckout']")).click();

    }

    @Then("Enter address")
    public void enter_address() {
        driver.findElement(By.linkText("Add delivery instructions")).click();



    }

    @Then("Enter instruction")
    public void enter_instruction() {

        String instruction=JsonUtils.get(CurrentTc , "Instruction_adding");
        driver.findElement(By.xpath("//textarea[@id='freeTextInstruction-HOUSE']")).sendKeys(instruction);

        driver.findElement(By.xpath("//input[@aria-labelledby='cdp-save-button-announce']")).click();

        driver.findElement(By.xpath("//input[@data-csa-c-slot-id='checkout-primary-continue-shipaddressselect']")).click();

    }

    @Then("Select payment")
    public void select_payment() {
        String paycode=JsonUtils.get(CurrentTc,"Payment_code");
        driver.findElement(By.xpath("//input[@placeholder='Enter Code']")).sendKeys(paycode);

        driver.findElement(By.xpath("//input[@name='ppw-claimCodeApplyPressed']")).click();
        //Home page again and logout
        driver.findElement(By.xpath("//a[@id='nav-logo-sprites']")).click();


    }


}
