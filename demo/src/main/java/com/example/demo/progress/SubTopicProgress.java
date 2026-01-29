package com.example.demo.progress;


import com.example.demo.course.Course;
import com.example.demo.course.Subtopic;
import com.example.demo.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(
        name = "subtopic_progress",
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"user_id", "subtopic_id"}
        )
)
public class SubTopicProgress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "subtopic_id", nullable = false)
    private Subtopic subtopic;

    @Column(nullable = false)
    private boolean completed;

    @Column(name = "completed_at")
    private Instant completedAt;
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
}