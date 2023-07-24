package ru.netology.web;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

class OrderingCardTest {
    @Test
   public void shouldTest() {
        open("http://localhost:9999");
        $("[data-test-id =name] input").setValue("Иванов Иван");
        $( "[data-test-id =phone] input").setValue("+79300000000");
        $( "[data-test-id =agreement]").click();
        $(".button").click();
        $( "[data-test-id =order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));

    }

    @Test
    public void shouldTestDash() {
        open("http://localhost:9999");
        $("[data-test-id =name] input").setValue("Иванов-Петров Иван");
        $( "[data-test-id =phone] input").setValue("+79300000000");
        $( "[data-test-id =agreement]").click();
        $(".button").click();
        $( "[data-test-id =order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));

    }
    //Не указана фамилия, тест проходит, хотя не должен проходить, заявка отправляется
        @Test
    public void shouldTestNoFamiliy() {
        open("http://localhost:9999");
        $("[data-test-id =name] input").setValue("Иван");
        $( "[data-test-id =phone] input").setValue("+79300000000");
        $( "[data-test-id =agreement]").click();
        $(".button").click();
        $( "[data-test-id =order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));

    }
    //Не указано имя, тест проходит, хотя не должен проходить, заявка отправляется
    @Test
    public void shouldTestNoName() {
        open("http://localhost:9999");
        $("[data-test-id =name] input").setValue("И");
        $( "[data-test-id =phone] input").setValue("+79300000000");
        $( "[data-test-id =agreement]").click();
        $(".button").click();
        $( "[data-test-id =order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));

    }
    @Test
    public void shouldTestPhone() {
        open("http://localhost:9999");
        $("[data-test-id =name] input").setValue("Иванов Иван");
        $( "[data-test-id =phone] input").setValue("+793000");
        $( "[data-test-id =agreement]").click();
        $(".button").click();
        $("[data-test-id =phone].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));

    }

    @Test
    public void shouldTestPhoneEight() {
        open("http://localhost:9999");
        $("[data-test-id =name] input").setValue("Иванов Иван");
        $( "[data-test-id =phone] input").setValue("89300000000");
        $( "[data-test-id =agreement]").click();
        $(".button").click();
        $("[data-test-id =phone].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));

    }
    @Test
    public void shouldTestNoPhone() {
        open("http://localhost:9999");
        $("[data-test-id =name] input").setValue("Иванов Иван");
        $( "[data-test-id =phone] input").setValue("");
        $( "[data-test-id =agreement]").click();
        $(".button").click();
        $("[data-test-id =phone].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));

    }
    @Test
    public void shouldTestEng() {
        open("http://localhost:9999");
        $("[data-test-id =name] input").setValue("Ivanov Ivan");
        $( "[data-test-id =phone] input").setValue("+79300000000");
        $( "[data-test-id =agreement]").click();
        $(".button").click();
        $("[data-test-id =name].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));

    }
    @Test
    public void shouldTestNoFamilyName() {
        open("http://localhost:9999");
        $("[data-test-id =name] input").setValue("");
        $( "[data-test-id =phone] input").setValue("+79300000000");
        $( "[data-test-id =agreement]").click();
        $(".button").click();
        $("[data-test-id =name].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));

    }
    @Test
    public void shouldTestNoClick() {
        open("http://localhost:9999");
        $("[data-test-id =name] input").setValue("Иванов Иван");
        $( "[data-test-id =phone] input").setValue("+79300000000");
        $(".button").click();
        $("[data-test-id =agreement].input_invalid .checkbox__text").shouldHave(exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй"));
    }
}
