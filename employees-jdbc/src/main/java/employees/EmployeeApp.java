package employees;

import org.flywaydb.core.Flyway;
import org.postgresql.ds.PGSimpleDataSource;

import java.util.List;

public class EmployeeApp {

    public static void main(String[] args) {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setURL("jdbc:postgresql://localhost:5433/employees");
        dataSource.setUser("employees");
        dataSource.setPassword("employees");

        Flyway flyway = Flyway.configure().dataSource(dataSource).load();
        flyway.migrate();

        EmployeeDao employeeDao = new EmployeeDao(dataSource);
        Employee employee = employeeDao.save(new Employee(null, "John Doe"));
        System.out.println(employee);

        List<Employee> employees = employeeDao.findAll();
        System.out.println(employees);

        employeeDao.saveAll(List.of(
                new Employee(null, "John Doe"),
                new Employee(null, "Jack Doe"),
                new Employee(null, "Jane Doe"),
                new Employee(null, "Joe Doe"),
                new Employee(null, "Steve Doe")
                ));
    }
}
