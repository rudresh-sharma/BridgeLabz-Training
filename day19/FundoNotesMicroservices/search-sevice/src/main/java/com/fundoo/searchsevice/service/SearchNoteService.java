package com.fundoo.searchsevice.service;

import java.util.List;


import org.springframework.stereotype.Service;

import com.fundoo.searchsevice.entity.SearchNote;
import com.fundoo.searchsevice.repository.SearchNoteRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SearchNoteService {

    private final SearchNoteRepository searchNoteRepository;

    public List<SearchNote> searchNotes(
            String email,
            String keyword,
            String status
    ) {

        boolean hasKeyword =
                keyword != null && !keyword.isBlank();

        boolean hasStatus =
                status != null && !status.isBlank();

        if (hasKeyword && hasStatus) {

            return searchNoteRepository.searchByKeywordAndStatus(
                    email,
                    keyword,
                    status
            );
        }

        if (hasKeyword) {

            return searchNoteRepository.searchByKeyword(
                    email,
                    keyword
            );
        }

        if (hasStatus) {

            return searchNoteRepository.searchByStatus(
                    email,
                    status
            );
        }

        return searchNoteRepository.findByEmail(email);
    }
}