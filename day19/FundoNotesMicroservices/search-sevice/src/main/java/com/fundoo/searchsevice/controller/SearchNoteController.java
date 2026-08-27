package com.fundoo.searchsevice.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fundoo.searchsevice.entity.SearchNote;
import com.fundoo.searchsevice.service.SearchNoteService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/search/notes")
@RequiredArgsConstructor
public class SearchNoteController {

	private final SearchNoteService searchNoteService;

	@GetMapping
	public ResponseEntity<List<SearchNote>> searchNotes(

			@RequestParam(required = false) String keyword,

			@RequestParam(required = false) String status, HttpServletRequest request) {

		String email = (String) request.getAttribute("email");
		
		

		return ResponseEntity.ok(searchNoteService.searchNotes(email, keyword, status));
	}
}