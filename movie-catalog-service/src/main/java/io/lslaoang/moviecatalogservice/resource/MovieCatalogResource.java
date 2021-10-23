package io.lslaoang.moviecatalogservice.resource;

import io.lslaoang.moviecatalogservice.model.CatalogItem;
import io.lslaoang.moviecatalogservice.model.Movie;
import io.lslaoang.moviecatalogservice.model.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
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
    public List<CatalogItem> getCatalog(@PathVariable("userId")  String userId){



        //Get all ratings
        List<Rating> ratings = Arrays.asList(
                new Rating("123",4),
                new Rating("456",5)
        );

        return ratings.stream().map(rating -> {

            Movie movie = restTemplate.getForObject("http://localhost:8083/movies/"+rating.getMovieId(), Movie.class);

            //Asynchronous approach
            /*
            Movie movie = webClientbuilder.build()
                    .get()
                    .uri("http://localhost:8083/movies/"+rating.getMovieId())
                    .retrieve()
                    .bodyToMono(Movie.class)
                    .block();
             */

            return new CatalogItem(movie.getName(), "desc",rating.getRating());

        })
                .collect(Collectors.toList());

    }
}
