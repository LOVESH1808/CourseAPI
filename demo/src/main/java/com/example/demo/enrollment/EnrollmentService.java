package com.example.demo.enrollment;


import com.example.demo.course.Subtopic;
import com.example.demo.user.User;

import java.util.List;
import java.util.Map;

public interface EnrollmentService {

    /**
     * Enroll a user in a course.
     * @param user the authenticated user
     * @param courseId the course ID
     * @return the enrollment entity
     */
    Enrollment enroll(User user, String courseId);

    /**
     * Mark a subtopic as completed for a user.
     * @param user the authenticated user
     * @param subtopicId the subtopic ID
     * @return the completed subtopic entity
     */
    CompletedSubtopic markSubtopicCompleted(User user, String subtopicId);

    /**
     * Get progress for a specific enrollment.
     * @param enrollmentId the enrollment ID
     * @return a map containing course progress details
     */
    Map<String, Object> getProgressForUser(Long enrollmentId, Long userId);
}