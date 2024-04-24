import com.codeborne.selenide.Configuration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static java.time.Duration.ofSeconds;

public class WikiSoftAssertionsTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.pageLoadStrategy = "eager";
        Configuration.baseUrl="https://github.com";
        Configuration.browserSize="3840x2160";
    }

    @Test
    void junitExampleExistOnWiki() {
        open("/selenide/selenide");

        $("#wiki-tab").shouldBe(visible, ofSeconds(10)).click();
        $("#wiki-pages-filter").shouldBe(visible, ofSeconds(10)).setValue("SoftAssertions");
        $(byText("SoftAssertions")).shouldBe(visible, ofSeconds(10)).click();
        $$(".heading-element")
                .filter(text("JUnit5"))
                .first()
                .parent()
                .sibling(0)
                .$("pre")
                .shouldBe(visible, ofSeconds(10));
    }

    @AfterAll
    static void afterAll() {
        closeWebDriver();
    }
}
