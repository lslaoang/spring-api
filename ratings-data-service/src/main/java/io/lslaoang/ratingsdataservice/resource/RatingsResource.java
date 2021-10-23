package io.lslaoang.ratingsdataservice.resource;

import io.lslaoang.ratingsdataservice.model.Rating;
import io.lslaoang.ratingsdataservice.model.UserRating;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/ratingsdata")
public class RatingsResource {

    @RequestMapping("/{movieId}")
    public Rating getRating(@PathVariable("movieId") String movieId){
        return  new Rating(movieId,4);
    }

    @RequestMapping("user/{userId}")
    public UserRating getUserRating(@PathVariable("userId") String userId){
        UserRating userRating = new UserRating();
        Rating userIdRating = null;
        //validation of user
        if(userId != ""){
             userIdRating =  new Rating(userId,3);
        }
        
        //default resource
        List<Rating> ratings = Arrays.asList(
                new Rating("123",4),
                new Rating("456",5),
                userIdRating
        );

        
        userRating.setUserRating(ratings);

        return userRating;
    }
}
