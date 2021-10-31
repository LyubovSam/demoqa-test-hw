package ru.lyubovsam;


import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class TestBoxTests {
    @BeforeAll
    static void beforeAll(){
        Configuration.startMaximized = true;

    }
    @Test
    void fillForTest(){
        open("https://demoqa.com/automation-practice-form");
        $("#firstName").setValue("Lyubov");
        $("#lastName").setValue("Samarkina");
        $("#userEmail").setValue("test@gmail.com");
        $(byText("Female")).click();
        $("#userNumber").setValue("1234567890");
        $("#dateOfBirthInput").click();

        $(".react-datepicker__month-select").selectOption("March");
        $(".react-datepicker__year-select").selectOption("1994");
        $$(".react-datepicker__day").find(text("12")).click();
        $("#subjectsInput").setValue("Biology").pressEnter();
        $(byText("Music")).click();

        $("#uploadPicture").uploadFile(new File("src/test/resources/image.jpg"));
        $("#uploadPicture").uploadFromClasspath("image.jpg");

        $("#currentAddress").setValue("currentAddress");
        $("#state").scrollTo().click();
        $("#state").find(byText("NCR")).click();
        $("#city").click();
        $("#city").$(byText("Delhi")).click();
        $("#submit").click();

        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $(".table-responsive").shouldHave(text("Lyubov Samarkina"), text("test@gmail.com"),
                text("Female"), text("1234567890"),text("12 March,1994"),
                text("Biology"), text("Music"),text("currentAddress"),text("NCR Delhi"));



    }
}
