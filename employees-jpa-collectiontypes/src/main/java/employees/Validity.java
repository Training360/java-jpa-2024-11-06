package employees;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.time.LocalDate;

@Embeddable
public record Validity(@Column(name = "validity_start") LocalDate start, @Column(name = "validity_end") LocalDate end) {

    public int daysBetween() {
        return start.getDayOfYear() - end.getDayOfYear();
    }
}
