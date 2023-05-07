package com.testtask.test_task_rest_api.service;

import com.testtask.test_task_rest_api.dao.AuthorDao;
import com.testtask.test_task_rest_api.dao.BookDao;
import com.testtask.test_task_rest_api.dto.BookDto;
import com.testtask.test_task_rest_api.mapper.BookMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookDao bookDao;

    private final BookMapper bookMapper;

    private final AuthorDao authorDao;

    public List<BookDto> findAll() {
        return bookDao.findAll().stream().map(bookMapper::toBookDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public BookDto findById(Long id) {
        return bookMapper.toBookDto(bookDao.findById(id).orElse(null));
    }


    @Transactional
    public BookDto save(final BookDto bookDto) {
        return bookMapper.toBookDto(bookDao.save(bookMapper.toBook(bookDto, authorDao)));
    }


    public void deleteById(Long id) {
        bookDao.deleteById(id);
    }

}
