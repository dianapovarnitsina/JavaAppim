package lib.UI.ios;

import io.appium.java_client.AppiumDriver;
import lib.UI.MyListPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IOSMyListPageObject extends MyListPageObject {

    static {
        ARTICLE_BY_TITLE_TPL = "xpath://XCUIElementTypeStaticText[@name='{TITLE}']";
    }

    public IOSMyListPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
