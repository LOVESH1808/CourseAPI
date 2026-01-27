package com.example.demo.search.dto;

import java.util.List;

public class CourseMatchDTO{

    private String courseId;
    private String courseTitle;
    private List<MatchDTO> matches;

    public CourseMatchDTO(
            String courseId,
            String courseTitle,
            List<MatchDTO> matches) {

        this.courseId = courseId;
        this.courseTitle = courseTitle;
        this.matches = matches;
    }

    public String getCourseId() { return courseId; }
    public String getCourseTitle() { return courseTitle; }
    public List<MatchDTO> getMatches() { return matches; }
}