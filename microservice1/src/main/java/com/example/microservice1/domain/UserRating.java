package com.example.microservice1.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRating {

    private String userId;
    private List<Rating> ratingList;
    public void initData(String userId) {
        this.setUserId(userId);
        this.setRatingList(Arrays.asList(
                new Rating("100", 3),
                new Rating("200", 4)
        ));
    }

}
