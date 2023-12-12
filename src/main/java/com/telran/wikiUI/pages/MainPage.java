package com.telran.wikiUI.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;


import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class MainPage {

    public SelenideElement createAccountLink = $x("//span[contains(text(),'Create account')]");
    public SelenideElement loginLink = $x("//span[contains(text(),'Log in')]");
    public SelenideElement discussionLink = $(By.linkText("Discussion"));

    public MainPage goToCreateAccountPage(){
        createAccountLink.click();
        return this;
    }

    public MainPage goToLoginPage(){
        loginLink.click();
        return this;
    }

    public MainPage goToDiscussionPage() {
        discussionLink.click();
        return this;
    }
}
