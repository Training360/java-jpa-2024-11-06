package employees;

import jakarta.persistence.Embeddable;

import java.time.LocalDate;

@Embeddable
public record VacationEntry(LocalDate start, int daysTaken) {
}
