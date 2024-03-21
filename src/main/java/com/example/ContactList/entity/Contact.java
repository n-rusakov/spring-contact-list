package com.example.ContactList.entity;

import lombok.Data;
import lombok.experimental.FieldNameConstants;

@Data
public class Contact {
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
}
