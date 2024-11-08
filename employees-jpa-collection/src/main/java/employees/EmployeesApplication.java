package employees;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class EmployeesApplication {

    public static void main(String[] args) {
        try (
                EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pu");
                ) {

            EmployeesDao employeesDao = new EmployeesDao(entityManagerFactory);

            Employee employee = new Employee("John Doe");
            employee.setNicknames(List.of("John", "Johnny", "J", "JD"));
            employeesDao.save(employee);

            employee = employeesDao.findById(employee.getId());
            System.out.println(employee.getNicknames());
        }

    }
}
