package lib.UI.mobileWeb;

import lib.UI.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWArticlePageObject extends ArticlePageObject {

    static {
        TITILE = "css:#content h1";
        FOOTER_ELEMENT = "css:footer";
        OPTIONS_ADD_TO_ME_LIST_BUTTON = "css:#page-actions li#page-actions-watch a#ca-watch.mw-ui-icon-wikimedia-star-base20";
        OPTIONS_REMOVE_FROM_MY_LIST_BUTTON = "css:#page-actions li#page-actions-watch a#ca-watch.mw-ui-icon-wikimedia-unStar-progressive";
    }

    public MWArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
