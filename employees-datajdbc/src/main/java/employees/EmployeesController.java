package employees;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employees")
@AllArgsConstructor
public class EmployeesController {

    private final EmployeesRepository employeesRepository;

    @GetMapping
    public List<Employee> getEmployees(@RequestParam Optional<String> prefix) {
//        return employeesRepository.findAll();
        return employeesRepository.findEmployeesByNameLike(
                prefix.orElse("") + "%"
        );
    }
}
