package ro.ubb.movieRental.core.model;

import lombok.*;

import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
@Builder
public class Rent extends BaseEntity<Long> implements Serializable {
    private long movieId;
    private long clientId;
    private Date pickUpDate;
}
