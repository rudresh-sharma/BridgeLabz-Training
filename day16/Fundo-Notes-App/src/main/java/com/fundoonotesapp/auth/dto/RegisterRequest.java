package com.fundoonotesapp.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {
	
	@NotBlank(message="Name is required")
	@Size(min = 2, max = 100)
    private String name;
	
	@Email
	@NotBlank
    private String email;
	
	
	@Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[@_+#%^&*-]).{8,}$",
			message="Password Must Contain At least One Uppercase, One Lowercase, One Digit, One Special Symbol "
					+ "and length should be 8 or more")
	private String password;
}