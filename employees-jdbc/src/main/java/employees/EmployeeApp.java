package employees;

import org.postgresql.ds.PGSimpleDataSource;

public class EmployeeApp {

    public static void main(String[] args) {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setURL("jdbc:postgresql://localhost:5433/employees");
        dataSource.setUser("employees");
        dataSource.setPassword("employees");

        EmployeeDao employeeDao = new EmployeeDao(dataSource);
        employeeDao.save(new Employee(null, "John Doe"));
    }
}
