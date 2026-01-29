package com.example.demo.progress;

import com.example.demo.enrollment.dto.EnrollmentProgressResponseDTO;
import com.example.demo.progress.dto.SubtopicProgressResponseDTO;

public interface ProgressService {
    SubtopicProgressResponseDTO completeSubtopic(
            String userEmail,
            String subtopicId);

    EnrollmentProgressResponseDTO getProgress(
            String userEmail,
            Long enrollmentId);
}