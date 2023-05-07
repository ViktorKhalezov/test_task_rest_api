package com.testtask.test_task_rest_api.controller;

import com.testtask.test_task_rest_api.dto.BookDto;
import com.testtask.test_task_rest_api.service.BookService;
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
@RequestMapping("/api/v1/book")
public class BookController {

    private final BookService bookService;


    @GetMapping
    public List<BookDto> getBookList() {
        return bookService.findAll();
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<?> getBook(@PathVariable("bookId") Long id) {
        BookDto bookDto;
        if (id != null) {
            bookDto = bookService.findById(id);
            if (bookDto != null) {
                return new ResponseEntity<>(bookDto, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> handlePost(@Validated @RequestBody BookDto bookDto) {
        BookDto savedBook = bookService.save(bookDto);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(URI.create("/api/v1/book/" + savedBook.getId()));
        return new ResponseEntity<>(httpHeaders, HttpStatus.CREATED);
    }

    @PutMapping("/{bookId}")
    public ResponseEntity<?> handleUpdate(@PathVariable("bookId") Long id, @Validated @RequestBody BookDto bookDto) {
        bookDto.setId(id);
        bookService.save(bookDto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{bookId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("bookId") Long id) {
        bookService.deleteById(id);
    }


}
