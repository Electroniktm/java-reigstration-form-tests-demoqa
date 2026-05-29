package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.focused;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class ExtendedLoginTests {

    @BeforeEach
    void setup() {
        closeWebDriver();
    }

    @Test
    void specialCharactersInLoginTest() {
        open("https://qa-guru.github.io/one-page-form/login.html");

        $("[data-testid=login-input]").setValue("user@#$%");
        $("[data-testid=password-input]").setValue("password1");
        $("[data-testid=submit-button]").click();

        $("[data-testid=error-message]").shouldHave(text("Wrong login or password"));
    }

    @Test
    void specialCharactersInPasswordTest() {
        open("https://qa-guru.github.io/one-page-form/login.html");

        $("[data-testid=login-input]").setValue("user1");
        $("[data-testid=password-input]").setValue("pass@#$word!");
        $("[data-testid=submit-button]").click();

        $("[data-testid=error-message]").shouldHave(text("Wrong login or password"));
    }

    @Test
    void whitespaceAtStartOfLoginTest() {
        open("https://qa-guru.github.io/one-page-form/login.html");

        $("[data-testid=login-input]").setValue(" user1");
        $("[data-testid=password-input]").setValue("password1");
        $("[data-testid=submit-button]").click();

        $("[data-testid=welcome-message]").shouldHave(text("Welcome, user1!"));
    }

    @Test
    void whitespaceAtEndOfLoginTest() {
        open("https://qa-guru.github.io/one-page-form/login.html");

        $("[data-testid=login-input]").setValue("user1 ");
        $("[data-testid=password-input]").setValue("password1");
        $("[data-testid=submit-button]").click();

        $("[data-testid=welcome-message]").shouldHave(text("Welcome, user1!"));
    }

    @Test
    void whitespaceAtBothSidesOfPasswordTest() {
        open("https://qa-guru.github.io/one-page-form/login.html");

        $("[data-testid=login-input]").setValue("user1");
        $("[data-testid=password-input]").setValue(" password1 ");
        $("[data-testid=submit-button]").click();

        $("[data-testid=welcome-message]").shouldHave(text("Welcome, user1!"));
    }

    @Test
    void longLoginTest() {
        open("https://qa-guru.github.io/one-page-form/login.html");

        String longLogin = "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz";
        $("[data-testid=login-input]").setValue(longLogin);
        $("[data-testid=password-input]").setValue("password1");
        $("[data-testid=submit-button]").click();

        $("[data-testid=error-message]").shouldHave(text("Wrong login or password"));
    }

    @Test
    void longPasswordTest() {
        open("https://qa-guru.github.io/one-page-form/login.html");

        String longPassword = "abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz";
        $("[data-testid=login-input]").setValue("user1");
        $("[data-testid=password-input]").setValue(longPassword);
        $("[data-testid=submit-button]").click();

        $("[data-testid=error-message]").shouldHave(text("Wrong login or password"));
    }

    @Test
    void loginWithNumbersTest() {
        open("https://qa-guru.github.io/one-page-form/login.html");

        $("[data-testid=login-input]").setValue("user123");
        $("[data-testid=password-input]").setValue("pass1234");
        $("[data-testid=submit-button]").click();

        $("[data-testid=error-message]").shouldHave(text("Wrong login or password"));
    }

    @Test
    void loginWithSpecialSymbolsTest() {
        open("https://qa-guru.github.io/one-page-form/login.html");

        $("[data-testid=login-input]").setValue("user_123!");
        $("[data-testid=password-input]").setValue("password1");
        $("[data-testid=submit-button]").click();

        $("[data-testid=error-message]").shouldHave(text("Wrong login or password"));
    }

    @Test
    void tabNavigationFromLoginToPasswordTest() {
        open("https://qa-guru.github.io/one-page-form/login.html");

        $("[data-testid=login-input]").setValue("user1").pressTab();

        $("[data-testid=password-input]").shouldHave(focused);
    }

    @Test
    void tabNavigationFromPasswordToSubmitTest() {
        open("https://qa-guru.github.io/one-page-form/login.html");

        $("[data-testid=password-input]").setValue("password1").pressTab();

        $("[data-testid=submit-button]").shouldHave(focused);
    }

    @Test
    void loginInputIsEmptyByDefaultTest() {
        open("https://qa-guru.github.io/one-page-form/login.html");

        String currentValue = $("[data-testid=login-input]").getValue();
        if (currentValue == null) {
            throw new AssertionError("Expected null value but got non-null");
        }
    }

    @Test
    void passwordInputIsEmptyByDefaultTest() {
        open("https://qa-guru.github.io/one-page-form/login.html");

        String currentValue = $("[data-testid=password-input]").getValue();
        if (currentValue == null) {
            throw new AssertionError("Expected null value but got non-null");
        }
    }

    @Test
    void clearFieldsAndReenterTest() {
        open("https://qa-guru.github.io/one-page-form/login.html");

        $("[data-testid=login-input]").setValue("user1");
        $("[data-testid=password-input]").setValue("password1");
        $("[data-testid=login-input]").setValue("");
        $("[data-testid=password-input]").setValue("");

        String loginVal = $("[data-testid=login-input]").getValue();
        String passwordVal = $("[data-testid=password-input]").getValue();
        if (loginVal != null && !loginVal.isEmpty()) {
            throw new AssertionError("Login should be empty but was: " + loginVal);
        }
        if (passwordVal != null && !passwordVal.isEmpty()) {
            throw new AssertionError("Password should be empty but was: " + passwordVal);
        }
    }

    @Test
    void correctCredentialsAfterClearTest() {
        open("https://qa-guru.github.io/one-page-form/login.html");

        $("[data-testid=login-input]").setValue("user1");
        $("[data-testid=password-input]").setValue("password1");
        $("[data-testid=login-input]").setValue("");
        $("[data-testid=password-input]").setValue("");

        $("[data-testid=login-input]").setValue("user1");
        $("[data-testid=password-input]").setValue("password1");
        $("[data-testid=submit-button]").click();

        $("[data-testid=welcome-message]").shouldHave(text("Welcome, user1!"));
    }

    @Test
    void doubleClickSubmitButtonTest() {
        open("https://qa-guru.github.io/one-page-form/login.html");

        $("[data-testid=login-input]").setValue("user1");
        $("[data-testid=password-input]").setValue("password1");
        $("[data-testid=submit-button]").click();

        $("[data-testid=welcome-message]").shouldHave(text("Welcome, user1!"));
    }

    @Test
    void rapidTypingAndSubmissionTest() {
        open("https://qa-guru.github.io/one-page-form/login.html");

        $("[data-testid=login-input]").setValue("user11");
        $("[data-testid=password-input]").setValue("password11");
        $("[data-testid=submit-button]").click();

        $("[data-testid=error-message]").shouldHave(text("Wrong login or password"));
    }

    @Test
    void pasteLongStringIntoLoginTest() {
        open("https://qa-guru.github.io/one-page-form/login.html");

        String longString = "A".repeat(100);
        $("[data-testid=login-input]").setValue(longString);
        $("[data-testid=password-input]").setValue("password1");
        $("[data-testid=submit-button]").click();

        $("[data-testid=error-message]").shouldHave(text("Wrong login or password"));
    }

    @Test
    void pasteLongStringIntoPasswordTest() {
        open("https://qa-guru.github.io/one-page-form/login.html");

        String longString = "B".repeat(100);
        $("[data-testid=login-input]").setValue("user1");
        $("[data-testid=password-input]").setValue(longString);
        $("[data-testid=submit-button]").click();

        $("[data-testid=error-message]").shouldHave(text("Wrong login or password"));
    }

    @Test
    void unicodeCharactersInLoginTest() {
        open("https://qa-guru.github.io/one-page-form/login.html");

        $("[data-testid=login-input]").setValue("юзер123");
        $("[data-testid=password-input]").setValue("password1");
        $("[data-testid=submit-button]").click();

        $("[data-testid=error-message]").shouldHave(text("Wrong login or password"));
    }

    @Test
    void unicodeCharactersInPasswordTest() {
        open("https://qa-guru.github.io/one-page-form/login.html");

        $("[data-testid=login-input]").setValue("user1");
        $("[data-testid=password-input]").setValue("пароль123");
        $("[data-testid=submit-button]").click();

        $("[data-testid=error-message]").shouldHave(text("Wrong login or password"));
    }

    @Test
    void emptyFieldsWithEnterKeyTest() {
        open("https://qa-guru.github.io/one-page-form/login.html");

        $("[data-testid=login-input]").pressEnter();

        $("[data-testid=error-message]").shouldHave(text("Login and password are required"));
    }

    @Test
    void onlyLoginFilledWithEnterTest() {
        open("https://qa-guru.github.io/one-page-form/login.html");

        $("[data-testid=login-input]").setValue("user1").pressEnter();

        $("[data-testid=error-message]").shouldHave(text("Password is required"));
    }

    @Test
    void onlyPasswordFilledWithEnterTest() {
        open("https://qa-guru.github.io/one-page-form/login.html");

        $("[data-testid=password-input]").setValue("password1").pressEnter();

        $("[data-testid=error-message]").shouldHave(text("Login is required"));
    }

    @Test
    void multipleRapidSubmissionsTest() {
        open("https://qa-guru.github.io/one-page-form/login.html");

        $("[data-testid=login-input]").setValue("user1");
        $("[data-testid=password-input]").setValue("password1");
        $("[data-testid=submit-button]").click();

        $("[data-testid=welcome-message]").shouldHave(text("Welcome, user1!"));
    }

    @Test
    void logoutAfterSuccessfulAuthorizationTest() {
        open("https://qa-guru.github.io/one-page-form/login.html");

        $("[data-testid=login-input]").setValue("user1");
        $("[data-testid=password-input]").setValue("password1");
        $("[data-testid=submit-button]").click();

        $("[data-testid=welcome-message]").shouldHave(text("Welcome, user1!"));

        $("[data-testid=logout-button]").click();

        $("[data-testid=login-input]").should(visible);
    }
}
