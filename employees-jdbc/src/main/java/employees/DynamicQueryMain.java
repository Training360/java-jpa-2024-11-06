package employees;

import org.postgresql.ds.PGSimpleDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DynamicQueryMain {

    public List<String> findEmployeesById(EmployeeQuery query) {
        // Nem kell StringBuilder sb = new StringBuilder();

        String sql = "select emp_name from employees where 1 = 1";
        Map<Integer, Object> params = new HashMap<>();
        int counter = 0;

        if (query.name() != null) {
            sql += " and emp_name like ?";
            params.put(++counter, query.name() + "%");
        }

        if (query.age() > 0) {
            sql += " and emp_age >= ?";
            params.put(++counter, query.age());
        }

        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setURL("jdbc:postgresql://localhost:5433/employees");
        dataSource.setUser("employees");
        dataSource.setPassword("employees");

        try (Connection conn = dataSource.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql);

        ) {
            // Comment: clean code szempontjából a törzset érdemes lenne metódusba kiszervezni!
            /*if (query.name() != null) {
                // Ez így nem jó, indexeléssel!!!
                preparedStatement.setString(1, query.name());
            }

            if (query.age() > 0) {
                preparedStatement.setInt(2, query.name());
            }*/
            for (Map.Entry<Integer, Object> entry : params.entrySet()) {
                preparedStatement.setObject(entry.getKey(), entry.getValue());
            }

            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    System.out.println(rs.getString("emp_name"));
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException("DB error", ex);
        }
        return List.of();
    }
}
