package com.example.demo.enrollment.dto;

import com.example.demo.enrollment.CompletedSubtopic;
import com.example.demo.enrollment.Enrollment;
import com.example.demo.progress.SubTopicProgress;
import com.example.demo.progress.dto.SubtopicProgressResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

public record EnrollmentProgressResponseDTO(
        Long enrollmentId,
        String courseId,
        String courseTitle,
        int totalSubtopics,
        int completedSubtopics,
        int completionPercentage,
        List<SubtopicProgressResponseDTO> subtopics
) {

    public static EnrollmentProgressResponseDTO from(
            Enrollment enrollment,
            int totalSubtopics,
            List<CompletedSubtopic> completedSubtopics
    ) {

        int completedCount = completedSubtopics.size();
        int percentage = totalSubtopics == 0
                ? 0
                : (int) ((completedCount * 100.0f) / totalSubtopics);

        List<SubtopicProgressResponseDTO> subtopicDTOs =
                completedSubtopics.stream()
                        .map(cs -> {
                            SubTopicProgress p = new SubTopicProgress();
                            p.setSubtopic(cs.getSubtopic());
                            p.setCompleted(true);
                            p.setCompletedAt(cs.getCompletedAt());
                            return SubtopicProgressResponseDTO.from(p);
                        })
                        .collect(Collectors.toList());

        return new EnrollmentProgressResponseDTO(
                enrollment.getId(),
                enrollment.getCourse().getId(),
                enrollment.getCourse().getTitle(),
                totalSubtopics,
                completedCount,
                percentage,
                subtopicDTOs
        );
    }
}