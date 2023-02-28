package app.core;

import app.secondary.ContactRepository;

import java.util.List;

public class GetContacts {
    private final ContactRepository contactRepository = new ContactRepository();

    public List<Contact> execute() {
        return contactRepository.getAll();
    }
}
