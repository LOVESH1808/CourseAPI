package com.example.demo.progress.dto;

import com.example.demo.progress.SubTopicProgress;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class SubtopicProgressResponseDTO {

    private final String subtopicId;
    private final boolean completed;
    private final Instant completedAt;

    public SubtopicProgressResponseDTO(
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

}