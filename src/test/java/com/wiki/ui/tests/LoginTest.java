package com.wiki.ui.tests;

import com.wiki.testdata.Constants;
import com.wiki.ui.pages.LoginPage;
import com.wiki.ui.pages.MainPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.testng.Assert.*;

public class LoginTest extends TestBase {

    MainPage mainPage = new MainPage();
    LoginPage loginPage = new LoginPage();
    @BeforeMethod
    public void prepare(){
        mainPage.goToLoginPage();
    }

    @Test
    public void successLogin(){
        loginPage.logInAccount(Constants.VALID_USER_NAME, Constants.VALID_PASSWORD);
        loginPage.LogOutBtn.shouldBe(visible);
        $(byText(Constants.VALID_USER_NAME)).shouldBe(visible);
    }

    @Test
    public void incorrectLogin(){
        loginPage.logInAccount(Constants.RAND_USERNAME, Constants.RAND_PASSWORD);
        loginPage.errorBox.shouldHave(text(Constants.INCORRECT_DATA_ERROR));
    }

    @Test
    public void loginWithSpacesInTheBeginning(){
        loginPage.logInAccount(" " + Constants.VALID_USER_NAME, " " + Constants.VALID_PASSWORD);
        loginPage.errorBox.shouldHave(text(Constants.INCORRECT_DATA_ERROR));
    }

    @Test
    public void loginWithSpacesInTheEnd(){
        loginPage.logInAccount(Constants.VALID_USER_NAME + " " ,  Constants.VALID_PASSWORD + " ");
        loginPage.errorBox.shouldHave(text(Constants.INCORRECT_DATA_ERROR));
    }

    @Test
    public void loginWithSpacesInTheMiddle(){
        loginPage.logInAccount(Constants.USER_NAME_WITH_SPACES, Constants.PASSWORD_WITH_SPACES);
        loginPage.errorBox.shouldHave(text(Constants.INCORRECT_DATA_ERROR));
    }

    @Test
    public void tryXssScriptOnLoginFields(){
        loginPage.logInAccount(Constants.XSS_SCRIPT, Constants.XSS_SCRIPT);
        loginPage.errorBox.shouldHave(text(Constants.SPECIAL_SYMBOLS_ERROR));
    }

    @Test
    public void trySqlInjectionOnLoginFields(){
        loginPage.logInAccount(Constants.SQL_INJECTION, Constants.SQL_INJECTION);
        loginPage.errorBox.shouldHave(text(Constants.INCORRECT_DATA_ERROR));
    }

    @Test
    public void tryLoginWithAdminCreds(){
        loginPage.logInAccount(Constants.ADMIN_TEXT, Constants.ADMIN_TEXT);
        loginPage.errorBox.shouldHave(text(Constants.INCORRECT_DATA_ERROR));
    }

    @Test
    public void LoginWithHighRegistryLetters(){
        loginPage.logInAccount(Constants.VALID_USER_NAME.toUpperCase(), Constants.VALID_PASSWORD.toUpperCase());
        loginPage.errorBox.shouldHave(text(Constants.INCORRECT_DATA_ERROR));
    }

    @Test
    public void LoginWithLowRegistryLetters(){
        loginPage.logInAccount(Constants.VALID_USER_NAME.toLowerCase(), Constants.VALID_PASSWORD.toLowerCase());
        loginPage.errorBox.shouldHave(text(Constants.INCORRECT_DATA_ERROR));
    }

    @Test
    public void loginWithSpecialSymbols(){
        loginPage.logInAccount(Constants.SPEC_SYMBOLS, Constants.SPEC_SYMBOLS);
        loginPage.errorBox.shouldHave(text(Constants.SPECIAL_SYMBOLS_ERROR));
    }

    @Test
    public void checkLinkToHelpWithLogin(){
        loginPage.helpLink.click();
        String currentUrl = getWebDriver().getCurrentUrl();
        assertEquals(currentUrl, Constants.HELP_LOGIN_URL);
    }

    @Test
    public void checkLinkToJoinTelRan(){
        loginPage.joinTelRanLink.click();
        String currentUrl = getWebDriver().getCurrentUrl();
        assertEquals(currentUrl,Constants.JOIN_TELRAN_URL +
                Constants.EXPECTED_JOIN_TELRAN_TEXT);
    }

    @Test
    public void checkLinkToForgotPasswordPage(){
        loginPage.restorePasswordLink.click();
        String currentUrl = getWebDriver().getCurrentUrl();
        assertEquals(currentUrl,Constants.PASS_RESET_URL);
    }





}
