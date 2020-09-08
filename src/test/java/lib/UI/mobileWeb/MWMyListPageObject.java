package lib.UI.mobileWeb;

import lib.UI.MyListPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWMyListPageObject extends MyListPageObject {

    static {
        ARTICLE_BY_TITLE_TPL = "xpath://ul[contains(@class, 'watchlist')]//h3[contains(text(), '{TITLE}')]";
        REMOVE_FROM_SAVED_BUTTON = "xpath://ul[contains(@class, 'watchlist')]//h3[contains(text(), '{TITLE}')]//../..//a[@class='mw-ui-icon mw-ui-icon-wikimedia-unStar-progressive mw-ui-icon-element   watch-this-article watched']";
    }

    public MWMyListPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
