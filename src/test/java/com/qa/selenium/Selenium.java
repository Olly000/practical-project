package com.qa.selenium;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
	public void updatePageTest() {
		driver.get("http://localhost:8081/update.html");
		Assert.assertEquals("Update Page", driver.getTitle());
	}
	
	@Test
	public void deletePageTest() {
		driver.get("http://localhost:8081/delete.html");
		Assert.assertEquals("Delete Page", driver.getTitle());
	}
	
	// navigation test
	@Test
	  public void menubutton() {
	    driver.get("http://localhost:8081/index.html");
	    driver.manage().window().setSize(new Dimension(901, 971));
	    driver.findElement(By.id("search-for")).click();
	    driver.findElement(By.id("menu-return")).click();
	    assertEquals(driver.getTitle(), "Musiconnect");
	    driver.findElement(By.id("add-item")).click();
	    driver.findElement(By.id("menu-return")).click();
	    assertEquals(driver.getTitle(), "Musiconnect");
	    driver.findElement(By.id("update-item")).click();
	    driver.findElement(By.id("menu-return")).click();
	    assertEquals(driver.getTitle(), "Musiconnect");
	    driver.findElement(By.id("delete-item")).click();
	    driver.findElement(By.id("menu-return")).click();
	    assertEquals(driver.getTitle(), "Musiconnect");
	  }
	
	//functional tests
	@Test
	  public void showallrecordingsTest() {
	    driver.get("http://localhost:8081/index.html");
	    driver.manage().window().setSize(new Dimension(1093, 899));
	    driver.findElement(By.id("search-for")).click();
	    driver.findElement(By.id("entity-choice")).click();
	    {
	      WebElement dropdown = driver.findElement(By.id("entity-choice"));
	      dropdown.findElement(By.xpath("//option[. = 'Recording']")).click();
	    }
	    driver.findElement(By.id("all")).click();
	    {
	      String value = driver.findElement(By.id("entity-choice")).getAttribute("value");
	      assertEquals(value, "Recording");
	    }
	  }
	
	
	@Test
	  public void showonemusicianTest() {
	    driver.get("http://localhost:8081/index.html");
	    driver.manage().window().setSize(new Dimension(1093, 899));
	    driver.findElement(By.id("search-for")).click();
	    driver.findElement(By.id("entity-choice")).click();
	    {
	      WebElement dropdown = driver.findElement(By.id("entity-choice"));
	      dropdown.findElement(By.xpath("//option[. = 'Musician']")).click();
	    }
	    {
	      String value = driver.findElement(By.id("entity-choice")).getAttribute("value");
	      assertEquals(value, "Musician");
	    }
	    driver.findElement(By.id("input-value")).click();
	    {
	      WebElement element = driver.findElement(By.id("input-value"));
	      Boolean isEditable = element.isEnabled() && element.getAttribute("readonly") == null;
	      assertTrue(isEditable);
	    }
	    driver.findElement(By.id("input-value")).sendKeys("Sam Prekop");
	    {
	      String value = driver.findElement(By.id("input-value")).getAttribute("value");
	      assertEquals(value, "Sam Prekop");
	    }
	    driver.findElement(By.cssSelector(".list-item:nth-child(3) > input")).click();
	  }
	 
	 
	 @Test
	  public void addbandTest() {
	    driver.get("http://localhost:8081/index.html");
	    driver.manage().window().setSize(new Dimension(943, 901));
	    driver.findElement(By.id("add-item")).click();
	    driver.findElement(By.id("bandName")).click();
	    {
	      WebElement element = driver.findElement(By.id("bandName"));
	      Boolean isEditable = element.isEnabled() && element.getAttribute("readonly") == null;
	      assertTrue(isEditable);
	    }
	    driver.findElement(By.id("bandName")).sendKeys("fuzz");
	    {
	      String value = driver.findElement(By.id("bandName")).getAttribute("value");
	      assertEquals(value, "fuzz");
	    }
	    driver.findElement(By.id("genre")).click();
	    {
	      WebElement element = driver.findElement(By.id("genre"));
	      Boolean isEditable = element.isEnabled() && element.getAttribute("readonly") == null;
	      assertTrue(isEditable);
	    }
	    driver.findElement(By.id("genre")).sendKeys("psych");
	    {
	      String value = driver.findElement(By.id("genre")).getAttribute("value");
	      assertEquals(value, "psych");
	    }
	    driver.findElement(By.id("yearFormed")).click();
	    {
	      WebElement element = driver.findElement(By.id("yearFormed"));
	      Boolean isEditable = element.isEnabled() && element.getAttribute("readonly") == null;
	      assertTrue(isEditable);
	    }
	    driver.findElement(By.id("yearFormed")).sendKeys("2016");
	    {
	      String value = driver.findElement(By.id("yearFormed")).getAttribute("value");
	      assertEquals(value, "2016");
	    }
	    driver.findElement(By.id("submit-band")).click();
	  }
	 
	 @Test
	  public void updatemusicianTest() {
	    driver.get("http://localhost:8081/index.html");
	    driver.manage().window().setSize(new Dimension(1093, 899));
	    driver.findElement(By.id("update-item")).click();
	    driver.findElement(By.id("fullName")).click();
	    {
	      WebElement element = driver.findElement(By.id("fullName"));
	      Boolean isEditable = element.isEnabled() && element.getAttribute("readonly") == null;
	      assertTrue(isEditable);
	    }
	    driver.findElement(By.id("fullName")).sendKeys("David Pajo");
	    {
	      String value = driver.findElement(By.id("fullName")).getAttribute("value");
	      assertEquals(value, "David Pajo");
	    }
	    driver.findElement(By.id("instrument")).click();
	    driver.findElement(By.id("instrument")).sendKeys("bass");
	    {
	      String value = driver.findElement(By.id("instrument")).getAttribute("value");
	      assertEquals(value, "bass");
	    }
	    driver.findElement(By.id("update-musician")).click();
	  }
	 
	 @Test
	  public void deleterecordingTest() {
	    driver.get("http://localhost:8081/index.html");
	    driver.manage().window().setSize(new Dimension(1093, 899));
	    driver.findElement(By.id("delete-item")).click();
	    driver.findElement(By.id("entity-choice")).click();
	    {
	      WebElement dropdown = driver.findElement(By.id("entity-choice"));
	      dropdown.findElement(By.xpath("//option[. = 'Recording']")).click();
	    }
	    {
	      String value = driver.findElement(By.id("entity-choice")).getAttribute("value");
	      assertEquals(value, "Recording");
	    }
	    driver.findElement(By.id("input-value")).click();
	    {
	      WebElement element = driver.findElement(By.id("input-value"));
	      Boolean isEditable = element.isEnabled() && element.getAttribute("readonly") == null;
	      assertTrue(isEditable);
	    }
	    driver.findElement(By.id("input-value")).sendKeys("one bedroom");
	    {
	      String value = driver.findElement(By.id("input-value")).getAttribute("value");
	      assertEquals(value, "one bedroom");
	    }
	    driver.findElement(By.id("delete-now")).click();
	  }
	 
}
