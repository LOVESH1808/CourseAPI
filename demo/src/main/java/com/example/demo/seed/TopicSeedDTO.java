package com.example.demo.seed;

import java.util.List;

public record TopicSeedDTO(
        String id,
        String title,
        List<SubtopicSeedDTO> subtopics
) {}