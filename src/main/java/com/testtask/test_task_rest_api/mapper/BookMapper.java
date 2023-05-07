package com.testtask.test_task_rest_api.mapper;


import com.testtask.test_task_rest_api.dao.AuthorDao;
import com.testtask.test_task_rest_api.dto.BookDto;
import com.testtask.test_task_rest_api.entity.Author;
import com.testtask.test_task_rest_api.entity.Book;
import org.mapstruct.*;
import java.util.NoSuchElementException;


@Mapper
public interface BookMapper {

    @Mapping(target = "author", ignore = true)
    Book toBook(BookDto bookDto, @Context AuthorDao authorDao);

    BookDto toBookDto(Book book);

    default String getAuthor(Author author) {
        return author.getFirstname() + " " + author.getLastname();
    }


    @AfterMapping
    default void bookComplete(@MappingTarget Book target, BookDto source, @Context AuthorDao authorDao) {
        String[] nameArray = source.getAuthor().split(" ");
        Author author = authorDao.findByFirstnameAndLastname(nameArray[0], nameArray[1]).orElseThrow(
                () -> new NoSuchElementException("There is no author with such full name " + source.getAuthor())
        );
        target.setAuthor(author);
    }


}

