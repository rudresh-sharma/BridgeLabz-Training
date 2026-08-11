package com.mycontactapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mycontactapp.dto.ContactRequestDTO;
import com.mycontactapp.dto.ContactResponseDTO;
import com.mycontactapp.entity.Contact;
import com.mycontactapp.exception.ContactNotFoundException;
import com.mycontactapp.exception.EmailAlreadyExistsException;
import com.mycontactapp.mapper.ContactMapper;
import com.mycontactapp.repository.ContactRepository;

@Service
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;
    private final ContactMapper contactMapper;

    public ContactServiceImpl(
            ContactRepository contactRepository,
            ContactMapper contactMapper) {

        this.contactRepository = contactRepository;
        this.contactMapper = contactMapper;
    }

    @Override
    public ContactResponseDTO createContact(
            ContactRequestDTO dto) {

        // Check if email already exists
        if (contactRepository.existsByEmail(dto.getEmail())) {
            throw new EmailAlreadyExistsException(
                    "Email already exists: " + dto.getEmail()
            );
        }

        // Normalize phone numbers
        String phone = normalizePhone(dto.getPhone());

        String alternatePhone =
                normalizePhone(dto.getAlternatePhone());

        // DTO → Entity
        Contact contact = contactMapper.toEntity(dto);

        // Set normalized phone numbers
        contact.setPhone(phone);
        contact.setAlternatePhone(alternatePhone);

        // Save entity
        Contact savedContact =
                contactRepository.save(contact);

        // Entity → Response DTO
        return contactMapper.toResponseDTO(savedContact);
    }

    @Override
    public ContactResponseDTO getContactById(Long id) {

        Contact contact = contactRepository.findById(id)
                .orElseThrow(() ->
                        new ContactNotFoundException(
                                "Contact not found with id: " + id
                        ));

        return contactMapper.toResponseDTO(contact);
    }

    @Override
    public List<ContactResponseDTO> getAllContacts() {

        return contactRepository.findAll()
                .stream()
                .map(contactMapper::toResponseDTO)
                .toList();
    }

    @Override
    public ContactResponseDTO updateContact(
            Long id,
            ContactRequestDTO dto) {

        Contact contact = contactRepository.findById(id)
                .orElseThrow(() ->
                        new ContactNotFoundException(
                                "Contact not found with id: " + id
                        ));

        // Normalize phone numbers
        String phone = normalizePhone(dto.getPhone());
        String alternatePhone =
                normalizePhone(dto.getAlternatePhone());

        // Update entity
        contact.setFirstName(dto.getFirstName());
        contact.setMiddleName(dto.getMiddleName());
        contact.setLastName(dto.getLastName());
        contact.setEmail(dto.getEmail());
        contact.setPhone(phone);
        contact.setAlternatePhone(alternatePhone);

        // Save updated entity
        Contact updatedContact =
                contactRepository.save(contact);

        // Entity → Response DTO
        return contactMapper.toResponseDTO(updatedContact);
    }

    @Override
    public void deleteContact(Long id) {

        if (!contactRepository.existsById(id)) {
            throw new ContactNotFoundException(
                    "Contact not found with id: " + id
            );
        }

        contactRepository.deleteById(id);
    }

    private String normalizePhone(String phone) {

        if (phone == null || phone.isBlank()) {
            return phone;
        }

        return phone.replaceFirst("^0+", "");
    }
}