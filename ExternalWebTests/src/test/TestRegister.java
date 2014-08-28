package test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import tools.ConnectionInstance;
import tools.DataSourceFactory;
import tools.DriverFactory;
import tools.PageAddress;
import tools.Utils;

import com.testify.ecfeed.runner.StaticRunner;
import com.testify.ecfeed.runner.annotations.EcModel;

@RunWith(StaticRunner.class)
@EcModel("src/model.ect")
public class TestRegister{
	private WebDriver driver = null;
	private ConnectionInstance connection;
	private String baseUrl = PageAddress.Base;
	private String registerUrl = PageAddress.Register;

	/*
	 * This tests checks and warnings for email field.
	 */
	@Test
	public void testRegisterEmail(String email, boolean expected_result) throws Exception{
		try{
			setUp();
			driver.get(baseUrl);

			driver.findElement(By.xpath("//div[@id='cart_info']/a[2]/span")).click();
			driver.findElement(By.id("customer.emailAddress")).clear();
			driver.findElement(By.id("customer.emailAddress")).sendKeys(email);
			driver.findElement(By.id("customer.firstName")).clear();
			driver.findElement(By.id("customer.firstName")).sendKeys("name");
			driver.findElement(By.id("customer.lastName")).clear();
			driver.findElement(By.id("customer.lastName")).sendKeys("lastname");
			driver.findElement(By.id("password")).clear();
			driver.findElement(By.id("password")).sendKeys("password");
			driver.findElement(By.id("passwordConfirm")).clear();
			driver.findElement(By.id("passwordConfirm")).sendKeys("password");
			driver.findElement(By.xpath("//input[@value='Register']")).click();

			if(!expected_result){
				Assert.assertTrue(isElementPresent(By.xpath("//*[contains(.,'" + ErrorMessage.AddressInvalid + "')]"))
						|| isElementPresent(By.xpath("//*[contains(.,'" + ErrorMessage.EnterAddress + "')]"))
						|| driver.getCurrentUrl().equals(registerUrl));
			} else{
				Assert.assertTrue((isElementPresent(By.xpath("//*[contains(.,'" + ErrorMessage.AddressInUse + "')]"))
						|| (isElementPresent(By.linkText("Logout"))))
						&& !isElementPresent(By.xpath("//*[contains(.,'" + ErrorMessage.AddressInvalid + "')]"))
						&& !isElementPresent(By.xpath("//*[contains(.,'" + ErrorMessage.EnterAddress + "')]")));
			}
		} finally{
			cleanUpAfterTest(email);
			tearDown();
		}
	}

	/*
	 * This tests checks and warnings for first name field.
	 */
	@Test
	public void testRegisterFirstName(String first_name, boolean expected_result) throws Exception{
		String email = "email@address.com";
		
		try{
			setUp();
			driver.get(baseUrl);

			driver.findElement(By.xpath("//div[@id='cart_info']/a[2]/span")).click();
			driver.findElement(By.id("customer.emailAddress")).clear();
			driver.findElement(By.id("customer.emailAddress")).sendKeys("email@address.com");
			driver.findElement(By.id("customer.firstName")).clear();
			driver.findElement(By.id("customer.firstName")).sendKeys(first_name);
			driver.findElement(By.id("customer.lastName")).clear();
			driver.findElement(By.id("customer.lastName")).sendKeys("lastname");
			driver.findElement(By.id("password")).clear();
			driver.findElement(By.id("password")).sendKeys("password");
			driver.findElement(By.id("passwordConfirm")).clear();
			driver.findElement(By.id("passwordConfirm")).sendKeys("password");
			driver.findElement(By.xpath("//input[@value='Register']")).click();

			if(!expected_result){
				Assert.assertTrue(isElementPresent(By.xpath("//*[contains(.,'" + ErrorMessage.EnterFirstName + "')]"))
						|| driver.getCurrentUrl().equals(registerUrl));
			} else{
				Assert.assertTrue((isElementPresent(By.xpath("//*[contains(.,'" + ErrorMessage.AddressInUse + "')]")) || isElementPresent(By.linkText("Logout"))));
			}
		} finally{
			cleanUpAfterTest(email);
			tearDown();
		}
	}

	/*
	 * This tests checks and warnings for last name field.
	 */
	@Test
	public void testRegisterLastName(String last_name, boolean expected_result) throws Exception{
		String email = "email@address.com";
		
		try{
			setUp();
			driver.get(baseUrl);

			driver.findElement(By.xpath("//div[@id='cart_info']/a[2]/span")).click();
			driver.findElement(By.id("customer.emailAddress")).clear();
			driver.findElement(By.id("customer.emailAddress")).sendKeys("email@address.com");
			driver.findElement(By.id("customer.firstName")).clear();
			driver.findElement(By.id("customer.firstName")).sendKeys("first_name");
			driver.findElement(By.id("customer.lastName")).clear();
			driver.findElement(By.id("customer.lastName")).sendKeys(last_name);
			driver.findElement(By.id("password")).clear();
			driver.findElement(By.id("password")).sendKeys("password");
			driver.findElement(By.id("passwordConfirm")).clear();
			driver.findElement(By.id("passwordConfirm")).sendKeys("password");
			driver.findElement(By.xpath("//input[@value='Register']")).click();

			if(!expected_result){
				Assert.assertTrue(isElementPresent(By.xpath("//*[contains(.,'" + ErrorMessage.EnterLastName + "')]"))
						|| driver.getCurrentUrl().equals(registerUrl));
			} else{
				Assert.assertTrue((isElementPresent(By.xpath("//*[contains(.,'" + ErrorMessage.AddressInUse + "')]"))
						|| isElementPresent(By.linkText("Logout"))));
			}
		} finally{
			cleanUpAfterTest(email);
			tearDown();
		}
	}

	/*
	 * This tests checks and warnings for password field.
	 */
	@Test
	public void testRegisterPassword(String password, boolean expected_result) throws Exception{
		String email = "email@address.com";
		
		try{
			setUp();
			driver.get(baseUrl);

			driver.findElement(By.xpath("//div[@id='cart_info']/a[2]/span")).click();
			driver.findElement(By.id("customer.emailAddress")).clear();
			driver.findElement(By.id("customer.emailAddress")).sendKeys("email@address.com");
			driver.findElement(By.id("customer.firstName")).clear();
			driver.findElement(By.id("customer.firstName")).sendKeys("firsname");
			driver.findElement(By.id("customer.lastName")).clear();
			driver.findElement(By.id("customer.lastName")).sendKeys("lastname");
			driver.findElement(By.id("password")).clear();
			driver.findElement(By.id("password")).sendKeys(password);
			driver.findElement(By.id("passwordConfirm")).clear();
			driver.findElement(By.id("passwordConfirm")).sendKeys(password);
			driver.findElement(By.xpath("//input[@value='Register']")).click();

			if(!expected_result){
				Assert.assertTrue(isElementPresent(By.xpath("//*[contains(.,'" + ErrorMessage.EnterPassword + "')]"))
						|| isElementPresent(By.xpath("//*[contains(.,'" + ErrorMessage.InvalidPassword + "')]"))
						|| driver.getCurrentUrl().equals(registerUrl));
			} else{
				Assert.assertTrue((isElementPresent(By.xpath("//*[contains(.,'" + ErrorMessage.AddressInUse + "')]")) || isElementPresent(By.linkText("Logout"))));
			}
		} finally{
			cleanUpAfterTest(email);
			tearDown();
		}
	}
	
	/*
	 * This tests checks and warnings for confirm password field.
	 */
	@Test
	public void testRegisterConfirmPassword(String password, String confirm_password, boolean expected_result) throws Exception{
		String email = "email@address.com";
		
		try{
			setUp();
			driver.get(baseUrl);

			driver.findElement(By.xpath("//div[@id='cart_info']/a[2]/span")).click();
			driver.findElement(By.id("customer.emailAddress")).clear();
			driver.findElement(By.id("customer.emailAddress")).sendKeys("email@address.com");
			driver.findElement(By.id("customer.firstName")).clear();
			driver.findElement(By.id("customer.firstName")).sendKeys("firsname");
			driver.findElement(By.id("customer.lastName")).clear();
			driver.findElement(By.id("customer.lastName")).sendKeys("lastname");
			driver.findElement(By.id("password")).clear();
			driver.findElement(By.id("password")).sendKeys(password);
			driver.findElement(By.id("passwordConfirm")).clear();
			driver.findElement(By.id("passwordConfirm")).sendKeys(confirm_password);
			driver.findElement(By.xpath("//input[@value='Register']")).click();

			if(!expected_result){
				Assert.assertTrue(isElementPresent(By.xpath("//*[contains(.,'" + ErrorMessage.PasswordsMustMatch + "')]"))
						|| isElementPresent(By.xpath("//*[contains(.,'" + ErrorMessage.ConfirmPasswod + "')]"))
						|| driver.getCurrentUrl().equals(registerUrl));
			} else{
				Assert.assertTrue((isElementPresent(By.xpath("//*[contains(.,'" + ErrorMessage.AddressInUse + "')]")) || isElementPresent(By.linkText("Logout"))));
			}
		} finally{
			cleanUpAfterTest(email);
			tearDown();
		}
	}
	
	/*
	 * Input: valid email, tests for possibility of registering twice using same login
	 */
	@Test
	public void testRegisterLoginTwice(String email) throws Exception{
		try{
			setUp();
			driver.get(baseUrl);

			cleanUpAfterTest(email);

			driver.findElement(By.xpath("//div[@id='cart_info']/a[2]/span")).click();
			driver.findElement(By.id("customer.emailAddress")).clear();
			driver.findElement(By.id("customer.emailAddress")).sendKeys(email);
			driver.findElement(By.id("customer.firstName")).clear();
			driver.findElement(By.id("customer.firstName")).sendKeys("name");
			driver.findElement(By.id("customer.lastName")).clear();
			driver.findElement(By.id("customer.lastName")).sendKeys("lastname");
			driver.findElement(By.id("password")).clear();
			driver.findElement(By.id("password")).sendKeys("password");
			driver.findElement(By.id("passwordConfirm")).clear();
			driver.findElement(By.id("passwordConfirm")).sendKeys("password");
			driver.findElement(By.xpath("//input[@value='Register']")).click();

			driver.findElement(By.cssSelector("a > span")).click();
			driver.findElement(By.linkText("Login")).click();

			driver.findElement(By.xpath("//div[@id='cart_info']/a[2]/span")).click();
			driver.findElement(By.id("customer.emailAddress")).clear();
			driver.findElement(By.id("customer.emailAddress")).sendKeys(email);
			driver.findElement(By.id("customer.firstName")).clear();
			driver.findElement(By.id("customer.firstName")).sendKeys("name");
			driver.findElement(By.id("customer.lastName")).clear();
			driver.findElement(By.id("customer.lastName")).sendKeys("lastname");
			driver.findElement(By.id("password")).clear();
			driver.findElement(By.id("password")).sendKeys("password");
			driver.findElement(By.id("passwordConfirm")).clear();
			driver.findElement(By.id("passwordConfirm")).sendKeys("password");
			driver.findElement(By.xpath("//input[@value='Register']")).click();

			Assert.assertTrue(isElementPresent(By.xpath("//*[contains(.,'" + ErrorMessage.AddressInUse + "')]")));
		} finally{
			cleanUpAfterTest(email);
			tearDown();
		}
	}

	/*
	 * This test checks registering in very simple binary manner - either
	 * success or failure. It doesn't check if proper warnings etc. appear.
	 */
	@Test
	public void testRegisterSuccess(String email, String first_name, String last_name, String password, String confpsswd,
			boolean expected_result) throws Exception{
		try{
			setUp();
			driver.get(baseUrl);

			driver.findElement(By.xpath("//div[@id='cart_info']/a[2]/span")).click();
			driver.findElement(By.id("customer.emailAddress")).clear();
			driver.findElement(By.id("customer.emailAddress")).sendKeys(email);
			driver.findElement(By.id("customer.firstName")).clear();
			driver.findElement(By.id("customer.firstName")).sendKeys(first_name);
			driver.findElement(By.id("customer.lastName")).clear();
			driver.findElement(By.id("customer.lastName")).sendKeys(last_name);
			driver.findElement(By.id("password")).clear();
			driver.findElement(By.id("password")).sendKeys(password);
			driver.findElement(By.id("passwordConfirm")).clear();
			driver.findElement(By.id("passwordConfirm")).sendKeys(confpsswd);
			driver.findElement(By.xpath("//input[@value='Register']")).click();

			if(expected_result){
				Assert.assertTrue((isElementPresent(By.linkText("Logout")) && isElementPresent(By.xpath("//*[contains(., '" + first_name
						+ "')]")))
						|| isElementPresent(By.xpath("//*[contains(.,'" + ErrorMessage.AddressInUse + "')]")));
			} else{
				Assert.assertTrue((isElementPresent(By.className("error")) || isElementPresent(By.linkText("Login")))
						&& driver.getCurrentUrl().equals(registerUrl));
			}
			driver.findElement(By.cssSelector("a > span")).click();
		} finally{
			cleanUpAfterTest(email);
			tearDown();
		}
	}

	private void cleanUpAfterTest(String email){
		try{
			connection.tryUpdate("DELETE FROM PUBLIC.blc_customer WHERE EMAIL_ADDRESS='" + Utils.escapeString(email) + "';");
		} catch(Exception e){
			throw new Error("Database connection failed");
		}
	}

	private void setUp(){
		try{
			connection = new ConnectionInstance(DataSourceFactory.getHSQLDataSource());
			connection.tryUpdate("DELETE FROM PUBLIC.blc_customer");
		} catch(Exception e){
			e.printStackTrace();
			throw new Error("Failed to initialize database connection");
		}

		try{
			driver = DriverFactory.getDriver();
		} catch(Exception e){
			throw new Error("Failed to initialize Selenium driver");
		}
	}

	private void tearDown() throws Exception{
		if(driver != null){
			driver.quit();
		}
		if(connection != null){
			connection.close();
		}
	}

	private boolean isElementPresent(By by){
		try{
			driver.findElement(by);
			return true;
		} catch(NoSuchElementException e){
			return false;
		}
	}
}