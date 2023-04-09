package application;

import application.pages.LoginPage;
import application.pages.MessengerPage;

public class App {

    public LoginPage loginPage;
    public MessengerPage messengerPage;

    public App() {
        loginPage = PageBuilder.buildLoginPage();
        messengerPage = PageBuilder.buildMessengerPage();
    }
}
