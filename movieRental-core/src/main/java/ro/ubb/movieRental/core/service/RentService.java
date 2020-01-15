package ro.ubb.movieRental.core.service;


import ro.ubb.movieRental.core.model.Rent;
import ro.ubb.movieRental.core.model.validators.RentValidator;

import java.util.List;
import java.util.Set;

public interface RentService {
    RentValidator rentValidator = new RentValidator();

    Rent findOne(Long id);

    Set<Rent> getAllRents();

    Rent save(Rent rent);

    Rent update(Long id, Rent rent);

    void delete(Long id);

}

