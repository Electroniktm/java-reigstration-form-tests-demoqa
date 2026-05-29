package tests;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationFormTests extends TestBase {

    @Test
    void fillAllFieldsAndSubmitTest() {
        open("/automation-practice-form");
        $("#firstName").setValue("John");
        $("#lastName").setValue("Doe");
        $("#userEmail").setValue("john.doe@example.com");

        $("#genterWrapper").$$("label").findBy(text("Male")).click();

        $("#userNumber").setValue("1234567890");

        $("[id=dateOfBirthInput]").click();
        $(".react-datepicker__month-select").selectOption(month - 1);
        $(".react-datepicker__year-select").selectOption(String.valueOf(year - 10));
        String dayFormatted = String.format("%03d", day-10);
        $(".react-datepicker__day--" + dayFormatted + ":not(.react-datepicker__day--outside-month)").click();

        $("#subjectsInput").click();
        $("#subjectsInput").setValue("English");
        $(".subjects-auto-complete__option").click();

        $("[id=hobbies-checkbox-1]").click();

        $("#uploadPicture").uploadFromClasspath("test.png");

        $("#currentAddress").setValue("123 Main Street, New York");

        $("[id='react-select-3-input']").setValue("Uttar Pradesh").pressEnter();
        $("[id='react-select-4-input']").setValue("Agra").pressEnter();

        $("#submit").click();

        $(".modal-content").shouldBe(visible);
        $(".table-responsive").shouldHave(text("Student Name"));
        $(".table-responsive").shouldHave(text("John Doe"));
        $(".table-responsive").shouldHave(text("john.doe@example.com"));
        $(".table-responsive").shouldHave(text("Male"));
        $(".table-responsive").shouldHave(text("1234567890"));
        $(".table-responsive").shouldHave(text("English"));
        $(".table-responsive").shouldHave(text("Sports"));
        $(".table-responsive").shouldHave(text("123 Main Street, New York"));
        $(".table-responsive").shouldHave(text("Uttar Pradesh"));
        $(".table-responsive").shouldHave(text("Agra"));
    }

    @Test
    void fillOnlyRequiredFieldsTest() {
        open("/automation-practice-form");
        $("#firstName").setValue("John");
        $("#lastName").setValue("Doe");
        $("#userEmail").setValue("john.doe@example.com");

        $("#genterWrapper").$$("label").findBy(text("Male")).click();

        $("#userNumber").setValue("1234567890");

        $("[id=dateOfBirthInput]").click();
        $(".react-datepicker__month-select").selectOption(month - 1);
        $(".react-datepicker__year-select").selectOption(String.valueOf(year - 10));
        String dayFormatted = String.format("%03d", day-10);
        $(".react-datepicker__day--" + dayFormatted + ":not(.react-datepicker__day--outside-month)").click();

        $("#submit").click();

        $(".modal-content").shouldBe(visible);
        $(".table-responsive").shouldHave(text("Student Name"));
        $(".table-responsive").shouldHave(text("John Doe"));
        $(".table-responsive").shouldHave(text("john.doe@example.com"));
        $(".table-responsive").shouldHave(text("Male"));
        $(".table-responsive").shouldHave(text("1234567890"));
    }

    @Test
    void invalidEmailFormatTest() {
        open("/automation-practice-form");
        $("#firstName").setValue("John");
        $("#lastName").setValue("Doe");
        $("#userEmail").setValue("john.doe@example");

        $("#genterWrapper").$$("label").findBy(text("Male")).click();

        $("#userNumber").setValue("1234567890");

        $("[id=dateOfBirthInput]").click();
        $(".react-datepicker__month-select").selectOption(month - 1);
        $(".react-datepicker__year-select").selectOption(String.valueOf(year - 10));
        String dayFormatted = String.format("%03d", day-10);
        $(".react-datepicker__day--" + dayFormatted + ":not(.react-datepicker__day--outside-month)").click();

        $("#submit").click();

        $("#userEmail").shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
    }

    @Test
    void missingGenderSelectionTest() {
        open("/automation-practice-form");
        $("#firstName").setValue("John");
        $("#lastName").setValue("Doe");
        $("#userEmail").setValue("john.doe@example.ru");

        $("#userNumber").setValue("1234567890");

        $("[id=dateOfBirthInput]").click();
        $(".react-datepicker__month-select").selectOption(month - 1);
        $(".react-datepicker__year-select").selectOption(String.valueOf(year - 10));
        String dayFormatted = String.format("%03d", day-10);
        $(".react-datepicker__day--" + dayFormatted + ":not(.react-datepicker__day--outside-month)").click();

        $("#submit").click();

        $("[id=gender-radio-1]").shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
        $("[id=gender-radio-2]").shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
        $("[id=gender-radio-3]").shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
    }

    @Test
    void invalidPhoneNumberTest() {
        open("/automation-practice-form");
        $("#firstName").setValue("John");
        $("#lastName").setValue("Doe");
        $("#userEmail").setValue("john.doe@example.ru");

        $("#genterWrapper").$$("label").findBy(text("Male")).click();

        $("#userNumber").setValue("1234567");

        $("[id=dateOfBirthInput]").click();
        $(".react-datepicker__month-select").selectOption(month - 1);
        $(".react-datepicker__year-select").selectOption(String.valueOf(year - 10));
        String dayFormatted = String.format("%03d", day-10);
        $(".react-datepicker__day--" + dayFormatted + ":not(.react-datepicker__day--outside-month)").click();

        $("#submit").click();

        $("#userNumber").shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
    }

    @Test
    void tooLongNameTest() {
        open("/automation-practice-form");
        $("#firstName").setValue("JohnJohnJohnJohnJohnJohnJohnJohnJohnJohnJohnJohnJohnJohnJohn" +
                "JohnJohnJohnJohJohnJohn");
        $("#lastName").setValue("Doe");
        $("#userEmail").setValue("john.doe@example.com");

        $("#genterWrapper").$$("label").findBy(text("Male")).click();

        $("#userNumber").setValue("1234567890");

        $("[id=dateOfBirthInput]").click();
        $(".react-datepicker__month-select").selectOption(month - 1);
        $(".react-datepicker__year-select").selectOption(String.valueOf(year - 10));
        String dayFormatted = String.format("%03d", day-10);
        $(".react-datepicker__day--" + dayFormatted + ":not(.react-datepicker__day--outside-month)").click();

        $("#submit").click();

        $(".modal-content").shouldBe(visible);
        $(".table-responsive").shouldHave(text("Student Name"));
        $(".table-responsive").shouldHave(text("JohnJohnJohnJohnJohnJohnJohnJohnJohnJohnJohnJohnJohnJohnJohnJohnJohnJohnJohJohnJohn"));
        $(".table-responsive").shouldHave(text("Doe"));
        $(".table-responsive").shouldHave(text("john.doe@example.com"));
        $(".table-responsive").shouldHave(text("Male"));
    }

    @Test
    void emptyRequiredFieldsTest() {
        open("https://demoqa.com/automation-practice-form");

        $("#submit").click();

        $("[id=gender-radio-1]").shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
        $("[id=gender-radio-2]").shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
        $("[id=gender-radio-3]").shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));

        $("#userNumber").shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));

        $("#firstName").shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
        $("#lastName").shouldHave(cssValue("border-color", "rgb(220, 53, 69)"));
    }
}
