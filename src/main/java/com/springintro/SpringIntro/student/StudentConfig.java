package com.springintro.SpringIntro.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
            Student onke = new Student(
                    "Onke",
                    "onke.fanti@gmail.com",
                    LocalDate.of(1918, Month.AUGUST,11));
            Student steve = new Student(
                    "Steve",
                    "stev.steve@gmail.com",
                    LocalDate.of(2001, Month.JANUARY,22));

            repository.saveAll(
                    List.of(onke, steve)
            );
        };

    }
}
