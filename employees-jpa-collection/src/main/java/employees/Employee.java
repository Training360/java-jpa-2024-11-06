package employees;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(generator = "employees_seq")
    @SequenceGenerator(name = "employees_seq", sequenceName = "seq_employees")
    private Long id;

    @Column(name = "emp_name")
    private String name;

    @Column(name = "employee_status")
//    @Enumerated(EnumType.STRING)
    @Convert(converter = EmployeeStatusConverter.class)
    private EmployeeStatus employeeStatus = EmployeeStatus.NEW;

    @Lob
    @Column
    private String cv = "lorem ipsum";

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "created_by")
    private String createdBy;

    @ElementCollection// EZT A MEGOLDÁST SOHA NE HASZNÁLD! (fetch = FetchType.EAGER)
    @CollectionTable(name = "nicknames", joinColumns = @JoinColumn(name = "emp_id"))
    @Column(name = "nickname")
    private List<String> nicknames;

    public Employee(String name) {
        this.name = name;
    }

    @PrePersist
    private void setAuditData() {
        this.createdAt = LocalDateTime.now();
        this.createdBy = "admin";
    }

    @PostPersist
    private void logPostPersist() {
        System.out.println("post persist");
    }
}
