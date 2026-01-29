package com.example.demo.enrollment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompletedSubtopicRepository extends JpaRepository<CompletedSubtopic, Long> {
    Optional<CompletedSubtopic> findByEnrollmentIdAndSubtopicId(Long enrollmentId, String subtopicId);
    long countByEnrollmentId(Long enrollmentId);
}