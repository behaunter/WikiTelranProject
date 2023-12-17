package com.wiki.ui.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class ProjectPage {

    public SelenideElement readBtn = $(By.linkText("Read"));
    public SelenideElement editBtn = $(By.linkText("Edit"));
    public SelenideElement historyBtn = $(By.linkText("View history"));
    public SelenideElement textBox = $("#wpTextbox1");
    public SelenideElement saveBtn = $("#wpSave");

    public void editText(String value){
        textBox.setValue(value);
        saveBtn.click();
    }

}
