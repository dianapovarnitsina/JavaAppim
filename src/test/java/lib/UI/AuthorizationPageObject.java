package lib.UI;

import org.openqa.selenium.remote.RemoteWebDriver;

public class AuthorizationPageObject extends MainPageObject {

    private  static final String
        LOGIN_BUTTON = "xpath://body/div[@class='drawer-container view-border-box']/div[@class='drawer drawer-container__drawer position-fixed visible']/a[text()='Log in']",
        LOGIN_INPUT = "css:input[name='wpName']",
        PASSWORD_INPUT = "css:input[name='wpPassword']",
        SUBMIT_BUTTON = "css:button#wpLoginAttempt";

    public AuthorizationPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    public void clickAuthButton() {
        this.waitForElementPresent(LOGIN_BUTTON, "Cannot find auth button", 10);
        this.waitForElementAndClick(LOGIN_BUTTON, "Cannot find and click auth button", 10);
    }

    public void enterLoginData(String login, String password) {
        this.waitForElementAndSendKeys(LOGIN_INPUT, login,"Cannot find and put login", 5);
        this.waitForElementAndSendKeys(PASSWORD_INPUT, password,"Cannot find and put password", 5);
    }

    public void sunmitForm() {
        this.waitForElementAndClick(SUBMIT_BUTTON, "Cannot find and click button",5);
    }
}
