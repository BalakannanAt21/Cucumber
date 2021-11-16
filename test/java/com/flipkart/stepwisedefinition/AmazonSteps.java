package com.flipkart.stepwisedefinition;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AmazonSteps {
	static String input;
	@Given("User lauchs amazon application")
	public void user_lauchs_amazon_application() {
		
		Hooks.driver.get("https://www.amazon.in/");
	}

	@Given("User enters Login credentials and click login button")
	public void user_enters_login_credentials_and_click_login_button() {
		try {
			WebElement button = Hooks.driver.findElement(By.xpath("//button[text()='✕']"));
			button.isDisplayed();
			button.click();
		} catch (Exception e) {
			System.out.println("pop messeage is not displayed");
		}
	}

	@When("user enters mobiles in search box")
	public void user_enters_mobiles_in_search_box() {
		input = "Samsung";
	   Hooks.driver.findElement(By.id("twoabsearchtextbox")).sendKeys("mobiles",Keys.ENTER);
	}
static String parentUrl;
	@When("user enters desired product and click on add to cart")
	public void user_enters_desired_product_and_click_on_add_to_cart() throws InterruptedException {
		WebElement mobiles = Hooks.driver.findElement(By.xpath("//div[contains(text(),'"+input+"')][1]"));
		Thread.sleep(3000);
		mobiles.click();
		
		 parentUrl = Hooks.driver.getWindowHandle();
		Set<String> childURL = Hooks.driver.getWindowHandles();
		for (String child : childURL) { 
			if (!parentUrl.equals(child)) {
				Hooks.driver.switchTo().window(child);
		}
	}
		Hooks.driver.findElement(By.xpath("//button[text()='ADD TO CART']")).click();
	}

	@Then("Navigate into add to cart and check order is placed")
	public void navigate_into_add_to_cart_and_check_order_is_placed() throws IOException {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd_MM_yyyy__HH_mm_ss");
		LocalDateTime now = LocalDateTime.now();
		String format = dtf.format(now);
		System.out.println(format);

		TakesScreenshot tk = (TakesScreenshot) Hooks.driver;
		File source = tk.getScreenshotAs(OutputType.FILE);
		File target = new File(".//target//report"+format+".png");
		FileUtils.copyFile(source, target);
	}
}
