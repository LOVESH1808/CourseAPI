package com.example.demo.progress;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubTopicProgressRepository
        extends JpaRepository<SubTopicProgress, Long> {

    Optional<SubTopicProgress> findByUserIdAndSubtopicId(
            Long userId,
            String subtopicId
    );

    List<SubTopicProgress> findByUserId(Long userId);
}