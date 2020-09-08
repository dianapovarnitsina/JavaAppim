package lib.UI.factories;

import lib.Platform;
import lib.UI.MyListPageObject;
import lib.UI.android.AndroidMyListPageObject;
import lib.UI.ios.IOSMyListPageObject;
import lib.UI.mobileWeb.MWMyListPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MyListPageObjectFactory {

    public static MyListPageObject get(RemoteWebDriver driver) {
        if (Platform.getInstance().isAndroid()) {
            return new AndroidMyListPageObject(driver);
        } else if (Platform.getInstance().isIOS()) {
            return new IOSMyListPageObject(driver);
        } else {
            return new MWMyListPageObject(driver);
        }
    }
}
