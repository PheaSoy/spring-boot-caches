package org.soyphea.service;

import lombok.extern.slf4j.Slf4j;
import org.soyphea.domain.Book;
import org.soyphea.exception.BookNotFoundException;
import org.soyphea.repo.BookRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Slf4j
public class BookService {

    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Transactional(readOnly = true,timeout = 20)
    @Cacheable("books")
    public Book findBookBySNB(String snb){
        log.info("Select book with snb:{}",snb);
        slow();
        return bookRepository.findBookBySnb(snb).orElseThrow(() -> new BookNotFoundException());
    }
    public void slow(){
        try{
            Thread.sleep(2000);
        }catch (Exception ex){}
        log.info("finished slow");
    }

}
