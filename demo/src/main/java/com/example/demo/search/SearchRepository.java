package com.example.demo.search;


import com.example.demo.course.Course;
import com.example.demo.search.dto.SearchResultDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SearchRepository extends JpaRepository<Course, String> {

    @Query("""
        select new com.example.demo.search.dto.SearchResultDTO(
            c.id,
            c.title,
            null
        )
        from Course c
        left join Topic t on t.course = c
        left join Subtopic s on s.topic = t
        where lower(c.title) like lower(concat('%', :query, '%'))
           or lower(c.description) like lower(concat('%', :query, '%'))
           or lower(t.title) like lower(concat('%', :query, '%'))
           or lower(s.title) like lower(concat('%', :query, '%'))
           or lower(s.content) like lower(concat('%', :query, '%'))
        group by c.id
    """)
    List<SearchResultDTO> search(@Param("query") String query);

}