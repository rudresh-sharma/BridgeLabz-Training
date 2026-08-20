package com.fundoonotesapp.search.service;

import com.fundoonotesapp.mapper.NoteMapper;
import com.fundoonotesapp.notes.entity.Note;
import com.fundoonotesapp.notes.entity.Note.NoteStatus;
import com.fundoonotesapp.search.document.NoteDocument;
import com.fundoonotesapp.search.dto.SearchNoteResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import java.util.List;
import java.util.List;
import com.fundoonotesapp.search.mapper.NoteDocumentMapper;
import com.fundoonotesapp.search.mapper.SearchSortField;
import com.fundoonotesapp.search.repository.NoteSearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SearchService {

	private final NoteSearchRepository noteSearchRepository;
	private final NoteDocumentMapper noteDocumentMapper;

	public void indexNote(Note note) {

		NoteDocument document = noteDocumentMapper.toDocument(note);

		noteSearchRepository.save(document);
	}

	public void deleteNoteFromIndex(Long noteId) {

		noteSearchRepository.deleteById(noteId);
	}

	public Page<SearchNoteResponse> searchNotes(Long userId, String keyword, NoteStatus status, int page, int size,
			SearchSortField sortBy, Sort.Direction direction) {

		Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy.getField()));
		
		Page<NoteDocument> notes;

		// Case 1: keyword + status
		if (keyword != null && !keyword.isBlank() && status != null) {

			notes = noteSearchRepository.searchByKeywordAndStatus(userId, keyword, status, pageable);
		}

		// Case 2: keyword only
		else if (keyword != null && !keyword.isBlank()) {

			notes = noteSearchRepository.searchByKeyword(userId, keyword, pageable);
		}

		// Case 3: status only
		else if (status != null) {

			notes = noteSearchRepository.searchByStatus(userId, status, pageable);
		}

		// Case 4: neither keyword nor status
		else {

			notes = Page.empty(pageable);
		}

		return notes.map(noteDocumentMapper::toResponse);
	}
}