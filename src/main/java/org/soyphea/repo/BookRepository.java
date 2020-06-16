package org.soyphea.repo;

import org.soyphea.domain.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface BookRepository extends CrudRepository<Book,Integer> {

    Optional<Book> findBookBySnb(String snb);

}
