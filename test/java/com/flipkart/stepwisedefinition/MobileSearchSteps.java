package com.flipkart.stepwisedefinition;


import java.util.List;
import java.util.Map;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class MobileSearchSteps {
	
	static String input;
	@Given("User launch flipkart application")
	public void user_launch_flipkart_application() {
		
    Hooks.driver.get("https://www.flipkart.com/");
        
	}

	@Given("User login credentials and click on login button")
	public void user_login_credentials_and_click_on_login_button() {
		try {
			WebElement button = Hooks.driver.findElement(By.xpath("//button[text()='✕']"));
			button.isDisplayed();
			button.click();
		} catch (Exception e) {
			System.out.println("pop messeage is not displayed");
		}
	}
   
	@When("User enters mobiles in search box")
	public void user_enters_mobiles_in_search_box()  { 
		
		input = "Samsung";
		WebElement searchBox = Hooks.driver.findElement(By.name("q"));
		searchBox.sendKeys("Iphone",Keys.ENTER);
	}
	static String parentUrl;
	@When("User enter desired product and click on add to cart")
	public void user_enter_desired_product_and_click_on_add_to_cart() throws InterruptedException {
		
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

	@Then("Navigate into add to cart and check order is placed correctly")
	public void navigate_into_add_to_cart_and_check_order_is_placed_correctly() {
   
		Hooks.driver.switchTo().window(parentUrl);
		
		Hooks.driver.findElement(By.xpath("//span[text()='Cart']")).click();
		try {
			WebElement button = Hooks.driver.findElement(By.xpath("//button[text()='✕']"));
			button.isDisplayed();
			button.click();
		} catch (Exception e) {
			System.out.println("pop messeage is not displayed");
		}
		//Assert.assertTrue(Hooks.driver.findElement(By.xpath("//span[text()='Place Order']")).isDisplayed());
	}	
	@When("User enters mobiles in search box by {int} dimensional list")
	public void user_enters_mobiles_in_search_box_by_dimensional_list(DataTable dataTable) {
	   
		List<String> data = dataTable.asList(String.class);
		WebElement searchBox = Hooks.driver.findElement(By.name("q"));
		input = data.get(1);
		searchBox.sendKeys(input, Keys.ENTER);
	}
	@When("User enters mobiles in search box by {int} dimensional Map concept")
	public void user_enters_mobiles_in_search_box_by_dimensional_map_concept(DataTable dataTable) {
		
		Map <String,String> data = dataTable.asMap(String.class, String.class);
		
		WebElement searchBox = Hooks.driver.findElement(By.name("q"));
		
		input = data.get("Phone1");
		searchBox.sendKeys(input, Keys.ENTER);
	} 

	@When("User enters mobiles in search box {string}")
	public void user_enters_mobiles_in_search_box(String PhoneName) {

        WebElement searchBox = Hooks.driver.findElement(By.name("q"));
		
		input = PhoneName;
		searchBox.sendKeys(input, Keys.ENTER);
	}
}