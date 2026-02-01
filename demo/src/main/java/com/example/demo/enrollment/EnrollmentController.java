package com.example.demo.enrollment;

import com.example.demo.user.User;
import com.example.demo.user.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/courses")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;
    private final UserRepository userRepository;

    public EnrollmentController(EnrollmentService enrollmentService, UserRepository userRepository) {
        this.enrollmentService = enrollmentService;
        this.userRepository = userRepository;
    }

    @PostMapping("/{courseId}/enroll")
    public ResponseEntity<?> enroll(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable String courseId) {
        String userEmail = userDetails.getUsername();
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new IllegalStateException("User not found"));

        try {
            var enrollment = enrollmentService.enroll(user, courseId);
            return ResponseEntity.ok(Map.of(
                    "enrollmentId", enrollment.getId(),
                    "courseId", enrollment.getCourse().getId(),
                    "courseTitle", enrollment.getCourse().getTitle(),
                    "enrolledAt", enrollment.getEnrolledAt()
            ));
        } catch (IllegalStateException ex) {
            return ResponseEntity.status(409).body(Map.of(
                    "error", "Already enrolled",
                    "message", ex.getMessage()
            ));
        }
    }

    @PostMapping("/subtopics/{subtopicId}/complete")
    public ResponseEntity<?> completeSubtopic(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable String subtopicId) {

        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new IllegalStateException("User not found"));

        CompletedSubtopic completed =
                enrollmentService.markSubtopicCompleted(user, subtopicId);

        return ResponseEntity.ok(Map.of(
                "completed", true,
                "subtopicId", completed.getSubtopic().getId(),
                "completedAt", completed.getCompletedAt()
        ));
    }

    @GetMapping("/enrollments/{enrollmentId}/progress")
    public ResponseEntity<?> progress(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long enrollmentId) {

        if (userDetails == null) {
            return ResponseEntity.status(401).build();
        }

        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new IllegalStateException("User not found"));

        Map<String, Object> progress =
                enrollmentService.getProgressForUser(enrollmentId, user.getId());

        return ResponseEntity.ok(progress);
    }
}