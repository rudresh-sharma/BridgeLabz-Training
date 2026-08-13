package com.mycontactapp;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.mycontactapp.dto.ContactRequestDTO;
import com.mycontactapp.dto.ContactResponseDTO;
import com.mycontactapp.entity.Contact;
import com.mycontactapp.exception.ContactNotFoundException;
import com.mycontactapp.exception.EmailAlreadyExistsException;
import com.mycontactapp.mapper.ContactMapper;
import com.mycontactapp.repository.ContactRepository;
import com.mycontactapp.service.ContactServiceImpl;

/**
 * Unit tests for {@link ContactServiceImpl}.
 *
 * These tests mock out the repository and mapper so that only the
 * business logic inside the service (email-uniqueness check, phone
 * normalization, not-found handling, delegation to the mapper/repository)
 * is under test.
 */
@ExtendWith(MockitoExtension.class)
class ContactServiceImplTest {

    @Mock
    private ContactRepository contactRepository;

    @Mock
    private ContactMapper contactMapper;

    @InjectMocks
    private ContactServiceImpl contactService;

    private ContactRequestDTO requestDTO;
    private Contact contactEntity;
    private Contact savedContactEntity;
    private ContactResponseDTO responseDTO;

    @BeforeEach
    void setUp() {
        requestDTO = new ContactRequestDTO(
                "John",
                "Q",
                "Doe",
                "john.doe@example.com",
                "09876543210",   // leading zero -> should be normalized
                "01234567890"    // leading zero -> should be normalized
        );

        contactEntity = new Contact();
        contactEntity.setFirstName("John");
        contactEntity.setMiddleName("Q");
        contactEntity.setLastName("Doe");
        contactEntity.setEmail("john.doe@example.com");
        contactEntity.setPhone("09876543210");
        contactEntity.setAlternatePhone("01234567890");

        savedContactEntity = new Contact();
        savedContactEntity.setId(1L);
        savedContactEntity.setFirstName("John");
        savedContactEntity.setMiddleName("Q");
        savedContactEntity.setLastName("Doe");
        savedContactEntity.setEmail("john.doe@example.com");
        savedContactEntity.setPhone("9876543210");
        savedContactEntity.setAlternatePhone("1234567890");

        responseDTO = new ContactResponseDTO(
                1L,
                "John",
                "Q",
                "Doe",
                "john.doe@example.com",
                "9876543210",
                "1234567890",
                false
        );
    }

    // ---------- createContact ----------

    @Test
    @DisplayName("createContact() saves and returns a response DTO when email is unique")
    void createContact_success() {
        when(contactRepository.existsByEmail(requestDTO.getEmail())).thenReturn(false);
        when(contactMapper.toEntity(requestDTO)).thenReturn(contactEntity);
        when(contactRepository.save(contactEntity)).thenReturn(savedContactEntity);
        when(contactMapper.toResponseDTO(savedContactEntity)).thenReturn(responseDTO);

        ContactResponseDTO result = contactService.createContact(requestDTO);

        assertThat(result).isEqualTo(responseDTO);
        verify(contactRepository).save(contactEntity);
    }

    @Test
    @DisplayName("createContact() throws EmailAlreadyExistsException when email already exists")
    void createContact_emailAlreadyExists_throwsException() {
        when(contactRepository.existsByEmail(requestDTO.getEmail())).thenReturn(true);

        assertThatThrownBy(() -> contactService.createContact(requestDTO))
                .isInstanceOf(EmailAlreadyExistsException.class)
                .hasMessageContaining(requestDTO.getEmail());

        verify(contactRepository, never()).save(any(Contact.class));
        verify(contactMapper, never()).toEntity(any(ContactRequestDTO.class));
    }

    @Test
    @DisplayName("createContact() strips leading zeros from phone and alternatePhone before saving")
    void createContact_normalizesPhoneNumbers() {
        when(contactRepository.existsByEmail(anyString())).thenReturn(false);
        when(contactMapper.toEntity(requestDTO)).thenReturn(contactEntity);
        when(contactRepository.save(any(Contact.class))).thenReturn(savedContactEntity);
        when(contactMapper.toResponseDTO(savedContactEntity)).thenReturn(responseDTO);

        contactService.createContact(requestDTO);

        ArgumentCaptor<Contact> captor = ArgumentCaptor.forClass(Contact.class);
        verify(contactRepository).save(captor.capture());

        Contact savedArg = captor.getValue();
        assertThat(savedArg.getPhone()).isEqualTo("9876543210");
        assertThat(savedArg.getAlternatePhone()).isEqualTo("1234567890");
    }

    @Test
    @DisplayName("createContact() leaves a null alternatePhone as null instead of throwing")
    void createContact_nullAlternatePhone_handledGracefully() {
        requestDTO.setAlternatePhone(null);
        when(contactRepository.existsByEmail(anyString())).thenReturn(false);
        when(contactMapper.toEntity(requestDTO)).thenReturn(contactEntity);
        when(contactRepository.save(any(Contact.class))).thenReturn(savedContactEntity);
        when(contactMapper.toResponseDTO(savedContactEntity)).thenReturn(responseDTO);

        contactService.createContact(requestDTO);

        ArgumentCaptor<Contact> captor = ArgumentCaptor.forClass(Contact.class);
        verify(contactRepository).save(captor.capture());
        assertThat(captor.getValue().getAlternatePhone()).isNull();
    }

    // ---------- getContactById ----------

    @Test
    @DisplayName("getContactById() returns the mapped DTO when the contact exists")
    void getContactById_found() {
        when(contactRepository.findById(1L)).thenReturn(Optional.of(savedContactEntity));
        when(contactMapper.toResponseDTO(savedContactEntity)).thenReturn(responseDTO);

        ContactResponseDTO result = contactService.getContactById(1L);

        assertThat(result).isEqualTo(responseDTO);
    }

    @Test
    @DisplayName("getContactById() throws ContactNotFoundException when the id does not exist")
    void getContactById_notFound_throwsException() {
        when(contactRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> contactService.getContactById(99L))
                .isInstanceOf(ContactNotFoundException.class)
                .hasMessageContaining("99");
    }

    // ---------- getAllContacts ----------
    @Test
    @DisplayName("getAllContacts() returns a mapped list when contacts exist")
    void getAllContacts_returnsList() {
        Contact second = new Contact();
        second.setId(2L);
        second.setEmail("jane@example.com");

        ContactResponseDTO secondDto = new ContactResponseDTO(
                2L, "Jane", null, "Roe", "jane@example.com", "1112223333", null, false);

        when(contactRepository.findAll())
                .thenReturn(Arrays.asList(savedContactEntity, second));
        when(contactMapper.toResponseDTO(savedContactEntity)).thenReturn(responseDTO);
        when(contactMapper.toResponseDTO(second)).thenReturn(secondDto);

        List<ContactResponseDTO> result = contactService.getAllContacts();

        assertThat(result).hasSize(2).containsExactly(responseDTO, secondDto);
    }
    @Test
    @DisplayName("getAllContacts() returns an empty list when there are no contacts")
    void getAllContacts_emptyList() {
        when(contactRepository.findAll()).thenReturn(Collections.emptyList());

        List<ContactResponseDTO> result = contactService.getAllContacts();

        assertThat(result).isEmpty();
    }

    // ---------- updateContact ----------

    @Test
    @DisplayName("updateContact() updates fields, normalizes phones, and saves the contact")
    void updateContact_success() {
        when(contactRepository.findById(1L)).thenReturn(Optional.of(savedContactEntity));
        when(contactRepository.save(any(Contact.class))).thenReturn(savedContactEntity);
        when(contactMapper.toResponseDTO(savedContactEntity)).thenReturn(responseDTO);

        ContactRequestDTO updateDto = new ContactRequestDTO(
                "Johnny", "Q", "Doe", "johnny@example.com", "05555555555", null);

        ContactResponseDTO result = contactService.updateContact(1L, updateDto);

        assertThat(result).isEqualTo(responseDTO);

        ArgumentCaptor<Contact> captor = ArgumentCaptor.forClass(Contact.class);
        verify(contactRepository).save(captor.capture());
        Contact saved = captor.getValue();
        assertThat(saved.getFirstName()).isEqualTo("Johnny");
        assertThat(saved.getEmail()).isEqualTo("johnny@example.com");
        assertThat(saved.getPhone()).isEqualTo("5555555555");
    }

    @Test
    @DisplayName("updateContact() throws ContactNotFoundException when the id does not exist")
    void updateContact_notFound_throwsException() {
        when(contactRepository.findById(42L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> contactService.updateContact(42L, requestDTO))
                .isInstanceOf(ContactNotFoundException.class)
                .hasMessageContaining("42");

        verify(contactRepository, never()).save(any(Contact.class));
    }

    // ---------- deleteContact ----------

    @Test
    @DisplayName("deleteContact() deletes the contact when it exists")
    void deleteContact_success() {
        when(contactRepository.existsById(1L)).thenReturn(true);

        contactService.deleteContact(1L);

        verify(contactRepository, times(1)).deleteById(1L);
    }

    @Test
    @DisplayName("deleteContact() throws ContactNotFoundException when the id does not exist")
    void deleteContact_notFound_throwsException() {
        when(contactRepository.existsById(anyLong())).thenReturn(false);

        assertThatThrownBy(() -> contactService.deleteContact(7L))
                .isInstanceOf(ContactNotFoundException.class)
                .hasMessageContaining("7");

        verify(contactRepository, never()).deleteById(anyLong());
    }
    
    @Test
    @DisplayName("getAllFavourites() returns only contacts marked as favourite")
    void getAllFavourites_returnsFavouritesOnly() {
        Contact favouriteContact = new Contact();
        favouriteContact.setId(3L);
        favouriteContact.setEmail("fav@example.com");
        favouriteContact.setIsFavourite(true);

        ContactResponseDTO favouriteDto = new ContactResponseDTO(
                3L, "Fav", null, "Person", "fav@example.com", "9999999999", null, true);

        when(contactRepository.findByIsFavouriteTrue())
                .thenReturn(List.of(favouriteContact));
        when(contactMapper.toResponseDTO(favouriteContact))
                .thenReturn(favouriteDto);

        List<ContactResponseDTO> result = contactService.getAllFavourites();

        assertThat(result).hasSize(1).containsExactly(favouriteDto);
    }
}