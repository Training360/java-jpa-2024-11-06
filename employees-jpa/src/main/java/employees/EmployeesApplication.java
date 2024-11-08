package employees;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class EmployeesApplication {

    public static void main(String[] args) {
        try (
                EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pu");
                ) {

            EmployeesDao employeesDao = new EmployeesDao(entityManagerFactory);

            Employee employee = new Employee("John Doe");
            employee.setCv("""
                    Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum facilisis non arcu eu scelerisque. Suspendisse hendrerit, augue ac semper scelerisque, ipsum libero porta dui, interdum vulputate ipsum ante aliquam mauris. Ut pretium non purus ut euismod. Aliquam a elit tristique, imperdiet ex vitae, ultricies urna. Sed rhoncus iaculis urna, gravida accumsan ante vestibulum ac. Morbi dui neque, egestas at mauris eget, mattis tincidunt ipsum. Aliquam feugiat nisl sed quam egestas, sed rutrum erat euismod. Etiam efficitur venenatis leo ut tincidunt. Quisque pulvinar elit at turpis rutrum interdum. Proin a elit vitae felis congue faucibus in vitae neque. Vivamus efficitur non sem vitae pretium. In condimentum efficitur diam pulvinar pretium. Nam hendrerit magna arcu, et tempor arcu auctor sed. Etiam a tempus augue. Curabitur eu tristique ex.
                    """);
            employeesDao.save(employee);

            System.out.println(employee.getId());

            employee = employeesDao.findById(employee.getId());

            System.out.println(employee.getId());
            System.out.println(employee.getCv());

//            employeesDao.delete(employee.getId());

            // JPQL
            List<Employee> employees = employeesDao.findAll();
            employees.stream().map(Employee::getId).forEach(System.out::println);
        }

    }
}
