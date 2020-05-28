package tests;

import static org.junit.Assert.*;

import org.easetech.easytest.annotation.DataLoader;
import org.easetech.easytest.annotation.Param;
import org.easetech.easytest.runner.DataDrivenTestRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import suporte.Web;

@RunWith(DataDrivenTestRunner.class)
@DataLoader(filePaths = "InformacoesUsuarioTestData.csv")
public class InformacoesUsuarioTest {

	private static WebDriver driver;

	@Before
	public void setUp() throws InterruptedException {

		driver = Web.createChrome();

		driver.findElement(By.linkText("Sign in")).click();

		WebElement formularioSigniInBox = driver.findElement(By.id("signinbox"));
		formularioSigniInBox.findElement(By.name("login")).sendKeys("julio0001");
		formularioSigniInBox.findElement(By.name("password")).sendKeys("123456");
		
		Thread.sleep(1000);
		driver.findElement(By.linkText("SIGN IN")).click();

		WebElement me = driver.findElement(By.className("me"));
		String textoNoElementoMe = me.getText();
		assertEquals("Hi, Julio", textoNoElementoMe);

		me.click();
		
		driver.findElement(By.linkText("MORE DATA ABOUT YOU")).click();
		
	}

	@Test
	public void testAdicionarUmaInformacaoAdicionalDoUsuario(@Param(name="tipo")String tipo, @Param(name="contato")String contato, @Param(name="mensagem")String mensagemEsperada) {

				
		driver.findElement(By.xpath("//button[@data-target=\"addmoredata\"]")).click();
		
		WebElement popupAddMoreData = driver.findElement(By.id("addmoredata"));
		

		System.out.println(tipo);
		System.out.println(contato);
		System.out.println(mensagemEsperada);
		
		
		WebElement campoType = popupAddMoreData.findElement(By.name("type"));
		new Select(campoType).selectByVisibleText(tipo);
		popupAddMoreData.findElement(By.name("contact")).sendKeys(contato);
		popupAddMoreData.findElement(By.linkText("SAVE")).click();
		
		WebElement mensagemPop = driver.findElement(By.id("toast-container"));
		String mensagem = mensagemPop.getText();
		assertEquals(mensagemEsperada, mensagem);
		
		
		
		
	}
	
	
	

	@After
	public void tearDown() {

		//driver.quit();

	}

}
