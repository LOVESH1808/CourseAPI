package com.example.demo.enrollment;

import com.example.demo.course.Course;
import com.example.demo.course.CourseRepository;
import com.example.demo.course.Subtopic;
import com.example.demo.user.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.*;
@Service
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final CompletedSubtopicRepository completedSubtopicRepository;
    private final CourseRepository courseRepository;
    private final SubtopicRepository subtopicRepository;

    public EnrollmentServiceImpl(
            EnrollmentRepository enrollmentRepository,
            CompletedSubtopicRepository completedSubtopicRepository,
            CourseRepository courseRepository,
            SubtopicRepository subtopicRepository
    ) {
        this.enrollmentRepository = enrollmentRepository;
        this.completedSubtopicRepository = completedSubtopicRepository;
        this.courseRepository = courseRepository;
        this.subtopicRepository = subtopicRepository;
    }

    // ---------------- ENROLL ----------------

    @Override
    @Transactional
    public Enrollment enroll(User user, String courseId) {

        if (enrollmentRepository.findByUserIdAndCourseId(user.getId(), courseId).isPresent()) {
            throw new IllegalStateException("Already enrolled");
        }

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new NoSuchElementException("Course not found"));

        Enrollment enrollment = new Enrollment();
        enrollment.setUser(user);
        enrollment.setCourse(course);
        enrollment.setEnrolledAt(Instant.now());

        return enrollmentRepository.save(enrollment);
    }

    // ---------------- MARK SUBTOPIC COMPLETE ----------------

    @Override
    @Transactional
    public CompletedSubtopic markSubtopicCompleted(User user, String subtopicId) {

        Subtopic subtopic = subtopicRepository.findById(subtopicId)
                .orElseThrow(() -> new IllegalArgumentException("Subtopic not found"));

        String courseId = subtopic.getTopic().getCourse().getId();

        Enrollment enrollment = enrollmentRepository
                .findByUserIdAndCourseId(user.getId(), courseId)
                .orElseThrow(() -> new IllegalStateException("User not enrolled"));

        return completedSubtopicRepository
                .findByEnrollmentIdAndSubtopicId(enrollment.getId(), subtopicId)
                .orElseGet(() -> {
                    CompletedSubtopic cs = new CompletedSubtopic();
                    cs.setEnrollment(enrollment);
                    cs.setSubtopic(subtopic);
                    cs.setCompletedAt(Instant.now());
                    return completedSubtopicRepository.save(cs);
                });
    }

    // ---------------- GET PROGRESS ----------------

    @Override
    @Transactional(readOnly = true)
    public Map<String, Object> getProgressForUser(Long enrollmentId, Long userId) {

        Enrollment enrollment = enrollmentRepository
                .findByIdAndUserId(enrollmentId, userId)
                .orElseThrow(() -> new IllegalStateException("Access denied"));

        long total = enrollment.getCourse().getTopics().stream()
                .flatMap(t -> t.getSubtopics().stream())
                .count();

        List<CompletedSubtopic> completedSubtopics =
                completedSubtopicRepository.findAllByEnrollmentId(enrollmentId);

        long completed = completedSubtopics.size();

        List<Map<String, Object>> completedItems = completedSubtopics.stream()
                .map(c -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("subtopicId", c.getSubtopic().getId());
                    map.put("subtopicTitle", c.getSubtopic().getTitle());
                    map.put("completedAt", c.getCompletedAt());
                    return map;
                })
                .toList();

        double percent = total == 0
                ? 0
                : Math.round((completed * 10000.0) / total) / 100.0;

        return Map.of(
                "enrollmentId", enrollment.getId(),
                "courseId", enrollment.getCourse().getId(),
                "courseTitle", enrollment.getCourse().getTitle(),
                "totalSubtopics", total,
                "completedSubtopics", completed,
                "completionPercentage", percent,
                "completedItems", completedItems
        );
    }
}