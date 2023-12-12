package com.telran.wikiUI.pages;

import com.codeborne.selenide.SelenideElement;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class LoginPage {

    public SelenideElement loginBtn = $("#wpLoginAttempt");
    public SelenideElement userNameInput = $("#wpName1");
    public SelenideElement passwordInput = $("#wpPassword1");
    public SelenideElement LogOutBtn = $x("//span[contains(text(),'Log out')]");
    public SelenideElement errorBox = $("div.mw-message-box-error.mw-message-box");
    public SelenideElement helpLink = $(By.linkText("Help with logging in"));
    public SelenideElement joinTelRanLink = $(By.linkText("Join Tel-ran"));
    public SelenideElement restorePasswordLink = $(By.linkText("Forgot your password?"));


    public LoginPage logInAccount(String username, String password){
        userNameInput.setValue(username);
        passwordInput.setValue(password);
        loginBtn.click();
        return this;
    }
}
