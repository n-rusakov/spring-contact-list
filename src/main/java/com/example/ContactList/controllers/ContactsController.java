package com.example.ContactList.controllers;

import com.example.ContactList.entity.Contact;
import com.example.ContactList.services.ContactsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class ContactsController {
    private final ContactsService contactsService;

    @GetMapping("/")
    public String index(Model model){
        List<Contact> contacts = contactsService.getAll();
        model.addAttribute("contacts", contacts);

        return "index";
    }

    //===========CREATE AND EDIT=============

    @GetMapping("/contact/create")
    public String showCreateForm(Model model) {
        model.addAttribute("contact", new Contact());

        return "create-edit";
    }

    @GetMapping("/contact/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Contact contact = contactsService.getById(id);
        if (contact != null) {
            model.addAttribute("contact", contact);
            return "create-edit";
        }
        return "redirect:/";
    }

    @PostMapping("/contact/create")
    public String createContact(@ModelAttribute Contact contact) {
        contactsService.saveContact(contact);

        return "redirect:/";
    }

    @PostMapping("/contact/update")
    public String updateContact(@ModelAttribute Contact contact) {
        contactsService.updateContact(contact);

        return "redirect:/";
    }

    //===========DELETE==================
    @GetMapping("/contact/delete/{id}")
    public String deleteContact(@PathVariable Long id) {
        contactsService.deleteById(id);

        return "redirect:/";
    }

}
