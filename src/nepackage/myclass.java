package nepackage;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class myclass {
	
	public static void main(String[] args) throws InterruptedException
	{
		
		WebDriver driver  = new FirefoxDriver();
		
		driver.get("https://www.myhcl.com/Login/home.aspx");
		
		    driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);		
			driver.findElement(By.id("txtUserID")).sendKeys("varun-b");
			driver.findElement(By.id("txtPassword")).sendKeys("switch@12");
			WebElement element = driver.findElement(By.id("ddlDomain"));
			
			Select dropdown = new Select(element);
			dropdown.selectByValue("HCLTECH");
			
			driver.findElement(By.id("btnSubmit")).click();
			
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			driver.findElement(By.id("Button4")).click();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			driver.findElement(By.id("txtSearch")).sendKeys("itime");
			
			 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			
			 driver.findElement(By.xpath("//*[@id='ui-id-1']/li[1]/table/tbody/tr/td/a")).click();
		
			 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			 
			 
			 driver.findElement(By.xpath("//*[@id='global-centrecontent']/table/tbody/tr[2]/td/table/tbody/tr/td/table/tbody/tr[3]/td/table/tbody/tr[1]/td/table/tbody/tr[1]/td/table/tbody/tr[1]/td[3]/table/tbody/tr/td[3]/a")).click();
			 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		     
		     
		     
		     for(int i = 0; i<=4;i++)
		     {
		    	String var = "txtHoursRow0Day" + String.valueOf(i);
		    	driver.findElement(By.xpath("//*[@id='"+var+"']")).sendKeys("5:00");
		    	String var1 = "txtHoursRow1Day" + String.valueOf(i);
		    	driver.findElement(By.xpath("//*[@id='"+var1+"']")).sendKeys("4:00");
		     }
		     
		     //
		    
		     //driver.findElement(By.id("btnSubmitforApproval")).click();	
		     
		     
		     //
		     driver.findElement(By.xpath("//button[contains(text(),'Ok')]")).click();
		     Thread.sleep(5000);
		     Alert alert = driver.switchTo().alert();
		     alert.accept();
		     driver.findElement(By.xpath("//button[contains(text(),'Ok')]")).click();
		     Thread.sleep(5000);
		     driver.switchTo().alert().dismiss();
		     driver.findElement(By.xpath("//button[contains(text(),'Ok')]")).click();
		     Thread.sleep(5000);
		     System.out.println(driver.switchTo().alert().getText());
		     driver.switchTo().alert().accept();

		     //
			 
		//driver.close();
	}
	
	

}
