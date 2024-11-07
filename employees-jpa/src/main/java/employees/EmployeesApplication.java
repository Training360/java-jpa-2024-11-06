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

            employeesDao.save(new Employee("John Doe"));

            // JPQL
            List<Employee> employees = employeesDao.findAll();
            employees.stream().map(Employee::getName).forEach(System.out::println);
        }

    }
}
