package com.example.demo.search;


import com.example.demo.search.dto.SearchResultDTO;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/search")
public class SearchController {

    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping
    public Map<String, Object> search(@RequestParam("q") String query) {
        List<SearchResultDTO> results = searchService.search(query);

        return Map.of(
                "query", query,
                "results", results
        );
    }
}