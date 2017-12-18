package org.qas.jbehave.example.steps;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.jbehave.core.annotations.AfterStory;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SimpleSearchSteps {

    protected WebDriver driver;

    public SimpleSearchSteps() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\phuta\\AppData\\Roaming\\npm\\node_modules\\protractor\\node_modules\\webdriver-manager\\selenium\\chromedriver_2.34.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Given("The Google homepage")
    public void theGoogleHomepage() {
        driver.get("http://www.google.com");

    }

    @When("I search for \"$term\"")
    public void searchForTerm(final String term) throws InterruptedException {
        WebElement elem = driver.findElement(By.xpath("//input[@title='Search']"));
        elem.sendKeys(term);
        elem.sendKeys(Keys.ENTER);
    }
    @Then("the text \"$textFragment\" is present")
    public void shouldSee(final String textFragment) {
        WebElement elem = driver.findElement(By.xpath("//*[contains(., '"+ textFragment+"')]"));
        assert(elem.isDisplayed());
    }

    @When("I click the link \"$linkText\"")
    public void followLinkWithText(final String linkText) {
        WebElement elem = driver.findElement(By.xpath("//a[contains(., '"+ linkText+"')]"));
        elem.click();
        // selenium.waitForPageToLoad(pageLoadTimeout);
    }

    @Then("the page's URL should be \"$url\"")
    public void pageURLShouldBe(final String url) {
        assertThat(driver.getCurrentUrl(), is(url));
    }

    @AfterStory // equivalent to @AfterStory(uponGivenStory=false)
    public void afterStory() {
        driver.close();
    }
}