import application.App;
import helpers.Driver;
import io.qameta.allure.Step;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.asserts.SoftAssert;

public class BaseTest {
    protected App app;
    protected SoftAssert softAssert;

    @BeforeClass
    @Step("Инициализация драйвера")
    public void setUp() {
        Driver.initDriver();

        app = new App();
        softAssert = new SoftAssert();
    }

    @AfterClass
    @Step("Закрытие драйвера")
    public void tearDown() {
        Driver.close();
    }
}
