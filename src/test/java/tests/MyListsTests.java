package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.UI.ArticlePageObject;
import lib.UI.AuthorizationPageObject;
import lib.UI.MyListPageObject;
import lib.UI.NavigationUI;
import lib.UI.SearchPageObject;
import lib.UI.factories.ArticlePageObjectFactory;
import lib.UI.factories.MyListPageObjectFactory;
import lib.UI.factories.NavigationUIFactory;
import lib.UI.factories.SearchPageObjectFactory;
import org.junit.Test;

public class MyListsTests extends CoreTestCase {

    private static final  String nameOfFolder = "Learning prodramming";
    private static final String
        login = "DiA1234567890",
        password = "A1234567890Di";

    @Test
    public void testSaveFirstArticleToMyList() {
        SearchPageObject searchPageObject = SearchPageObjectFactory.get(driver);

        searchPageObject.intitSearchInput();
        searchPageObject.typeSearchLine("Java");
        searchPageObject.clickByArticleWhithSubsting("Object-oriented programming language");

        ArticlePageObject articlePageObject = ArticlePageObjectFactory.get(driver);
        articlePageObject.waitForTitleElement("Java (programming language)");
        String articleTitle = articlePageObject.getArticleTitle("Java (programming language)");

        if (Platform.getInstance().isAndroid()) {
            articlePageObject.addArticleToMyList(nameOfFolder);
        } else {
            articlePageObject.addArticlesToMySaved();
        }

        if (Platform.getInstance().isMW()) {
            articlePageObject.clickOptionsAddToList();
            AuthorizationPageObject auth = new AuthorizationPageObject(driver);
            auth.clickAuthButton();
            auth.enterLoginData(login, password);
            auth.sunmitForm();

            articlePageObject.waitForTitleElement("Java (programming language)");

            assertEquals("We are not on the same page after login",
                articleTitle,
                articlePageObject.getArticleTitle("Java (programming language)")
            );

            articlePageObject.addArticlesToMySaved();
        }

        articlePageObject.closeArticle();

        NavigationUI navigationUI = NavigationUIFactory.get(driver);
        navigationUI.openNavigation();
        navigationUI.clickMyList();

        MyListPageObject myListPageObject = MyListPageObjectFactory.get(driver);

        if (Platform.getInstance().isAndroid()) {
            myListPageObject.openFolderByName(nameOfFolder);
        }
        myListPageObject.swipeByArticleToDelete(articleTitle);
    }
}
