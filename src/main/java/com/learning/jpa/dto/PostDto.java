package com.learning.jpa.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class PostDto {

    private String title;
    private String authorName;
    private Integer size;
}
