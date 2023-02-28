package app.secondary;

import app.core.Contact;

import java.util.List;

public class ContactRepository {
    private final List<Contact> contacts = List.of(
        new Contact(
            "Homer Simpson",
            "homersimpson@gmail.com",
            "113335555",
            "742 Evergreen Terrace, 1548 Springfield"
        ),
        new Contact(
            "Ned Flanders",
            "nedflanders@gmail.com",
            "332221111",
            "740 Evergreen Terrace, 1548 Springfield"
        )
    );

    public List<Contact> getAll() {
        return contacts;
    }
}
