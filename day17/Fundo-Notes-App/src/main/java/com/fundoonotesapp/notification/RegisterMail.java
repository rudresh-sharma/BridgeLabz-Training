package com.fundoonotesapp.notification;

import java.io.Serializable;

public record RegisterMail (
		
		String fullname,
		String email
		) implements Serializable{}
