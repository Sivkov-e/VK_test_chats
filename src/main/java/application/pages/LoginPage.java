package application.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import helpers.Driver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class LoginPage extends BasePage {

    private final SelenideElement russianLanguageButton = $(By.xpath("//div[@id='index_footer_wrap']//a[@class='footer_lang_link'][contains(text(),'Русский')]"));
    private final SelenideElement emailInput = $(By.xpath("//input[@id='index_email']"));
    private final SelenideElement saveSessionCheckbox = $(By.xpath("//span[@class='VkIdCheckbox__name']"));
    private final SelenideElement loginButton = $(By.xpath("//button[@type='submit']//span[@class='FlatButton__in']"));
    private final SelenideElement passwordInput = $(By.xpath("//input[@placeholder='Введите пароль']"));
    private final SelenideElement continueButton = $(By.xpath("//span[contains(text(),'Продолжить')]"));


    public LoginPage(String pageUrl) {
        super(pageUrl);
    }

    @Step("Авторизация пользователя и смена языка на русский")
    public void login() {
        String testPassword = System.getProperty("testPassword");
        russianLanguageButton.waitUntil(Condition.visible, 3000);
        russianLanguageButton.click();
        emailInput.setValue("79905263065");
        saveSessionCheckbox.click();
        loginButton.click();
        Driver.waitForUrlContains("auth");
        passwordInput.setValue(testPassword);
        continueButton.click();
        Driver.waitForUrlContains("feed");
    }
}
