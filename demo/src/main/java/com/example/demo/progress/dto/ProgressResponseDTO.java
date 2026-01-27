package com.example.demo.progress.dto;

import java.time.Instant;
import java.util.List;

public class ProgressResponseDTO {

    private Long enrollmentId;
    private String courseId;
    private String courseTitle;
    private int totalSubtopics;
    private int completedSubtopics;
    private double completionPercentage;
    private List<CompletedItemDto> completedItems;

    public ProgressResponseDTO(
            Long enrollmentId,
            String courseId,
            String courseTitle,
            int totalSubtopics,
            int completedSubtopics,
            double completionPercentage,
            List<CompletedItemDto> completedItems) {

        this.enrollmentId = enrollmentId;
        this.courseId = courseId;
        this.courseTitle = courseTitle;
        this.totalSubtopics = totalSubtopics;
        this.completedSubtopics = completedSubtopics;
        this.completionPercentage = completionPercentage;
        this.completedItems = completedItems;
    }

    public Long getEnrollmentId() { return enrollmentId; }
    public String getCourseId() { return courseId; }
    public String getCourseTitle() { return courseTitle; }
    public int getTotalSubtopics() { return totalSubtopics; }
    public int getCompletedSubtopics() { return completedSubtopics; }
    public double getCompletionPercentage() { return completionPercentage; }
    public List<CompletedItemDto> getCompletedItems() { return completedItems; }

    public static class CompletedItemDto {

        private String subtopicId;
        private String subtopicTitle;
        private Instant completedAt;

        public CompletedItemDto(
                String subtopicId,
                String subtopicTitle,
                Instant completedAt) {

            this.subtopicId = subtopicId;
            this.subtopicTitle = subtopicTitle;
            this.completedAt = completedAt;
        }

        public String getSubtopicId() { return subtopicId; }
        public String getSubtopicTitle() { return subtopicTitle; }
        public Instant getCompletedAt() { return completedAt; }
    }
}