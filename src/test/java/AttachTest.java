import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.testng.AllureTestNg;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({AllureTestNg.class})
public class AttachTest extends BaseTest {

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Feature("Аттач")
    @Description("Проверка возможности прикрепления к сообщениям музыки из поиска")
    public void attachSong() throws InterruptedException {
        app.loginPage.open();
        app.loginPage.login();
        app.messengerPage.openMessages();
        app.messengerPage.openFirstDialog();
        app.messengerPage.attachSong("По чуть-чуть");
        softAssert.assertEquals(app.messengerPage.getSongNameBeforeSending(), "По чуть-чуть");
        softAssert.assertEquals(app.messengerPage.getArtistNameBeforeSending(), "Верка Сердючка");
        app.messengerPage.sendMessage();

        if (app.messengerPage.isValidationPopupDisplayed()) {
            app.messengerPage.closeValidationPopup();
        }
        if (app.messengerPage.isCaptchaDisplayed()) {
            throw new RuntimeException("Captcha is displayed, stopping the test");
        }

        softAssert.assertEquals(app.messengerPage.getLastSubmittedSongArtistName(), "По чуть-чуть");
        softAssert.assertEquals(app.messengerPage.getLastSubmittedSongName(), "Верка Сердючка");
        softAssert.assertAll();
    }
}
