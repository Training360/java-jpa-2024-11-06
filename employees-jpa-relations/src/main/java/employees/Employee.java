package employees;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

    // OneToOne, ManyToOne eager
    // OneToMany, ManyToMany lazy

    // Owner side
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private ParkingPlace parkingPlace;

    // Alkalmazottnak milyen saját telefonszámai
    // Inverse side
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
//    @OrderBy("number")
    private List<PhoneNumber> phoneNumbers = new ArrayList<>();

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

    public void addPhoneNumber(PhoneNumber phoneNumber) {
        phoneNumbers.add(phoneNumber);
        phoneNumber.setEmployee(this);
    }
}
