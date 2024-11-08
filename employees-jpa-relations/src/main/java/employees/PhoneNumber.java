package employees;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
public class PhoneNumber {

    @Id
    @GeneratedValue(generator = "seq_phone_number")
    @SequenceGenerator(name = "seq_phone_number", sequenceName = "seq_phone_number")
    private Long id;

    private String type;

    private String number;

    // Kihez tartozik
    // Owner side
    // Ő A FELELŐS A KAPCSOLAT KEZELÉSÉÉRT!!
    @ManyToOne
    private Employee employee;

    public PhoneNumber(String type, String number) {
        this.type = type;
        this.number = number;
    }
}
