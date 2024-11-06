package employees;

import lombok.SneakyThrows;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
                        "insert into employees(id, emp_name) values (nextval('seq_employees'), ?)")
            ) {
            stmt.setString(1, employee.name());
            stmt.executeUpdate();
            return null;
        }
    }
}
