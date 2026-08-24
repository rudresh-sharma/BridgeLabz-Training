package com.fundoonotesapp.search.repository;

import com.fundoonotesapp.notes.entity.Note.NoteStatus;
import com.fundoonotesapp.search.document.NoteDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteSearchRepository
        extends ElasticsearchRepository<NoteDocument, Long> {

    // ==========================================
    // SEARCH BY KEYWORD
    // ==========================================
    @Query("""
        {
          "bool": {
            "must": [
              {
                "term": {
                  "userId": ?0
                }
              },
              {
                "bool": {
                  "should": [
                    {
                      "match_phrase_prefix": {
                        "title": {
                          "query": "?1"
                        }
                      }
                    },
                    {
                      "match_phrase_prefix": {
                        "content": {
                          "query": "?1"
                        }
                      }
                    }
                  ],
                  "minimum_should_match": 1
                }
              }
            ]
          }
        }
        """)
    Page<NoteDocument> searchByKeyword(
            Long userId,
            String keyword,
            Pageable pageable
    );


    // ==========================================
    // SEARCH BY STATUS
    // ==========================================
    @Query("""
        {
          "bool": {
            "must": [
              {
                "term": {
                  "userId": ?0
                }
              },
              {
                "term": {
                  "status.keyword": "?1"
                }
              }
            ]
          }
        }
        """)
    Page<NoteDocument> searchByStatus(
            Long userId,
            NoteStatus status,
            Pageable pageable
    );


    // ==========================================
    // SEARCH BY KEYWORD + STATUS
    // ==========================================
    @Query("""
        {
          "bool": {
            "must": [
              {
                "term": {
                  "userId": ?0
                }
              },
              {
                "term": {
                  "status.keyword": "?2"
                }
              },
              {
                "bool": {
                  "should": [
                    {
                      "match_phrase_prefix": {
                        "title": {
                          "query": "?1"
                        }
                      }
                    },
                    {
                      "match_phrase_prefix": {
                        "content": {
                          "query": "?1"
                        }
                      }
                    }
                  ],
                  "minimum_should_match": 1
                }
              }
            ]
          }
        }
        """)
    Page<NoteDocument> searchByKeywordAndStatus(
            Long userId,
            String keyword,
            NoteStatus status,
            Pageable pageable
    );
}