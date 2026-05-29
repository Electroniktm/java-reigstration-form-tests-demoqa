package tests;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TextBoxTests extends TestBase {

    @Test
    void successfulFillFormTest() {
        open("/text-box");
        $("[id=userName]").setValue("Alex Black");
        $("[id=userEmail]").setValue("alex@black.com");
        $("[id=currentAddress]").setValue("first address 1");
        $("[id=permanentAddress]").setValue("second address 2");
        $("[id=submit]").click();

        $("[id=output] [id=name]").shouldHave(text("Alex Black"));
        $("[id=output] [id=email]").shouldHave(text("alex@black.com"));
        $("[id=output] [id=currentAddress]").shouldHave(text("first address 1"));
        $("[id=output] [id=permanentAddress]").shouldHave(text("second address 2"));
    }

    @Test
    void textBoxMinimalFieldsTest() {
        open("/text-box");
        $("#userName").setValue("John Doe");
        $("#submit").click();

        $("#output").shouldBe(visible);
        $("[id=output] [id=name]").shouldHave(text("John Doe"));
    }

    @Test
    void textBoxEmptyFieldsTest() {
        open("/text-box");
        $("#submit").click();
        $("#output").shouldBe(hidden);
    }

    @Test
    void textBoxTooLongNameTest() {
        open("https://demoqa.com/text-box");
        $("#userName").setValue("JohnJohnJohnJohnJohnJohnJohnJohnJohnJohn");
        $("#submit").click();

        $("#output").shouldBe(visible);
        $("[id=output] [id=name]").shouldHave(text("JohnJohnJohnJohnJohnJohnJohnJohnJohnJohn"));
    }

    @Test
    void failEmailFillFormTest() {
        open("/text-box");
        $("[id=userName]").setValue("Alex Black");
        $("[id=userEmail]").setValue("alex@black");
        $("[id=currentAddress]").setValue("first address 1");
        $("[id=permanentAddress]").setValue("second address 2");
        $("[id=submit]").click();

        $("[id=userEmail]").shouldHave(cssValue("border-color", "rgb(255, 0, 0)"));
    }
}
