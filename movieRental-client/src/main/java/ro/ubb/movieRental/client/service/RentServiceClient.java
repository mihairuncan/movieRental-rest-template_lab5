package ro.ubb.movieRental.client.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ro.ubb.movieRental.web.dto.RentDto;
import ro.ubb.movieRental.web.dto.RentsDto;

@Service
public class RentServiceClient {
    public static final String URL = "http://localhost:8080/api/rents";

    @Autowired
    RestTemplate restTemplate;

    public RentsDto getAllRents() {
        return restTemplate.getForObject(URL, RentsDto.class);
    }

    public RentDto saveRent(RentDto rentDto){
        return restTemplate.postForObject(
                URL,
                rentDto,
                RentDto.class
        );
    }

    public void updateRent(Long id, RentDto rentDto){
        restTemplate.put(URL+"/{id}",rentDto,id);
    }

    public void deleteById(Long id){
        restTemplate.delete(URL+"/{id}",id);
    }
}
