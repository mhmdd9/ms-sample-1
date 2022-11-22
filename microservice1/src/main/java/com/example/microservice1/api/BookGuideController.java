package com.example.microservice1.api;

import com.example.microservice1.domain.GuideItem;
import com.example.microservice1.domain.UserRating;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/book-guide")
public class BookGuideController {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/{userId}")
    public List<GuideItem> getGuide(@PathVariable String userId) {
        UserRating userRating = restTemplate.getForObject("http://ms3/rating/user/" + userId, UserRating.class);
        return userRating.getRatingList().stream()
                .map(rating -> new GuideItem(rating.getRating(), "test", "desc"))
                .collect(Collectors.toList());
    }

    @GetMapping("/test")
    @ResponseStatus(HttpStatus.OK)
    @CircuitBreaker(name = "ms3", fallbackMethod = "fallbackMethod")
    @TimeLimiter(name = "ms3")
    @Retry(name = "ms3")
    public CompletableFuture<String> getGuide() {
        return CompletableFuture.supplyAsync(() -> restTemplate.getForObject("http://ms3/rating/user/foo", UserRating.class).getUserId());
    }

    public CompletableFuture<String> fallbackMethod(RuntimeException exception) {
        return CompletableFuture.supplyAsync(() -> "Something went wrong!");
    }
}
