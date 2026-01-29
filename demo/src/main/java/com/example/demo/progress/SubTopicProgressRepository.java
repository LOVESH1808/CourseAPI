package com.example.demo.progress;

import com.example.demo.course.Course;
import com.example.demo.course.Subtopic;
import com.example.demo.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SubTopicProgressRepository
        extends JpaRepository<SubTopicProgress, Long> {

    Optional<SubTopicProgress> findByUserAndSubtopic(
            User user,
            Subtopic subtopic);

    List<SubTopicProgress> findAllByUserAndCourse(
            User user,
            Course course);
}