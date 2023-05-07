package com.testtask.test_task_rest_api.dao;

import com.testtask.test_task_rest_api.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AuthorDao extends JpaRepository<Author, Long> {

    Optional<Author> findByFirstnameAndLastname(String firstname, String lastname);

}
