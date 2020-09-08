package lib.UI.factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.UI.SearchPageObject;
import lib.UI.android.AndroidSearchPageObject;
import lib.UI.ios.IOSSearchPageObject;
import lib.UI.mobileWeb.MWSearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class SearchPageObjectFactory {

    public static SearchPageObject get(RemoteWebDriver driver) {
        if (Platform.getInstance().isAndroid()) {
            return new AndroidSearchPageObject(driver);
        } else  if (Platform.getInstance().isIOS()) {
            return new IOSSearchPageObject(driver);
        } else {
            return new MWSearchPageObject(driver);
        }
    }
}
