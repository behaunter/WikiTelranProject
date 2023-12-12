package com.telran.wikiUi.tests;

import com.telran.testData.Constants;
import com.telran.wikiUI.pages.CreateAccountPage;
import com.telran.wikiUI.pages.MainPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;


public class CreateAccountTest extends TestBase{
    MainPage mainPage = new MainPage();
    CreateAccountPage createAccountPage = new CreateAccountPage();
   @BeforeMethod
   public void prepare(){
       mainPage.goToCreateAccountPage();
   }


    @Test
    public void successCreateAccountWithAllFields(){
       createAccountPage.createAccount(Constants.testUsername, Constants.testPassword,Constants.testPassword,
               Constants.testEmail, Constants.testRealName);
       createAccountPage.header.shouldBe(visible).shouldHave(text(Constants.successfulRegister));

    }

    @Test
    public void successCreateAccountWithReqFieldsOnly()  {
        createAccountPage.createAccount(Constants.testUsername,Constants.testPassword,Constants.testPassword,
                "","");
        createAccountPage.header.shouldBe(visible).shouldHave(text(Constants.successfulRegister));

    }

    @Test
    public void checkMostCommonlyUsedPasswordError(){
        createAccountPage.createAccount(Constants.testUsername,Constants.mostCommonPassword,Constants.mostCommonPassword,
                "","");
        createAccountPage.errorBox.shouldBe(visible).shouldHave(text(Constants.mostCommonPassError));
    }

    @Test
    public void tooShortPasswordError(){
        createAccountPage.createAccount(Constants.testUsername,Constants.shortPassword,Constants.shortPassword,
                "","");
        createAccountPage.errorBox.shouldBe(visible).shouldHave(text(Constants.tooShortPassError));
    }

    @Test
    public void incorrectUsernameError(){
        createAccountPage.createAccount(Constants.specSymbolsText,Constants.shortPassword,Constants.shortPassword,
                "","");
        createAccountPage.errorBox.shouldBe(visible).shouldHave(text(Constants.incorrectUsernameError));
    }

    @Test
    public void retypePasswordIsNotCorrectError(){
        createAccountPage.createAccount(Constants.testUsername,Constants.passWithSymbols,Constants.shortPassword,
                "","");
        createAccountPage.errorBox.shouldBe(visible).shouldHave(text(Constants.problemsWithInputError));
        $(byText(Constants.retypePassIsNotCorrect)).shouldBe(visible);
    }

}
