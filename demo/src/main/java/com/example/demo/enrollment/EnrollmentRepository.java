package com.example.demo.enrollment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EnrollmentRepository
        extends JpaRepository<Enrollment, Long> {

    Optional<Enrollment> findByUserIdAndCourseId(
            Long userId,
            String courseId);

    boolean existsByUserIdAndCourseId(
            Long userId,
            String courseId);

    Optional<Enrollment> findByIdAndUserId(
            Long enrollmentId,
            Long userId
    );
}