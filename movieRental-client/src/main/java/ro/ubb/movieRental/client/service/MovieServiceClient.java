package ro.ubb.movieRental.client.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ro.ubb.movieRental.web.dto.MovieDto;
import ro.ubb.movieRental.web.dto.MoviesDto;

@Service
public class MovieServiceClient {
    public static final String URL = "http://localhost:8080/api/movies";

    @Autowired
    RestTemplate restTemplate;

    public MoviesDto getAllMovies(){
        return restTemplate.getForObject(URL,MoviesDto.class);
    }

    public MovieDto saveMovie(MovieDto movieDto){
        return restTemplate.postForObject(
          URL,
          movieDto,
          MovieDto.class
        );
    }

    public void updateMovie(Long id,MovieDto movieDto){
        restTemplate.put(URL+"/{id}",movieDto,id);
    }

    public void deleteById(Long id){
        restTemplate.delete(URL+"/{id}",id);
    }


}
