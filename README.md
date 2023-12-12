
# WIKI UI and API Tests

My name is Mykhailo Shved and it's my final project in Tel-Ran tech school to show my knowledge in Java Test automation.

## Contents
- [Tech Stack](#TechStack)
- [Main test packages/files](#Maintestpackages/files)
- [How to add new tests?](#Howtoaddnewtests?)
- [The possible ways to fix tests](#Thepossiblewaystofixtests)
- [Authors](#Authors)

## Tech Stack

- Java 18
- TestNG
- Rest-Assured
- Lombok
- Selenide
- Allure


## Main test packages/files


`src/main/java/com/telran/testData` contains all Constants that were used in test methods as Test Data;

`src/main/java/com/telran/wikiApi/dto` contains all DTO (Data Transfer Object) that were used for API test;

`src/main/java/com/telran/wikiUI/pages` UI package contains all data regarding pages in POM (page object model);

`src/main/resources` contains information about allure reports and logback configuration;

`com/telran/wikiUi/tests` contains different tests for UI automation of wiki site;

`com/telran/wikiApi/tests`
 contains different tests for API automation of wiki site;

`src/test/java/com/telran/wikiUi/tests/TestBase.java`  Base Class for Test configurations of Selenide Framework;

## How to add new tests?

1. First, open needed UI page
2. Add needed selenide locators in specific UI page class
3. Take these locators and combine them in the methods of page classes
4. Add API layers in case if it's needed
5. Combine page classes in the tests


## The possible ways to fix tests

1. Restart tests

If the restart doesn't help - then let's dig deeper.

2. If locator is broken.
Try to find new, correct locator, add it to the specific page.

3. If API method is broken.
Check that status code == expected status code.
If the status code >= 500 - ask devs to recheck the server status. It could be just infrastructure and fix will be rapid. Or, it could be, a bug. In this case, you should create a bug and submit it to the responsible dev
Update endpoint version and commit the changes.


## Authors

- [@behaunter (Shved Mykhailo)](https://github.com/behaunter)

