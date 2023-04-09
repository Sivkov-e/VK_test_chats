package application;

import application.pages.LoginPage;
import application.pages.MessengerPage;

public class PageBuilder {

    public static LoginPage buildLoginPage() {
        return new LoginPage("/");
    }

    public static MessengerPage buildMessengerPage() {
        return new MessengerPage("/im");
    }
}
