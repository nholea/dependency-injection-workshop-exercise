package app;

import app.core.ContactRepository;
import app.core.GetContacts;
import app.core.SendMessage;
import app.core.SmsSender;
import app.core.UserInterface;
import app.dependencies.Registration;
import app.dependencies.RegistrationsReader;
import app.core.Contact;
import app.primary.CLI;
import app.primary.Controller;
import app.primary.Presenter;

import app.secondary.ContactRepositoryImpl;
import app.secondary.DefaultSmsSender;
import java.io.IOException;
import java.util.List;

public class App {
    public static void main(String[] args) throws IOException {
        UserInterface userInterface = new CLI();
        Presenter presenter = new Presenter(userInterface);
        ContactRepository contactRepository = new ContactRepositoryImpl();
        GetContacts getContacts = new GetContacts(contactRepository);
        SmsSender smsSender = new DefaultSmsSender();
        SendMessage sendMessage = new SendMessage(smsSender);
        Controller controller = new Controller(getContacts, sendMessage);
        List<Registration> registrations = new RegistrationsReader().getRegistrations();

        System.out.println(registrations.stream().spliterator());

        while (true) {
            List<Contact> contacts = controller.getContacts();
            Contact chosenContact = presenter.requestContactChoice(contacts);
            String message = presenter.requestMessage(chosenContact.name());
            controller.sendMessage(chosenContact, message);
        }
    }
}
