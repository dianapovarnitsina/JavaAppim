package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Platform {
    private static final String PLATFORM_IOS = "ios";
    private static final String PLATFORM_ANDROID = "android";
    private static final String PLATFORM_MOBILE_WEB = "mobile_web";
    private static final String APPIUM_URL = "http://127.0.0.1:4723/wd/hub";

    private static Platform instance;

    private Platform() {}

    public static Platform getInstance() {
        if (instance == null) {
            instance = new Platform();
        }
        return instance;
    }

    private DesiredCapabilities getAndroidDesignCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "8.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "/Users/diana.povarnitsina/JavaAppiumAuto/apks/org.wikipedia.apk");
        return capabilities;
    }

    private DesiredCapabilities getIOSDesignCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "IOS");
        capabilities.setCapability("deviceName", "iPhone SE");
        capabilities.setCapability("platformVersion", "13.7");
        capabilities.setCapability("app", "/Users/diana.povarnitsina/JavaAppiumAuto/apks/Wikipedia.app");
        return capabilities;
    }

    private ChromeOptions getMVChromeOptions() {
        Map<String, Object> deviceMetrics = new HashMap<String, Object>();
        deviceMetrics.put("width", 360);
        deviceMetrics.put("height", 640);
        deviceMetrics.put("pixelRatio", 3.0);

        Map<String, Object> moboleEmulation = new HashMap<String, Object>();
        moboleEmulation.put("deviceMetrics", deviceMetrics);
        moboleEmulation.put("userAgent", "Mozilla/5.0 (Linux; Android 4.2.1; en-us; Nexus 5 Build/JOP40D) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.166 Mobile Safari/535.19");

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("window-size=340,640");
        return chromeOptions;
    }

    private boolean isPlatform(String myPlatform) {
        String platform = this.getPlatformVar();
        System.out.println(platform);
        return myPlatform.equals(platform);
    }

    public String getPlatformVar() {
        return System.getenv("PLATFORM");
    }

    public boolean isAndroid() {
        return isPlatform(PLATFORM_ANDROID);
    }

    public boolean isIOS() {
        return isPlatform(PLATFORM_IOS);
    }

    public boolean isMW() {
        return isPlatform(PLATFORM_MOBILE_WEB);
    }


    public RemoteWebDriver getDriver() throws Exception {
        URL url = new URL(APPIUM_URL);
        if (this.isAndroid()) {
            return new AndroidDriver(url, this.getAndroidDesignCapabilities());
        } else if (this.isIOS()) {
            return new IOSDriver(url, this.getIOSDesignCapabilities());
        } else if (this.isMW()) {
            return new ChromeDriver(this.getMVChromeOptions());
        } else {
            throw new Exception("Cannot detect type of the Driver. Platform value" + this.getPlatformVar());
        }
    }
}
