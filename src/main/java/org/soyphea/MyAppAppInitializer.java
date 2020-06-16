package org.soyphea;

import org.soyphea.domain.Book;
import org.soyphea.repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Configuration
public class MyAppAppInitializer implements CommandLineRunner {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public void run(String... args) throws Exception {
        bookRepository.saveAll(
                Stream.of(new Book(1,"Cloud-Native Java","snb-1","Jose and Kinney")
                        ,new Book(2,"Building MicroServices","snb-2","Sam Newman"))
                        .collect(Collectors.toList())
        );
    }
}
