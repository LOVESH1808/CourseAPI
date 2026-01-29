package com.example.demo.enrollment;

import com.example.demo.course.Course;
import com.example.demo.course.CourseRepository;
import com.example.demo.enrollment.dto.EnrollmentResponseDTO;
import com.example.demo.user.User;
import com.example.demo.user.UserRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    public EnrollmentServiceImpl(
            EnrollmentRepository enrollmentRepository,
            CourseRepository courseRepository,
            UserRepository userRepository) {

        this.enrollmentRepository = enrollmentRepository;
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }

    @Override
    public EnrollmentResponseDTO enroll(
            String userEmail,
            String courseId) {

        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() ->
                        new RuntimeException("Course not found"));

        if (enrollmentRepository.existsByUserIdAndCourseId(
                user.getId(), course.getId())) {
            throw new RuntimeException("Already enrolled");
        }

        Enrollment enrollment = new Enrollment();
        enrollment.setUser(user);
        enrollment.setCourse(course);
        enrollment.setEnrolledAt(Instant.now());

        enrollmentRepository.save(enrollment);

        return EnrollmentResponseDTO.from(enrollment);
    }
}