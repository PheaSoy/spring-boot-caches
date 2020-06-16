package org.soyphea.controller;

import lombok.extern.slf4j.Slf4j;
import org.soyphea.domain.Book;
import org.soyphea.service.BookService;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class BookController {

    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books/{snb}")
    public Book getBookBySNB(@DefaultValue("snb-2") @PathVariable("snb") String sbn) {
        log.info("SBN:{}",sbn);
        return bookService.findBookBySNB(sbn);
    }
}
