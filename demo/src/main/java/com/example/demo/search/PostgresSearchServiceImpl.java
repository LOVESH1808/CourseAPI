package com.example.demo.search;


import com.example.demo.course.Course;
import com.example.demo.course.Subtopic;
import com.example.demo.course.Topic;
import com.example.demo.search.dto.MatchDTO;
import com.example.demo.search.dto.SearchResultDTO;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class PostgresSearchServiceImpl implements SearchService {

    private final SearchRepository searchRepository;

    public PostgresSearchServiceImpl(SearchRepository searchRepository) {
        this.searchRepository = searchRepository;
    }
    @Override
    public List<SearchResultDTO> search(String query) {
        List<SearchResultDTO> results = new ArrayList<>();
        String lowerQuery = query.toLowerCase();

        List<Course> courses = searchRepository.findAll(); // fetch all courses

        for (Course course : courses) {
            List<SearchResultDTO.MatchDTO> matches = new ArrayList<>();

            // Match course title
            if (course.getTitle() != null && course.getTitle().toLowerCase().contains(lowerQuery)) {
                matches.add(new SearchResultDTO.MatchDTO(
                        "course", null, null, course.getTitle(), course.getTitle()));
            }

            // Match course description
            if (course.getDescription() != null && course.getDescription().toLowerCase().contains(lowerQuery)) {
                matches.add(new SearchResultDTO.MatchDTO(
                        "content", null, null, null, course.getDescription()));
            }

            // Match topics and subtopics
            if (course.getTopics() != null) {
                for (Topic topic : course.getTopics()) {
                    if (topic.getTitle() != null && topic.getTitle().toLowerCase().contains(lowerQuery)) {
                        matches.add(new SearchResultDTO.MatchDTO(
                                "topic", topic.getTitle(), null, null, topic.getTitle()));
                    }

                    if (topic.getSubtopics() != null) {
                        for (Subtopic sub : topic.getSubtopics()) {
                            if ((sub.getTitle() != null && sub.getTitle().toLowerCase().contains(lowerQuery)) ||
                                    (sub.getContent() != null && sub.getContent().toLowerCase().contains(lowerQuery))) {
                                matches.add(new SearchResultDTO.MatchDTO(
                                        "subtopic",
                                        topic.getTitle(),
                                        sub.getId(),
                                        sub.getTitle(),
                                        sub.getContent()
                                ));
                            }
                        }
                    }
                }
            }

            if (!matches.isEmpty()) {
                results.add(new SearchResultDTO(course.getId(), course.getTitle(), matches));
            }
        }

        return results;
    }
}
