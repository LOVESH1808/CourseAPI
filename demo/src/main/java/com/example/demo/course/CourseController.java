package com.example.demo.course;

import com.example.demo.course.dto.CourseDetailDTO;
import com.example.demo.course.dto.CourseSummaryDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public List<CourseSummaryDTO> getAllCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping("/{courseId}")
    public Optional<CourseDetailDTO> getCourse(@PathVariable String courseId) {
        return courseService.getCourseById(courseId);
    }
}
