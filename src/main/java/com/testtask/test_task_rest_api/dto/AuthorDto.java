package com.testtask.test_task_rest_api.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDto {

    private Long id;

    @NotBlank
    private String firstname;

    @NotBlank
    private String lastname;

    private String city;

    private String country;

    private Integer age;

    private Set<BookDto> books;

}