package application.pages;

import com.codeborne.selenide.*;
import com.codeborne.selenide.conditions.Visible;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MessengerPage extends BasePage {

    private final SelenideElement messages = $(By.xpath("//a[@href='/im']//div[@class='LeftMenu__icon']//*[name()='svg']"));
    private final ElementsCollection firstDialog = $$(By.xpath("//*[contains(@class,'nim-dialog--name')]"));
    private final SelenideElement moreLabel = $(By.xpath("//span[@class='ms_item_more_label']"));
    private final SelenideElement audioElement = $(By.xpath("//a[@class='ms_item ms_item_audio _type_audio']"));
    private final SelenideElement audioSearchInput = $(By.xpath("//input[@id='ape_edit_playlist_search']"));
    private final ElementsCollection attachAudioButtons = $$(By.xpath("//div[contains(text(),'Прикрепить')]"));
    private final SelenideElement audioNameBeforeSending = $(By.xpath("//a[@class='audio_row__title_inner _audio_row__title_inner']"));
    private final SelenideElement artistNameBeforeSending = $(By.xpath("//a[@class='artist_link']"));
    private final SelenideElement sendButton = $(By.xpath("//button[@class='im-send-btn im-chat-input--send " +
            "_im_send im-send-btn_send']//span[@class='im-send-btn__icon im-send-btn__icon--send']"));
    private final SelenideElement captcha = $(By.xpath("//div[contains(text(),'Введите код с картинки')]"));
    private final ElementsCollection submittedArtistName = $$(By.xpath("//div[@class='audio_row__performers']"));
    private final ElementsCollection submittedSongName = $$(By.xpath("//div[@class='audio_row__title_inner _audio_row__title_inner']"));
    private final SelenideElement validationPhonePopup = $(By.xpath("//button[@id='validation_send_phone']"));
    private final SelenideElement skipValidationButton = $(By.xpath("//a[@id='validation_skip']"));
    private final SelenideElement newChatButton = $(By.xpath("//button[@aria-label='Новый чат']"));
    private final SelenideElement newDialogNameInput = $(By.xpath("//input[@id='im_dialogs_creation_name']"));
    private final SelenideElement createChatButton = $(By.xpath("//*[contains(text(),'Создать чат')]"));

    public MessengerPage(String pageUrl) {
        super(pageUrl);
    }

    public void openMessages() {
        messages.click();
    }

    @Step("Открываем самый верхний диалог в списке")
    public void openFirstDialog() {
        firstDialog.first().click();
    }

    @Step("Прикрепляем к сообщению первое аудио из поиска по названию {songName}")
    public void attachSong(String songName) throws InterruptedException {
        moreLabel.click();
        audioElement.click();
        Thread.sleep(3000); //fix it
        audioSearchInput.setValue(songName);
        attachAudioButtons.first().click();
    }

    @Step("Получаем название прикрепленной песни до отправки сообщения")
    public String getSongNameBeforeSending() {
        return audioNameBeforeSending.getText();
    }

    @Step("Получаем название исполнителя прикрепленной песни до отправки сообщения")
    public String getArtistNameBeforeSending() {
        return artistNameBeforeSending.getText();
    }

    @Step("Отправляем сообщение")
    public void sendMessage() {
        sendButton.click();
    }

    @Step("Проверяем отображается ли окно подтверждения телефона")
    public boolean isValidationPopupDisplayed() throws InterruptedException {
        Thread.sleep(1000);
        return validationPhonePopup.isDisplayed();
    }

    @Step("Окно подтверждения номера отображается. Пропускаем валидация")
    public void closeValidationPopup() {
        skipValidationButton.click();
    }

    @Step("Проверяем отображается ли проверка капчи")
    public boolean isCaptchaDisplayed() {
        return captcha.isDisplayed();
    }

    @Step("Получаем название песни из последнего сообщения в переписке")
    public String getLastSubmittedSongArtistName() {
        return submittedArtistName.last().getText();
    }

    @Step("Получаем название исполнителя песни из последнего сообщения в переписке")
    public String getLastSubmittedSongName() {
        return submittedSongName.last().getText();
    }

    @Step("Открываем диалог создания чата")
    public void openChatDialog() {
        newChatButton.waitUntil(Condition.visible, 3000);
        newChatButton.click();
    }

    @Step("Проверяем что кнопка 'Создать чат' неактивна")
    public boolean isCreatingChatButtonDisable() {
        String classList = createChatButton.getAttribute("class");
        return classList.contains("FlatButton--disabled");
    }
}
