package com.mycontactapp.mapper;

import org.springframework.stereotype.Component;

import com.mycontactapp.dto.ContactRequestDTO;
import com.mycontactapp.dto.ContactResponseDTO;
import com.mycontactapp.entity.Contact;

@Component
public class ContactMapper {

    public Contact toEntity(ContactRequestDTO dto) {

        Contact contact = new Contact();

        contact.setName(dto.getName());
        contact.setEmail(dto.getEmail());
        contact.setPhone(dto.getPhone());
        contact.setAlternatePhone(dto.getAlternatePhone());

        return contact;
    }

    public ContactResponseDTO toResponseDTO(Contact contact) {

        return new ContactResponseDTO(
                contact.getId(),
                contact.getName(),
                contact.getEmail(),
                contact.getPhone(),
                contact.getAlternatePhone()
        );
    }
}