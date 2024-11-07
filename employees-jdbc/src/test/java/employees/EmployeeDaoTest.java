package employees;

import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.postgresql.ds.PGSimpleDataSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class EmployeeDaoTest {

    EmployeeDao dao;

    @BeforeEach
    void createEmployeeDao() {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setURL("jdbc:postgresql://localhost:5433/employees");
        dataSource.setUser("employees");
        dataSource.setPassword("employees");

        Flyway flyway = Flyway.configure()
                .cleanDisabled(false)
//                .loadDefaultConfigurationFiles()
                .dataSource(dataSource).load();
        flyway.clean();
        flyway.migrate();

        dao = new EmployeeDao(dataSource);
    }

    @Test
    void save() {
        Employee employee = dao.save(new Employee(null, "John Doe"));
        assertEquals("John Doe", employee.name());
        assertNotNull(employee.id());
    }

    @Test
    void findAll() {
        dao.save(new Employee(null, "John Doe"));
        dao.save(new Employee(null, "Jack Doe"));

        List<Employee> employees = dao.findAll();

        assertThat(employees)
                .extracting(Employee::name)
                .containsExactlyInAnyOrder("John Doe", "Jack Doe");
    }
}