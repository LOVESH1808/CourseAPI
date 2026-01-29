package com.example.demo.seed;

import java.util.List;

public record CourseSeedDTO(
        String id,
        String title,
        String description,
        List<TopicSeedDTO> topics
) {}