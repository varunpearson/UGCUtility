package nepackage;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class gmail {
	public static void main(String[] args) throws InterruptedException, AWTException
	{
	
		WebDriver driver;
	
	driver=new FirefoxDriver();
	
	driver.get("https://gmail.com");
	
	driver.manage().window().maximize();
	
	driver.findElement(By.id("Email")).sendKeys("bvarun45@gmail.com");
	driver.findElement(By.id("next")).click();
	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	driver.findElement(By.id("Passwd")).sendKeys("17july91");
	
	
	driver.findElement(By.id("signIn")).click();
	
	Thread.sleep(30000);
		driver.findElement(By.xpath("//div[@class='z0']//div[contains(text(),'COMPOSE')]")).click();
		
		driver.findElement(By.xpath(".//div[@class='aoD az6']")).sendKeys("Sending Automatic Mail");
		
		driver.findElement(By.xpath("//*[@id=':oe']")).sendKeys("varunb719@gmail.com");
		
		driver.findElement(By.xpath("//*[@id=':nu']")).click();
		
		
		driver.findElement(By.xpath("//div[contains(@command,'Files')]//div[contains(@class,'aaA')]")).click();
		Robot rb =new Robot();
		rb.keyPress(KeyEvent.VK_C);
	    rb.keyRelease(KeyEvent.VK_C);
		Thread.sleep(2000);
		rb.keyPress(KeyEvent.VK_SHIFT);
		rb.keyPress(KeyEvent.VK_SEMICOLON);
		rb.keyRelease(KeyEvent.VK_SEMICOLON);
		rb.keyRelease(KeyEvent.VK_SHIFT);
		rb.keyPress(KeyEvent.VK_BACK_SLASH);
		rb.keyRelease(KeyEvent.VK_BACK_SLASH);
		Thread.sleep(2000);
		 
		rb.keyPress(KeyEvent.VK_P);
		rb.keyRelease(KeyEvent.VK_P);
		rb.keyPress(KeyEvent.VK_I);
		rb.keyRelease(KeyEvent.VK_I);
		rb.keyPress(KeyEvent.VK_C);
		rb.keyRelease(KeyEvent.VK_C);
		Thread.sleep(2000);
		rb.keyPress(KeyEvent.VK_ENTER);
		rb.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(2000);
		//driver.quit();
	}
		
}


