package tests;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.easetech.easytest.annotation.DataLoader;
import org.easetech.easytest.runner.DataDrivenTestRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import suporte.Generator;
import suporte.Screenshot;

@RunWith(DataDrivenTestRunner.class)
@DataLoader(filePaths = "DeletandoInformacoesDoUsuarioTest.csv")
public class DeletandoInformacoesDoUsuarioTest {

	private static WebDriver driver;

	@Rule
	public TestName test = new TestName();
	

	@Before
	public void setUp() {

		System.setProperty("webdriver.chrome.driver", "D:/Automacao/Drivers/Chrome Drive/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://www.juliodelima.com.br/taskit");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.findElement(By.linkText("Sign in")).click();

		WebElement formularioSigniInBox = driver.findElement(By.id("signinbox"));
		formularioSigniInBox.findElement(By.name("login")).sendKeys("julio0001");
		formularioSigniInBox.findElement(By.name("password")).sendKeys("123456");
		driver.findElement(By.linkText("SIGN IN")).click();

		WebElement me = driver.findElement(By.className("me"));
		String textoNoElementoMe = me.getText();
		assertEquals("Hi, Julio", textoNoElementoMe);

		me.click();

		driver.findElement(By.linkText("MORE DATA ABOUT YOU")).click();

	}

	@Test
	public void removerUmContatoDeUmUsuario() throws InterruptedException {

		// +5511912345773 // CLICAR NO ELEMENTO PELO SEU // XPATH//span[text()="+5511912345773"]/following-sibling::a
		driver.findElement(By.xpath("//span[text()=\"+5511912345773\"]/following-sibling::a")).click();

		// CONFIRMAR A JANELA JAVASCRIPT
		driver.switchTo().alert().accept();

		// VALIDAR QUE A MENSAGEM APRESENTADA FOI REST IN PEACE, DEAR PHONE!
		WebElement mensagemPop = driver.findElement(By.id("toast-container"));
		String mensagem = mensagemPop.getText();
		assertEquals("Rest in peace, dear phone!", mensagem);

		//TirarScreenShot
		String screenshotArquivo = "C:/Temp/Reports/test-report/taskit/"
				+ Generator.dataHoraParaArquivo() + test.getMethodName() + ".png";
		Screenshot.tirar(driver, screenshotArquivo);

		// AGUARDAR ATÉ 10 SEGUNDOS PARA QUE A JANELA DESAPAREÇA
		WebDriverWait aguardar = new WebDriverWait(driver, 10);
		aguardar.until(ExpectedConditions.stalenessOf(mensagemPop));

		// CLICAR NO LINK COM TEXTO "Logout"
		driver.findElement(By.linkText("Logout")).click();
	}

	@After
	public void tearDown() {

		// driver.quit();

	}

}
