package lib.UI;

import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class MyListPageObject extends MainPageObject {

    protected static String
        FOLDER_BY_NAME_TPL,
        ARTICLE_BY_TITLE_TPL,
        REMOVE_FROM_SAVED_BUTTON;

    private static String getFolderXpathByName(String nameOfFolder) {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_MAME}", nameOfFolder);
    }

    private static String getFolderXpathByTitle(String articleTitle) {
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", articleTitle);
    }

    private static String getRemoveButtonByTitle(String articleTitle) {
        return REMOVE_FROM_SAVED_BUTTON.replace("{TITLE}", articleTitle);
    }

    public MyListPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    public void openFolderByName(String nameOfFolder) {
        this.waitForElementAndClick(
            getFolderXpathByName(nameOfFolder),
            "Cannot find folder By name" + nameOfFolder,
            5
        );
    }

    public void waitForArticleToAppearByTitle(String articleTitle) {
        this.waitForElementPresent(getFolderXpathByTitle(articleTitle), "Cannot find saved article by title" + articleTitle, 15);
    }

    public void waitForArticleToDisappearByTitle(String articleTitle) {
        this.waitForElementNotPresent(getFolderXpathByTitle(articleTitle), "Saved article srill present with title" + articleTitle, 15
        );
    }

    public void swipeByArticleToDelete(String articleTitle) {
        this.waitForArticleToAppearByTitle(articleTitle);
        String articleXpath = getFolderXpathByTitle(articleTitle);

        if (Platform.getInstance().isIOS() || Platform.getInstance().isAndroid()) {

            this.swipeElementToLeft(
                articleXpath,
                "Cannot find saved article"
            );
        } else {
            String removeLocator = getRemoveButtonByTitle(articleTitle);
            this.waitForElementAndClick(
                removeLocator,
                "Cannot click button to remove article from saved",
                10
            );
        }

        if (Platform.getInstance().isIOS()) {
            this.clickElementToTheRightUppeeCorner(articleXpath, "Cannot find saved article");
        }

        if (Platform.getInstance().isMW()) {
            driver.navigate().refresh();
        }

        this.waitForArticleToDisappearByTitle(articleTitle);
    }
}
