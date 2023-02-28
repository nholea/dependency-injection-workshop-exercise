package app.secondary;

import app.core.SmsSender;

public class ScreamingSmsSender implements SmsSender {
    public void send(String telephone, String content) {
        String exclamations = "\u2757\uFE0F".repeat(5);
        System.out.println("The following SMS was sent to " + telephone + ": " + content.toUpperCase() + exclamations);
    }
}
