package com.example.demo.course.dto;

public class CourseSummaryDTO {

    private String id;
    private String title;
    private String description;
    private int topicCount;
    private int subtopicCount;

    public CourseSummaryDTO(
            String id,
            String title,
            String description,
            int topicCount,
            int subtopicCount) {

        this.id = id;
        this.title = title;
        this.description = description;
        this.topicCount = topicCount;
        this.subtopicCount = subtopicCount;
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public int getTopicCount() { return topicCount; }
    public int getSubtopicCount() { return subtopicCount; }
}