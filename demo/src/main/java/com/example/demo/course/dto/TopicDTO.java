package com.example.demo.course.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class TopicDTO {
    private String id;
    private String title;
    List<SubTopicDTO> subtopics;

}