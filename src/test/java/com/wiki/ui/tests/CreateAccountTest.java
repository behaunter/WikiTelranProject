package com.wiki.ui.tests;

import com.wiki.testdata.Constants;
import com.wiki.ui.pages.CreateAccountPage;
import com.wiki.ui.pages.MainPage;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;


public class CreateAccountTest extends TestBase {
    MainPage mainPage = new MainPage();
    CreateAccountPage createAccountPage = new CreateAccountPage();
    String TEST_USERNAME;

    @BeforeMethod
    public void prepare() {
        mainPage.goToCreateAccountPage();
        TEST_USERNAME = RandomStringUtils.randomAlphanumeric(3, 10);
    }


    @Test
    public void successCreateAccountWithAllFields() {
        createAccountPage.createAccount(TEST_USERNAME, Constants.TEST_PASSWORD, Constants.TEST_PASSWORD,
                Constants.TEST_EMAIL, Constants.TEST_REAL_NAME);
        createAccountPage.header.shouldBe(visible).shouldHave(text(Constants.SUCCESSFUL_REGISTER));

    }

    @Test
    public void successCreateAccountWithReqFieldsOnly() {

        createAccountPage.createAccount(TEST_USERNAME, Constants.TEST_PASSWORD, Constants.TEST_PASSWORD,
                "", "");
        createAccountPage.header.shouldBe(visible).shouldHave(text(Constants.SUCCESSFUL_REGISTER));

    }

    @Test
    public void checkMostCommonlyUsedPasswordError() {
        createAccountPage.createAccount(TEST_USERNAME, Constants.MOST_COMMON_PASSWORD, Constants.MOST_COMMON_PASSWORD,
                "", "");
        createAccountPage.errorBox.shouldBe(visible).shouldHave(text(Constants.MOST_COMMON_PASS_ERROR));
    }

    @Test
    public void tooShortPasswordError() {
        createAccountPage.createAccount(TEST_USERNAME, Constants.SHORT_PASSWORD, Constants.SHORT_PASSWORD,
                "", "");
        createAccountPage.errorBox.shouldBe(visible).shouldHave(text(Constants.TOO_SHORT_PASS_ERROR));
    }

    @Test
    public void incorrectUsernameError() {
        createAccountPage.createAccount(Constants.SPEC_SYMBOLS_TEXT, Constants.SHORT_PASSWORD, Constants.SHORT_PASSWORD,
                "", "");
        createAccountPage.errorBox.shouldBe(visible).shouldHave(text(Constants.INCORRECT_USERNAME_ERROR));
    }

    @Test
    public void retypePasswordIsNotCorrectError() {
        createAccountPage.createAccount(TEST_USERNAME, Constants.PASS_WITH_SYMBOLS, Constants.SHORT_PASSWORD,
                "", "");
        createAccountPage.errorBox.shouldBe(visible).shouldHave(text(Constants.PROBLEMS_WITH_INPUT_ERROR));
        $(byText(Constants.RETYPE_PASS_IS_NOT_CORRECT)).shouldBe(visible);
    }

}
