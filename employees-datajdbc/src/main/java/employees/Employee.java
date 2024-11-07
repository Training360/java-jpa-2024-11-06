package employees;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("employees")
public record Employee(@Id Long id,@Column("emp_name") String name) {
}
