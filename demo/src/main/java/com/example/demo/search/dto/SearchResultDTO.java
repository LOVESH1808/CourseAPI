package com.example.demo.search.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class SearchResultDTO {
    private String courseId;
    private String courseTitle;
    private List<MatchDTO> matches;

    @Getter
    @Setter
    @AllArgsConstructor
    public static class MatchDTO {
        private String type;        // "topic", "subtopic", or "content"
        private String topicTitle;
        private String subtopicId;
        private String subtopicTitle;
        private String snippet;
    }
}