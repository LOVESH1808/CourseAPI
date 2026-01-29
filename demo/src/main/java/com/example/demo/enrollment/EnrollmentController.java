package com.example.demo.enrollment;

import com.example.demo.enrollment.dto.EnrollmentResponseDTO;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/courses")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @PostMapping("/{courseId}/enroll")
    public EnrollmentResponseDTO enroll(
            @PathVariable String courseId,
            @AuthenticationPrincipal UserDetails user) {

        return enrollmentService.enroll(user.getUsername(), courseId);
    }
}