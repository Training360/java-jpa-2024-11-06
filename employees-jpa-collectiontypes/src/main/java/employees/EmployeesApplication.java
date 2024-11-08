package employees;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class EmployeesApplication {

    public static void main(String[] args) {
        try (
                EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pu");
                ) {

            EmployeesDao employeesDao = new EmployeesDao(entityManagerFactory);

            Employee employee = new Employee("John Doe");
            employee.setNicknames(List.of("John", "Johnny", "J", "JD"));
            employee.setVacationEntries(List.of(
                    new VacationEntry(LocalDate.now(), 10),
                    new VacationEntry(LocalDate.of(2024, 12, 20), 10)
            ));
            employee.setValidity(
                    new Validity(LocalDate.of(2024, 1, 1),
                            LocalDate.of(2024, 12, 31)));

            employee.setPhones(Map.of("home", "1111",
                    "office", "2222",
                    "mobile", "3333"));

            employeesDao.save(employee);
        }

    }
}
