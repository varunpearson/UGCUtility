package nepackage;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

import javax.swing.Box;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;




public class UGCDataDelete extends JApplet {
	private JButton executeButton = new JButton("DeleteData");
	JRadioButton rButton1 = new JRadioButton("Default");
	JRadioButton rButton2 = new JRadioButton("Other");
	
	String[] names = new String[] {"Select ConfigCode","4MVREVIEWV2", "PSOCPP",
    "PSOCPP117"};
	private JComboBox<String> comboBox = new JComboBox<>(names);
	
	private static Container myContainer = null;
	
		public static void main(String[] args)
		{
			run(new UGCDataDelete(), 400, 550);
		}
		
		public void init() {
			myContainer = getContentPane(); 
		    myContainer.setLayout(new FlowLayout()); 
			executeButton.setPreferredSize(new Dimension(100, 20));
			executeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				

                Object selected = comboBox.getSelectedItem();
                String nameselected = selected.toString();
                
                if(nameselected.equals("Select Config Code"))
                {
                	JOptionPane.showMessageDialog(null, "Please select your Name.");
                } 
                else {
	                try {
						UGCDataDelete.execute(nameselected);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}	
                }
			}
			
			});
			
			
			myContainer.add(comboBox);
			
			//myContainer.add(Box.createRigidArea(new Dimension(200, 10))); 
			
			//myContainer.add(Box.createVerticalGlue());
			//myContainer.add(Box.createRigidArea(new Dimension(200, 10))); 
			myContainer.add(rButton1);
			myContainer.add(rButton2);
			
			myContainer.add(executeButton);

		}			

		
		public static void run(JApplet applet, int width, int height)
		{
			JFrame frame = new JFrame();
			//setWindowPosition(frame, 0);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setResizable(false);
			frame.getContentPane().add(applet);
			frame.setSize(width, height);
			applet.init();
			applet.start();
			frame.setVisible(true);
			
		}

		
		public static void execute(String name)  throws InterruptedException {
			
			
			
			WebDriver driver = new FirefoxDriver();
			driver.get("https://psoc-user-work-dashboard-dct.azurewebsites.net/Account/Login?ReturnUrl=%2F");
	     	driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			String username[];
			if(name.equals("Varun"))
			{
				username = new String[] {"Tesec14grd01","Tesec14grdkg","S01sec14grd01", "S01sec14grdkg"};
			}
			else if(name.equals("Pooja"))
			{
				username = new String[] {"Tesec10grd01","Tesec10grdkg","S01sec10grd01", "S01sec10grdkg"};
			}
			else
			{
				username = new String[] {"Tesec10grd01","Tesec10grdkg","S01sec10grd01", "S01sec10grdkg"};
				driver.quit();
			}
			for (int i = 0 ; i <=4 ; i ++)
			{
				
			driver.findElement(By.id("UserName")).sendKeys(username[i]);
			
			driver.findElement(By.id("Password")).sendKeys("psocsq3");
			
			driver.findElement(By.xpath("//*[@id='loginForm']/form/div[3]/div/input")).click();
			
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			//delete all
			driver.findElement(By.xpath("//*[@id='mainTable']/thead/tr/th[1]/a")).click();
			Alert alert = driver.switchTo().alert();
			Thread.sleep(3000);
			alert.accept();
		
			//sharingInbox
			driver.findElement(By.xpath("html/body/header/div[2]/div/div[2]/ul[1]/li[2]/a")).click();
			Thread.sleep(20000);
			driver.findElement(By.xpath("//*[@id='mainTable']/thead/tr/th[1]/a")).click();
			
			//
			alert.accept();
			Thread.sleep(3000);
			//shareduserworks
			driver.findElement(By.xpath("html/body/header/div[2]/div/div[2]/ul[1]/li[3]/a")).click();
			Thread.sleep(20000);
			driver.findElement(By.xpath("//*[@id='mainTable']/thead/tr/th[1]/a")).click();
		
			alert.accept();
			Thread.sleep(3000);
			//logoff
			driver.findElement(By.xpath("html/body/header/div[2]/div/div[2]/ul[2]/li[2]/a")).click();
			
		}
	}
}
