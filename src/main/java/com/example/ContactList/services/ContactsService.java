package com.example.ContactList.services;

import com.example.ContactList.entity.Contact;
import com.example.ContactList.repositories.ContactsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ContactsService {
    private final ContactsRepository contactsRepository;

    public List<Contact> getAll() {
        return contactsRepository.findAll();
    }

    public Contact getById(Long id) {
        return contactsRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        contactsRepository.deleteById(id);
    }

    public void saveContact(Contact contact) {
        contactsRepository.save(contact);
    }

    public void updateContact(Contact contact) {
        Optional<Contact> existedContact = contactsRepository.
                findById(contact.getId());
        if (existedContact.isPresent()) {
            contactsRepository.update(contact);
        }
    }


}
