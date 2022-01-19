package tests;

import io.appium.java_client.MobileBy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.qameta.allure.Allure.step;

public class SimpleAndroidTests extends TestBase {

    String searchText = "Anime";
    String changeLanguage = "Russian";
    String checkChangeLang = "RU";

    @Test
    @DisplayName("Поиск по слову в вики")
    void SearchTest() {
        step("Открываем википедию и вводим текст" + searchText, () -> {
            $(MobileBy.AccessibilityId("Search Wikipedia")).click();
            $(MobileBy.id("org.wikipedia.alpha:id/search_src_text")).sendKeys(searchText);
        });
        step("Проверяем, что кол-во результатов больше 0", () -> {
            $$(byClassName("android.widget.TextView")).shouldHave(sizeGreaterThan(0));
        });

    }

    @Test
    @DisplayName("Меняем зяык в вики")
    void changeLanguageTest() {
        step("Открываем википедию и кликаем на кнопку смены языка", () -> {
            $(MobileBy.AccessibilityId("Search Wikipedia")).click();
            $(MobileBy.id("org.wikipedia.alpha:id/search_lang_button")).click();
        });
        step("В открывшемся окне вводим текст" + changeLanguage, () -> {
            $(MobileBy.id("org.wikipedia.alpha:id/preference_languages_filter")).sendKeys(changeLanguage);
        });
        step("Выбираем язык и проверяем, что язык сменился на нужный: " + checkChangeLang, () -> {
            $(MobileBy.id("org.wikipedia.alpha:id/localized_language_name")).click();
            $(MobileBy.id("org.wikipedia.alpha:id/search_lang_button")).shouldHave(text(checkChangeLang));

        });
    }

}
