package com.example.demo.enrollment;

import com.example.demo.course.Subtopic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubtopicRepository extends JpaRepository<Subtopic, String> {
    // Optional: add custom queries here if needed
}