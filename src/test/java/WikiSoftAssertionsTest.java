import com.codeborne.selenide.Configuration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

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

        $("#wiki-tab").shouldBe(visible).click();
        $("#wiki-pages-filter").shouldBe(visible).setValue("SoftAssertions");
        $(byText("SoftAssertions")).shouldBe(visible).click();
        $$(".heading-element")
                .filter(text("JUnit5"))
                .first()
                .parent()
                .sibling(0)
                .shouldHave(text("""
            @ExtendWith({SoftAssertsExtension.class})
            class Tests {
                @Test
                void test() {
                    Configuration.assertionMode = SOFT;
                    open("page.html");

                    $("#first").should(visible).click();
                    $("#second").should(visible).click();
                }
            }"""));
    }

    @AfterAll
    static void afterAll() {
        closeWebDriver();
    }
}
