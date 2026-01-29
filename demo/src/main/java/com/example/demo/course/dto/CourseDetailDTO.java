package com.example.demo.course.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Getter
@Setter
public class CourseDetailDTO {

    private String id;
    private String title;
    private String description;
    private List<TopicDTO> topics;


    // getters and setters
    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public List <TopicDTO> getTopics() { return topics; }
}