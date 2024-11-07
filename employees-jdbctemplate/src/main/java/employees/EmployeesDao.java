package employees;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class EmployeesDao {

    private JdbcTemplate jdbcTemplate;

    public EmployeesDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Employee save(Employee employee) {
//        jdbcTemplate.update("insert into employees(id, emp_name) values (nextval('seq_employees'), ?)",
//                employee.name());
//        return null;
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(conn -> {
            PreparedStatement ps = conn.prepareStatement(
                "insert into employees(id, emp_name) values (nextval('seq_employees'), ?)", new String[]{"id"});
            ps.setString(1, employee.name());
            return ps;
        }, keyHolder);

        return new Employee(keyHolder.getKey().longValue(), employee.name());
    }

    public List<Employee> findAll() {
        return jdbcTemplate.query("select id, emp_name from employees order by emp_name",
                EmployeesDao::mapRow
                );
    }

    public Employee findById(long id) {
        return jdbcTemplate.queryForObject("select id, emp_name from employees where id = ?",
                EmployeesDao::mapRow,
                id
                );
    }

    private static Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Employee(rs.getLong("id"), rs.getString("emp_name"));
    }

}
