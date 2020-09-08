package lib.UI.android;

import io.appium.java_client.AppiumDriver;
import lib.UI.MyListPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidMyListPageObject extends MyListPageObject {

    static {
        FOLDER_BY_NAME_TPL = "xpath://*[@text='{FOLDER_MAME}']";
        ARTICLE_BY_TITLE_TPL = "xpath://*[@text='{TITLE}']";
    }

    public AndroidMyListPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
