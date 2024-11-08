package employees;

import java.util.List;

public record EmployeeDto(Long id, String name, List<String> nicks, List<String> phones) {
}
