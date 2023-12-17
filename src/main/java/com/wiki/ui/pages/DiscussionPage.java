package com.wiki.ui.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class DiscussionPage {

    public SelenideElement readBtn = $(By.linkText("Read"));
    public SelenideElement editBtn = $(By.linkText("Edit"));
    public SelenideElement historyBtn = $(By.linkText("View history"));
    public SelenideElement addTopicBtn = $(By.linkText("Add topic"));
    public SelenideElement textBox = $("#wpTextbox1");
    public SelenideElement saveBtn = $("#wpSave");
    public SelenideElement summaryInput = $("#wpSummary");

    public void editText(String text){
        textBox.setValue(text);
        saveBtn.click();
    }

    public void addNewTopic(String summary,String text){
        summaryInput.setValue(summary);
        textBox.setValue(text);
        saveBtn.click();
    }
}
