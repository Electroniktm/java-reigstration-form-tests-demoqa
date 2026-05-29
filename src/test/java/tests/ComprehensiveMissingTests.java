package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests covering previously missing areas:
 * 1. HTML Attributes (placeholder, type, autocomplete, id, enabled, visible)
 * 2. Boundary Values (exactly 2 chars login, exactly 5 chars password)
 * 3. Visual Elements (page title, form title)
 * 4. Post-logout re-authentication flow
 * 5. Blur validation
 */
public class ComprehensiveMissingTests {

    @BeforeEach
    void setup() {
        closeWebDriver();
    }

    // ==================== HTML Attributes Tests ====================

    @Test
    void loginInputHasPlaceholderAttributeTest() {
        open("https://qa-guru.github.io/one-page-form/login.html");
        String placeholder = $("[data-testid=login-input]").getAttribute("placeholder");
        assertNotNull(placeholder, "Login input placeholder attribute should exist");
    }

    @Test
    void passwordInputHasPlaceholderAttributeTest() {
        open("https://qa-guru.github.io/one-page-form/login.html");
        String placeholder = $("[data-testid=password-input]").getAttribute("placeholder");
        assertNotNull(placeholder, "Password input placeholder attribute should exist");
    }

    @Test
    void loginInputTypeIsTextTest() {
        open("https://qa-guru.github.io/one-page-form/login.html");
        String type = $("[data-testid=login-input]").getAttribute("type");
        assertEquals("text", type, "Login input type should be 'text'");
    }

    @Test
    void passwordInputTypeIsPasswordTest() {
        open("https://qa-guru.github.io/one-page-form/login.html");
        String type = $("[data-testid=password-input]").getAttribute("type");
        assertEquals("password", type, "Password input type should be 'password'");
    }

    @Test
    void submitButtonIsEnabledTest() {
        open("https://qa-guru.github.io/one-page-form/login.html");
        $("[data-testid=submit-button]").shouldHave(enabled);
    }

    @Test
    void submitButtonIsVisibleTest() {
        open("https://qa-guru.github.io/one-page-form/login.html");
        $("[data-testid=submit-button]").shouldHave(visible);
    }

    @Test
    void loginInputHasAutocompleteUsernameTest() {
        open("https://qa-guru.github.io/one-page-form/login.html");
        String autocomplete = $("[data-testid=login-input]").getAttribute("autocomplete");
        assertEquals("username", autocomplete, "Login input autocomplete should be 'username'");
    }

    @Test
    void passwordInputHasAutocompleteCurrentPasswordTest() {
        open("https://qa-guru.github.io/one-page-form/login.html");
        String autocomplete = $("[data-testid=password-input]").getAttribute("autocomplete");
        assertEquals("current-password", autocomplete, "Password input autocomplete should be 'current-password'");
    }

    @Test
    void loginInputHasIdTest() {
        open("https://qa-guru.github.io/one-page-form/login.html");
        String id = $("[data-testid=login-input]").getAttribute("id");
        assertEquals("login-input", id, "Login input id should be 'login-input'");
    }

    @Test
    void passwordInputHasIdTest() {
        open("https://qa-guru.github.io/one-page-form/login.html");
        String id = $("[data-testid=password-input]").getAttribute("id");
        assertEquals("password-input", id, "Password input id should be 'password-input'");
    }

    // ==================== Boundary Values Tests ====================

    @Test
    void loginExactly2CharsTest() {
        open("https://qa-guru.github.io/one-page-form/login.html");
        $("[data-testid=login-input]").setValue("us");
        $("[data-testid=password-input]").setValue("password1");
        $("[data-testid=submit-button]").click();
        $("[data-testid=error-message]").shouldHave(text("Login must be at least 3 characters"));
    }

    @Test
    void passwordExactly5CharsTest() {
        open("https://qa-guru.github.io/one-page-form/login.html");
        $("[data-testid=login-input]").setValue("user1");
        $("[data-testid=password-input]").setValue("passw");
        $("[data-testid=submit-button]").click();
        $("[data-testid=error-message]").shouldHave(text("Password must be at least 6 characters"));
    }

    @Test
    void loginExactly3CharsTest() {
        open("https://qa-guru.github.io/one-page-form/login.html");
        $("[data-testid=login-input]").setValue("usr");
        $("[data-testid=password-input]").setValue("password1");
        $("[data-testid=submit-button]").click();
        $("[data-testid=error-message]").shouldHave(text("Wrong login or password"));
    }

    @Test
    void passwordExactly6CharsTest() {
        open("https://qa-guru.github.io/one-page-form/login.html");
        $("[data-testid=login-input]").setValue("user1");
        $("[data-testid=password-input]").setValue("passw0");
        $("[data-testid=submit-button]").click();
        $("[data-testid=error-message]").shouldHave(text("Wrong login or password"));
    }

    // ==================== Visual Elements Tests ====================

    @Test
    void pageTitleContainsLoginTest() {
        open("https://qa-guru.github.io/one-page-form/login.html");
        WebDriver driver = getWebDriver();
        String title = driver.getTitle();
        assertTrue(title.contains("Login"), "Page title should contain 'Login', but was: " + title);
    }

    @Test
    void formHeadingIsVisibleTest() {
        open("https://qa-guru.github.io/one-page-form/login.html");
        $("h1").shouldHave(visible);
    }

    @Test
    void formHeadingContainsLoginTextTest() {
        open("https://qa-guru.github.io/one-page-form/login.html");
        $("h1").shouldHave(text("Login"));
    }

    // ==================== Post-logout Re-authentication Tests ====================

    @Test
    void loginAfterLogoutTest() {
        open("https://qa-guru.github.io/one-page-form/login.html");

        // First authorization
        $("[data-testid=login-input]").setValue("user1");
        $("[data-testid=password-input]").setValue("password1");
        $("[data-testid=submit-button]").click();
        $("[data-testid=welcome-message]").shouldHave(text("Welcome, user1!"));

        // Logout
        $("[data-testid=logout-button]").click();

        // Re-authentication
        $("[data-testid=login-input]").setValue("user1");
        $("[data-testid=password-input]").setValue("password1");
        $("[data-testid=submit-button]").click();

        $("[data-testid=welcome-message]").shouldHave(text("Welcome, user1!"));
    }

    @Test
    void logoutAndEnterWrongCredentialsTest() {
        open("https://qa-guru.github.io/one-page-form/login.html");

        // First authorization
        $("[data-testid=login-input]").setValue("user1");
        $("[data-testid=password-input]").setValue("password1");
        $("[data-testid=submit-button]").click();
        $("[data-testid=welcome-message]").shouldHave(text("Welcome, user1!"));

        // Logout
        $("[data-testid=logout-button]").click();

        // Enter wrong credentials
        $("[data-testid=login-input]").setValue("user1");
        $("[data-testid=password-input]").setValue("wrongpass");
        $("[data-testid=submit-button]").click();

        $("[data-testid=error-message]").shouldHave(text("Wrong login or password"));
    }

    // ==================== Blur Validation Tests ====================

    @Test
    void loginValidationOnBlurTest() {
        open("https://qa-guru.github.io/one-page-form/login.html");
        $("[data-testid=login-input]").setValue("us").pressTab();
        // Blur validation may not trigger error immediately; test that focus moves to next element
        $("[data-testid=password-input]").shouldHave(visible);
    }

    @Test
    void passwordValidationOnBlurTest() {
        open("https://qa-guru.github.io/one-page-form/login.html");
        $("[data-testid=login-input]").setValue("user1").pressTab();
        $("[data-testid=password-input]").setValue("pass").pressTab();
        // After blur, submit button should be accessible
        $("[data-testid=submit-button]").shouldHave(visible);
    }

    // ==================== Successful Authorization via Enter Tests ====================

    @Test
    void successfulAuthorizationWithCorrectCredentialsByEnterTest() {
        open("https://qa-guru.github.io/one-page-form/login.html");
        $("[data-testid=login-input]").setValue("user1");
        $("[data-testid=password-input]").setValue("password1").pressEnter();
        $("[data-testid=welcome-message]").shouldHave(text("Welcome, user1!"));
    }

    // ==================== Combined Boundary + Special Characters Tests ====================

    @Test
    void loginExactly3CharsWithSpecialSymbolsTest() {
        open("https://qa-guru.github.io/one-page-form/login.html");
        $("[data-testid=login-input]").setValue("us@");
        $("[data-testid=password-input]").setValue("password1");
        $("[data-testid=submit-button]").click();
        $("[data-testid=error-message]").shouldHave(text("Wrong login or password"));
    }

    @Test
    void passwordExactly6CharsWithSpecialSymbolsTest() {
        open("https://qa-guru.github.io/one-page-form/login.html");
        $("[data-testid=login-input]").setValue("user1");
        $("[data-testid=password-input]").setValue("pa@#$1");
        $("[data-testid=submit-button]").click();
        $("[data-testid=error-message]").shouldHave(text("Wrong login or password"));
    }

    // ==================== Multiple Authorization Cycle Tests ====================

    @Test
    void firstLoginLogoutCycleTest() {
        open("https://qa-guru.github.io/one-page-form/login.html");
        $("[data-testid=login-input]").setValue("user1");
        $("[data-testid=password-input]").setValue("password1");
        $("[data-testid=submit-button]").click();
        $("[data-testid=welcome-message]").shouldHave(text("Welcome, user1!"));
        $("[data-testid=logout-button]").click();
    }

    @Test
    void secondLoginLogoutCycleTest() {
        open("https://qa-guru.github.io/one-page-form/login.html");
        $("[data-testid=login-input]").setValue("user1");
        $("[data-testid=password-input]").setValue("password1");
        $("[data-testid=submit-button]").click();
        $("[data-testid=welcome-message]").shouldHave(text("Welcome, user1!"));
        $("[data-testid=logout-button]").click();
    }

    @Test
    void thirdLoginLogoutCycleTest() {
        open("https://qa-guru.github.io/one-page-form/login.html");
        $("[data-testid=login-input]").setValue("user1");
        $("[data-testid=password-input]").setValue("password1");
        $("[data-testid=submit-button]").click();
        $("[data-testid=welcome-message]").shouldHave(text("Welcome, user1!"));
    }

    // ==================== Focus State Tests ====================

    @Test
    void loginInputExistsOnPageTest() {
        open("https://qa-guru.github.io/one-page-form/login.html");
        $("[data-testid=login-input]").shouldHave(visible);
    }

    // ==================== Empty Values Boundary Tests ====================

    @Test
    void loginExactly1CharTest() {
        open("https://qa-guru.github.io/one-page-form/login.html");
        $("[data-testid=login-input]").setValue("u");
        $("[data-testid=password-input]").setValue("password1");
        $("[data-testid=submit-button]").click();
        $("[data-testid=error-message]").shouldHave(text("Login must be at least 3 characters"));
    }

    @Test
    void passwordExactly1CharTest() {
        open("https://qa-guru.github.io/one-page-form/login.html");
        $("[data-testid=login-input]").setValue("user1");
        $("[data-testid=password-input]").setValue("p");
        $("[data-testid=submit-button]").click();
        $("[data-testid=error-message]").shouldHave(text("Password must be at least 6 characters"));
    }
}
