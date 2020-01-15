package ro.ubb.movieRental.core.model.validators;


import ro.ubb.movieRental.core.model.Rent;
import ro.ubb.movieRental.core.model.exceptions.ValidatorException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RentValidator implements Validator<Rent> {
    @Override
    public void validate(Rent rent) throws ValidatorException {
        Date pickUpDate = rent.getPickUpDate();

        if (pickUpDate.after(new Date())) {
            throw new ValidatorException("Pick up date can't be after current date!!!");
        }


    }
}
