package app.secondary;

import app.core.SmsSender;

public class DefaultSmsSender implements SmsSender {
    public void send(String telephone, String content) {
        System.out.println("The following SMS was sent to " + telephone + ": " + content);
    }
}
