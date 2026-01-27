package com.example.demo.course;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubTopicRepository extends JpaRepository<Subtopic, String> {

    List<Subtopic> findByTopicId(String topicId);
}