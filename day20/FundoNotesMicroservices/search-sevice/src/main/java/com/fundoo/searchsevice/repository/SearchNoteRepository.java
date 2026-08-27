package com.fundoo.searchsevice.repository;

import java.util.List;

import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.fundoo.searchsevice.entity.*;

public interface SearchNoteRepository
        extends ElasticsearchRepository<SearchNote, Long> {

	
	
	List<SearchNote> findByEmail(String email);
    // ==========================================
    // SEARCH BY KEYWORD
    // ==========================================
    @Query("""
        {
          "bool": {
            "must": [
              {
                "term": {
                  "email": "?0"
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
    List<SearchNote> searchByKeyword(
            String email,
            String keyword
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
                  "email": "?0"
                }
              },
              {
                "term": {
                  "status": "?1"
                }
              }
            ]
          }
        }
        """)
    List<SearchNote> searchByStatus(
            String email,
            String status
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
                  "email": "?0"
                }
              },
              {
                "term": {
                  "status": "?2"
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
    List<SearchNote> searchByKeywordAndStatus(
            String email,
            String keyword,
            String status
    );
}