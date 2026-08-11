package com.mycontactapp.dto;

import jakarta.validation.constraints.Email;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContactRequestDTO {

    @NotBlank(message = "Name is required")
    @Size(
        min = 2,
        max = 50,
        message = "Name must be between 2 and 50 characters"
    )
    private String firstName;
    
    private String middleName;
    
    
    @NotBlank(message = "Name is required")
    @Size(
    		min = 2,
    		max = 50,
    		message = "Name must be between 2 and 50 characters"
    		)
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Enter a valid email address")
    private String email;

    @NotBlank(message = "Phone number is required")
    @Pattern(
        regexp = "^0?[0-9]{10}$",
        message = "Phone number must contain exactly 10 digits"
    )
    private String phone;
    
    @Pattern(
            regexp = "^0?[0-9]{10}$",
            message = "Alternate phone number must contain 10 digits, optionally preceded by 0"
        )
    private String alternatePhone;
}