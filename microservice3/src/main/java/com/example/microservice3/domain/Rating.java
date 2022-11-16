package com.example.microservice3.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Rating {

    private String bookId;
    private int rating;
}
