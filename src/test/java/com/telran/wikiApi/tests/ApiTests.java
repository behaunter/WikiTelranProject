package com.telran.wikiApi.tests;

import com.telran.wikiApi.dto.*;
import io.qameta.allure.Epic;
import org.testng.annotations.Test;

import java.util.List;


import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class ApiTests {

    private static int createdPageId;
    private static String createdTitleOfPage;



    @Epic(value = "Positive Test")
    @Test(priority = 1)

    public void successfulCreationOfPage() {

        Specifications.installSpecification(Specifications.requestSpec(Data.URL), Specifications.responseSpec(200));

        ResponseEditDto response = given()
                .when()
                .multiPart("action", "edit")
                .multiPart("format", "json")
                .multiPart("title", Data.RAND_NAME)
                .multiPart("text", Data.RAND_TEXT)
                .multiPart("summary", Data.RAND_TEXT)
                .multiPart("token", "+\\")
                .multiPart("formatversion", "2")
                .post("")
                .then()
                .log().all()
                .extract().response().jsonPath().getObject("edit", ResponseEditDto.class);

        assertEquals(response.getTitle().toUpperCase(), Data.RAND_NAME.toUpperCase());
        createdPageId = response.getPageid();
        createdTitleOfPage = response.getTitle();


    }

    @Epic(value = "Positive Test")
    @Test(priority = 2)
    public void findCreatedPageByTitleName() {

        Specifications.installSpecification(Specifications.requestSpec(Data.URL), Specifications.responseSpec(200));

        List<ResponsePagesDto> pagesList = given()
                .when()
                .multiPart("action", "query")
                .multiPart("format", "json")
                .multiPart("titles", createdTitleOfPage)
                .multiPart("formatversion", "2")
                .post("")
                .then()
                .log().all()
                .extract().response().jsonPath().getList("query.pages", ResponsePagesDto.class);

        pagesList.forEach(x -> assertEquals(x.getPageid(), createdPageId));
        pagesList.forEach(x -> assertEquals(x.getTitle(), createdTitleOfPage));
    }

    @Epic(value = "Positive Test")
    @Test(priority = 2)
    public void findCreatedPageByID() {

        Specifications.installSpecification(Specifications.requestSpec(Data.URL), Specifications.responseSpec(200));

        List<ResponsePagesDto> pagesList = given()
                .when()
                .multiPart("action", "query")
                .multiPart("format", "json")
                .multiPart("pageids", createdPageId)
                .multiPart("formatversion", "2")
                .post("")
                .then()
                .log().all()
                .extract().response().jsonPath().getList("query.pages", ResponsePagesDto.class);

        pagesList.stream().forEach(x -> assertEquals(x.getPageid(), createdPageId));
        pagesList.stream().forEach(x -> assertEquals(x.getTitle(), createdTitleOfPage));
    }

    @Epic(value = "Positive Test")
    @Test
    public void givePermissionsToUser() {

        Specifications.installSpecification(Specifications.requestSpec(Data.URL), Specifications.responseSpec(200));

        ResponseUserRightsDto response = given()
                .when()
                .multiPart("action", "userrights")
                .multiPart("format", "json")
                .multiPart("user","Mshved")
                .multiPart("add", "interface-admin|bureaucrat|bot|suppress|sysop")
                .multiPart("reason", "Updating rights")
                .multiPart("formatversion", "2")
                .multiPart("token", "+\\")
                .post("")
                .then()
                .log().all()
                .extract().response().jsonPath().getObject("userrights", ResponseUserRightsDto.class);

        assertEquals(response.getUser(),"Mshved");
        assertEquals(response.getUserid(),5);


    }

    @Epic(value = "Negative Test")
    @Test
    public void permissionDeniedErrorWhenDeletePage() {
        Specifications.installSpecification(Specifications.requestSpec(Data.URL), Specifications.responseSpec(200));

        ErrorResponseEditPageDto response = given()
                .when()
                .multiPart("action", "delete")
                .multiPart("format", "json")
                .multiPart("pageid", "157")
                .multiPart("token", "+\\")
                .multiPart("formatversion", "2")
                .post("")
                .then()
                .log().all()
                .extract().response().jsonPath().getObject("error", ErrorResponseEditPageDto.class);

        assertEquals(response.getCode(), "permissiondenied");
        assertEquals(response.getInfo(), "The action you have requested is limited to users in the group: [[Tel-ran:Administrators|Administrators]].");
    }

    @Epic(value = "Negative Test")
    @Test
    public void invalidTitleErrorWhenCreatePage() {
        Specifications.installSpecification(Specifications.requestSpec(Data.URL), Specifications.responseSpec(200));

        ErrorResponseEditPageDto response = given()
                .when()
                .multiPart("action", "edit")
                .multiPart("format", "json")
                .multiPart("title", " ")
                .multiPart("text", " ")
                .multiPart("summary", " ")
                .multiPart("token", "+\\")
                .multiPart("formatversion", "2")
                .post("")
                .then()
                .log().all()
                .extract().response().jsonPath().getObject("error", ErrorResponseEditPageDto.class);

        assertEquals(response.getCode(), "invalidtitle");
        assertEquals(response.getInfo(), "Bad title \" \".");

    }

    @Epic(value = "Negative Test")
    @Test
    public void invalidParammixErrorWhenCreatePage() {
        Specifications.installSpecification(Specifications.requestSpec(Data.URL), Specifications.responseSpec(200));

        ErrorResponseEditPageDto response = given()
                .when()
                .multiPart("action", "edit")
                .multiPart("format", "json")
                .multiPart("title", " ")
                .multiPart("pageid", "157")
                .multiPart("text", " ")
                .multiPart("summary", " ")
                .multiPart("token", "+\\")
                .multiPart("formatversion", "2")
                .post("")
                .then()
                .log().all()
                .extract().response().jsonPath().getObject("error", ErrorResponseEditPageDto.class);

        assertEquals(response.getCode(), "invalidparammix");
        assertEquals(response.getInfo(), "The parameters \"title\" and \"pageid\" can not be used together.");

    }

    @Epic(value = "Negative Test")
    @Test
    public void missingParamErrorWhenCreatePage() {
        Specifications.installSpecification(Specifications.requestSpec(Data.URL), Specifications.responseSpec(200));

        ErrorResponseEditPageDto response = given()
                .when()
                .multiPart("action", "edit")
                .multiPart("format", "json")
                .multiPart("title", " ")
                .multiPart("summary", " ")
                .multiPart("token", "+\\")
                .multiPart("formatversion", "2")
                .post("")
                .then()
                .log().all()
                .extract().response().jsonPath().getObject("error", ErrorResponseEditPageDto.class);

        assertEquals(response.getCode(), "missingparam");
        assertEquals(response.getInfo(), "At least one of the parameters \"text\", \"appendtext\", \"prependtext\" and \"undo\" is required.");

    }

    @Epic(value = "Negative Test")
    @Test
    public void badIntegerErrorWhenCreatePage() {
        Specifications.installSpecification(Specifications.requestSpec(Data.URL), Specifications.responseSpec(200));

        ErrorResponseEditPageDto response = given()
                .when()
                .multiPart("action", "edit")
                .multiPart("format", "json")
                .multiPart("title", Data.RAND_NAME)
                .multiPart("pageid", "")
                .multiPart("text", " ")
                .multiPart("summary", " ")
                .multiPart("token", "+\\")
                .multiPart("formatversion", "2")
                .post("")
                .then()
                .log().all()
                .extract().response().jsonPath().getObject("error", ErrorResponseEditPageDto.class);

        assertEquals(response.getCode(), "badinteger");
        assertEquals(response.getInfo(), "Invalid value \"\" for integer parameter \"pageid\".");

    }

    @Epic(value = "Positive Test")
    @Test
    public void successfulResetOfPasswordByEmail() {
        Specifications.installSpecification(Specifications.requestSpec(Data.URL), Specifications.responseSpec(200));

        ResponseResetPasswordDto response = given()
                .when()
                .multiPart("action", "resetpassword")
                .multiPart("format", "json")
                .multiPart("email", "shved.mykhailo@gmail.com")
                .multiPart("token", "+\\")
                .multiPart("formatversion", "2")
                .post("")
                .then()
                .log().all()
                .extract().response().jsonPath().getObject("resetpassword", ResponseResetPasswordDto.class);

        assertEquals(response.getStatus(), "success");
    }

    @Epic(value = "Positive Test")
    @Test
    public void successfulResetOfPasswordByUsername() {
        Specifications.installSpecification(Specifications.requestSpec(Data.URL), Specifications.responseSpec(200));

        ResponseResetPasswordDto response = given()
                .when()
                .multiPart("action", "resetpassword")
                .multiPart("format", "json")
                .multiPart("user", "testUser")
                .multiPart("token", "+\\")
                .multiPart("formatversion", "2")
                .post("")
                .then()
                .log().all()
                .extract().response().jsonPath().getObject("resetpassword", ResponseResetPasswordDto.class);

        assertEquals(response.getStatus(), "success");
    }


}
