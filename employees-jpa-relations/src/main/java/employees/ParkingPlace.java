package employees;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
public class ParkingPlace {

    @Id
    @GeneratedValue(generator = "seq_parking_place")
    @SequenceGenerator(name = "seq_parking_place", sequenceName = "seq_parking_place")
    private Long id;

    private int position;

    public ParkingPlace(int position) {
        this.position = position;
    }
}
