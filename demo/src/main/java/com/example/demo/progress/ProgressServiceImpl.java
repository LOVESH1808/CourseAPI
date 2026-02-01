package com.example.demo.progress;

import com.example.demo.course.Subtopic;
import com.example.demo.course.SubTopicRepository;
import com.example.demo.enrollment.CompletedSubtopic;
import com.example.demo.enrollment.CompletedSubtopicRepository;
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
    private final CompletedSubtopicRepository completedSubtopicRepository;

    public ProgressServiceImpl(
            UserRepository userRepository,
            SubTopicRepository subtopicRepository,
            EnrollmentRepository enrollmentRepository,
            CompletedSubtopicRepository completedSubtopicRepository
    ) {
        this.userRepository = userRepository;
        this.subtopicRepository = subtopicRepository;
        this.enrollmentRepository = enrollmentRepository;
        this.completedSubtopicRepository = completedSubtopicRepository;
    }

    @Override
    public SubtopicProgressResponseDTO completeSubtopic(String userEmail, String subtopicId) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Subtopic subtopic = subtopicRepository.findById(subtopicId)
                .orElseThrow(() -> new RuntimeException("Subtopic not found"));

        Enrollment enrollment = enrollmentRepository
                .findByUserIdAndCourseId(user.getId(), subtopic.getTopic().getCourse().getId())
                .orElseThrow(() -> new RuntimeException("User not enrolled in course"));

        CompletedSubtopic completedSubtopic = completedSubtopicRepository
                .findByEnrollmentIdAndSubtopicId(enrollment.getId(), subtopicId)
                .orElseGet(() -> {
                    CompletedSubtopic cs = new CompletedSubtopic();
                    cs.setEnrollment(enrollment);
                    cs.setSubtopic(subtopic);
                    cs.setCompletedAt(Instant.now());
                    return completedSubtopicRepository.save(cs);
                });

        return new SubtopicProgressResponseDTO(
                completedSubtopic.getSubtopic().getId(),
                true,
                completedSubtopic.getCompletedAt()
        );
    }

    @Override
    public EnrollmentProgressResponseDTO getProgress(String userEmail, Long enrollmentId) {
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Enrollment enrollment = enrollmentRepository.findById(enrollmentId)
                .orElseThrow(() -> new RuntimeException("Enrollment not found"));

        if (!enrollment.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Forbidden");
        }

        int totalSubtopics = enrollment.getCourse().getTotalSubtopicCount();

        List<CompletedSubtopic> completed = completedSubtopicRepository
                .findAllByEnrollmentId(enrollment.getId());

        return EnrollmentProgressResponseDTO.from(
                enrollment,
                totalSubtopics,
                completed
        );
    }
}