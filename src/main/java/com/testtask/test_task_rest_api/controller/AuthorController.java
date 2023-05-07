package com.testtask.test_task_rest_api.controller;

import com.testtask.test_task_rest_api.dto.AuthorDto;
import com.testtask.test_task_rest_api.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/author")
public class AuthorController {

    private final AuthorService authorService;


    @GetMapping
    public List<AuthorDto> getAuthorList() {
        return authorService.findAll();
    }

    @GetMapping("/{authorId}")
    public ResponseEntity<?> getAuthor(@PathVariable("authorId") Long id) {
        AuthorDto authorDto;
        if (id != null) {
            authorDto = authorService.findById(id);
            if (authorDto != null) {
                return new ResponseEntity<>(authorDto, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> handlePost(@Validated @RequestBody AuthorDto authorDto) {
        AuthorDto savedAuthor = authorService.save(authorDto);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(URI.create("/api/v1/author/" + savedAuthor.getId()));
        return new ResponseEntity<>(httpHeaders, HttpStatus.CREATED);
    }

    @PutMapping("/{authorId}")
    public ResponseEntity<?> handleUpdate(@PathVariable("authorId") Long id, @Validated @RequestBody AuthorDto authorDto) {
        authorDto.setId(id);
        authorService.save(authorDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{authorId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("authorId") Long id) {
        authorService.deleteById(id);
    }

}
