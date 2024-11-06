package employees;

import org.postgresql.ds.PGSimpleDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeesSelectMain {

    public static void main(String[] args) {
        // 1. DataSource: hozzáférési adatokat tárolja
        // 2. Connection

        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setURL("jdbc:postgresql://localhost:5433/employees");
        dataSource.setUser("employees");
        dataSource.setPassword("employees");

        //language=sql
        String select = """
                        select emp_name from employees
                        """;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(select);
             ResultSet rs = preparedStatement.executeQuery()
        ) {
            while (rs.next()) {
                System.out.println(rs.getString("emp_name"));
            }
        } catch (SQLException ex) {
            throw new RuntimeException("DB error", ex);
        }

    }
}
