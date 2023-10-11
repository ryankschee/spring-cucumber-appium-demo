package com.example.appium;

import jakarta.annotation.PostConstruct;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AppiumServiceImpl implements AppiumService {
  @Value("${device.name}")
  private String deviceName;
  @Value("${device.version}")
  private String deviceVersion;
  @Value("${device.platform}")
  private String devicePlatform;
  @Value("${application.package}")
  private String applicationPackage;
  @Value("${application.activity}")
  private String applicationActivity;

  private DesiredCapabilities capabilities = new DesiredCapabilities();

  @PostConstruct
  public void setup(){
    capabilities.setCapability("deviceName", deviceName);
    capabilities.setCapability(CapabilityType.VERSION, deviceVersion);
    capabilities.setCapability("platformName", devicePlatform);
    capabilities.setCapability("appPackage", applicationPackage);
    capabilities.setCapability("appActivity", applicationActivity);
  }

  public DesiredCapabilities getCapabilities(){
    return capabilities;
  }
}
