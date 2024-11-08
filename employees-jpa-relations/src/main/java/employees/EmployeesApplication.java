package employees;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.ArrayList;
import java.util.List;

public class EmployeesApplication {

    public static void main(String[] args) {
        try (
                EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pu");
                ) {

            EmployeesDao employeesDao = new EmployeesDao(entityManagerFactory);

            Employee employee = new Employee("John Doe");
            ParkingPlace parkingPlace = new ParkingPlace(100);
            employee.setParkingPlace(parkingPlace);

            PhoneNumber phoneNumberHome = new PhoneNumber("home", "1111");
            PhoneNumber phoneNumberWork = new PhoneNumber("work", "2222");
            employee.addPhoneNumber(phoneNumberHome);
            employee.addPhoneNumber(phoneNumberWork);

            employeesDao.save(employee);

            PhoneNumber phoneNumber = new PhoneNumber("mobile", "3333");
            employee.addPhoneNumber(phoneNumber);
            employeesDao.save(phoneNumber);

//            employee.getPhoneNumbers().add();
//            employeesDao.save(employee);

            System.out.println(employee.getPhoneNumbers().size());

            Employee employee2 = employeesDao.findByIdWithPhoneNumbers(employee.getId());
            System.out.println(employee2.getPhoneNumbers().size());


            employeesDao.addPhoneNumber(employee2.getId(), new PhoneNumber("weekend", "4444"));
        }

    }
}
