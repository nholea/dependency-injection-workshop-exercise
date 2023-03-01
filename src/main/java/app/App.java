package app;

import app.core.ContactRepository;
import app.core.DateProvider;
import app.core.GetContacts;
import app.core.SendMessage;
import app.core.SmsSender;
import app.core.UserInterface;
import app.dependencies.Registration;
import app.dependencies.RegistrationsReader;
import app.primary.CLI;
import app.primary.Controller;
import app.primary.Presenter;

import app.secondary.ContactRepositoryImpl;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;


public class App {
    public static void main(String[] args)
      throws IOException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        RegistrationsReader registrationsReader =  new RegistrationsReader();
        List<Registration> registrations = registrationsReader.getRegistrations();
        Registration registration= registrations.get(0);
        SmsSender smsSender = registrationsReader.getDesiredSmsServiceFrom(registration);
        DateProvider dateProvider = new DateProvider();
        UserInterface userInterface = new CLI();
        Presenter presenter = new Presenter(userInterface);
        ContactRepository contactRepository = new ContactRepositoryImpl();
        GetContacts getContacts = new GetContacts(contactRepository);
        SendMessage sendMessage = new SendMessage(smsSender, dateProvider);
        Controller controller = new Controller(getContacts, sendMessage, presenter);

        System.out.println(registrations);

        while (true) {
            controller.sendMessage();
        }
    }
}
