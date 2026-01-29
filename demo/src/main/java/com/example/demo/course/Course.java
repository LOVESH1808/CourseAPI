package com.example.demo.course;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "courses")
public class Course {

    @Id
    private String id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @OneToMany(
            mappedBy = "course",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Topic> topics = new HashSet<>();

    public int getTotalSubtopicCount() {
        if (topics == null) return 0;
        return topics.stream()
                .mapToInt(topic -> topic.getSubtopics() != null ? topic.getSubtopics().size() : 0)
                .sum();
    }
}