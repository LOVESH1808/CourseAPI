package com.example.demo.enrollment;

import com.example.demo.course.Subtopic;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "completed_subtopics",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"enrollment_id", "subtopic_id"})})
public class CompletedSubtopic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "enrollment_id")
    private Enrollment enrollment;

    @ManyToOne(optional = false)
    @JoinColumn(name = "subtopic_id")
    private Subtopic subtopic;

    private Instant completedAt = Instant.now();
}