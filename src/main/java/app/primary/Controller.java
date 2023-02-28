package app.primary;

import app.core.Contact;
import app.core.GetContacts;
import app.core.Message;
import app.core.SendMessage;

import java.util.List;

public class Controller {
    private final GetContacts getContacts = new GetContacts();
    private final SendMessage sendMessage = new SendMessage();

    public List<Contact> getContacts() {
        return getContacts.execute();
    }

    public void sendMessage(Contact contact, String content) {
        Message message = new Message(content, contact);
        sendMessage.execute(message);
    }
}
