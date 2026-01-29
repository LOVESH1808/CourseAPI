package com.example.demo.seed;

import com.example.demo.course.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public class CourseDataSeeder {

    private final EntityManager entityManager;

    public CourseDataSeeder(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public void seed() throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();

        Long count = entityManager
                .createQuery("select count(c) from Course c", Long.class)
                .getSingleResult();

        if (count > 0) {
            return;
        }

        InputStream is = getClass()
                .getResourceAsStream("/data/courses.json");

        CourseSeedWrapper wrapper =
                objectMapper.readValue(is, CourseSeedWrapper.class);

        for (CourseSeedDTO courseDTO : wrapper.courses()) {

            Course course = new Course();
            course.setId(courseDTO.id());
            course.setTitle(courseDTO.title());
            course.setDescription(courseDTO.description());

            for (TopicSeedDTO topicDTO : courseDTO.topics()) {

                Topic topic = new Topic();
                topic.setId(topicDTO.id());
                topic.setTitle(topicDTO.title());
                topic.setCourse(course);

                for (SubtopicSeedDTO subDTO : topicDTO.subtopics()) {

                    Subtopic subtopic = new Subtopic();
                    subtopic.setId(subDTO.id());
                    subtopic.setTitle(subDTO.title());
                    subtopic.setContent(subDTO.content());
                    subtopic.setTopic(topic);

                    topic.getSubtopics().add(subtopic);
                }

                course.getTopics().add(topic);
            }

            entityManager.persist(course);
        }

        System.out.println("✅ ALL courses, topics, and subtopics seeded");
    }
}