package com.telran.wikiUi.tests;

import com.telran.testData.Constants;
import com.telran.wikiUI.pages.LoginPage;
import com.telran.wikiUI.pages.MainPage;
import org.testng.Assert;
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
        loginPage.logInAccount(Constants.validUserName, Constants.validPassword);
        loginPage.LogOutBtn.shouldBe(visible);
        $(byText(Constants.validUserName)).shouldBe(visible);
    }

    @Test
    public void incorrectLogin(){
        loginPage.logInAccount(Constants.randUsername, Constants.randPassword);
        loginPage.errorBox.shouldHave(text(Constants.incorrectDataError));
    }

    @Test
    public void loginWithSpacesInTheBeginning(){
        loginPage.logInAccount(" " + Constants.validUserName, " " + Constants.validPassword);
        loginPage.errorBox.shouldHave(text(Constants.incorrectDataError));
    }

    @Test
    public void loginWithSpacesInTheEnd(){
        loginPage.logInAccount(Constants.validUserName + " " ,  Constants.validPassword  + " ");
        loginPage.errorBox.shouldHave(text(Constants.incorrectDataError));
    }

    @Test
    public void loginWithSpacesInTheMiddle(){
        loginPage.logInAccount(Constants.userNameWithSpaces, Constants.PasswordWithSpaces);
        loginPage.errorBox.shouldHave(text(Constants.incorrectDataError));
    }

    @Test
    public void tryXssScriptOnLoginFields(){
        loginPage.logInAccount(Constants.xssScript, Constants.xssScript);
        loginPage.errorBox.shouldHave(text(Constants.specialSymbolsError));
    }

    @Test
    public void trySqlInjectionOnLoginFields(){
        loginPage.logInAccount(Constants.sqlInjection, Constants.sqlInjection);
        loginPage.errorBox.shouldHave(text(Constants.incorrectDataError));
    }

    @Test
    public void tryLoginWithAdminCreds(){
        loginPage.logInAccount(Constants.adminText, Constants.adminText);
        loginPage.errorBox.shouldHave(text(Constants.incorrectDataError));
    }

    @Test
    public void LoginWithHighRegistryLetters(){
        loginPage.logInAccount(Constants.validUserName.toUpperCase(), Constants.validPassword.toUpperCase());
        loginPage.errorBox.shouldHave(text(Constants.incorrectDataError));
    }

    @Test
    public void LoginWithLowRegistryLetters(){
        loginPage.logInAccount(Constants.validUserName.toLowerCase(), Constants.validPassword.toLowerCase());
        loginPage.errorBox.shouldHave(text(Constants.incorrectDataError));
    }

    @Test
    public void loginWithSpecialSymbols(){
        loginPage.logInAccount(Constants.specSymbols, Constants.specSymbols);
        loginPage.errorBox.shouldHave(text(Constants.specialSymbolsError));
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
                Constants.expectedJoinTelRanText);
    }

    @Test
    public void checkLinkToForgotPasswordPage(){
        loginPage.restorePasswordLink.click();
        String currentUrl = getWebDriver().getCurrentUrl();
        assertEquals(currentUrl,Constants.PASS_RESET_URL);
    }





}
