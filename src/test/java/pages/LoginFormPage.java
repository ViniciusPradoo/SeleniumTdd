package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginFormPage extends BasePage {
	
	public LoginFormPage(WebDriver driver) {
		super(driver);
	}


	public LoginFormPage digitarLogin(String login){
		
		driver.findElement(By.id("signinbox")).findElement(By.name("login")).sendKeys(login);
		
		return this;
	}
	
	
	public LoginFormPage digitarSenha(String senha) {
				
		driver.findElement(By.id("signinbox")).findElement(By.name("password")).sendKeys(senha);
		
		return this;
	}
	
	public SecretaPage clicarSignIn() throws InterruptedException {
		
		Thread.sleep(1000);
		driver.findElement(By.linkText("SIGN IN")).click();
		
		return new SecretaPage(driver);
	}
	
	public SecretaPage fazerLogin(String login, String senha) throws InterruptedException {
		
		digitarLogin(login);
		digitarSenha(senha);
		clicarSignIn();
		
		return new SecretaPage(driver);
		
	}

}
