package lib.UI.ios;

import lib.UI.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IOSArticlePageObject extends ArticlePageObject {

    static {
        TITILE = "id:{TITLE}";
        FOOTER_ELEMENT = "id:View article in browser";
        OPTIONS_ADD_TO_ME_LIST_BUTTON = "id:Save for later";
        CLOSE_ARTICLE_BUTTON = "id:Back";
        AUTH_CLOSE = "id:places auth close";
        CLEAR_MINI = "id:clear mini";
//        FOLDER = "//*[@class='android.widget.TextView'][@text='{FOLDER_MAME}']";
    }

    public IOSArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
