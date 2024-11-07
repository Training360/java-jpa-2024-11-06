package employees;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class EmployeesApplication {

    public static void main(String[] args) {
        try (
                EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pu");
                EntityManager em = entityManagerFactory.createEntityManager()) {
            // JPQL
            List<Employee> employees = em.createQuery("select e from Employee e", Employee.class).getResultList();
            employees.stream().map(Employee::getName).forEach(System.out::println);
        }

    }
}
