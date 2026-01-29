package com.example.demo.enrollment.dto;

import com.example.demo.progress.SubTopicProgress;
import com.example.demo.progress.dto.SubtopicProgressResponseDTO;
import com.example.demo.enrollment.Enrollment;

import java.util.List;
import java.util.stream.Collectors;

public record EnrollmentProgressResponseDTO(
        Long enrollmentId,
        Long courseId,
        String courseTitle,
        int totalSubtopics,
        int completedSubtopics,
        int completionPercentage,
        List<SubtopicProgressResponseDTO> subtopics
) {

    public static EnrollmentProgressResponseDTO from(
            Enrollment enrollment,
            int totalSubtopics,
            List<SubTopicProgress> completedSubtopics) {

        int completedCount = completedSubtopics.size();
        int percentage = totalSubtopics == 0 ? 0 : (int) ((completedCount * 100.0f) / totalSubtopics);

        List<SubtopicProgressResponseDTO> subtopicDTOs = completedSubtopics.stream()
                .map(SubtopicProgressResponseDTO::from)
                .collect(Collectors.toList());

        return new EnrollmentProgressResponseDTO(
                enrollment.getId(),
                enrollment.getCourse().getId().hashCode() > 0 ? enrollment.getCourse().getId().hashCode() : 0L,
                enrollment.getCourse().getTitle(),
                totalSubtopics,
                completedCount,
                percentage,
                subtopicDTOs
        );
    }
}