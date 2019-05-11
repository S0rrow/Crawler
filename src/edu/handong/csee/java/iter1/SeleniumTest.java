package edu.handong.csee.java.iter1;

import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumTest {
	public static void main(String[] argv) {
		SeleniumTest tester = new SeleniumTest();
		tester.run();
	}
	
	private WebDriver driver;
	private WebElement webElement;
	
	private String url;
	
	public static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
	public static final String WEB_DRIVER_PATH = "C:/Users/S0rrow/eclipse-workspace/Crawler/chromedriver.exe";
	public Scanner scanner;
	private boolean trig = true;
	public SeleniumTest() {
		super();
		
		System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
		
		ChromeOptions options = new ChromeOptions();
		options.setCapability("ignoreProtectModeSettings", trig);
		options.addArguments("headless");
		driver = new ChromeDriver(options);
		
		url = "http://hisnet.handong.edu";
	}
	
	public void FrameCall(WebDriver driver, String FrameID) {
		try{
			driver.switchTo().frame(FrameID);
			try{
				driver.switchTo().frame(FrameID);
			} catch(Exception e) {
				//null
			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void run() {
		try {
			scanner = new Scanner(System.in);
			
			driver.get(url);
			FrameCall(driver, "MainFrame");
			//System.out.println(driver.getPageSource());
			
			
			
			webElement = driver.findElement(By.xpath("//input[contains(@name, 'id')]"));
			//System.out.println(webElement.getText());
			System.out.print("ID: ");
			String id = scanner.nextLine();
			webElement.sendKeys(id);

			//System.out.println(driver.getPageSource());
			webElement = driver.findElement(By.xpath("//input[contains(@name, 'password')]"));
			System.out.print("PW: ");
			String pw = scanner.nextLine();
			webElement.sendKeys(pw);
			
			webElement = driver.findElement(By.xpath("//input[@type='image' and contains(@src, '/2012_images/intro/btn_login.gif')]"));
			webElement.click();
			
			driver.switchTo().defaultContent();
			FrameCall(driver, "MainFrame");
			webElement = driver.findElement(By.xpath("//a[contains(@href,'/for_student/course/01.php')]"));
			webElement.click();
			
			
			driver.switchTo().defaultContent();
			FrameCall(driver, "MainFrame");
			//WebElement att_list = driver.findElement(By.xpath("//table[@id='att_list']"));
			List<WebElement> timeTable = driver.findElements(By.xpath("//*[@id='att_list']/tbody/tr/td"));
			for(int i = 0; i < timeTable.size(); i++) {
				WebElement cell = timeTable.get(i);
				String[] info = cell.getText().split("\n");
				System.out.print("|"+String.join(",",info)+"|");
				if(i%7==6) System.out.println("");
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			scanner.close();
			driver.close();
		}
	}
}
