package github;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class TestGithub {

    @BeforeAll
    static void beforeAll() {Configuration.startMaximized = true;
    }

    @Test
    void shouldFindSelenideRepoInGithub () {

        // Откройте страницу Selenide в Github

        open ("https://github.com/");
        $("[data-test-selector=nav-search-input]").setValue("selenide").pressEnter();
        $$(".repo-list li").first().$("a").click();

        // Перейдите в раздел Wiki проекта

        $(byText("Wiki")).click();

        // Убедитесь, что в списке страниц (Pages) есть страница SoftAssertions

        $(".markdown-body").shouldHave(text("Soft assertions"));

        // Откройте страницу SoftAssertions, проверьте что внутри есть пример кода для JUnit5

        $(".markdown-body").$(byText("Soft assertions")).click();
        $(".markdown-body").shouldHave(text("com.codeborne.selenide.junit5.SoftAssertsExtension"));

    }

}
