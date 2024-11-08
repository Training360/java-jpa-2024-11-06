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

            for (int i = 0; i < 10; i++) {
                Employee employee = new Employee("John Doe " + i);
                employee.setNicknames(List.of("John", "Johnny", "J", "JD"));
                employeesDao.save(employee);
            }

            Employee employee = employeesDao.findByIdWithNicknames(1);
            System.out.println(employee.getName());
            System.out.println(employee.getNicknames());

            List<Employee> employees = employeesDao.findAllWithNicknames();
            System.out.println(employees.size());
            employees.forEach(e -> System.out.println(e.getNicknames()));
        }

    }
}
