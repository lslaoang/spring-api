package io.lslaoang.moviecatalogservice.resource;

import io.lslaoang.moviecatalogservice.model.Catalog;
import io.lslaoang.moviecatalogservice.model.CatalogItem;
import io.lslaoang.moviecatalogservice.model.Movie;
import io.lslaoang.moviecatalogservice.model.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WebClient.Builder webClientbuilder;

    @RequestMapping("/{userId}")
    public Catalog getCatalog(@PathVariable("userId")  String userId){

        //Get all ratings
        UserRating ratings = restTemplate.getForObject("http://localhost:8082/ratingsdata/user/"+userId, UserRating.class);

        List<CatalogItem> ratingList = ratings.getUserRating().stream().map(rating -> {
            //For each movie ID, call  movie info service and get details
            Movie movie = restTemplate.getForObject("http://localhost:8083/movies/"+rating.getMovieId(), Movie.class);
            //Put them all together
            return new CatalogItem(movie.getName(),"desc",rating.getRating());
        }).collect(Collectors.toList());

        Catalog catalog = new Catalog();
        catalog.setCatalogItemList(ratingList);

        return  catalog;
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