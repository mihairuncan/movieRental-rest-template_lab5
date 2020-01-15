package ro.ubb.movieRental.core.model.validators;


import ro.ubb.movieRental.core.model.Client;
import ro.ubb.movieRental.core.model.exceptions.ValidatorException;

import java.text.SimpleDateFormat;

public class ClientValidator implements Validator<Client> {
    @Override
    public void validate(Client client) throws ValidatorException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

        try {
            Long.parseLong(client.getCnp());
        } catch (NumberFormatException ne) {
            throw new ValidatorException("Invalid CNP!!!");
        }

        if (client.getCnp().length() != 13) {
            throw new ValidatorException("CNP must have 13 characters!!!");
        }
    }
}
