package features;

import com.example.SpringCucumberAppiumDemoApplication;
import com.example.appium.AppiumService;
import io.appium.java_client.android.options.UiAutomator2Options;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import io.appium.java_client.android.AndroidDriver;;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

@ContextConfiguration(classes = SpringCucumberAppiumDemoApplication.class)
@WebAppConfiguration
@Slf4j
public class BaseTest {
  @Value("${appium.server}")
  private String appiumServer;
  @Value("${appium.wait}")
  private Long appiumWait;
  @Value("${appium.timeout}")
  private Long appiumTimeout;

  @Autowired
  private AppiumService appiumService;
  private AndroidDriver driver;

  public AndroidDriver getDriver() throws MalformedURLException {
    if(driver == null){
      log.info("Starting Appium service: url={}", appiumServer);
//      UiAutomator2Options options = new UiAutomator2Options()
//          .setUdid("123456")
//          .setApp("/Users/ivyyip/Downloads/ApiDemos-debug.apk");
//      driver = new AndroidDriver(new URL(appiumServer), options);
      driver = new AndroidDriver(new URL(appiumServer), appiumService.getCapabilities());
      driver.manage().timeouts().implicitlyWait(appiumWait, TimeUnit.SECONDS);
    }
    return driver;
  }

  public WebElement waitForElement(WebElement element){
    WebDriverWait wait =  new WebDriverWait(driver, Duration.ofSeconds(appiumTimeout));
    wait.until(ExpectedConditions.visibilityOf(element));
    return element;
  }

  DesiredCapabilities getCapabilities(){
    return appiumService.getCapabilities();
  }
}
