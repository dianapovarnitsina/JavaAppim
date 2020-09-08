package lib.UI;

import lib.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class ArticlePageObject extends MainPageObject {

    protected static String
        TITILE,
        FOOTER_ELEMENT,
        OPTIONS_BUTTON,
        OPTIONS_ADD_TO_ME_LIST_BUTTON,
        OPTIONS_REMOVE_FROM_MY_LIST_BUTTON,
        ADD_TO_MY_LIST_OVERLAY,
        MY_LIST_NAME_INPUT,
        MY_LIST_OK_BUTTON,
        CLOSE_ARTICLE_BUTTON,
        FOLDER,
        AUTH_CLOSE,
        CLEAR_MINI;

    public static String getSaveArticleXpathByTitle(String article_title) {
        return TITILE.replace("{TITLE}", article_title);
    }

    private static String getFolderXpathByName(String nameOfFolder) {
        return FOLDER.replace("{FOLDER}", nameOfFolder);
    }

    public ArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }

    //ожидаем появления заголовка на прогружаемой странице
    public WebElement waitForTitleElement(String nameTitle) {
        if (Platform.getInstance().isAndroid()) {
            return this.waitForElementPresent(TITILE, "Cannot find article title on page", 15);
        }
        else {
            return this.waitForElementPresent(getSaveArticleXpathByTitle(nameTitle), "Cannot find article title on page", 15);
        }
    }

    //получаем текст заголовка
    public String getArticleTitle(String nameTitle) {
        WebElement titleElement = waitForTitleElement(nameTitle);
        if (Platform.getInstance().isAndroid()) {
            return titleElement.getAttribute("text");
        } else if (Platform.getInstance().isIOS()) {
            return titleElement.getAttribute("name");
        } else {
            return titleElement.getText();
        }
    }

    //свайпаем вверх, спускаемся к низу страницы
    public void swipeToFolder() {
        if (Platform.getInstance().isAndroid()) {
            this.swipeUpToFindElement(
                FOOTER_ELEMENT,
                "Cannot find the end of article",
                40
            );
        } else if (Platform.getInstance().isIOS()){
            this.swipeUPTitleElementAppear(FOOTER_ELEMENT,
                "Cannot find the end of article",
                40);
        } else {
            this.scrolWebPageTitlElementNotVisible(
                FOOTER_ELEMENT,
                "Cannot find the end of article",
                40);
        }
    }

    public void addArticleToMyList(String nameOfFolder) {
        this.waitForElementAndClick(
            OPTIONS_BUTTON,
            "Cannot find button to open artilce options",
            5
        );

        this.waitForElementAndClick(
            OPTIONS_ADD_TO_ME_LIST_BUTTON,
            "Cannot find option to add artilce to reading list",
            5
        );

        this.waitForElementAndClick(
            ADD_TO_MY_LIST_OVERLAY,
            "Cannot find 'Got it' ip overlay",
            5
        );

        this.waitForElementAndClear(
            MY_LIST_NAME_INPUT,
            "Cannot find input to set name of articles folder",
            5
        );

        this.waitForElementAndSendKeys(
            MY_LIST_NAME_INPUT,
            nameOfFolder,
            "Cannot put text into articles folder input",
            5
        );

        this.waitForElementAndClick(
            MY_LIST_OK_BUTTON,
            "Cannot press OK button",
            5
        );
    }

    public void addArticleToMyExistingList(String nameOfFolder) {
        this.waitForElementAndClick(
            OPTIONS_BUTTON,
            "Cannot find button to open artilce options",
            5
        );

        this.waitForElementAndClick(
            OPTIONS_ADD_TO_ME_LIST_BUTTON,
            "Cannot find option to add artilce to reading list",
            5
        );

        this.waitForElementAndClick(
            getFolderXpathByName(nameOfFolder),
            "",
            5
        );
    }

    public void addArticlesToMySaved() {
        if (Platform.getInstance().isMW()) {
            this.removeArticleFromSavedIfItAdded();
        }
        this.waitForElementAndClick(OPTIONS_ADD_TO_ME_LIST_BUTTON, "Cannot find option to add article to reading list", 5);
    }

    public void removeArticleFromSavedIfItAdded() {
        if (this.isElementPresent(OPTIONS_REMOVE_FROM_MY_LIST_BUTTON)) {
            this.waitForElementAndClick(
                OPTIONS_REMOVE_FROM_MY_LIST_BUTTON,
                "Cannot click to remove",
                1
            );
            this.waitForElementPresent(
                OPTIONS_REMOVE_FROM_MY_LIST_BUTTON,
                "Cannot find button to add an article to saved list after ..."
            );
        }
    }

    public void closeArticle() {

        if (Platform.getInstance().isIOS() || Platform.getInstance().isAndroid()) {
            this.waitForElementAndClick(
                CLOSE_ARTICLE_BUTTON,
                "Cannot close article, cannot find X link",
                5
            );
        } else {
            System.out.println("Method closeArticle do nothing for platform" + Platform.getInstance().getPlatformVar());
        }
    }

    public void iosAuthClose() {
        this.waitForElementAndClick(AUTH_CLOSE, "", 5);
    }

    public void iosClearSearchString() {
        this.waitForElementAndClick(CLEAR_MINI, "", 5);
    }

    public void clickOptionsAddToList() {
        this.waitForElementAndClick(
            OPTIONS_ADD_TO_ME_LIST_BUTTON,
            "Cannot find option to add artilce to reading list",
            15
        );
    }
}
