import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.testng.AllureTestNg;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({AllureTestNg.class})
public class ConversationCreationTest extends BaseTest{

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Feature("Создание новой беседы")
    @Description("Проверка возможности создания новой беседы")
    public void creatingTest() {
        app.loginPage.open();
        app.loginPage.login();
        app.messengerPage.openMessages();
        app.messengerPage.openChatDialog();
        softAssert.assertTrue(app.messengerPage.isCreatingChatButtonDisable(), "Кнопка 'Создать чат' должна быть неактивна");
        softAssert.assertAll();
    }
}
