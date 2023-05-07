package com.testtask.test_task_rest_api.mapper;


import com.testtask.test_task_rest_api.dao.AuthorDao;
import com.testtask.test_task_rest_api.dto.AuthorDto;
import com.testtask.test_task_rest_api.entity.Author;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import java.util.NoSuchElementException;


@Mapper(uses = BookMapper.class)
public interface AuthorMapper {


    Author toAuthor(AuthorDto authorDto, @Context AuthorDao authorDao);

    AuthorDto toAuthorDto(Author author);


    default Author getAuthor(String fullName, @Context AuthorDao authorDao) {
        String[] nameArray = fullName.split(" ");
        return authorDao.findByFirstnameAndLastname(nameArray[0], nameArray[1]).orElseThrow(
                () -> new NoSuchElementException("There is no author with such full name " + fullName)
        );
    }

}

