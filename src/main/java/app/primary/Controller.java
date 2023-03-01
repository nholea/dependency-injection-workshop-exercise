package app.primary;

import app.core.Contact;
import app.core.GetContacts;
import app.core.Message;
import app.core.SendMessage;

import java.io.IOException;
import java.util.List;

public class Controller {
    private final GetContacts getContacts;
    private final SendMessage sendMessage;
    private final Presenter presenter;

    public Controller(GetContacts getContacts, SendMessage sendMessage, Presenter presenter) {
        this.getContacts = getContacts;
        this.sendMessage = sendMessage;
        this.presenter = presenter;
    }


    public void sendMessage() throws IOException {
        List<Contact> contacts = getContacts.execute();
        Contact chosenContact = presenter.requestContactChoice(contacts);
        String content = presenter.requestMessage(chosenContact.name());
        Message message = new Message(content, chosenContact);
        sendMessage.execute(message);
    }
}
