package app;

import app.core.ContactRepository;
import app.core.GetContacts;
import app.dependencies.Registration;
import app.dependencies.RegistrationsReader;
import app.core.Contact;
import app.primary.Controller;
import app.primary.Presenter;

import app.secondary.ContactRepositoryImpl;
import java.io.IOException;
import java.util.List;

public class App {
    public static void main(String[] args) throws IOException {
        Presenter presenter = new Presenter();
        ContactRepository contactRepository = new ContactRepositoryImpl();
        GetContacts getContacts = new GetContacts(contactRepository);
        Controller controller = new Controller(getContacts);
        List<Registration> registrations = new RegistrationsReader().getRegistrations();

        System.out.println(registrations);

        while (true) {
            List<Contact> contacts = controller.getContacts();
            Contact chosenContact = presenter.requestContactChoice(contacts);
            String message = presenter.requestMessage(chosenContact.name());
            controller.sendMessage(chosenContact, message);
        }
    }
}
