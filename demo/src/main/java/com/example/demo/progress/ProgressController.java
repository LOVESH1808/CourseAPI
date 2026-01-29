package com.example.demo.progress;


import com.example.demo.progress.dto.SubtopicProgressResponseDTO;
import com.example.demo.enrollment.dto.EnrollmentProgressResponseDTO;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ProgressController {

    private final ProgressService progressService;

    public ProgressController(ProgressService progressService) {
        this.progressService = progressService;
    }

    @PostMapping("/subtopics/{subtopicId}/complete")
    public SubtopicProgressResponseDTO completeSubtopic(
            @PathVariable String subtopicId,
            @AuthenticationPrincipal UserDetails user) {

        return progressService.completeSubtopic(
                user.getUsername(), subtopicId);
    }

    @GetMapping("/enrollments/{enrollmentId}/progress")
    public EnrollmentProgressResponseDTO getProgress(
            @PathVariable Long enrollmentId,
            @AuthenticationPrincipal UserDetails user) {

        return progressService.getProgress(
                user.getUsername(), enrollmentId);
    }
}