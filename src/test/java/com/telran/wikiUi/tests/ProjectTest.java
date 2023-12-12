package com.telran.wikiUi.tests;

import com.telran.testData.Constants;
import com.telran.wikiUI.pages.ProjectPage;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class ProjectTest extends TestBase {

    ProjectPage projectPage = new ProjectPage();

    @Test
    public void editProjectPage() {
        projectPage.editBtn.click();
        projectPage.editText(Constants.TEXT_PROJECT);
        projectPage.readBtn.click();
        $(byText(Constants.TEXT_DISCUSSION)).shouldBe(visible);
    }
}
