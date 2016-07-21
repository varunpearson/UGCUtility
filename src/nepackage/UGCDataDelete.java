package nepackage;

import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.util.AbstractMap;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.swing.Box;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class UGCDataDelete extends JApplet {
	
	private CheckboxGroup radioGroup = null;
	private JButton executeButton = new JButton("DeleteData");
	private static JLabel userNameLabel = null;
	private static JLabel TextCC = null;
	private static JLabel passwordLabel = null;
	private static JTextField username = null;
	private static JTextField password = null;
	private static Map sectionUserMap = null;
	private static Map ConfigCodeMap = null;
	private JComboBox section = new JComboBox();
	private static String selectedCC = null;
	private static String EnteredCC = null;
	private static String selectedSection = null;
	private static String URL = null;
	String sel= "Select Config Code";
	private JComboBox comboBox = new JComboBox();
	private static Object[] CCsKey = null;
	private static String[] CCsValues = null;
	private static JTextField ChangeCOnfigCodeUrl = null;
	private JButton ChangeCC = new JButton("ChangeConfigCode");
    
	
	
	
  
	
	private static Container myContainer = null;
	
		public static void main(String[] args)
		{
			sectionUserMap = readSectionUserFile();
			ConfigCodeMap = readConfigCodeFile();
			
			run(new UGCDataDelete(), 400, 550);
		}
		
		public void init() {
			radioGroup = new CheckboxGroup();
			Checkbox defaultRadioBtn = new Checkbox("Default", radioGroup, false);
			Checkbox otherRadioBtn = new Checkbox("Other", radioGroup, false);
			
			myContainer = getContentPane(); 
		    myContainer.setLayout(new FlowLayout()); 
		    
		    Set<String> sections = sectionUserMap.keySet();
		    section.addItem("--Select Your Section--");
		    for (String sec:sections) {
		    	section.addItem(sec);
			}
		    Set<String> CCsKey = ConfigCodeMap.keySet();
		    
		    comboBox.addItem(sel);
		    for (String config:CCsKey) {
		    	comboBox.addItem(config);
			}
		    comboBox.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
				 selectedCC = ((JComboBox) e.getSource()).getSelectedItem().toString();
				 JOptionPane.showMessageDialog(null, ConfigCodeMap.get(selectedCC), "Info", JOptionPane.INFORMATION_MESSAGE);
			}
			});
		    section.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			    	selectedSection = ((JComboBox) e.getSource()).getSelectedItem().toString();
					//JOptionPane.showMessageDialog(null, sectionUserMap.get(selectedCC), "Info", JOptionPane.INFORMATION_MESSAGE);selectedSection = ((JComboBox) event.getSource()).getSelectedItem().toString();
			}
				});
             otherRadioBtn.addItemListener(new ItemListener() {
				
				@Override
				public void itemStateChanged(ItemEvent event) {
					username.setVisible(true);
					password.setVisible(true);
					userNameLabel.setVisible(true);
					passwordLabel.setVisible(true);
					section.setVisible(false);
					ChangeCOnfigCodeUrl.setVisible(true);
					TextCC.setVisible(true);
					
				}
			});
		    defaultRadioBtn.addItemListener(new ItemListener() {
			    @Override
				public void itemStateChanged(ItemEvent event) {
					username.setVisible(false);
					password.setVisible(false);
					userNameLabel.setVisible(false);
					passwordLabel.setVisible(false);
					section.setVisible(true);
					ChangeCOnfigCodeUrl.setVisible(false);
					TextCC.setVisible(false);
					
					
				}
			});
		    executeButton.setPreferredSize(new Dimension(100, 20));
			executeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			String Uname = username.getText();
			String PWD = password.getText();
			String EnteredURL = ChangeCOnfigCodeUrl.getText();
				
				//selectedCC = ((JComboBox) e.getSource()).getSelectedItem().toString();
				//JOptionPane.showMessageDialog(null, ConfigCodeMap.get(selectedCC), "Info", JOptionPane.INFORMATION_MESSAGE);
		    URL = ConfigCodeMap.get(selectedCC).toString();
			if(otherRadioBtn.getState())
                {
                	try {
						UGCDataDelete.execute(Uname, PWD, URL, EnteredURL);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                }
                else
                {
                	
	                try {
						UGCDataDelete.execute(URL);
					}catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}	
                }
			}
		});
			/*ChangeCC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String  = username.getText();
				
					
					//selectedCC = ((JComboBox) e.getSource()).getSelectedItem().toString();
					//JOptionPane.showMessageDialog(null, ConfigCodeMap.get(selectedCC), "Info", JOptionPane.INFORMATION_MESSAGE);
			    URL = ConfigCodeMap.get(selectedCC).toString();
				if(otherRadioBtn.getState())
	                {
	                	try {
							UGCDataDelete.execute(Uname, PWD, URL);
						} catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
	                }
	                else
	                {
	                	
		                try {
							UGCDataDelete.execute(URL);
						}catch (InterruptedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}	
	                }
				}
			});*/
			
			JLabel SectionLabel = new JLabel("Choose Section: ");	
			
		    myContainer.setBackground(new Color(255,255,255));
			myContainer.add(Box.createHorizontalGlue());
			myContainer.add(Box.createRigidArea(new Dimension(300, 100)));
			comboBox.setPreferredSize(new Dimension(150, 20));
			myContainer.add(comboBox);
			myContainer.add(Box.createHorizontalGlue());
			myContainer.add(Box.createRigidArea(new Dimension(140, 30))); 
			
	        myContainer.add(SectionLabel);
	        myContainer.add(Box.createRigidArea(new Dimension(5, 10))); 
	        myContainer.add(defaultRadioBtn);
	        myContainer.add(Box.createHorizontalGlue());
	        myContainer.add(Box.createRigidArea(new Dimension(50, 1)));
	        myContainer.add(otherRadioBtn);
	        myContainer.add(Box.createHorizontalGlue());
	        myContainer.add(Box.createRigidArea(new Dimension(50, 1)));
	        username = new JTextField();
	        username.setPreferredSize(new Dimension(121, 20));
	        username.setToolTipText("Enter UserName");
	        password = new JTextField();
	        password.setPreferredSize(new Dimension(121, 20));
	        password.setToolTipText("Enter Password");
	        userNameLabel = new JLabel("Username");
	        passwordLabel = new JLabel("Password");
	        TextCC = new JLabel("Enter URL(if not present)");
	        TextCC.setVisible(false);
		    myContainer.add(TextCC);

	        ChangeCOnfigCodeUrl = new JTextField();
		    ChangeCOnfigCodeUrl.setPreferredSize(new Dimension(150, 20));
		    
		    myContainer.add(ChangeCOnfigCodeUrl);
		    ChangeCOnfigCodeUrl.setVisible(false);
		    myContainer.add(Box.createHorizontalGlue());
		    myContainer.add(Box.createRigidArea(new Dimension(90, 20)));
		    
	        myContainer.add(userNameLabel);
	        userNameLabel.setVisible(false);
	       
	        myContainer.add(username);
	        username.setVisible(false);
	        myContainer.add(Box.createHorizontalGlue());
	        myContainer.add(Box.createRigidArea(new Dimension(140, 1)));
	        myContainer.add(passwordLabel);
	        passwordLabel.setVisible(false);
	        myContainer.add(Box.createRigidArea(new Dimension(80, 1)));
	        myContainer.add(password);
	        password.setVisible(false);
	        myContainer.add(Box.createHorizontalGlue());
	        myContainer.add(Box.createRigidArea(new Dimension(100, 1)));
	        myContainer.add(section);
	        section.setVisible(false);
	        
	        myContainer.add(Box.createHorizontalGlue());
			myContainer.add(Box.createRigidArea(new Dimension(200, 100))); 
			
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

		
		public static void execute(String username, String Pwd, String URL,  String EnteredUrl)  throws InterruptedException {
			
			
			
			WebDriver driver = new FirefoxDriver();
			if(null!= URL){
			driver.get(URL);
			}
			else
				driver.get(EnteredUrl);
	     	driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			driver.findElement(By.id("UserName")).sendKeys(username);
			driver.findElement(By.id("Password")).sendKeys(Pwd);
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
      public static void execute(String URL)  throws InterruptedException {
			
    	    String listUsernames = sectionUserMap.get(selectedSection).toString();
		    String[] UsernamesList = new String[50];
	        UsernamesList = listUsernames.split(",");
			
			WebDriver driver = new FirefoxDriver();
			driver.get(URL);
			
	     	for(int i = 0; i<= UsernamesList.length; i++){
	        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			driver.findElement(By.id("UserName")).sendKeys(UsernamesList[i]);
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
		
		public static Map readSectionUserFile(){
			Map sectionMap = new HashMap<String, String>();
			try {
	    		File myFile = new File("sectionuser.properties");
	    		if(myFile.exists()) {
	    	        Scanner scan = new Scanner(myFile);
	    	        String line="";
	    	        int index = 1;
	    	        while (scan.hasNextLine()) {
	    	            line = scan.nextLine();
	    	            String ketVal[] = line.split("=");
	    	            sectionMap.put(ketVal[0], ketVal[1]);
	    	        }
	    		}
	    	} catch(Exception e) {
	    		System.out.println("2. Unable to load config properties");
	    	}
	    	return sectionMap;
		}
		public static Map readConfigCodeFile(){
			Map CCMap = new HashMap<String, String>();
			try {
	    		File myFile = new File("CCUrl.properties");
	    		if(myFile.exists()) {
	    	        Scanner scan = new Scanner(myFile);
	    	        String line="";
	    	        int index = 1;
	    	        while (scan.hasNextLine()) {
	    	            line = scan.nextLine();
	    	            String ketVal[] = line.split("=");
	    	            CCMap.put(ketVal[0], ketVal[1]);
	    	        }
	    		}
	    	} catch(Exception e) {
	    		System.out.println("2. Unable to load CC file");
	    	}
	    	return CCMap;
		}
}
