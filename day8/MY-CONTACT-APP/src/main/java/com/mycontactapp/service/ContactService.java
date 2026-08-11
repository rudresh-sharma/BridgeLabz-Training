package com.mycontactapp.service;

import java.util.List;

import com.mycontactapp.dto.ContactRequestDTO;
import com.mycontactapp.dto.ContactResponseDTO;

public interface ContactService {

    ContactResponseDTO createContact(ContactRequestDTO dto);

    ContactResponseDTO getContactById(Long id);

    List<ContactResponseDTO> getAllContacts();

    ContactResponseDTO updateContact(
            Long id,
            ContactRequestDTO dto);

    void deleteContact(Long id);
}