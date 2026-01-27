package com.example.demo.course.dto;

import java.util.List;

public class TopicDTO {

    private String id;
    private String title;
    private List<SubTopicDTO> subtopics;

    public TopicDTO(String id, String title, List<SubTopicDTO> subtopics) {
        this.id = id;
        this.title = title;
        this.subtopics = subtopics;
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public List<SubTopicDTO> getSubtopics() { return subtopics; }
}