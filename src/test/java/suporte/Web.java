package suporte;

import java.util.concurrent.TimeUnit;
import java.net.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Web {

	public static final String USERNAME = "viniciusprado4";
	public static final String AUTOMATE_KEY = "Kkymm4QUpR8Xkvx73k6C";
	public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

	public static WebDriver createChrome() {

		System.setProperty("webdriver.chrome.driver", "D:/Automacao/Drivers/Chrome Drive/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		driver.get("http://www.juliodelima.com.br/taskit");

		return driver;
	}

	public static WebDriver createBrowserStack() {

		// https://www.browserstack.com/automate/java#run-tests-on-desktop-mobile

		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("browser", "Chrome");
		caps.setCapability("browser_version", "83.0 beta");
		caps.setCapability("os", "Windows");
		caps.setCapability("os_version", "10");
		caps.setCapability("resolution", "1280x800");
		caps.setCapability("browserstack.debug", "true");

		WebDriver driver = null;

		try {

			driver = new RemoteWebDriver(new URL(URL), caps);
			driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
			driver.get("http://www.juliodelima.com.br/taskit");

		} catch (MalformedURLException e) {

			System.out.println("Houveram Problemas com a URL: " + e.getMessage());

		}

		return driver;

	}

}
