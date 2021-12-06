package ru.lyubovsam.demoqa;

import org.junit.jupiter.api.Test;
import java.io.File;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class StudentRegistrationForm extends TestBase {
    @Test
    void fillForTest(){
        step("Open students registration form", () -> {
            open("https://demoqa.com/automation-practice-form");
            $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
        });
        step("Fill student registration form common date", () -> {
            $("#firstName").setValue("Lyubov");
            $("#lastName").setValue("Samarkina");
            $("#userEmail").setValue("test@gmail.com");
            $(byText("Female")).click();
            $("#userNumber").setValue("1234567890");
        });
        step("Set date", () -> {
            $("#dateOfBirthInput").click();
            $(".react-datepicker__month-select").selectOption("March");
            $(".react-datepicker__year-select").selectOption("1994");
            $$(".react-datepicker__day").find(text("12")).click();
        });
        step("Set subjects", () -> {
            $("#subjectsInput").setValue("Biology").pressEnter();
        });
        step("Set hobbies", () ->
                $(byText("Music")).click());
        step("Upload picture", () -> {
            $("#uploadPicture").uploadFile(new File("src/test/resources/image.jpg"));
            $("#uploadPicture").uploadFromClasspath("image.jpg");
        });
        step("Set address", () -> {
            $("#currentAddress").setValue("currentAddress");
            $("#state").scrollTo().click();
            $("#state").find(byText("NCR")).click();
            $("#city").click();
            $("#city").$(byText("Delhi")).click();
        });
        step("Submit form", () ->
            $("#submit").click());

        step("Verify successful form submit", () -> {
            $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
            $(".table-responsive").shouldHave(text("Lyubov Samarkina"),
                    text("test@gmail.com"),
                    text("Female"),
                    text("1234567890"),
                    text("12 March,1994"),
                    text("Biology"),
                    text("Music"),
                    text("currentAddress"),
                    text("NCR Delhi"));
        });
    }
}
