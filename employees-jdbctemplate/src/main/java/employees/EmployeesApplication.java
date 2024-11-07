package employees;

import org.postgresql.ds.PGSimpleDataSource;

import java.util.List;

public class EmployeesApplication {

    public static void main(String[] args) {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setURL("jdbc:postgresql://localhost:5433/employees");
        dataSource.setUser("employees");
        dataSource.setPassword("employees");

        EmployeesDao employeesDao = new EmployeesDao(dataSource);
        Employee employee = employeesDao.save(new Employee("John Doe Spring JdbcTemplate"));
        System.out.println(employee);

        List<Employee> employees = employeesDao.findAll();
        System.out.println(employees);

        Employee employee2 = employeesDao.findById(employee.id());
        System.out.println(employee2);
    }
}
