package com.fundoonotesapp.search.controller;

import com.fundoonotesapp.notes.entity.Note.NoteStatus;
import com.fundoonotesapp.search.dto.SearchNoteResponse;
import com.fundoonotesapp.search.mapper.SearchSortField;
import com.fundoonotesapp.search.service.SearchService;
import com.fundoonotesapp.security.CustomUserDetails;
import org.springframework.data.domain.Sort;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/search")
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;

    @GetMapping("/notes")
    public ResponseEntity<Page<SearchNoteResponse>> searchNotes(

            @RequestParam(required = false) String keyword,

            @RequestParam(required = false) NoteStatus status,

            @RequestParam(defaultValue = "0") int page,

            @RequestParam(defaultValue = "10") int size,

            @RequestParam(defaultValue = "CREATED_AT")
            SearchSortField sortBy,
            
            @RequestParam(defaultValue = "DESC") Sort.Direction direction,

            @AuthenticationPrincipal CustomUserDetails userDetails) {

        Page<SearchNoteResponse> notes =
                searchService.searchNotes(
                        userDetails.getUser().getId(),
                        keyword,
                        status,
                        page,
                        size,
                        sortBy,
                        direction
                );

        return ResponseEntity.ok(notes);
    }
}