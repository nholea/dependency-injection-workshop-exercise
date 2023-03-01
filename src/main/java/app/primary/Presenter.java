package app.primary;

import app.core.Contact;

import app.core.UserInterface;
import java.io.IOException;
import java.util.List;

public class Presenter {
    private final UserInterface userInterface;

    public Presenter(UserInterface userInterface) {
        this.userInterface = userInterface;
    }

    public Contact requestContactChoice(List<Contact> contacts) {
        userInterface.printWhiteSpace();
        userInterface.display("CONTACTS LIST");
        for (int i = 0; i < contacts.size(); i++) {
            Contact contact = contacts.get(i);
            userInterface.display(i + ". " + contact.name());
        }
        userInterface.printWhiteSpace();
        userInterface.display("Select a contact to send a message (write contact number and press Enter):");

        Contact contact = null;

        do {
            try {
                int contactNumber = Integer.parseInt(userInterface.read());
                contact = contacts.get(contactNumber);
            } catch (Exception error) {
                userInterface.display("Entry does not correspond to a contact number. Please enter number again.");
            }
        } while (contact == null);


        return contact;
    }

    public String requestMessage(String contactName) throws IOException {
        userInterface.printWhiteSpace();
        userInterface.display("Write a message for " + contactName + ". It will be sent when you press Enter.");
        String message = userInterface.read();
        userInterface.printWhiteSpace();

        return message;
    }
}
