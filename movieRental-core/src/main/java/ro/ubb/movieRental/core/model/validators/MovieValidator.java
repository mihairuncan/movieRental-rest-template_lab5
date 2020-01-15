package ro.ubb.movieRental.core.model.validators;


import ro.ubb.movieRental.core.model.Movie;
import ro.ubb.movieRental.core.model.exceptions.ValidatorException;

import java.util.Calendar;

public class MovieValidator implements Validator<Movie> {

    @Override
    public void validate(Movie movie) throws ValidatorException {

        if (movie.getYear() > Calendar.getInstance().get(Calendar.YEAR)) {
            throw new ValidatorException("Year must not be greater than current year!");
        }
    }

}
