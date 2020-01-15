package ro.ubb.movieRental.core.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.movieRental.core.model.Rent;
import ro.ubb.movieRental.core.model.exceptions.ValidatorException;
import ro.ubb.movieRental.core.repository.ClientRepository;
import ro.ubb.movieRental.core.repository.MovieRepository;
import ro.ubb.movieRental.core.repository.RentRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class RentServiceImpl implements RentService {
    public static final Logger LOGGER = LoggerFactory.getLogger(RentServiceImpl.class);

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    RentRepository rentRepository;


    /**
     * Find the entity with the given {@code id}.
     *
     * @param id must not be null
     * @return a Rent with the given id
     */
    @Override
    public Rent findOne(Long id) {
        LOGGER.trace("findOne --- method entered");

        Rent rent = rentRepository.findById(id).get();

        LOGGER.trace("findOne --- method exit");

        return rent;
    }

    /**
     * @return all Rents
     */
    @Override
    public Set<Rent> getAllRents() {
        LOGGER.trace("getAllClients --- method entered");

        Iterable<Rent> rents = rentRepository.findAll();

        LOGGER.trace("getAllClients: result={}", rents);

        return StreamSupport.stream(rents.spliterator(), false).collect(Collectors.toSet());
    }

    /**
     * Saves the given Rent
     *
     * @param rent must not be null
     * @return null if the Rent was saved otherwise (e.g. id already exists) returns the Rent.
     */
    @Override
    public Rent save(Rent rent) {
        LOGGER.trace("save --- method entered");

        try{
            rentValidator.validate(rent);
        } catch (ValidatorException ve){
            System.out.println(ve.getMessage());
        }

        LOGGER.trace("save --- rent is valid");

        return (movieRepository.findById(rent.getMovieId()).isPresent() &&
                clientRepository.findById(rent.getClientId()).isPresent()) ?
                rentRepository.save(rent) : rent;
    }

    /**
     * @param rent must not be null
     * @return null if the Rent was updated otherwise (e.g. id does not exist) returns the Rent.
     */
    @Override
    @Transactional
    public Rent update(Long id, Rent rent) {
        LOGGER.trace("update --- method entered");

        try {
            rentValidator.validate(rent);
        } catch (ValidatorException ve){
            System.out.println(ve.getMessage());
        }

        LOGGER.trace("update --- rent is valid");

        Rent update = rentRepository.findById(id).orElseThrow();
        update.setClientId(rent.getClientId());
        update.setMovieId(rent.getMovieId());
        update.setPickUpDate(rent.getPickUpDate());

        LOGGER.trace("update: result{}", update);

        return update;
    }

    /**
     * Deletes the rent with given id
     *
     * @param id must not be null
     * @return null if there is no Rent with the given id, otherwise the removed Rent.
     */
    @Override
    public void delete(Long id) {
        LOGGER.trace("delete --- method entered");

        rentRepository.deleteById(id);

        LOGGER.trace("delete --- method exit");
    }

}
