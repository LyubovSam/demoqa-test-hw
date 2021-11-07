package selenide;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class SelenideTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
    }

    @Test
    void drugAndDropTests(){

        // не работает
        open("https://the-internet.herokuapp.com/drag_and_drop");
        $("column-a").dragAndDropTo($("column-b"));
        $("column-a").shouldHave(text("B"));
        $("column-b").shouldHave(text("A"));

        // actions().moveToElement($("column-a")).clickAndHold()
                //.moveByOffset(300, 0).release().perform();



    }

}
