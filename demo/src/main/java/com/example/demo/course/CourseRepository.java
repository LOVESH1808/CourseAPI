package com.example.demo.course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, String> {
    @Query("""
    select distinct c from Course c
    left join fetch c.topics t
    left join fetch t.subtopics
    where c.id = :courseId
""")
    Optional<Course> findByIdWithTopicsAndSubtopics(String courseId);

    @Query("select count(t) from Topic t where t.course.id = :courseId")
    long countTopics(String courseId);

    @Query("select count(s) from Subtopic s where s.topic.course.id = :courseId")
    long countSubtopics(String courseId);

    @Query("""
        select c from Course c
        left join fetch c.topics t
        left join fetch t.subtopics s
        where lower(c.title) like lower(concat('%', :query, '%'))
           or lower(c.description) like lower(concat('%', :query, '%'))
           or lower(t.title) like lower(concat('%', :query, '%'))
           or lower(s.title) like lower(concat('%', :query, '%'))
           or lower(s.content) like lower(concat('%', :query, '%'))
        """)
    List<Course> searchCourses(@Param("query") String query);
}
