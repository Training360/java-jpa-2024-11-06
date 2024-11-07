package employees;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface EmployeesRepository extends ListCrudRepository<Employee, Long> {

    @Query("select id, emp_name from employees where emp_name like :name")
    List<Employee> findEmployeesByNameLike(String name);
}
