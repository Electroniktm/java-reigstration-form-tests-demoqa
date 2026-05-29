package tests;

import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;


public class RegistrationFormTests extends TestBase {

    // ==================== REGISTRATION FORM TESTS ====================

    @Test
    void fillAllFieldsAndSubmitTest() {
        open("/automation-practice-form");
/*        executeJavaScript("""
        document.getElementById('fixedban')?.remove();
        document.querySelector('footer')?.remove();
        """); */
        // Personal Details
        $("#firstName").setValue("John");
        $("#lastName").setValue("Doe");
        $("#userEmail").setValue("john.doe@example.com");

        // Gender - select Male (radio button)
        $("[id=gender-radio-1]").click();

        // Phone
        $("#userNumber").setValue("1234567890");

        // Date of Birth
        $("[id=dateOfBirthInput]").click();
        // Выбор месяца (selectOption по индексу: январь=0, поэтому month-1)
        $(".react-datepicker__month-select").selectOption(month - 1);
        // Выбор года
        $(".react-datepicker__year-select").selectOption(String.valueOf(year - 10));
        // Выбор дня — форматируем с ведущим нулём: 1 → "001", 24 → "024"
        String dayFormatted = String.format("%03d", day-10);
        $(".react-datepicker__day--" + dayFormatted + ":not(.react-datepicker__day--outside-month)").click();

        // Subjects - type to search and select
        $("#subjectsInput").click();
        $("#subjectsInput").setValue("English");
        $(".subjects-auto-complete__option").click();

        // Hobbies - select Sports
        $("#hobbies-checkbox-1").click();

        // Picture upload
        $("#uploadPicture").uploadFile(new File("src/test/resources/test.png"));

        // Address
        $("#currentAddress").setValue("123 Main Street, New York");

        // State
        $("[id=state]").click();
        $("[id=state]").shouldBe(visible);
        $("[id=react-select-3-option-1]").click();

        // City — откроется react-select-4
        $("[id=city]").click();
        $("[id=city]").shouldBe(visible);
        $("[id=react-select-4-option-2]").click();

        // Submit
        $("#submit").click();

        // Verify success message
        $(".modal-content").shouldBe(visible);
        $(".table-responsive").shouldHave(text("Student Name"));
    }

    @Test
    void fillOnlyRequiredFieldsTest() {
        open("/automation-practice-form");
/*        executeJavaScript("""
        document.getElementById('fixedban')?.remove();
        document.querySelector('footer')?.remove();
        """); */
        // Personal Details
        $("#firstName").setValue("John");
        $("#lastName").setValue("Doe");
        $("#userEmail").setValue("john.doe@example.com");

        // Gender - select Male (radio button)
        $("[id=gender-radio-1]").click();

        // Phone
        $("#userNumber").setValue("1234567890");

        // Date of Birth
        $("[id=dateOfBirthInput]").click();
        // Выбор месяца (selectOption по индексу: январь=0, поэтому month-1)
        $(".react-datepicker__month-select").selectOption(month - 1);
        // Выбор года
        $(".react-datepicker__year-select").selectOption(String.valueOf(year - 10));
        // Выбор дня — форматируем с ведущим нулём: 1 → "001", 24 → "024"
        String dayFormatted = String.format("%03d", day-10);
        $(".react-datepicker__day--" + dayFormatted + ":not(.react-datepicker__day--outside-month)").click();

        // Submit
        $("#submit").click();

        // Verify success message
        $(".modal-content").shouldBe(visible);
        $(".table-responsive").shouldHave(text("Student Name"));
    }

    @Test
    void invalidEmailFormatTest() {
        open("/automation-practice-form");
/*        executeJavaScript("""
        document.getElementById('fixedban')?.remove();
        document.querySelector('footer')?.remove();
        """); */
        // Personal Details
        $("#firstName").setValue("John");
        $("#lastName").setValue("Doe");
        $("#userEmail").setValue("john.doe@example");


        // Gender - select Male (radio button)
        $("[id=gender-radio-1]").click();

        // Phone
        $("#userNumber").setValue("1234567890");

        // Date of Birth
        $("[id=dateOfBirthInput]").click();
        // Выбор месяца (selectOption по индексу: январь=0, поэтому month-1)
        $(".react-datepicker__month-select").selectOption(month - 1);
        // Выбор года
        $(".react-datepicker__year-select").selectOption(String.valueOf(year - 10));
        // Выбор дня — форматируем с ведущим нулём: 1 → "001", 24 → "024"
        String dayFormatted = String.format("%03d", day-10);
        $(".react-datepicker__day--" + dayFormatted + ":not(.react-datepicker__day--outside-month)").click();

        // Submit
        $("#submit").click();

        // Verify email error
        $("#userEmail").shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
    }

    @Test
    void missingGenderSelectionTest() {
        open("/automation-practice-form");
/*        executeJavaScript("""
        document.getElementById('fixedban')?.remove();
        document.querySelector('footer')?.remove();
        """); */
        // Personal Details
        $("#firstName").setValue("John");
        $("#lastName").setValue("Doe");
        $("#userEmail").setValue("john.doe@example.ru");

        // Phone
        $("#userNumber").setValue("1234567890");

        // Date of Birth
        $("[id=dateOfBirthInput]").click();
        // Выбор месяца (selectOption по индексу: январь=0, поэтому month-1)
        $(".react-datepicker__month-select").selectOption(month - 1);
        // Выбор года
        $(".react-datepicker__year-select").selectOption(String.valueOf(year - 10));
        // Выбор дня — форматируем с ведущим нулём: 1 → "001", 24 → "024"
        String dayFormatted = String.format("%03d", day-10);
        $(".react-datepicker__day--" + dayFormatted + ":not(.react-datepicker__day--outside-month)").click();

        // Submit
        $("#submit").click();

        // Verify gender error
        $("[id=gender-radio-1]").shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
        $("[id=gender-radio-2]").shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
        $("[id=gender-radio-3]").shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
    }

    @Test
    void invalidPhoneNumberTest() {
        open("/automation-practice-form");
/*        executeJavaScript("""
        document.getElementById('fixedban')?.remove();
        document.querySelector('footer')?.remove();
        """); */
        // Personal Details
        $("#firstName").setValue("John");
        $("#lastName").setValue("Doe");
        $("#userEmail").setValue("john.doe@example.ru");


        // Gender - select Male (radio button)
        $("[id=gender-radio-1]").click();

        // Phone
        $("#userNumber").setValue("1234567");

        // Date of Birth
        $("[id=dateOfBirthInput]").click();
        // Выбор месяца (selectOption по индексу: январь=0, поэтому month-1)
        $(".react-datepicker__month-select").selectOption(month - 1);
        // Выбор года
        $(".react-datepicker__year-select").selectOption(String.valueOf(year - 10));
        // Выбор дня — форматируем с ведущим нулём: 1 → "001", 24 → "024"
        String dayFormatted = String.format("%03d", day-10);
        $(".react-datepicker__day--" + dayFormatted + ":not(.react-datepicker__day--outside-month)").click();

        // Submit
        $("#submit").click();

        // Verify email error
        $("#userNumber").shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
    }

    @Test
    void tooLongNameTest() {
        open("/automation-practice-form");
/*        executeJavaScript("""
        document.getElementById('fixedban')?.remove();
        document.querySelector('footer')?.remove();
        """); */
        // Personal Details
        $("#firstName").setValue("JohnJohnJohnJohnJohnJohnJohnJohnJohnJohnJohnJohnJohnJohnJohn" +
                "JohnJohnJohnJohJohnJohn");
        $("#lastName").setValue("Doe");
        $("#userEmail").setValue("john.doe@example.com");

        // Gender - select Male (radio button)
        $("[id=gender-radio-1]").click();

        // Phone
        $("#userNumber").setValue("1234567890");

        // Date of Birth
        $("[id=dateOfBirthInput]").click();
        // Выбор месяца (selectOption по индексу: январь=0, поэтому month-1)
        $(".react-datepicker__month-select").selectOption(month - 1);
        // Выбор года
        $(".react-datepicker__year-select").selectOption(String.valueOf(year - 10));
        // Выбор дня — форматируем с ведущим нулём: 1 → "001", 24 → "024"
        String dayFormatted = String.format("%03d", day-10);
        $(".react-datepicker__day--" + dayFormatted + ":not(.react-datepicker__day--outside-month)").click();

        // Submit
        $("#submit").click();

        // Verify success message
        $(".modal-content").shouldBe(visible);
        $(".table-responsive").shouldHave(text("Student Name"));
    }

    @Test
    void emptyRequiredFieldsTest() {
        open("https://demoqa.com/automation-practice-form");
/*        executeJavaScript("""
document.getElementById('fixedban')?.remove();
document.querySelector('footer')?.remove();
""");
*/
        // Don't fill anything, just click Submit
        $("#submit").click();

        // Verify gender error
        $("[id=gender-radio-1]").shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
        $("[id=gender-radio-2]").shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
        $("[id=gender-radio-3]").shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));

        // Verify phone error
        $("#userNumber").shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));

        // Verify Name error
        $("#firstName").shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
        $("#lastName").shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
    }
}
