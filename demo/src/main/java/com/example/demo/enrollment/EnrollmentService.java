package com.example.demo.enrollment;

import com.example.demo.enrollment.dto.EnrollmentResponseDTO;

public interface EnrollmentService {
    EnrollmentResponseDTO enroll(String userEmail, String courseId);
}