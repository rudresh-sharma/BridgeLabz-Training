package com.mycontactapp;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycontactapp.controller.ContactController;
import com.mycontactapp.dto.ContactRequestDTO;
import com.mycontactapp.dto.ContactResponseDTO;
import com.mycontactapp.exception.ContactNotFoundException;
import com.mycontactapp.exception.EmailAlreadyExistsException;
import com.mycontactapp.exception.GlobalExceptionHandler;
import com.mycontactapp.service.ContactService;

/**
 * Unit tests for {@link ContactController}.
 *
 * MockMvc is built in "standalone" mode (no full Spring context) with the
 * controller under test plus {@link GlobalExceptionHandler} registered, so
 * that bean-validation errors and service-thrown exceptions are handled the
 * same way they would be at runtime.
 */
@ExtendWith(MockitoExtension.class)
class ContactControllerTest {

    @Mock
    private ContactService contactService;

    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    private ContactRequestDTO validRequest;
    private ContactResponseDTO responseDTO;

    @BeforeEach
    void setUp() {
        ContactController controller = new ContactController(contactService);

        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();

        validRequest = new ContactRequestDTO(
                "John",
                "Q",
                "Doe",
                "john.doe@example.com",
                "9876543210",
                "1234567890"
        );

        responseDTO = new ContactResponseDTO(
                1L,
                "John",
                "Q",
                "Doe",
                "john.doe@example.com",
                "9876543210",
                "1234567890"
        );
    }

    // ---------- POST /api/contacts ----------

    @Test
    @DisplayName("POST /api/contacts returns 201 and the created contact for a valid request")
    void createContact_returns201() throws Exception {
        when(contactService.createContact(any(ContactRequestDTO.class))).thenReturn(responseDTO);

        mockMvc.perform(post("/api/contacts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(validRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.email").value("john.doe@example.com"));
    }

    @Test
    @DisplayName("POST /api/contacts returns 400 when required fields are blank")
    void createContact_invalidData_returns400() throws Exception {
        ContactRequestDTO invalid = new ContactRequestDTO();
        invalid.setFirstName("");
        invalid.setLastName("");
        invalid.setEmail("not-an-email");
        invalid.setPhone("");

        mockMvc.perform(post("/api/contacts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalid)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("POST /api/contacts returns 409 when the email already exists")
    void createContact_duplicateEmail_returns409() throws Exception {
        when(contactService.createContact(any(ContactRequestDTO.class)))
                .thenThrow(new EmailAlreadyExistsException(
                        "Email already exists: " + validRequest.getEmail()));

        mockMvc.perform(post("/api/contacts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(validRequest)))
                .andExpect(status().isConflict())
                .andExpect(content().string(org.hamcrest.Matchers.containsString(validRequest.getEmail())));
    }

    // ---------- GET /api/contacts/{id} ----------

    @Test
    @DisplayName("GET /api/contacts/{id} returns 200 and the contact when found")
    void getContactById_returns200() throws Exception {
        when(contactService.getContactById(1L)).thenReturn(responseDTO);

        mockMvc.perform(get("/api/contacts/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.firstName").value("John"));
    }

    @Test
    @DisplayName("GET /api/contacts/{id} returns 404 when the contact does not exist")
    void getContactById_notFound_returns404() throws Exception {
        when(contactService.getContactById(99L))
                .thenThrow(new ContactNotFoundException("Contact not found with id: 99"));

        mockMvc.perform(get("/api/contacts/99"))
                .andExpect(status().isNotFound())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("99")));
    }

    // ---------- GET /api/contacts ----------

    @Test
    @DisplayName("GET /api/contacts returns 200 and a list of contacts")
    void getAllContacts_returns200WithList() throws Exception {
        ContactResponseDTO second = new ContactResponseDTO(
                2L, "Jane", null, "Roe", "jane@example.com", "1112223333", null);

        List<ContactResponseDTO> contacts = Arrays.asList(responseDTO, second);
        when(contactService.getAllContacts()).thenReturn(contacts);

        mockMvc.perform(get("/api/contacts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].email").value("john.doe@example.com"))
                .andExpect(jsonPath("$[1].email").value("jane@example.com"));
    }

    @Test
    @DisplayName("GET /api/contacts returns 200 and an empty array when there are no contacts")
    void getAllContacts_emptyList_returns200() throws Exception {
        when(contactService.getAllContacts()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/contacts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));
    }

    // ---------- PUT /api/contacts/{id} ----------

    @Test
    @DisplayName("PUT /api/contacts/{id} returns 200 and the updated contact")
    void updateContact_returns200() throws Exception {
        when(contactService.updateContact(anyLong(), any(ContactRequestDTO.class)))
                .thenReturn(responseDTO);

        mockMvc.perform(put("/api/contacts/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(validRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    @DisplayName("PUT /api/contacts/{id} returns 404 when the contact does not exist")
    void updateContact_notFound_returns404() throws Exception {
        when(contactService.updateContact(anyLong(), any(ContactRequestDTO.class)))
                .thenThrow(new ContactNotFoundException("Contact not found with id: 55"));

        mockMvc.perform(put("/api/contacts/55")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(validRequest)))
                .andExpect(status().isNotFound())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("55")));
    }

    @Test
    @DisplayName("PUT /api/contacts/{id} returns 400 when the request body fails validation")
    void updateContact_invalidData_returns400() throws Exception {
        ContactRequestDTO invalid = new ContactRequestDTO();
        invalid.setFirstName("J"); // too short (min 2)
        invalid.setLastName("Doe");
        invalid.setEmail("john.doe@example.com");
        invalid.setPhone("123"); // does not match 10-digit pattern

        mockMvc.perform(put("/api/contacts/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalid)))
                .andExpect(status().isBadRequest());
    }

    // ---------- DELETE /api/contacts/{id} ----------

    @Test
    @DisplayName("DELETE /api/contacts/{id} returns 204 when the contact is deleted")
    void deleteContact_returns204() throws Exception {
        mockMvc.perform(delete("/api/contacts/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("DELETE /api/contacts/{id} returns 404 when the contact does not exist")
    void deleteContact_notFound_returns404() throws Exception {
        org.mockito.Mockito.doThrow(new ContactNotFoundException("Contact not found with id: 7"))
                .when(contactService).deleteContact(7L);

        mockMvc.perform(delete("/api/contacts/7"))
                .andExpect(status().isNotFound())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("7")));
    }
}