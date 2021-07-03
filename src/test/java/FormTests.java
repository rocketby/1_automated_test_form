import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byTagName;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

import java.io.File;

public class FormTests {

    @BeforeAll
    static void setup() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.startMaximized = true;
    }

    @Test
    void positiveFillTest() {
        open("/automation-practice-form");
        $("#firstName").setValue("Tatiana");
        $("#lastName").setValue("Ivanovna");
        $("#userEmail").setValue("test@tt.tt");
        $(byText("Female")).click();
        $("#userNumber").setValue("1234567898");

        $("#dateOfBirthInput").click();
        $("select.react-datepicker__month-select").selectOptionByValue("10");
        $("select.react-datepicker__year-select").selectOptionByValue("1980");
        $x("//div[@class='react-datepicker__week']/*[text()=21]").click();

        $("#subjectsInput").setValue("Maths").pressEnter();
        $("#subjectsInput").setValue("Accounting").pressEnter();
        $("#subjectsInput").setValue("History").pressEnter();

        File file = new File("src/test/resources/flower.jpg");
        $("#uploadPicture").uploadFile(file);

        $(byText("Sports")).click();
        $(byText("Music")).click();

        $(byTagName("textarea")).setValue("village Druzhnaya, str. Central, h. 90, 234931");

        $(byText("Select State")).scrollIntoView(true);
        $(byText("Select State")).click();
        $(byText("Uttar Pradesh")).click();
        $(byText("Select City")).click();
        $(byText("Agra")).click();

        $("button#submit").click();

        $(byText("Thanks for submitting the form")).shouldBe();
        $x("//td[text()='Student Name']/following-sibling::td").shouldHave(text("Tatiana Ivanovna"));
        $x("//td[text()='Student Email']/following-sibling::td").shouldHave(text("test@tt.tt"));
        $x("//td[text()='Gender']/following-sibling::td").shouldHave(text("Female"));
        $x("//td[text()='Mobile']/following-sibling::td").shouldHave(text("1234567898"));
        $x("//td[text()='Date of Birth']/following-sibling::td").shouldHave(text("21 November,1980"));
        $x("//td[text()='Subjects']/following-sibling::td").shouldHave(text("Maths, Accounting, History"));
        $x("//td[text()='Hobbies']/following-sibling::td").shouldHave(text("Sports, Music"));
        $x("//td[text()='Picture']/following-sibling::td").shouldHave(text("flower.jpg"));
        $x("//td[text()='Address']/following-sibling::td").shouldHave(text("village Druzhnaya, str. Central, h. 90, 234931"));
        $x("//td[text()='State and City']/following-sibling::td").shouldHave(text("Uttar Pradesh Agra"));
    }
}