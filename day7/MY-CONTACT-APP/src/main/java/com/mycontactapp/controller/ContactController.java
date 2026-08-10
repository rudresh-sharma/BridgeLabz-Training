package com.mycontactapp.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycontactapp.dto.ContactRequestDTO;
import com.mycontactapp.dto.ContactResponseDTO;
import com.mycontactapp.exception.ContactNotFoundException;
import com.mycontactapp.service.ContactService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/contacts")
@Tag(
    name = "Contact Management",
    description = "APIs for creating, retrieving, updating and deleting contacts"
)
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    // Create contact
    @Operation(
        summary = "Create a contact",
        description = "Creates a new contact"
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "201",
            description = "Contact created successfully"
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid contact data"
        )
    })
    @PostMapping
    public ResponseEntity<ContactResponseDTO> createContact(
            @Valid @RequestBody ContactRequestDTO dto) {

        ContactResponseDTO response =
                contactService.createContact(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    // Get contact by ID
    @Operation(
        summary = "Get contact by ID",
        description = "Retrieves a contact using its ID"
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Contact found"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Contact not found"
        )
    })
    @GetMapping("/{id}")
    public ResponseEntity<ContactResponseDTO> getContactById(
            @Parameter(
                description = "ID of the contact",
                example = "1"
            )
            @PathVariable Long id) {

        ContactResponseDTO response =
                contactService.getContactById(id);

        return ResponseEntity.ok(response);
    }

    // Get all contacts
    @Operation(
        summary = "Get all contacts",
        description = "Retrieves all contacts"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Contacts retrieved successfully"
    )
    @GetMapping
    public ResponseEntity<List<ContactResponseDTO>> getAllContacts() {

        List<ContactResponseDTO> response =
                contactService.getAllContacts();

        return ResponseEntity.ok(response);
    }

    // Update contact
    @Operation(
        summary = "Update a contact",
        description = "Updates an existing contact using its ID"
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Contact updated successfully"
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid contact data"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Contact not found"
        )
    })
    @PutMapping("/{id}")
    public ResponseEntity<ContactResponseDTO> updateContact(
            @Parameter(
                description = "ID of the contact",
                example = "1"
            )
            @PathVariable Long id,

            @Valid @RequestBody ContactRequestDTO dto) {

        ContactResponseDTO response =
                contactService.updateContact(id, dto);

        return ResponseEntity.ok(response);
    }

    // Delete contact
    @Operation(
        summary = "Delete a contact",
        description = "Deletes an existing contact using its ID"
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "204",
            description = "Contact deleted successfully"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Contact not found"
        )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContact(
            @Parameter(
                description = "ID of the contact",
                example = "1"
            )
            @PathVariable Long id) {

        contactService.deleteContact(id);

        return ResponseEntity.noContent().build();
    }

    // Controller-level exception handling
    @ExceptionHandler(ContactNotFoundException.class)
    @ApiResponse(
        responseCode = "404",
        description = "Contact not found"
    )
    public ResponseEntity<String> handleContactNotFound(
            ContactNotFoundException exception) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }
}

