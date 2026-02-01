package com.example.demo.enrollment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CompletedSubtopicRepository
        extends JpaRepository<CompletedSubtopic, Long> {

    long countByEnrollmentId(Long enrollmentId);

    Optional<CompletedSubtopic>
    findByEnrollmentIdAndSubtopicId(Long enrollmentId, String subtopicId);

    List<CompletedSubtopic>
    findAllByEnrollmentId(Long enrollmentId);
}
