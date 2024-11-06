package employees;

import lombok.SneakyThrows;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao {

    private DataSource dataSource;

    public EmployeeDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @SneakyThrows
    public Employee save(Employee employee) {
        try (
                Connection conn = dataSource.getConnection();
                PreparedStatement stmt = conn.prepareStatement(
                        "insert into employees(id, emp_name) values (nextval('seq_employees'), ?)", new String[]{"id"})
            ) {
            stmt.setString(1, employee.name());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                long id = rs.getLong("id");
                return new Employee(id, employee.name());
            } else {
                throw new IllegalStateException("Could not find generated key");
            }
        }
    }

    @SneakyThrows
    public List<Employee> findAll() {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement("select id, emp_name from employees");
             ResultSet rs = stmt.executeQuery()
        ) {
            List<Employee> employees = new ArrayList<>();
            while (rs.next()) {
                long id = rs.getLong("id");
                String name = rs.getString("emp_name");
                employees.add(new Employee(id, name));
            }
            return employees;
        }
    }

    public Employee update(Employee employee) {
        return null;
    }

    public Employee findById(long id) {
        return null;
    }

    public void deleteById(long id) {

    }
}
