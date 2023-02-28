package app.secondary;

public class EmailSender {

    public void send(String address, String content) {
        System.out.println("The following email was sent to " + address + ": " + content);
    }
}
