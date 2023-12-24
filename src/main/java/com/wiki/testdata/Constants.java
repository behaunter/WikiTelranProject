package com.wiki.testdata;

import org.apache.commons.lang3.RandomStringUtils;

public class Constants {
    public static final String WIKI_URL = "http://wiki.telran-edu.de:8989/";

    //Create Account Page
    public static final String CREATE_ACCOUNT_TEXT = "Create account";
//    public static final String TEST_USERNAME = RandomStringUtils.randomAlphanumeric(3, 10);
    public static final String TEST_PASSWORD = RandomStringUtils.randomAlphanumeric(8, 20);
    public static final String TEST_EMAIL = RandomStringUtils.randomAlphanumeric(8, 20) + "@gmail.com";
    public static final String TEST_REAL_NAME = RandomStringUtils.randomAlphanumeric(3, 20);
    public static final String SUCCESSFUL_REGISTER = "Welcome," ;
    public static final String MOST_COMMON_PASS_ERROR = "The password entered is in a list of very commonly used passwords. " +
            "Please choose a more unique password.";
    public static final String TOO_SHORT_PASS_ERROR = "Passwords must be at least 8 characters.";
    public static final String INCORRECT_USERNAME_ERROR = "You have not specified a valid username.";
    public static final String PROBLEMS_WITH_INPUT_ERROR = "There are problems with some of your input.";
    public static final String RETYPE_PASS_IS_NOT_CORRECT = "The passwords you entered do not match.";
    public static final String MOST_COMMON_PASSWORD = "password";
    public static final String SHORT_PASSWORD = "pass";
    public static final String SPEC_SYMBOLS_TEXT = "!@#$%^&";
    public static final String PASS_WITH_SYMBOLS = "pass123!@#";

    //Login Page
    public static final String HELP_LOGIN_URL = "https://www.mediawiki.org/wiki/Help:Logging_in";
    public static final String JOIN_TELRAN_URL = "http://wiki.telran-edu.de:8989/index.php?title=Special:";
    public static final String PASS_RESET_URL = "http://wiki.telran-edu.de:8989/index.php/Special:PasswordReset";
    public static final String EXPECTED_JOIN_TELRAN_TEXT = "CreateAccount&returnto=Tel-ran:Tel-Ran";
    public static final String RAND_USERNAME = RandomStringUtils.randomAlphanumeric(1, 20);
    public static final String RAND_PASSWORD = RandomStringUtils.randomAlphanumeric(1, 20);
    public static final String VALID_USER_NAME = "Mshved";
    public static final String VALID_PASSWORD = "Popaxxxx4342004";
    public static final String XSS_SCRIPT = "<script>alert(1)</script>";
    public static final String SQL_INJECTION = "SELECT * FROM Users WHERE UserId = 1;";
    public static final String USER_NAME_WITH_SPACES = "M s h v e d";
    public static final String ADMIN_TEXT = "admin";
    public static final String SPEC_SYMBOLS = "!@#$%^&*";
    public static final String PASSWORD_WITH_SPACES = "P o p a x x x x 4 3 4 2 0 0 4";
    public static final String SPECIAL_SYMBOLS_ERROR = "The supplied credentials could not be authenticated.";
    public static final String INCORRECT_DATA_ERROR = "Incorrect username or password entered.\n" +
            "Please try again.";

    // Project Page
    public static final String TEXT_PROJECT = RandomStringUtils.randomAlphanumeric(1, 10);

    //Discussion Page
    public static final String TEXT_DISCUSSION = RandomStringUtils.randomAlphanumeric(1, 10);
    public static final String NEW_TOPIC_TEXT = RandomStringUtils.randomAlphanumeric(1, 200);
    public static final String SUMMARY_TEXT = RandomStringUtils.randomAlphanumeric(1, 10);

}
