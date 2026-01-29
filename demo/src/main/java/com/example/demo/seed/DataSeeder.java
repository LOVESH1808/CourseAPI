package com.example.demo.seed;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSeeder {

    private final CourseDataSeeder courseDataSeeder;

    public DataSeeder(CourseDataSeeder courseDataSeeder) {
        this.courseDataSeeder = courseDataSeeder;
    }

    @Bean
    CommandLineRunner seedCourses() {
        return args -> courseDataSeeder.seed();
    }
}