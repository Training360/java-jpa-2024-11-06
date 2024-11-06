package employees;

import org.postgresql.ds.PGPoolingDataSource;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EmployeesInsertMain {

    public static void main(String[] args) {
        // 1. DataSource: hozzáférési adatokat tárolja
        // 2. Connection

        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setURL("jdbc:postgresql://localhost:5433/employees");
        dataSource.setUser("employees");
        dataSource.setPassword("employees");

        try (Connection conn = dataSource.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(
                     "insert into employees values (nextval('seq_employees'), 'John Doe')");
        ) {
            System.out.println("Name: " + conn.getClass().getName());
            int count = preparedStatement.executeUpdate();
            System.out.println("Count: " + count);
        } catch (SQLException ex) {
            throw new RuntimeException("DB error", ex);
        }

    }
}
