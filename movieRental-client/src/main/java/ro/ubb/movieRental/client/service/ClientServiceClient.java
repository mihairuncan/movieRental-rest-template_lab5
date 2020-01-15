package ro.ubb.movieRental.client.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ro.ubb.movieRental.web.dto.ClientDto;
import ro.ubb.movieRental.web.dto.ClientsDto;

@Service
public class ClientServiceClient {
    public static final String URL = "http://localhost:8080/api/clients";

    @Autowired
    private RestTemplate restTemplate;

    public ClientsDto getAllClients(){
        return restTemplate.getForObject(URL,ClientsDto.class);
    }

    public ClientDto saveClient(ClientDto clientDto){
        return restTemplate.postForObject(
                URL,
                clientDto,
                ClientDto.class
        );
    }

    public void updateClient(Long id,ClientDto clientDto){
        restTemplate.put(URL+"/{id}",clientDto,id);
    }

    public void deleteById(Long id){
        restTemplate.delete(URL+"/{id}",id);
    }
}
