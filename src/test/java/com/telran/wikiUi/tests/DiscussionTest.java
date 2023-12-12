package com.telran.wikiUi.tests;

import com.telran.testData.Constants;
import com.telran.wikiUI.pages.DiscussionPage;
import com.telran.wikiUI.pages.MainPage;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class DiscussionTest extends TestBase{

    DiscussionPage discussionPage = new DiscussionPage();
    MainPage mainPage = new MainPage();

    @BeforeMethod
    public void prepare(){
        mainPage.goToDiscussionPage();
    }

    @Test
    public void editDiscussionPage() {
        discussionPage.editBtn.click();
        discussionPage.editText(Constants.TEXT_DISCUSSION);
        discussionPage.readBtn.click();
        $(byText(Constants.TEXT_DISCUSSION)).shouldBe(visible);
    }

    @Test
    public void addTopic() {
        discussionPage.addTopicBtn.click();
        discussionPage.addNewTopic(Constants.SUMMARY_TEXT,Constants.NEW_TOPIC_TEXT);
        discussionPage.readBtn.click();
        $(byText(Constants.SUMMARY_TEXT)).shouldBe(visible);
    }

}
