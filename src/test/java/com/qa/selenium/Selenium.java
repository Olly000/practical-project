package com.qa.selenium;

import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;

import com.qa.runner.PracticalProjectApplication;

@SpringBootTest(classes = {PracticalProjectApplication.class})
public class Selenium {

	private static WebDriver driver;
	
	@BeforeAll()
	public static void setup() {
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		driver = new ChromeDriver();
		System.out.println("driver = ChromeDriver");
	}
	
	@AfterAll()
	public static void tearDown() {
		driver.quit();
	}
	
	// page tests
	@Test
	public void indexPageTest() {
		driver.get("http://localhost:8081/index.html");
		Assert.assertEquals("Musiconnect", driver.getTitle());
	}
	
	@Test
	public void searchPageTest() {
		driver.get("http://localhost:8081/search.html");
		Assert.assertEquals("Search Page", driver.getTitle());
	}
	
	@Test
	public void addPageTest() {
		driver.get("http://localhost:8081/add.html");
		Assert.assertEquals("Add Page", driver.getTitle());
	}
	
	@Test
	public void editPageTest() {
		driver.get("http://localhost:8081/edit.html");
		Assert.assertEquals("Update Page", driver.getTitle());
	}
	
	@Test
	public void deletePageTest() {
		driver.get("http://localhost:8081/delete.html");
		Assert.assertEquals("Delete Page", driver.getTitle());
	}
	
	//functional tests
	@Test
	  public void showallmusicians() {
	    driver.get("http://localhost:8081/index.html");
	    driver.manage().window().setSize(new Dimension(1093, 899));
	    driver.findElement(By.id("search-for")).click();
	    driver.findElement(By.id("entity-choice")).click();
	    {
	      WebElement dropdown = driver.findElement(By.id("entity-choice"));
	      dropdown.findElement(By.xpath("//option[. = 'Musician']")).click();
	    }
	    driver.findElement(By.id("all")).click();
	  }
	
	@Test
	  public void showallrecordings() {
	    driver.get("http://localhost:8081/index.html");
	    driver.manage().window().setSize(new Dimension(1093, 899));
	    driver.findElement(By.id("search-for")).click();
	    driver.findElement(By.id("entity-choice")).click();
	    {
	      WebElement dropdown = driver.findElement(By.id("entity-choice"));
	      dropdown.findElement(By.xpath("//option[. = 'Recording']")).click();
	    }
	    driver.findElement(By.id("all")).click();
	  }
	
	 @Test
	  public void showonemusician() {
	    driver.get("http://localhost:8081/index.html");
	    driver.manage().window().setSize(new Dimension(1093, 899));
	    driver.findElement(By.id("search-for")).click();
	    driver.findElement(By.id("entity-choice")).click();
	    {
	      WebElement dropdown = driver.findElement(By.id("entity-choice"));
	      dropdown.findElement(By.xpath("//option[. = 'Musician']")).click();
	    }
	    driver.findElement(By.id("input-value")).click();
	    driver.findElement(By.id("input-value")).sendKeys("Sam Prekop");
	    driver.findElement(By.cssSelector(".list-item:nth-child(3) > input")).click();
	  }
	 
	 @Test
	  public void testshowallbands() {
	    driver.get("http://localhost:8081/index.html");
	    driver.manage().window().setSize(new Dimension(1093, 899));
	    driver.findElement(By.id("search-for")).click();
	    {
		      WebElement dropdown = driver.findElement(By.id("entity-choice"));
		      dropdown.findElement(By.xpath("//option[. = 'Band']")).click();
		    }
	    driver.findElement(By.id("all")).click();
	  }
	 
	 @Test
	  public void addoneband() {
	    driver.get("http://localhost:8081/index.html");
	    driver.manage().window().setSize(new Dimension(1093, 899));
	    driver.findElement(By.id("add-item")).click();
	    driver.findElement(By.id("bandName")).click();
	    driver.findElement(By.id("bandName")).sendKeys("fuzz");
	    driver.findElement(By.id("genre")).sendKeys("psych");
	    driver.findElement(By.id("yearFormed")).sendKeys("2016");
	    driver.findElement(By.id("submit-band")).click();
	  }
	 
	 @Test
	  public void updatemusician() {
	    driver.get("http://localhost:8081/index.html");
	    driver.manage().window().setSize(new Dimension(1093, 899));
	    driver.findElement(By.id("edit-item")).click();
	    driver.findElement(By.id("fullName")).click();
	    driver.findElement(By.id("fullName")).sendKeys("David Pajo");
	    driver.findElement(By.id("instrument")).sendKeys("bass");
	    driver.findElement(By.id("update-musician")).click();
	  }
	 
	 @Test
	  public void deleterecording() {
	    driver.get("http://localhost:8081/index.html");
	    driver.manage().window().setSize(new Dimension(1093, 899));
	    driver.findElement(By.id("delete-item")).click();
	    driver.findElement(By.id("entity-choice")).click();
	    {
	      WebElement dropdown = driver.findElement(By.id("entity-choice"));
	      dropdown.findElement(By.xpath("//option[. = 'Recording']")).click();
	    }
	    driver.findElement(By.id("input-value")).click();
	    driver.findElement(By.id("input-value")).sendKeys("one bedroom");
	    driver.findElement(By.id("delete-now")).click();
	  }
	 
}
