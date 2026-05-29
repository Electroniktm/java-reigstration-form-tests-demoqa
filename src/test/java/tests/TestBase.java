package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.By;

import java.time.LocalDate;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.closeWebDriver;

public class TestBase {

    int month;
    int year;
    int day = LocalDate.now().getDayOfMonth();

    {
        month = LocalDate.now().getMonthValue();
        year =  LocalDate.now().getYear();
    }


    @BeforeAll
    static void beforeALL() {
        Configuration.browserSize = "1920x1280";
        Configuration.browser = "chrome";
        Configuration.baseUrl = "https://demoqa.com";
    }

    @AfterEach
    void afterEach() {
        closeWebDriver();
    }
}
