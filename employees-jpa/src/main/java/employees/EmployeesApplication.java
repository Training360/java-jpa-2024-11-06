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
            employeesDao.save(employee);

            System.out.println(employee.getId());

            employee = employeesDao.findById(employee.getId());

            System.out.println(employee.getId());

//            employeesDao.delete(employee.getId());

            // JPQL
            List<Employee> employees = employeesDao.findAll();
            employees.stream().map(Employee::getId).forEach(System.out::println);
        }

    }
}
