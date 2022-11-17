package com.example.microservice1.api;

import com.example.microservice1.domain.GuideItem;
import com.example.microservice1.domain.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/book-guide")
public class BookGuideController {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/{userId}")
    public List<GuideItem> getGuide(@PathVariable String userId) {
        UserRating userRating = restTemplate.getForObject("http://ms3:8083/rating/user/" + userId, UserRating.class);
        return userRating.getRatingList().stream()
                .map(rating -> new GuideItem(rating.getRating(), "test", "desc"))
                .collect(Collectors.toList());
    }
}
