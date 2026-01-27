package com.example.demo.course;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, String> {

    List<Topic> findByCourseId(String courseId);
}