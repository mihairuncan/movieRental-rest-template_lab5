package ro.ubb.movieRental.web.converter;

import org.springframework.stereotype.Component;
import ro.ubb.movieRental.core.model.Rent;
import ro.ubb.movieRental.web.dto.RentDto;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@Component
public class RentConverter extends BaseConverter<Rent, RentDto> {
    @Override
    public Rent convertDtoToModel(RentDto dto) {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        try {
            Rent rent = Rent.builder()
                    .clientId(dto.getClientId())
                    .movieId(dto.getMovieId())
                    .pickUpDate(format.parse(dto.getPickUpDate()))
                    .build();
            rent.setId(dto.getId());
            return rent;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public RentDto convertModelToDto(Rent rent) {
        RentDto dto = RentDto.builder()
                .clientId(rent.getClientId())
                .movieId(rent.getMovieId())
                .pickUpDate(rent.getPickUpDate().toString())
                .build();
        dto.setId(rent.getId());
        return dto;
    }
}
