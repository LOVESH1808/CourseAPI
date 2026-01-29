package com.example.demo.course;


import com.example.demo.course.dto.CourseDetailDTO;
import com.example.demo.course.dto.CourseSummaryDTO;
import com.example.demo.course.dto.SubTopicDTO;
import com.example.demo.course.dto.TopicDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public Optional<CourseDetailDTO> getCourseById(String courseId) {
        Course course = courseRepository
                .findByIdWithTopicsAndSubtopics(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        CourseDetailDTO dto = new CourseDetailDTO(
                course.getId(),
                course.getTitle(),
                course.getDescription(),
                course.getTopics().stream()
                        .map(topic -> new TopicDTO(
                                topic.getId(),
                                topic.getTitle(),
                                topic.getSubtopics().stream()
                                        .map(sub -> new SubTopicDTO(
                                                sub.getId(),
                                                sub.getTitle(),
                                                sub.getContent()
                                        ))
                                        .toList()
                        ))
                        .toList()
        );

        return Optional.of(dto);
    }

    @Override
    public List<CourseSummaryDTO> getAllCourses() {

        List<Course> courses = courseRepository.findAll();
        List<CourseSummaryDTO> result = new java.util.ArrayList<>();

        for (Course course : courses) {

            long topicCount =
                    courseRepository.countTopics(course.getId());

            long subtopicCount =
                    courseRepository.countSubtopics(course.getId());

            CourseSummaryDTO dto = new CourseSummaryDTO(
                    course.getId(),
                    course.getTitle(),
                    course.getDescription(),
                    (int)(topicCount),
                    (int) subtopicCount
            );

            result.add(dto);
        }

        return result;
    }
}