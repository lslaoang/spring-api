package io.lslaoang.moviecatalogservice.resource;

import io.lslaoang.moviecatalogservice.model.CatalogItem;
import io.lslaoang.moviecatalogservice.model.CatalogItemList;
import io.lslaoang.moviecatalogservice.model.Movie;
import io.lslaoang.moviecatalogservice.model.UserRating;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    //rest template will be replaced with webFlux
    private final RestTemplate restTemplate;

    public MovieCatalogResource(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;

    }


    @RequestMapping("/{userId}")
    public CatalogItemList getCatalog(@PathVariable("userId")  String userId){

        //Get all ratings
        UserRating ratings = restTemplate.getForObject("http://ratings-data-service/ratingsdata/user/"+userId, UserRating.class);

        List<CatalogItem> ratingList = ratings.getUserRating().stream().map(rating -> {
            //For each movie ID, call  movie info service and get details
            Movie movie = restTemplate.getForObject("http://movie-info-service/movies/"+rating.getMovieId(), Movie.class);
            //Put them all together
            return new CatalogItem(movie.getName(),"desc",rating.getRating());
        }).collect(Collectors.toList());

        CatalogItemList catalogItemList = new CatalogItemList();
        catalogItemList.setCatalogItemList(ratingList);

        return catalogItemList;
    }
}
//Asynchronous approach
/*
            Movie movie = webClientbuilder.build()
                    .get()
                    .uri("http://localhost:8083/movies/"+rating.getMovieId())
                    .retrieve()
                    .bodyToMono(Movie.class)
                    .block();
 */