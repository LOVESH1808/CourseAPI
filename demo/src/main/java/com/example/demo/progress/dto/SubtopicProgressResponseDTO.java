package com.example.demo.progress.dto;

import com.example.demo.progress.SubTopicProgress;

import java.time.Instant;

public class SubtopicProgressResponseDTO {

    private final String subtopicId;
    private final boolean completed;
    private final Instant completedAt;

    private SubtopicProgressResponseDTO(
            String subtopicId,
            boolean completed,
            Instant completedAt) {

        this.subtopicId = subtopicId;
        this.completed = completed;
        this.completedAt = completedAt;
    }

    public static SubtopicProgressResponseDTO from(
            SubTopicProgress progress) {

        return new SubtopicProgressResponseDTO(
                progress.getSubtopic().getId(),
                progress.isCompleted(),
                progress.getCompletedAt()
        );
    }

    public String getSubtopicId() { return subtopicId; }
    public boolean isCompleted() { return completed; }
    public Instant getCompletedAt() { return completedAt; }
}