package employees;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class EmployeesDao {

    private EntityManagerFactory emf;

    public EmployeesDao(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public void save(PhoneNumber phoneNumber) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(phoneNumber);
            em.getTransaction().commit();
        }
    }

    public void save(Employee employee) {
            try (EntityManager em = emf.createEntityManager()) {
                em.getTransaction().begin();
//                em.persist(employee.getParkingPlace());
                em.persist(employee);
                em.getTransaction().commit();
            }
    }

    public List<Employee> findAll() {
        try (EntityManager em = emf.createEntityManager()) {
            return em.createQuery("select e from Employee e", Employee.class).getResultList();
        }
    }

    public Employee findById(long id) {
        try (EntityManager em = emf.createEntityManager()) {
            System.out.println("first find");
            Employee employee = em.find(Employee.class, id);
            em.detach(employee);
            em.clear();

            System.out.println("second find");
            employee = em.find(Employee.class, id);

            return employee;
        }
    }

    public Employee findByIdWithPhoneNumbers(long id) {
        try (EntityManager em = emf.createEntityManager()) {
            return em.createQuery("select e from Employee e left join fetch e.phoneNumbers", Employee.class)
                    .getSingleResult();
        }
    }

    public void approve(ApproveCommand command) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Employee entity = em.find(Employee.class, command.employeeId());
            entity.setEmployeeStatus(EmployeeStatus.ACCEPTED);
            em.getTransaction().commit();
        }
    }

    public void update(Employee employee) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Employee entity = em.find(Employee.class, employee.getId());
            entity.setName(employee.getName());
            em.getTransaction().commit();
        }
    }

    public void delete(long id) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Employee employee = em.find(Employee.class, id);
            em.remove(employee);
            em.getTransaction().commit();
        }
    }

    public void addPhoneNumber(long id, PhoneNumber phoneNumber) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();

//            Employee employee = em.find(Employee.class, id);
//            employee.addPhoneNumber(phoneNumber);

            Employee employee = em.getReference(Employee.class, id);
            System.out.println(employee.getClass().getName());

            System.out.println(employee.getName());

            System.out.println(employee.getClass().getName());

            phoneNumber.setEmployee(employee);
            em.persist(phoneNumber);

            em.getTransaction().commit();
        }
    }
}
