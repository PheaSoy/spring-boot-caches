package org.soyphea;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.soyphea.domain.Book;
import org.soyphea.repo.BookRepository;
import org.soyphea.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.Duration;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Optional.*;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookServiceTesting {

    @Autowired
    private BookService bookService;

    @MockBean
    private BookRepository bookRepository;

    @BeforeEach
    @Timeout(2)
    public void setUp(){
        bookRepository.saveAll(
                Stream.of(new Book(1,"Cloud-Native Java","snb-1","Jose and Kinney")
                        ,new Book(2,"Building MicroServices","snb-2","Sam Newman"))
                        .collect(Collectors.toList())
        );
    }
    @Test
    @DisplayName("Test search book with cache - first time should be should be > 2s and second time < 10ms")
    void test_failed() {
        when(bookRepository.findBookBySnb(any())).thenReturn(of(new Book(2,"Building MicroServices","snb-2","Sam Newman")));
        Assertions.assertTimeout(Duration.ofSeconds(3),() ->bookService.findBookBySNB("snb-2"));
        Assertions.assertTimeout(Duration.ofMillis(10),() -> bookService.findBookBySNB("snb-2"));

    }


}
