package RealTimeProject.EcommmerseProject.Test;

import static org.openqa.selenium.support.locators.RelativeLocator.with;
import static org.testng.Assert.assertEquals;

import java.time.Duration;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.apache.hc.core5.util.Asserts;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import net.bytebuddy.dynamic.scaffold.TypeWriter.MethodPool.Record.ForDefinedMethod.WithAnnotationDefaultValue;

public class Standalone {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");
	   //List<String> items=Arrays.asList("Brocolli","Mushroom","Onion");
	List<String> items=Arrays.asList("Brocolli","Mushroom","Onion");
	  List<WebElement> veg= driver.findElements(By.xpath("//h4[@class='product-name']"));
	  //or
	 //List<WebElement> veg=driver.findElements(By.cssSelector(".product"));
	  int x=0;
	   for(int i=0;i<veg.size();i++)
	   {
		 List<String> vegitmes=veg.stream().map(s->s.getText().split("-")[0].trim()).collect(Collectors.toList());
		if(items.contains(vegitmes.get(i)))
		{
			x++;
			driver.findElements(By.xpath("//div[@class='product-action']/button")).get(i).click();

		}	
	  if(x==3)
	  {
		  break;
	  }
	  
	  }
	   driver.findElement(By.cssSelector(".cart-icon")).click();
	   WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(6));
	   wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".action-block button:nth-child(1)")));
	   driver.findElement(By.cssSelector(".action-block button:nth-child(1)")).click();
	  List<WebElement> selectedveg= driver.findElements(By.xpath("//p[@class='product-name']"));
	  boolean xyz = false;
	  for(String item:items)
	  {
		  xyz=selectedveg.stream().anyMatch(s->s.getText().split("-")[0].trim().equalsIgnoreCase(item));
	  }
	  Assert.assertTrue(xyz);
	  wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".promoCode")));
	  driver.findElement(By.cssSelector(".promoCode")).sendKeys("rahulshettyacademy");
	  driver.findElement(By.cssSelector(".promoBtn")).click();
	  wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".promoInfo")));
	  String coupe=driver.findElement(By.cssSelector(".promoInfo")).getText();
	  Assert.assertEquals("Code applied ..!",coupe);
	  driver.findElement(By.xpath("(//div/button)[2]")).click();
	  WebElement selectedelement=driver.findElement(By.cssSelector("select[style*='width']"));
	  Select select=new Select(selectedelement);
	  select.selectByVisibleText("India");
	  WebElement checkbox=driver.findElement(By.cssSelector(".chkAgree"));
	  checkbox.click();
	  driver.findElement(with(By.tagName("button")).below(checkbox)).click();
	  Thread.sleep(5000);
	  System.out.println(driver.getCurrentUrl());
	  System.out.println("Hello Bro");
	  System.out.println("GitDemo Path");
	  System.out.println("I am from India");


	}	
}
