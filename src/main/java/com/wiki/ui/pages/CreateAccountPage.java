package com.wiki.ui.pages;

import com.codeborne.selenide.SelenideElement;


import static com.codeborne.selenide.Selenide.$;


public class CreateAccountPage {

    public SelenideElement usernameInput = $("#wpName2");
    public SelenideElement passwordInput = $("#wpPassword2");
    public SelenideElement retypePassInput = $("#wpRetype");
    public SelenideElement emailInput = $("#wpEmail");
    public SelenideElement realNameInput = $("#wpRealName");
    public SelenideElement createAccBtn = $("#wpCreateaccount");
    public SelenideElement header = $("#firstHeading");
    public SelenideElement errorBox = $("div.mw-message-box-error.mw-message-box");




    public CreateAccountPage createAccount(String username, String password, String retypePass, String email, String realName){
        usernameInput.setValue(username);
        passwordInput.setValue(password);
        retypePassInput.setValue(retypePass);
        emailInput.setValue(email);
        realNameInput.setValue(realName);
        createAccBtn.click();
        return this;
    }


}
