package com.example.demo.search;

import com.example.demo.search.dto.SearchResultDTO;

import java.util.List;

public interface SearchService {

    /**
     * Search for courses, topics, and subtopics based on a free-text query.
     * @param query The search query (case-insensitive, partial matches)
     * @return SearchResultDTO containing matched courses and snippets
     */
    List<SearchResultDTO> search(String query);
}