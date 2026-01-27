package com.example.demo.progress;


import com.example.demo.course.Subtopic;
import com.example.demo.user.User;
import jakarta.persistence.*;
import java.time.Instant;

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

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "subtopic_id")
    private Subtopic subtopic;

    @Column(name = "completed_at", nullable = false)
    private Instant completedAt;

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Subtopic getSubtopic() {
        return subtopic;
    }

    public Instant getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(Instant completedAt) {
        this.completedAt = completedAt;
    }
}