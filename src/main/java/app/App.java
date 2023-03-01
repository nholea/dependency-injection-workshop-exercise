package app;

import app.core.ContactRepository;
import app.core.DateProvider;
import app.core.GetContacts;
import app.core.SendMessage;
import app.core.SmsSender;
import app.dependencies.Registration;
import app.dependencies.RegistrationsReader;
import app.primary.CLI;
import app.primary.Controller;
import app.primary.Presenter;

import app.secondary.ContactRepositoryImpl;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.List;


public class App {
public static void main(String[] args) throws IOException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
RegistrationsReader registrationsReader =  new RegistrationsReader();
List<Registration> registrations = registrationsReader.getRegistrations();
    Registration registration= registrations.get(0);

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    CLI cli = new CLI(reader);
    Presenter presenter = new Presenter(cli);

    ContactRepository contactRepository = new ContactRepositoryImpl();
    GetContacts getContacts = new GetContacts(contactRepository);
    DateProvider dateProvider = new DateProvider();
    SmsSender smsSender = registrationsReader.getDesiredSmsServiceFrom(registration);
    SendMessage sendMessage = new SendMessage(smsSender, dateProvider);
    Controller controller = new Controller(getContacts, sendMessage, presenter);

    System.out.println(registrations);

    while (true) {
    controller.sendMessage();
        }
    }
}
