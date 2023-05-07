package com.testtask.test_task_rest_api.service;


import com.testtask.test_task_rest_api.dao.AuthorDao;
import com.testtask.test_task_rest_api.dto.AuthorDto;
import com.testtask.test_task_rest_api.mapper.AuthorMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorDao authorDao;

    private final AuthorMapper authorMapper;


    public List<AuthorDto> findAll() {
        return authorDao.findAll().stream().map(authorMapper::toAuthorDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public AuthorDto findById(Long id) {
        return authorMapper.toAuthorDto(authorDao.findById(id).orElse(null));
    }


    @Transactional
    public AuthorDto save(final AuthorDto authorDto) {
        return authorMapper.toAuthorDto(authorDao.save(authorMapper.toAuthor(authorDto, authorDao)));
    }


    public void deleteById(Long id) {
            authorDao.deleteById(id);
    }

}
