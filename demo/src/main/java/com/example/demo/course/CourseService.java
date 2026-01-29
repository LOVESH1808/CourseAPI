package com.example.demo.course;
import com.example.demo.course.dto.CourseDetailDTO;
import com.example.demo.course.dto.CourseSummaryDTO;

import java.util.List;
import java.util.Optional;

public interface CourseService {
    List<CourseSummaryDTO> getAllCourses();
    Optional<CourseDetailDTO> getCourseById(String courseId);
}
