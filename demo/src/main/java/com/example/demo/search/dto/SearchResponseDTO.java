package com.example.demo.search.dto;

import java.util.List;

public class SearchResponseDTO {
private String query;
private List<CourseMatchDTO> results;

public SearchResponseDTO(String query, List<CourseMatchDTO> results) {
    this.query = query;
    this.results = results;
}

public String getQuery() { return query; }
public List<CourseMatchDTO> getResults() { return results; }
}