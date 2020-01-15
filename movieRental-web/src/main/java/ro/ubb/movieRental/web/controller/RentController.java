package ro.ubb.movieRental.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.movieRental.core.model.Rent;
import ro.ubb.movieRental.core.service.RentService;
import ro.ubb.movieRental.web.converter.RentConverter;
import ro.ubb.movieRental.web.dto.RentDto;
import ro.ubb.movieRental.web.dto.RentsDto;

import java.util.Set;

@RestController
public class RentController {
    public static final Logger LOGGER = LoggerFactory.getLogger(RentController.class);

    @Autowired
    private RentService rentService;

    @Autowired
    private RentConverter rentConverter;

    @RequestMapping(value = "/rents", method = RequestMethod.GET)
    RentsDto getRents() {
        LOGGER.trace("getRents --- method entered");

        Set<Rent> rents = rentService.getAllRents();
        RentsDto result = new RentsDto(rentConverter.convertModelsToDtos(rents));

        LOGGER.trace("getRents: result{}", result);

        return result;
    }

    @RequestMapping(value = "/rents", method = RequestMethod.POST)
    RentDto saveRent(@RequestBody RentDto rentDto) {
        LOGGER.trace("saveRent --- method entered");

        Rent rentSaved = rentService.save(rentConverter.convertDtoToModel(rentDto));
        RentDto result = rentConverter.convertModelToDto(rentSaved);

        LOGGER.trace("saveRent --- method exit");

        return result;
    }

    @RequestMapping(value = "/rents/{id}", method = RequestMethod.PUT)
    RentDto updateRent(@PathVariable Long id, @RequestBody RentDto rentDto) {
        LOGGER.trace("updateRent --- method entered");

        Rent updatedRent = rentService.update(id, rentConverter.convertDtoToModel(rentDto));
        RentDto result = rentConverter.convertModelToDto(updatedRent);

        LOGGER.trace("updateRent --- method exit");

        return result;
    }

    @RequestMapping(value = "/rents/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteRent(@PathVariable Long id) {
        LOGGER.trace("deleteRent --- method entered");

        rentService.delete(id);

        LOGGER.trace("deleteRent --- method exit");

        return new ResponseEntity<>(HttpStatus.OK);
    }


}
