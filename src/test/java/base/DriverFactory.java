package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

	public static WebDriver initDriver(String browser) {

		System.out.println("Launching Browser: " + browser);

		if (browser.equalsIgnoreCase("chrome")) {

			WebDriverManager.chromedriver().setup();
			driver.set(new ChromeDriver());

		} else if (browser.equalsIgnoreCase("edge")) {

			try {

				WebDriverManager.edgedriver().setup();

			} catch (Exception e) {

				System.out.println("⚠️ EdgeDriver he download failed. Using local driver.");

				System.setProperty("webdriver.edge.driver",
						"C://Users//disha//OneDrive//Desktop//edgedriver_win64//msedgedriver.exe");
			}

			driver.set(new EdgeDriver());
		} else {
			throw new RuntimeException("Browser not supported: " + browser);
		}

		getDriver().manage().window().maximize();
		String strop = getDriver().getCurrentUrl();
		System.out.println(strop);

		return getDriver();
	}

	public static WebDriver getDriver() {
		return driver.get();
	}

	public static void quitDriver() {

		if (driver.get() != null) {

			driver.get().quit();
			driver.remove();
		}
	}
}