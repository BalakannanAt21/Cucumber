package com.flipkart.stepwisedefinition;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class MobileSearchSteps {
	static WebDriver driver;
	
	@Given("User launch flipkart application")
	public void user_launch_flipkart_application() {
		
		WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.flipkart.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
   
	}

	@Given("User login credentials and click on login button")
	public void user_login_credentials_and_click_on_login_button() {
		try {
			WebElement button = driver.findElement(By.xpath("//button[text()='✕']"));
			button.isDisplayed();
			button.click();
		} catch (Exception e) {
			System.out.println("pop messeage is not displayed");
		}
	}

	@When("User enters mobiles in search box")
	public void user_enters_mobiles_in_search_box() {
		WebElement searchBox = driver.findElement(By.name("q"));
		searchBox.sendKeys("realme", Keys.ENTER);
	}
	
	static String parentUrl;
	@When("User enter desired product and click on add to cart")
	public void user_enter_desired_product_and_click_on_add_to_cart() {
		WebElement mobiles = driver.findElement(By.xpath("//div[text()='realme Narzo 50A (Oxygen Blue, 64 GB)']"));
		mobiles.click();
		
		 parentUrl = driver.getWindowHandle();
		Set<String> childUrl = driver.getWindowHandles();
		for (String child : childUrl) {
			if (!parentUrl.equals(child)) {
				driver.switchTo().window(child);
		}
	}
		driver.findElement(By.xpath("//button[text()='ADD TO CART']")).click();
}
	@Then("Navigate into add to cart and check order is placed correctly")
	public void navigate_into_add_to_cart_and_check_order_is_placed_correctly() {
		
		driver.switchTo().window(parentUrl);
		
		driver.findElement(By.xpath("//span[text()='Cart']")).click();
		try {
			WebElement button = driver.findElement(By.xpath("//button[text()='✕']"));
			button.isDisplayed();
			button.click();
		} catch (Exception e) {
			System.out.println("pop messeage is not displayed");
		}
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Place Order']")).isDisplayed());
	}	
}
