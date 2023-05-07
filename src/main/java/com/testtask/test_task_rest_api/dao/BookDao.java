package com.testtask.test_task_rest_api.dao;


import com.testtask.test_task_rest_api.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookDao extends JpaRepository<Book, Long> {
}
