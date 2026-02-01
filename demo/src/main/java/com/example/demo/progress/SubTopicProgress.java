package com.example.demo.progress;

import com.example.demo.course.Subtopic;
import com.example.demo.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Getter
@Setter
@Table(
        name = "subtopic_progress",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"user_id", "subtopic_id"})
        }
)
public class SubTopicProgress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "subtopic_id")
    private Subtopic subtopic;

    private boolean completed;

    @Column(name = "completed_at")
    private Instant completedAt;
}
