package com.example.demo.enrollment.dto;

import com.example.demo.enrollment.Enrollment;

import java.time.Instant;

public class EnrollmentResponseDTO {

    private final Long enrollmentId;
    private final String courseId;
    private final String courseTitle;
    private final Instant enrolledAt;

    private EnrollmentResponseDTO(
            Long enrollmentId,
            String courseId,
            String courseTitle,
            Instant enrolledAt) {

        this.enrollmentId = enrollmentId;
        this.courseId = courseId;
        this.courseTitle = courseTitle;
        this.enrolledAt = enrolledAt;
    }

    public static EnrollmentResponseDTO from(
            Enrollment enrollment) {

        return new EnrollmentResponseDTO(
                enrollment.getId(),
                enrollment.getCourse().getId(),
                enrollment.getCourse().getTitle(),
                enrollment.getEnrolledAt()
        );
    }

    public Long getEnrollmentId() { return enrollmentId; }
    public String getCourseId() { return courseId; }
    public String getCourseTitle() { return courseTitle; }
    public Instant getEnrolledAt() { return enrolledAt; }
}