package com.example.demo.progress;

import com.example.demo.course.Subtopic;
import com.example.demo.course.SubTopicRepository;
import com.example.demo.enrollment.Enrollment;
import com.example.demo.enrollment.EnrollmentRepository;
import com.example.demo.enrollment.dto.EnrollmentProgressResponseDTO;
import com.example.demo.progress.dto.SubtopicProgressResponseDTO;
import com.example.demo.user.User;
import com.example.demo.user.UserRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class ProgressServiceImpl implements ProgressService {

    private final UserRepository userRepository;
    private final SubTopicRepository subtopicRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final SubTopicProgressRepository progressRepository;

    public ProgressServiceImpl(
            UserRepository userRepository,
            SubTopicRepository subtopicRepository,
            EnrollmentRepository enrollmentRepository,
            SubTopicProgressRepository progressRepository) {

        this.userRepository = userRepository;
        this.subtopicRepository = subtopicRepository;
        this.enrollmentRepository = enrollmentRepository;
        this.progressRepository = progressRepository;
    }

    @Override
    public SubtopicProgressResponseDTO completeSubtopic(
            String userEmail,
            String subtopicId) {

        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Subtopic subtopic = subtopicRepository.findById(subtopicId)
                .orElseThrow(() -> new RuntimeException("Subtopic not found"));

        Enrollment enrollment =
                enrollmentRepository.findByUserIdAndCourseId(
                        user.getId(),
                        subtopic.getTopic().getCourse().getId()
                ).orElseThrow(() ->
                        new RuntimeException("User not enrolled in course"));
        return progressRepository
                .findByUserAndSubtopic(user, subtopic)
                .map(SubtopicProgressResponseDTO::from)
                .orElseGet(() -> {
                    SubTopicProgress progress = new SubTopicProgress();
                    progress.setUser(user);
                    progress.setSubtopic(subtopic);
                    progress.setCompleted(true);
                    progress.setCompletedAt(Instant.now());

                    progressRepository.save(progress);
                    return SubtopicProgressResponseDTO.from(progress);
                });

    }

    @Override
    public EnrollmentProgressResponseDTO getProgress(
            String userEmail,
            Long enrollmentId) {

        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Enrollment enrollment = enrollmentRepository.findById(enrollmentId)
                .orElseThrow(() -> new RuntimeException("Enrollment not found"));

        if (!enrollment.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Forbidden");
        }

        int totalSubtopics =
                enrollment.getCourse().getTotalSubtopicCount();

        List<SubTopicProgress> completed =
                progressRepository.findAllByUserAndCourse(
                        user,
                        enrollment.getCourse());

        return EnrollmentProgressResponseDTO.from(
                enrollment,
                totalSubtopics,
                completed
        );
    }
}