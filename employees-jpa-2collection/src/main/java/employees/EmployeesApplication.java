package employees;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;
import java.util.Set;

public class EmployeesApplication {

    public static void main(String[] args) {
        try (
                EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pu");
                ) {

            EmployeesDao employeesDao = new EmployeesDao(entityManagerFactory);

            for (int i = 0; i < 10; i++) {
                Employee employee = new Employee("John Doe " + i);
                employee.setNicknames(List.of("John", "Johnny", "J", "JD"));
                employee.setPhones(List.of("111", "222", "333", "444"));
                employeesDao.save(employee);
            }

            List<EmployeeDto> employees = employeesDao.findAllWithNicknamesAndPhones();
            employees.forEach(e -> System.out.println(e.nicks() + " " + e.phones()));
        }

    }
}
