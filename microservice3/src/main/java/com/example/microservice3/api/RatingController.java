package com.example.microservice3.api;

import com.example.microservice3.domain.Rating;
import com.example.microservice3.domain.UserRating;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rating")
public class RatingController {

    @GetMapping("/{bookId}")
    public Rating getRating(@PathVariable String bookId) {
        return new Rating(bookId, 4);
    }

    @RequestMapping("/user/{userId}")
    @SneakyThrows //do not use it in prod
    public UserRating getUserRatings(@PathVariable("userId") String userId) {
        Thread.sleep(10000);
        UserRating userRating = new UserRating();
        userRating.initData(userId);
        return userRating;

    }
}
